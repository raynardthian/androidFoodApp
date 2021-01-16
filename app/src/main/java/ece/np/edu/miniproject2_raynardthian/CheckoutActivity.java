package ece.np.edu.miniproject2_raynardthian;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity {

    ArrayAdapter customerArrayAdapter;

    int customerAge;
    String customerAddress, CustomerName;
    TextView tvCostPrice, tvAddress;
    Button btOrder;
    ListView lvFoodItems;
    ArrayList<String> fooditems;
    ArrayList<String> quantity;

    Float TotalCost;
    String totalFood[], quantityArray[], foodItemArray[];
    private AdapterView.OnItemClickListener foodListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            float foodPrice = 0.0f;
            String removeFood = (String)parent.getItemAtPosition(position);
            int posID = (int) parent.getItemIdAtPosition((int) id);
            String foodOutput = foodItemArray[posID];
            String quantityOutput = quantityArray[posID];
            int intQuantity = Integer.valueOf(quantityOutput);
            Toast.makeText(CheckoutActivity.this, "Food Item Removed : " + removeFood, Toast.LENGTH_SHORT).show();
            totalFood = removeTheElement(totalFood, posID);

            DataBaseHelper db = new DataBaseHelper(CheckoutActivity.this);
            Cursor cursor = db.getFoodItemData(foodOutput);
            if (cursor.moveToFirst()) {
                do {
                    foodPrice = cursor.getFloat(3);
                } while (cursor.moveToNext());
            }
            List<String> totalQuantityAndFood = Arrays.asList(totalFood);
            customerArrayAdapter = new ArrayAdapter(CheckoutActivity.this, android.R.layout.simple_list_item_1, totalQuantityAndFood);
            customerArrayAdapter.notifyDataSetChanged();
            lvFoodItems.setAdapter(customerArrayAdapter);
            float Reduction = foodPrice * intQuantity;
            TotalCost =  TotalCost - Reduction;
            Toast.makeText(CheckoutActivity.this, "Total Price : $" + TotalCost, Toast.LENGTH_SHORT).show();
            String TotalCostString = String.valueOf(TotalCost);
            tvCostPrice.setText(TotalCostString);
            quantityArray = removeTheElement(quantityArray,posID);
            foodItemArray = removeTheElement(foodItemArray,posID);
        }
    };
    private View.OnClickListener orderListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(CheckoutActivity.this,FinalCheckout.class);
            i.putExtra("customer_name_string", CustomerName);
            i.putExtra("customer_address",customerAddress);
            i.putExtra("order_cost",TotalCost);
            i.putExtra("total_food_array",totalFood);
            startActivity(i);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        Intent i = this.getIntent();
        // getting all the values from the shoppingActivity
        TotalCost = i.getFloatExtra("string_total_cost", 1);
        CustomerName = i.getStringExtra("customerName");
        fooditems = i.getStringArrayListExtra("array_food");
        quantity = i.getStringArrayListExtra("array_quantity");
        tvCostPrice = this.findViewById(R.id.tvCostPrice);
        tvAddress = this.findViewById(R.id.tvAddress);
        lvFoodItems = this.findViewById(R.id.lvFoodItems);
        lvFoodItems.setOnItemClickListener(foodListener);
        // By getting the customer name from the loginActivity, this is to input the name into the database
        // and getting out the data out from the database of the user
        DataBaseHelper db = new DataBaseHelper(CheckoutActivity.this);
        Cursor cursor = db.getPersonDatas(CustomerName);
        if (cursor.moveToFirst()) {
            do {
                customerAge = cursor.getInt(3);
                customerAddress = cursor.getString(4);
            } while (cursor.moveToNext());
        }
        String TotalCostString = String.valueOf(TotalCost);
        tvCostPrice.setText(TotalCostString);
        tvAddress.setText(customerAddress);
        // Converting arrayList into Array
        quantityArray = GetStringArray(quantity);
        foodItemArray = GetStringArray(fooditems);
        // Getting the size of the array
        int n1 = fooditems.size();
        totalFood = new String[n1];
        // combining the arrays together
        for (int s = 0; s < quantityArray.length; s++) {

            totalFood[s] = quantityArray[s] + " " + foodItemArray[s];
        }
        List<String> totalQuantityAndFood = Arrays.asList(totalFood);

        customerArrayAdapter = new ArrayAdapter(CheckoutActivity.this, android.R.layout.simple_list_item_1, totalQuantityAndFood);
        lvFoodItems.setAdapter(customerArrayAdapter);
        btOrder = this.findViewById(R.id.btOrder);
        btOrder.setOnClickListener(orderListener);
    }

    public static String[] GetStringArray(ArrayList<String> arr) {
        // declaration and initialise String Array
        String str[] = new String[arr.size()];
        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {
            // Assign each value to String array
            str[j] = arr.get(j);
        }
        return str;
    }

    public static String[] removeTheElement(String arr[], int index) {
        // Create another array of size one less
        String anotherArray[] = new String[arr.length - 1];
        // Copy the elements except the index
        // from original array to the other array
        for (int i = 0, k = 0; i < arr.length; i++) {
            // if the index is
            // the removal element index
            if (i == index) {
                continue;
            }
            // if the index is not
            // the removal element index
            anotherArray[k++] = arr[i];
        }
        // return the resultant array
        return anotherArray;
    }
}