package com.mindorks.bootcamp.learndagger.data.remote.request

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DummyReq(

        @Expose
        @SerializedName("id")
        var id:String
)
