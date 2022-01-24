package com.example.task.ecommerce.view;

import com.example.task.ecommerce.utils.Example;

import retrofit2.Response;

public interface IProductView {
    void onSuccessProductList(Response<Example> response);
}
