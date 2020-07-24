package com.llandro.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private int quantity = 0;
    Spinner spinner;
    ArrayList<String> spinnerArrayList;
    ArrayAdapter spinnerAdapter;

    HashMap goodsMap;
    String goodsName = "";
    double price;

    EditText userNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userNameEditText = (EditText) findViewById(R.id.nameEditText);

        initValues();
        createSpinner();
    }

    void initValues(){
        spinnerArrayList = new ArrayList<String>();
        spinnerArrayList.add("guitar");
        spinnerArrayList.add("violin");
        spinnerArrayList.add("cello");
        spinnerArrayList.add("toad");

        goodsMap = new HashMap();
        goodsMap.put(spinnerArrayList.get(0),500.0);
        goodsMap.put(spinnerArrayList.get(1),1000.0);
        goodsMap.put(spinnerArrayList.get(2),1500.0);
        goodsMap.put(spinnerArrayList.get(3),2000.0);

    }

    void createSpinner(){
        spinner = findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        spinnerAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item, spinnerArrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

    }

    void updatePrice(){
        TextView priceTextView = findViewById(R.id.priceTextView);
        priceTextView.setText(String.valueOf(price*quantity));

    }

    public void increaseQuantity(View view) {
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantity+=1;
        quantityTextView.setText(String.valueOf(quantity));

        updatePrice();
    }

    public void decreaseQuantity(View view) {
        if(quantity==0) return;
        quantity-=1;
        TextView quantityTextView = findViewById(R.id.quantityTextView);
        quantityTextView.setText(String.valueOf(quantity));
        updatePrice();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        goodsName = spinner.getSelectedItem().toString();
        price = (double) goodsMap.get(goodsName);
        updatePrice();

        ImageView goodsImageView = findViewById(R.id.goodsImageView);
        switch (goodsName){
            case "guitar":
                goodsImageView.setImageResource(R.drawable.guitar);
                break;
            case "violin":
                goodsImageView.setImageResource(R.drawable.violin);
                break;
            case "cello":
                goodsImageView.setImageResource(R.drawable.cello);
                break;
            case "toad":
                goodsImageView.setImageResource(R.drawable.toad);
                break;
            default:
                goodsImageView.setImageResource(R.drawable.toad1);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void addToCart(View view) {
        Order order = new Order();
        order.userName = userNameEditText.getText().toString();
        order.goodsName = goodsName;
        order.quantity = quantity;
        order.orderPrice = quantity*price;

        Intent orderIntent = new Intent(MainActivity.this, OrderActivity.class);

        orderIntent.putExtra("userName",order.userName);
        orderIntent.putExtra("goodsName",order.goodsName);
        orderIntent.putExtra("quantity", order.quantity);
        orderIntent.putExtra("orderPrice", order.orderPrice);

        startActivity(orderIntent);

    }
}