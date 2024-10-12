package com.example.productmanagementapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AddProductActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        EditText nameInput = findViewById(R.id.product_name_input);
        EditText descriptionInput = findViewById(R.id.product_description_input);
        EditText priceInput = findViewById(R.id.product_price_input);

        Button addButton = findViewById(R.id.add_product_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the product and return it to MainActivity
                String name = nameInput.getText().toString();
                String description = descriptionInput.getText().toString();
                double price = Double.parseDouble(priceInput.getText().toString());

                Intent resultIntent = new Intent();
                resultIntent.putExtra("name", name);
                resultIntent.putExtra("description", description);
                resultIntent.putExtra("price", price);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
