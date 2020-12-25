package ece.np.edu.miniproject2_raynardthian;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerActivity extends AppCompatActivity {
    EditText etUser, etPass;
    Button btLogin;
    TextView tvTries, tvForgot, tvRegistration;
    private View.OnClickListener forgotListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };
    private View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);

        etUser = this.findViewById(R.id.etUser);
        etPass = this.findViewById(R.id.etPass);
        btLogin = this.findViewById(R.id.btLogin);
        tvTries = this.findViewById(R.id.tvTries);
        tvForgot = this.findViewById(R.id.tvForgot);
        tvRegistration = this.findViewById(R.id.tvRegistration);
        btLogin.setOnClickListener(loginListener);
        tvForgot.setOnClickListener(forgotListener);
        tvRegistration.setOnClickListener(registerListener);
    }
}