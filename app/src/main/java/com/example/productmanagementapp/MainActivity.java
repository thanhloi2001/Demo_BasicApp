package com.example.productmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProductAdapter adapter;
    private ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        products = new ArrayList<>();
        // Add some initial products
        products.add(new Product("Smartphone", "Latest model with high-end features", 999.99));
        products.add(new Product("Laptop", "Powerful laptop for professionals", 1499.99));
        products.add(new Product("Headphones", "Noise-cancelling wireless headphones", 299.99));

        recyclerView = findViewById(R.id.product_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ProductAdapter(products, this::removeProduct);
        recyclerView.setAdapter(adapter);

        ImageButton addProductButton = findViewById(R.id.add_product_button);
        addProductButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddProductActivity.class);
            startActivityForResult(intent, 1); // Start AddProductActivity
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            // Get product from intent and add to list
            String name = data.getStringExtra("name");
            String description = data.getStringExtra("description");
            double price = data.getDoubleExtra("price", 0.0);
            products.add(new Product(name, description, price));
            adapter.notifyDataSetChanged(); // Update RecyclerView
        }
    }

    private void removeProduct(int position) {
        products.remove(position);
        adapter.notifyItemRemoved(position); // Remove the item from the RecyclerView
    }
}
