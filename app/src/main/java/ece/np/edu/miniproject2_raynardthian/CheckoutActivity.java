package ece.np.edu.miniproject2_raynardthian;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    ArrayAdapter customerArrayAdapter;
    Customer customer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Intent i = this.getIntent();
        String TotalFood = i.getStringExtra("string_total_food");
        Float TotalCost = i.getFloatExtra("string_total_cost",1);
        String CustomerName = i.getStringExtra("customerName");

        System.out.println(TotalCost);
        System.out.println(TotalFood);
        System.out.println(CustomerName);
        // I cannot get the datbase to search for the customer name to get out the details
        DataBaseHelper dataBaseHelper = new DataBaseHelper(CheckoutActivity.this);
        List<Customer> person = dataBaseHelper.getEveryone();
        customer = new Customer();
        System.out.println(person);
        System.out.println(customer.getName());
    }
}