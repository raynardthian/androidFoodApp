package ece.np.edu.miniproject2_raynardthian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ShoppingActivity extends AppCompatActivity {
    Button btProfile;
    ImageView ivChicken,ivMac,ivCutlet;
    private View.OnClickListener profileListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ShoppingActivity.this,ProfileActivity.class);
            startActivity(i);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping);
        btProfile= this.findViewById(R.id.btProfile);
        ivChicken = this.findViewById(R.id.ivChicken);
        ivCutlet = this.findViewById(R.id.ivCutlet);
        ivMac = this.findViewById(R.id.ivMac);

        btProfile.setOnClickListener(profileListener);
    }
}