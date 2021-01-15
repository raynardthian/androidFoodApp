package ece.np.edu.miniproject2_raynardthian;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingActivity extends AppCompatActivity implements AlbumAdapter.OnNoteListener {
    Button btCheckout,btProf;
    RecyclerView rvShop;
    RecyclerView.Adapter adapter;
    ArrayList<String> PicturePath;
    ArrayList<String> Description;
    ArrayList<String> foodCost;
    ArrayList<String> FoodNames;
    ArrayList<String> fooditem;
    ArrayList<String> quantity;
    String Food, Cost, Quantity, CustomerName;
    Float TotalCost = 0.0f, incomingCost;
    private static final int REQUEST_CODE = 5;

    private View.OnClickListener profileListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ShoppingActivity.this, ProfileActivity.class);
            i.putExtra("customer_name_string",CustomerName);
            startActivity(i);
        }
    };


    private View.OnClickListener checkoutListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ShoppingActivity.this, CheckoutActivity.class);
            i.putExtra("string_total_cost", TotalCost);
            i.putExtra("customerName",CustomerName);
            i.putStringArrayListExtra("array_food",fooditem);
            i.putStringArrayListExtra("array_quantity",quantity);
            startActivity(i);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        btProf =this.findViewById(R.id.btProf);
        btCheckout = this.findViewById(R.id.btCheckout);
        btCheckout.setOnClickListener(checkoutListener);
        btProf.setOnClickListener(profileListener);
        rvShop = this.findViewById(R.id.rvShop);
        foodCost = new ArrayList<>();
        FoodNames = new ArrayList<>();
        Description = new ArrayList<>();
        PicturePath = new ArrayList<>();
        fooditem = new ArrayList<>();
        quantity = new ArrayList<>();
        DataBaseHelper db = new DataBaseHelper(ShoppingActivity.this);
        Cursor cursor = db.getFoodNames();
        cursor.moveToFirst();
            // loop through the cursor (result set) and create new Food Item objects. Put them into the return list
            do {
                String FoodName = cursor.getString(1);
                String FoodDescription = cursor.getString(2);
                float retrievedFoodCost = cursor.getFloat(3);
                String picturePath = cursor.getString(4);
                FoodNames.add(FoodName);
                String FoodCostString = String.valueOf(retrievedFoodCost);
                foodCost.add(FoodCostString);
                Description.add(FoodDescription);
                PicturePath.add(picturePath);
            } while (cursor.moveToNext());
        adapter = new AlbumAdapter(PicturePath, FoodNames, foodCost, Description ,this);
        rvShop.setLayoutManager(new GridLayoutManager(this, 1));
        rvShop.setHasFixedSize(true);
        rvShop.setAdapter(adapter);

//        btProfile.setOnClickListener(profileListener);
        Intent i = this.getIntent();
        CustomerName = i.getStringExtra("customer_name");
    }

    // This is to send out the values from this activity to the foodItemActivity
    // Getting the data out from FoodItemActivity the Quantity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                Food = data.getStringExtra("string_from_chosen_food");
                Cost = data.getStringExtra("string_from_chosen_cost");
                Quantity = data.getStringExtra("string_from_quantity");
                incomingCost = Float.valueOf(Cost);
                TotalCost = TotalCost + incomingCost;
                fooditem.add(Food);
                quantity.add(Quantity);
            }
        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(ShoppingActivity.this, "Cancelled & Nothing Returned", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    public void onNoteClick(int position) {
        Intent i = new Intent(ShoppingActivity.this, FoodItemActivity.class);
        i.putExtra("Food", FoodNames.get(position));
        i.putExtra("Cost", foodCost.get(position));
        i.putExtra("Description",Description.get(position));
        i.putExtra("PicturePath",PicturePath.get(position));
        startActivityForResult(i, REQUEST_CODE);
    }
}