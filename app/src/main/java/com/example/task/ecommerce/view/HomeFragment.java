package com.example.task.ecommerce.view;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.task.ecommerce.R;
import com.example.task.ecommerce.presenter.IProductPresenter;
import com.example.task.ecommerce.presenter.ProductPresenter;
import com.example.task.ecommerce.utils.DBHandler;
import com.example.task.ecommerce.utils.Example;
import com.example.task.ecommerce.utils.GridSpaceItemDecoration;
import com.example.task.ecommerce.utils.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment implements IProductView {
    private RecyclerView recyclerView;
    private IProductPresenter presenter;
    private List<Product> productList = new ArrayList<>();
    private ProductAdapter productAdapter;
    private GridSpaceItemDecoration gridSpaceItemDecoration;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private DBHandler dbHandler;
    private String productCount;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public HomeFragment() {
        // Required empty public constructor
    }


    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.prod_recycler);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter = new ProductPresenter(this);

        presenter.onGetProductList(getActivity());

        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        gridSpaceItemDecoration = new GridSpaceItemDecoration(2, (int) getResources().getDimension(R.dimen.grid_layout_margin), false, 0);
        recyclerView.addItemDecoration(gridSpaceItemDecoration);
    }

    @Override
    public void onSuccessProductList(Response<Example> response) {
        productList = response.body().getProducts();
        productAdapter = new ProductAdapter(getActivity(), productList, new ProductAdapter.AddItemsListener() {
            @Override
            public void addButtonPressed(String prodID, String prodImg, String prodName, String prodPrice, String itemCount) {
                productCount = itemCount;
                dbHandler = new DBHandler(getContext());
                dbHandler.addPurchasedItems(prodID, prodImg, prodName, prodPrice, itemCount);
                Toast.makeText(getContext(), "Item added in shopping cart", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void removeItem(String prodID) {
                dbHandler = new DBHandler(getContext());

                dbHandler.deleteItem(prodID);
            }

            @Override
            public void updateItemCount(String prodID, String itemCount) {
                dbHandler = new DBHandler(getContext());
                dbHandler.updateProuctiItem(prodID,productCount);
            }
        });
        recyclerView.setAdapter(productAdapter);
        productAdapter.notifyDataSetChanged();
    }

}