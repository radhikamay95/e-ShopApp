package com.example.task.ecommerce.utils;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/v2/5def7b172f000063008e0aa2")
    Call<Example> doGetListResources();
}
