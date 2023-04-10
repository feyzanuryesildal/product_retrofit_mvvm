package com.example.product_retrofit_mvvm.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.product_retrofit_mvvm.network.RetrofitInstance
import com.example.product_retrofit_mvvm.network.ProductService
import com.example.product_retrofit_mvvm.network.model.ProductsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel(){
    //var ProductDataList = MutableLiveData<List<ProductsModel>>()
    /*val productList: MutableLiveData<List<ProductsModel>> by lazy {
        MutableLiveData<List<ProductsModel>>()
    }*/
    private val productList = MutableLiveData<ProductsModel> ()
    val productListData: LiveData<ProductsModel> get() = productList
    fun getApiData(){

        //val retrofitInstance = RetrofitInstance.getRetrofitInstance().create(ProductService::class.java)
        RetrofitInstance.getRetrofitInstance().getDataFromApi().enqueue(object : Callback<ProductsModel> {
            override fun onResponse(
                call: Call<ProductsModel>,
                response: Response<ProductsModel>
            ) {
                if (response.isSuccessful) {
                    Log.d("Service", "Succses")
                    response.body()?.let {
                        Log.d("Service", "Body")
                        productList.postValue(it)
                    }?: kotlin.run {
                        Log.d("Service", "Empty Body")
                    }

                } else {
                    Log.d("Service", "Failed")
                }

            }

            override fun onFailure(call: Call<ProductsModel>, t: Throwable) {
                Log.d("Service", "Failed: ${t.message}")

            }

        })

    }
}