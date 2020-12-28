package ece.np.edu.miniproject2_raynardthian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShoppingActivity extends AppCompatActivity {
    Button btProfile, btGo;
    ImageView ivChicken, ivMac, ivCutlet;
    RecyclerView rvShop;
    RecyclerView.Adapter adapter;
    ArrayList<String> users;
    private View.OnClickListener profileListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ShoppingActivity.this, ProfileActivity.class);
            startActivity(i);
        }
    };
    private View.OnClickListener chickenListener = new View.OnClickListener() {
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
        btGo = this.findViewById(R.id.btGo);
        rvShop = this.findViewById(R.id.rvShop);

        users = new ArrayList<>();
        users.add("Chicken Rice");
        users.add("Chicken Cutlet");
        users.add("Mac and Cheese");
//        for (int i = 0; i < 10; i++) {
//            users.add("hello " + i);
//            System.out.println(i);
//        }

        rvShop.setLayoutManager(new LinearLayoutManager(this));
        adapter = new UserAdapter(users);
        rvShop.setAdapter(adapter);

        ivChicken.setOnClickListener(chickenListener);
        btProfile.setOnClickListener(profileListener);
    }
}