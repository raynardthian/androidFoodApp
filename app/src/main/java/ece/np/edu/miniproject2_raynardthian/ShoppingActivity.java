package ece.np.edu.miniproject2_raynardthian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ShoppingActivity extends AppCompatActivity {
    Button btProfile;
    ImageView ivChicken, ivMac, ivCutlet;
    FloatingActionButton fabCart;
    RecyclerView rvShopping;
    RecyclerView.Adapter adapter;

    private View.OnClickListener profileListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ShoppingActivity.this, ProfileActivity.class);
            startActivity(i);
        }
    };
    private View.OnClickListener cartListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        btProfile = this.findViewById(R.id.btProfile);
        ivChicken = this.findViewById(R.id.ivChicken);
        ivCutlet = this.findViewById(R.id.ivCutlet);
        ivMac = this.findViewById(R.id.ivMac);
        fabCart = this.findViewById(R.id.fabCart);
        rvShopping = this.findViewById(R.id.rvShopping);

        fabCart.setOnClickListener(cartListener);
        btProfile.setOnClickListener(profileListener);

//        rvShopping = RecyclerView.setLayoutManager(new ConstraintLayout(this));

    }
}