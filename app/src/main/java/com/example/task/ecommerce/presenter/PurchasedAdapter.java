package com.example.task.ecommerce.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.task.ecommerce.R;
import com.example.task.ecommerce.utils.PurchasedProduct;
import com.example.task.ecommerce.view.ProductAdapter;

import java.util.ArrayList;

public class PurchasedAdapter extends RecyclerView.Adapter<PurchasedAdapter.PurchasedViewHolder> {
    private Context context;
    private ArrayList<PurchasedProduct> purchasedProductArrayList;
    private PurchasedAdapter.ItemsListener itemsListener;
    private static int count;

    public PurchasedAdapter(Context context, ArrayList<PurchasedProduct> purchasedProductArrayList, ItemsListener itemsListener) {
        this.context = context;
        this.purchasedProductArrayList = purchasedProductArrayList;
        this.itemsListener = itemsListener;
    }

    @NonNull
    @Override
    public PurchasedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_purchased, parent, false);
        return new PurchasedAdapter.PurchasedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchasedViewHolder holder, int position) {
        Glide.with(context).asBitmap().load(purchasedProductArrayList.get(position).getPurchasedImage()).into(holder.imageView);
        holder.prodName.setText(purchasedProductArrayList.get(position).getPurchasedName());
        holder.prodPrice.setText(purchasedProductArrayList.get(position).getPurchasedPrice());
        holder.itemCount.setText(purchasedProductArrayList.get(position).getPurchasedItemCount());
        count = Integer.parseInt(purchasedProductArrayList.get(position).getPurchasedItemCount());
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.plus.setClickable(true);
                holder.itemCount.setText(String.valueOf(++count));
                if (count >= 1 && count <= 5) {
                    itemsListener.updateItemCount(purchasedProductArrayList.get(position).getPurchasedID(), String.valueOf(count));
                } else {
                    Toast.makeText(context, "Can't be shop more than 5 items", Toast.LENGTH_SHORT).show();
                    holder.plus.setClickable(false);

                }
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.minus.setClickable(true);
                holder.itemCount.setText(String.valueOf(--count));
                if (count == 0) {
                    itemsListener.removeItem(purchasedProductArrayList.get(position).getPurchasedID());
                }
                if (count >= 1 && count <= 5) {
                    itemsListener.updateItemCount(purchasedProductArrayList.get(position).getPurchasedID(), String.valueOf(count));
                } else {
                    holder.minus.setClickable(false);
                    if (position == holder.getAdapterPosition()) {
                        count = 0;
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return purchasedProductArrayList.size();
    }

    public class PurchasedViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView prodName, prodPrice, itemCount;
        private Button plus, minus;

        public PurchasedViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cart_img);
            prodName = itemView.findViewById(R.id.cart_name);
            prodPrice = itemView.findViewById(R.id.cart_price);
            plus = itemView.findViewById(R.id.prod_plus_bt);
            minus = itemView.findViewById(R.id.prod_minus_bt);
            itemCount = itemView.findViewById(R.id.prod_item_count);
        }
    }

    public interface ItemsListener {
//        void addButtonPressed(String prodID, String prodImg, String prodName, String prodPrice, String itemCount);

        void removeItem(String prodID);

        void updateItemCount(String prodID, String itemCount);
    }
}
