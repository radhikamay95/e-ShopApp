package com.example.task.ecommerce.view;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.task.ecommerce.R;
import com.example.task.ecommerce.utils.Product;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;
    private AddItemsListener addItemsListener;
    private static int count = 0;

    public ProductAdapter(Context context, List<Product> productList, AddItemsListener addItemsListener) {
        this.context = context;
        this.productList = productList;
        this.addItemsListener = addItemsListener;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Glide.with(context).asBitmap().load(productList.get(position).getImage()).into(holder.prodImg);
        holder.name.setText(productList.get(position).getName());
        holder.price.setText(productList.get(position).getPrice());
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.add.setVisibility(View.GONE);
                holder.layout.setVisibility(View.VISIBLE);
            }
        });
        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.plus.setClickable(true);
                if (count < 6) {
                    holder.itemCount.setText(String.valueOf(++count));
                }
                if (count == 1) {
                    addItemsListener.addButtonPressed(productList.get(position).getProductId(), productList.get(position).getImage(),
                            productList.get(position).getName(), productList.get(position).getPrice(), holder.itemCount.getText().toString());
                } else {
                    if (count > 1 && count <= 5) {
                        addItemsListener.updateItemCount(productList.get(position).getProductId(), String.valueOf(count));
                    } else {
                        Toast.makeText(context, "Can't be shop more than 5 items", Toast.LENGTH_SHORT).show();
                        holder.plus.setClickable(false);
                    }
                }
            }
        });
        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.minus.setClickable(true);
                if (count > 0) {
                    holder.itemCount.setText(String.valueOf(--count));
                }
                if (count == 0) {
                    addItemsListener.removeItem(productList.get(position).getProductId());
                } else {
                    if (count > 0 && count <= 5) {
                        addItemsListener.updateItemCount(productList.get(position).getProductId(), String.valueOf(count));
                    } else {
                        holder.minus.setClickable(false);
                        if (position == holder.getAdapterPosition()) {
                            count = 0;
                        }
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        private ImageView prodImg;
        private TextView name, price, itemCount;
        private Button add, plus, minus;
        private LinearLayout layout;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            prodImg = itemView.findViewById(R.id.prod_img);
            name = itemView.findViewById(R.id.prod_name);
            price = itemView.findViewById(R.id.prod_price);
            add = itemView.findViewById(R.id.add_bt);
            layout = itemView.findViewById(R.id.parent_add_minus);
            itemCount = itemView.findViewById(R.id.prod_item_count);
            plus = itemView.findViewById(R.id.prod_plus_bt);
            minus = itemView.findViewById(R.id.prod_minus_bt);
        }
    }

    public interface AddItemsListener {
        void addButtonPressed(String prodID, String prodImg, String prodName, String prodPrice, String itemCount);

        void removeItem(String prodID);

        void updateItemCount(String prodID, String itemCount);


    }


}
