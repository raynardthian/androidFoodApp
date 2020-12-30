package ece.np.edu.miniproject2_raynardthian;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FoodItemActivity extends AppCompatActivity {
    ImageView ivFood;
    TextView tvFood,tvCost,tvDescription;
    Button btBack,btEnter;
    Float selectedCost;
    EditText etQuantity;
    String food;
    private View.OnClickListener enterListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String quantity = etQuantity.getText().toString();
            Float quantityFloat = Float.valueOf(quantity);
            Float TotalCost = quantityFloat * selectedCost;
            String TotalCostString = String.valueOf(TotalCost);
            Intent i = new Intent();
            i.putExtra("string_from_chosen_food",food);
            i.putExtra("string_from_chosen_cost",TotalCostString);
            i.putExtra("string_from_quantity",quantity);
            // Have yet to send quantity over
            setResult(RESULT_OK,i);
            finish();
        }
    };
    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(FoodItemActivity.this,ShoppingActivity.class);
            startActivity(i);
            Toast.makeText(FoodItemActivity.this, "Cancelled & Nothing Returned", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_item);
        ivFood = this.findViewById(R.id.ivFood);
        tvFood = this.findViewById(R.id.tvFood);
        tvCost = this.findViewById(R.id.tvCost);
        tvDescription = this.findViewById(R.id.tvDescription);
        btBack = this.findViewById(R.id.btBack);
        btEnter = this.findViewById(R.id.btEnter);
        etQuantity = this.findViewById(R.id.etQuantity);
        btBack.setOnClickListener(backListener);
        btEnter.setOnClickListener(enterListener);
        Intent i = this.getIntent();
//      The Cost for food being transferred from ShoppingActivity is in String
        food = i.getStringExtra("Food");
        String cost = i.getStringExtra("Cost");
        String desc = i.getStringExtra("Description");
        String picPath = i.getStringExtra("PicturePath");
        selectedCost = Float.valueOf(cost);
//        String actualCost = String.valueOf(cost);
        tvFood.setText(food);
        tvCost.setText("$   "+ cost);
        tvDescription.setText(desc);
        ivFood.setImageBitmap(BitmapFactory.decodeFile(picPath));
    }
}