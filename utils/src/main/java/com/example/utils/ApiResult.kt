package com.example.utils

sealed class ApiResult<T>(val data:T?=null, val error:String?=null){
    class Success<T>(products: T): ApiResult<T>(data = products)
    class Error<T>(error: String): ApiResult<T>(error = error)
    class Loading<T>: ApiResult<T>()
}