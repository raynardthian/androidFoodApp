package ece.np.edu.miniproject2_raynardthian;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    Button btCustomer, btAdmin;
    TextView tvRegister;
    private View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener adminListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener customerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btCustomer = this.findViewById(R.id.btCustomer);
        btAdmin = this.findViewById(R.id.btAdmin);
        tvRegister = this.findViewById(R.id.tvRegister);

        btCustomer.setOnClickListener(customerListener);
        btAdmin.setOnClickListener(adminListener);
        tvRegister.setOnClickListener(registerListener);
    }
}