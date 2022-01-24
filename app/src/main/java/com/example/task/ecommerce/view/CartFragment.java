package com.example.task.ecommerce.view;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.task.ecommerce.R;
import com.example.task.ecommerce.presenter.PurchasedAdapter;
import com.example.task.ecommerce.utils.DBHandler;
import com.example.task.ecommerce.utils.PurchasedProduct;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CartFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CartFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<PurchasedProduct> purchasedProductArrayList;
    private RecyclerView recyclerView;
    private DBHandler dbHandler;
    private PurchasedAdapter purchasedAdapter;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private String productCount;

    public CartFragment() {
        // Required empty public constructor
    }


    public static CartFragment newInstance(String param1, String param2) {
        CartFragment fragment = new CartFragment();
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
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        recyclerView = view.findViewById(R.id.purchased_recycler);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        purchasedProductArrayList = new ArrayList<>();
        dbHandler = new DBHandler(getContext());
        purchasedProductArrayList = dbHandler.readPurchasedData();
        purchasedAdapter = new PurchasedAdapter(getContext(), purchasedProductArrayList, new PurchasedAdapter.ItemsListener() {
//            @Override
//            public void addButtonPressed(String prodID, String prodImg, String prodName, String prodPrice, String itemCount) {
//                productCount = itemCount;
//                dbHandler = new DBHandler(getContext());
//                dbHandler.addPurchasedItems(prodID, prodImg, prodName, prodPrice, itemCount);
//
//                Toast.makeText(getContext(), "Item added", Toast.LENGTH_SHORT).show();
//            }

            @Override
            public void removeItem(String prodID) {
                dbHandler = new DBHandler(getContext());
                dbHandler.deleteItem(prodID);

                Toast.makeText(getContext(), "Item removed from cart", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void updateItemCount(String prodID, String itemCount) {
                dbHandler = new DBHandler(getContext());
                dbHandler.updateProuctiItem(prodID,itemCount);
            }
        });
        recyclerView.setAdapter(purchasedAdapter);
        purchasedAdapter.notifyDataSetChanged();
    }
}