package com.example.article.base

data class BaseResponse<T>(
    var status : String ? = null,
    var code : Int ? = null,
    var data : T ? = null
)