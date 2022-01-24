package com.example.task.ecommerce.presenter;

import com.example.task.ecommerce.utils.Example;

import retrofit2.Response;

public interface IProductResponsePresenter {
    void onSuccessProductList(Response<Example> response);
}
