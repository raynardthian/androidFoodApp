package ece.np.edu.miniproject2_raynardthian;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class FinalCheckout extends AppCompatActivity {
    ListView lvFood;
    TextView tvCustomerName, tvCostPrice3, tvAddress2;
    ArrayAdapter customerArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final_checkout);
        lvFood = this.findViewById(R.id.lvFood);
        tvCostPrice3 = this.findViewById(R.id.tvCostPrice3);
        tvAddress2 = this.findViewById(R.id.tvAddress2);
        tvCustomerName = this.findViewById(R.id.tvCustomerName);
        Intent i = this.getIntent();
        // To get all the data from the checkoutActivity
        String name = i.getStringExtra("customer_name_string");
        String address = i.getStringExtra("customer_address");
        float totalCost = i.getFloatExtra("order_cost", 1);
        String[] totalFood = i.getStringArrayExtra("total_food_array");
        String CostPriceString = String.valueOf(totalCost);

        tvCustomerName.setText(name);
        tvAddress2.setText(address);
        tvCostPrice3.setText("$ "+CostPriceString);

        customerArrayAdapter = new ArrayAdapter(FinalCheckout.this, android.R.layout.simple_list_item_1, totalFood);
        customerArrayAdapter.notifyDataSetChanged();
        lvFood.setAdapter(customerArrayAdapter);
    }
}