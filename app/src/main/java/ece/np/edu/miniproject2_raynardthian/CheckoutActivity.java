package ece.np.edu.miniproject2_raynardthian;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CheckoutActivity extends AppCompatActivity {

    ArrayAdapter customerArrayAdapter;
    Customer customer;
    int customerAge;
    String customerAddress;
    TextView tvCostPrice, tvOrder,tvAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Intent i = this.getIntent();
        String TotalFood = i.getStringExtra("string_total_food");
        Float TotalCost = i.getFloatExtra("string_total_cost", 1);
        String CustomerName = i.getStringExtra("customerName");
        tvCostPrice = this.findViewById(R.id.tvCostPrice);
        tvOrder = this.findViewById(R.id.tvOrder);
        tvAddress = this.findViewById(R.id.tvAddress);

        System.out.println(TotalCost);
        System.out.println(TotalFood);
        System.out.println(CustomerName);

        DataBaseHelper db = new DataBaseHelper(CheckoutActivity.this);
        Cursor cursor = db.getPersonDatas(CustomerName);
        if (cursor.moveToFirst()) {
            do {
                customerAge = cursor.getInt(3);
                customerAddress = cursor.getString(4);
            } while (cursor.moveToNext());
        }
        System.out.println(customerAge);
        System.out.println(customerAddress);
        tvOrder.setText(TotalFood);
        String TotalCostString = String.valueOf(TotalCost);
        tvCostPrice.setText(TotalCostString);
        tvAddress.setText(customerAddress);


    }
}