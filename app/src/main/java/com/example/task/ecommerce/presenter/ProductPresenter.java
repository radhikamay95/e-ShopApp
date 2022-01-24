package com.example.task.ecommerce.presenter;

import android.app.Activity;

import com.example.task.ecommerce.model.IProductModel;
import com.example.task.ecommerce.model.ProductModel;
import com.example.task.ecommerce.utils.Example;
import com.example.task.ecommerce.view.IProductView;

import retrofit2.Response;

public class ProductPresenter implements IProductPresenter, IProductResponsePresenter {
    private IProductView iProductView;
    private IProductModel iProductModel;

    public ProductPresenter(IProductView iProductView) {
        this.iProductView = iProductView;
        iProductModel = new ProductModel(this);
    }
    @Override
    public void onGetProductList(Activity activity) {
        iProductModel.onGetProductList(activity);
    }

    @Override
    public void onSuccessProductList(Response<Example> response) {
        iProductView.onSuccessProductList(response);
    }
}
