package com.mindorks.bootcamp.learndagger.data.remote

import com.mindorks.bootcamp.learndagger.data.remote.request.DummyReq
import com.mindorks.bootcamp.learndagger.data.remote.request.LoginReq
import com.mindorks.bootcamp.learndagger.data.remote.response.DummyResponse
import com.mindorks.bootcamp.learndagger.data.remote.response.GeneralResponse
import com.mindorks.bootcamp.learndagger.data.remote.response.LoginResponse
import com.mindorks.bootcamp.learndagger.data.remote.response.PostListResponse

import io.reactivex.Single
import retrofit2.http.*


/**
 * Dummy class to simulate the actual NetworkService using Retrofit or OkHttp etc
 */
interface NetworkService{


    @POST(Endpoints.DUMMY)
    fun doDummyCall(
            @Body request:DummyReq,
            @Header(Networking.HEADER_API_KEY) apiKey:String = Networking.API_KEY
    ):Single<DummyResponse>


    @GET(Endpoints.DUMMY)
    fun doDummyGetCall(
            @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ):Single<DummyResponse>


    @GET(Endpoints.HOME_POSTS_LIST)
    fun doHomePostCall(
        @Query("firstPostId")  firstPostId:String?,
        @Query("lastPostId") lastPostId:String?,
        @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.HEADER_API_KEY,
        @Header(Networking.HEADER_ACCESS_TOKEN) accessToken:String = Networking.HEADER_ACCESS_TOKEN,
        @Header(Networking.HEADER_USER_ID) userId:String = Networking.HEADER_USER_ID
    ):Single<PostListResponse>



    @POST(Endpoints.LOGIN)
    fun doLoginCall(
            @Body loginReq: LoginReq,
            @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.HEADER_API_KEY
    ):Single<LoginResponse>


    @DELETE(Endpoints.POST_DELETE)
    fun doPostDeleteCall(
            @Path("postId") postId:String,
            @Header(Networking.HEADER_API_KEY) apiKey: String = Networking.API_KEY
    ):Single<GeneralResponse>

}
