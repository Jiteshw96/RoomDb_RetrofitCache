package com.mindorks.bootcamp.learndagger.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class LoginReq(
        @Expose
        @SerializedName("email")
        var email:String,

        @Expose
        @SerializedName("password")
        var password:String
)
