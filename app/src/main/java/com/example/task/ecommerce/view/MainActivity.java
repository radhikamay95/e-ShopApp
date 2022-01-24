package com.example.task.ecommerce.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.task.ecommerce.R;
import com.example.task.ecommerce.presenter.IProductPresenter;
import com.example.task.ecommerce.presenter.ProductPresenter;
import com.example.task.ecommerce.utils.Example;
import com.example.task.ecommerce.utils.GridSpaceItemDecoration;
import com.example.task.ecommerce.utils.Product;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private FrameLayout frameLayout;
    private Fragment homeFragment;
    private Fragment cartFragment;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        actionBar = getSupportActionBar();
        ColorDrawable colorDrawable
                = new ColorDrawable(Color.parseColor("#FF6200EE"));
        actionBar.setBackgroundDrawable(colorDrawable);
        homeFragment = new HomeFragment();
        cartFragment = new CartFragment();
        moveToFragment(homeFragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                moveToFragment(homeFragment);
                return true;
            case R.id.cart:
                moveToFragment(cartFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void moveToFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.prod_frag, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();

    }
}