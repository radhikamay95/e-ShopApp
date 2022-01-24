package com.example.task.ecommerce.model;

import android.app.Activity;
import android.app.ProgressDialog;
import android.util.Log;

import com.example.task.ecommerce.presenter.IProductResponsePresenter;
import com.example.task.ecommerce.utils.ApiClient;
import com.example.task.ecommerce.utils.ApiInterface;
import com.example.task.ecommerce.utils.Example;
import com.example.task.ecommerce.view.IProductView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductModel implements IProductModel {
    private ApiInterface apiInterface;
    private IProductResponsePresenter iProductResponsePresenter;

    public ProductModel(IProductResponsePresenter iProductResponsePresenter) {
        this.iProductResponsePresenter = iProductResponsePresenter;
    }

    @Override
    public void onGetProductList(Activity activity) {
        ProgressDialog progress = new ProgressDialog(activity);
        progress.setMessage("Loading......");
        progress.show();

        apiInterface = ApiClient.getClientInstance().create(ApiInterface.class);
        Call<Example> exampleCall = apiInterface.doGetListResources();

            exampleCall.enqueue(new Callback<Example>() {
                @Override
                public void onResponse(Call<Example> call, Response<Example> response) {
                    if (response.body() != null) {
                        iProductResponsePresenter.onSuccessProductList(response);
                    }
                    progress.dismiss();
                }

                @Override
                public void onFailure(Call<Example> call, Throwable t) {
                    Log.i("TAG", "t");
                    progress.dismiss();
                }
            });
    }
}
