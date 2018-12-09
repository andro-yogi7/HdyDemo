package com.heady.headydemoapp.rest;

import com.heady.headydemoapp.model.ApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yogeshwarb on 28-Feb-2018.
 */
//: https://stark-spire-93433.herokuapp.com/json
public interface ApiInterface {
    @GET("json")
    Call<ApiResponse> getData();
}
