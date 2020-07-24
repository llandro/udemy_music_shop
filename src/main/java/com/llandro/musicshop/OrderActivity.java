package com.llandro.musicshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    String orderText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent orderIntent = getIntent();
        String userName = orderIntent.getStringExtra("userName");
        String goodsName = orderIntent.getStringExtra("goodsName");
        int quantity = orderIntent.getIntExtra("quantity",0);
        double orderPrice = orderIntent.getDoubleExtra("orderPrice", 0.0);

        orderText = "Customer name: "+userName+
                "\nGoods name: "+goodsName+
                "\nQuantity: "+ quantity+
                "\nPrice: "+(orderPrice/quantity)+
                "\nOrder Price: "+orderPrice;

        TextView orderTextView = findViewById(R.id.orderTextView);
        orderTextView.setText(orderText);
    }

    public void submitOrder(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*"); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Your order");
        intent.putExtra(Intent.EXTRA_TEXT, orderText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }
}