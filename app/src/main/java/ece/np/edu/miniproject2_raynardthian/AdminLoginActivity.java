package ece.np.edu.miniproject2_raynardthian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AdminLoginActivity extends AppCompatActivity {
    Button btAdminLogin;
    EditText etAdminUsername, etAdminPassword;
    private View.OnClickListener adminListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String user = "Raynard";
            String pass = "123";
            String userInput = etAdminUsername.getText().toString();
            String userPassword = etAdminPassword.getText().toString();

            if (user.equals(userInput) && (pass.equals(userPassword))) {
                Intent i = new Intent(AdminLoginActivity.this, AdminActivity.class);
                startActivity(i);
            } else {

            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        btAdminLogin = this.findViewById(R.id.btAdminLogin);
        etAdminPassword = this.findViewById(R.id.etAdminPassword);
        etAdminUsername = this.findViewById(R.id.etAdminUsername);

        btAdminLogin.setOnClickListener(adminListener);
    }
}