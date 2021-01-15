package ece.np.edu.miniproject2_raynardthian;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText etUsername, etPassword, etAge, etAddress;
    Button btRegister,btBackToLogin;
    DataBaseHelper db;
    private View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Customer customerList;
            String name = etUsername.getText().toString();
            String pass = etPassword.getText().toString();

            String ageString = etAge.getText().toString();
            String address = etAddress.getText().toString();

            if (name.equals("")) {
                Toast.makeText(RegisterActivity.this, "Please input your Name", Toast.LENGTH_SHORT).show();
            } else if (pass.equals("")) {
                Toast.makeText(RegisterActivity.this, "Please input your Password", Toast.LENGTH_SHORT).show();
            } else if (ageString.equals("")) {
                Toast.makeText(RegisterActivity.this, "Please input your Age", Toast.LENGTH_SHORT).show();
            } else if (address.equals("")) {
                Toast.makeText(RegisterActivity.this, "Please input your Address", Toast.LENGTH_SHORT).show();
            } else {
                Cursor cursor = db.getAllData();
                cursor.moveToFirst();
                boolean exist = false;
                // Check for same username
                for (int i = 0; i < cursor.getCount(); i++) {
                    String nameData = cursor.getString(1);
                    if (name.equals(nameData)) {
                        exist = true;
                    } else {
                        cursor.moveToNext();
                    }
                }
                // if username is found the same
                if (exist) {
                    Toast.makeText(RegisterActivity.this, "Username is taken", Toast.LENGTH_SHORT).show();
                }
                // if username is not taken
                else {
                    Integer age = Integer.parseInt(etAge.getText().toString());
                    try {
                        customerList = new Customer(-1, name, pass, age, address);
                        Toast.makeText(RegisterActivity.this, customerList.toString(), Toast.LENGTH_SHORT).show();
                        db.addOne(customerList);
                        Toast.makeText(RegisterActivity.this, "Created Successfully", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(RegisterActivity.this, "Error Creating Customer", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

    };
    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(RegisterActivity.this,CustomerActivity.class);
            startActivity(i);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etPassword = this.findViewById(R.id.etPassword);
        etUsername = this.findViewById(R.id.etUsername);
        etAge = this.findViewById(R.id.etAge);
        btRegister = this.findViewById(R.id.btRegister);
        btBackToLogin = this.findViewById(R.id.btBackToLogin);
        etAddress = this.findViewById(R.id.etAddress);
        db = new DataBaseHelper(RegisterActivity.this);
        btRegister.setOnClickListener(registerListener);
        btBackToLogin.setOnClickListener(backListener);
    }
}