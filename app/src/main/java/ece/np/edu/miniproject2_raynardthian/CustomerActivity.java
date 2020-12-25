package ece.np.edu.miniproject2_raynardthian;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CustomerActivity extends AppCompatActivity {
    public static final String COLUMN_CUSTOMER_NAME = "CUSTOMER_NAMETEXT";
    public static final String COLUMN_CUSTOMER_PASSWORD = "CUSTOMER_PASSWORD";
    public static final String COLUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLUMN_ID = "ID";

    EditText etUser, etPass;
    Button btLogin;
    TextView tvTries, tvForgot, tvRegistration;
    DataBaseHelper db;
    int tries;
    private View.OnClickListener forgotListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(CustomerActivity.this, ForgotActivity.class);
            startActivity(i);
        }
    };
    private View.OnClickListener loginListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            DataBaseHelper dataBaseHelper = new DataBaseHelper(CustomerActivity.this);
//            List<Customer> everyone = dataBaseHelper.getEveryone();
            String name = etUser.getText().toString();
            String pass = etPass.getText().toString();

            Cursor cursor = db.getAllData();
            cursor.moveToFirst();
            boolean found = false;
            do {
                String nameData = cursor.getString(1);
                String passData = cursor.getString(2);
                if ((name.equals(nameData)) && (pass.equals(passData))) {
                    found = true;
                    //WHAT DO IF MATCH
                    Intent i = new Intent(CustomerActivity.this, ShoppingActivity.class);
                    // To send the name over to the shopping class activity
                    i.putExtra("name",name);
                    startActivity(i);
                    break;
                }
            } while (cursor.moveToNext());

            if (!found) {
                //WHAT DO IF CANNOT FOUND
                tries--;
                String t = String.valueOf(tries);
                tvTries.setText(t);
                Toast.makeText(CustomerActivity.this, "Invalid Username or Password!", Toast.LENGTH_SHORT).show();
                if (tries == 0) {
                        Intent i = new Intent(CustomerActivity.this,MainActivity.class);
                        startActivity(i);
                }
            }
            cursor.close();
            db.close();
        }
    };
    private View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(CustomerActivity.this, RegisterActivity.class);
            startActivity(i);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        db = new DataBaseHelper(this);
        etUser = this.findViewById(R.id.etUser);
        etPass = this.findViewById(R.id.etPass);
        btLogin = this.findViewById(R.id.btLogin);
        tvTries = this.findViewById(R.id.tvTries);
        tvForgot = this.findViewById(R.id.tvForgot);
        tvRegistration = this.findViewById(R.id.tvRegistration);
        btLogin.setOnClickListener(loginListener);
        tvForgot.setOnClickListener(forgotListener);
        tvRegistration.setOnClickListener(registerListener);
        tries = 5;
    }
}