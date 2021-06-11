package com.nx.demo.ApiUtil;


import retrofit2.Call;
import retrofit2.http.GET;

public interface UserService {

    @GET("posts")
    Call<Object> postlist();


}
