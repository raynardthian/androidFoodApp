package ece.np.edu.miniproject2_raynardthian;

import android.content.Intent;
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
    Button btProfile, btCheckout;
    RecyclerView rvShop;
    RecyclerView.Adapter adapter;
    // users are food
    ArrayList<String> users;
    ArrayList<String> foodCost;
    String TotalSelectedFood="", Food, Cost, Quantity;
    Float TotalCost=0.0f, incomingCost;
    private RecyclerView.LayoutManager layoutManager;
    // Images Array
    private int[] images = {R.drawable.chickenrice, R.drawable.chickenbento, R.drawable.mcandcheese};

    private View.OnClickListener profileListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ShoppingActivity.this, ProfileActivity.class);
            startActivity(i);
        }
    };



    private View.OnClickListener checkoutListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ShoppingActivity.this,CheckoutActivity.class);
            i.putExtra("string_total_food",TotalSelectedFood);
            i.putExtra("string_total_cost",TotalCost);
            startActivity(i);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        btProfile = this.findViewById(R.id.btProfile);

        btCheckout = this.findViewById(R.id.btCheckout);
        btCheckout.setOnClickListener(checkoutListener);
        rvShop = this.findViewById(R.id.rvShop);
        // users are just food
        users = new ArrayList<>();
        foodCost = new ArrayList<>();
        users.add("Chicken Rice");
        users.add("Chicken Cutlet");
        users.add("Mac and Cheese");
        foodCost.add("5.50");
        foodCost.add("6.50");
        foodCost.add("4.50");
//        for (int i = 0; i < 10; i++) {
//            users.add("hello " + i);
//            System.out.println(i);
//        }

//        rvShop.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new UserAdapter(users);
//        rvShop.setAdapter(adapter);

        adapter = new AlbumAdapter(images, users, foodCost, this);
        rvShop.setLayoutManager(new GridLayoutManager(this, 1));
        rvShop.setHasFixedSize(true);
        rvShop.setAdapter(adapter);

        btProfile.setOnClickListener(profileListener);

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
                TotalSelectedFood = TotalSelectedFood + Quantity + " " + Food + "\n";
//                TotalSelectedFood = Quantity + " " + Food + "\n";
//                    SelectedFood = SelectedFood + data.getStringExtra("string_from_chosen_food");
//                    SelectedCost = SelectedCost + data.getStringExtra("string_from_chosen_cost");
                TotalCost = TotalCost + incomingCost;
                System.out.println(TotalSelectedFood);
                System.out.println(TotalCost);

            }


        } else if (resultCode == RESULT_CANCELED) {
            Toast.makeText(ShoppingActivity.this, "Cancelled & Nothing Returned", Toast.LENGTH_SHORT).show();

        }
    }


    private static final int REQUEST_CODE = 4;

    @Override
    public void onNoteClick(int position) {
        Intent i = new Intent(ShoppingActivity.this, FoodItemActivity.class);
        i.putExtra("Food", users.get(position));
        i.putExtra("Cost", foodCost.get(position));
        startActivityForResult(i, REQUEST_CODE);
    }
}