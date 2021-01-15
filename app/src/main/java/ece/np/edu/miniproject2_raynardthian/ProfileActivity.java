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

public class ProfileActivity extends AppCompatActivity {
    Button btBackShopping, btSave;
    EditText etNewPassword, etNewAge, etNewAddress;
    TextView tvDisplayUsername, tvDisplayPassword, tvDisplayAge, tvDisplayAddress;
    DataBaseHelper db;
    int customerAge = 0;
    String customerUsername, customerAddress, customerPassword, customerAgeString;
    private View.OnClickListener saveListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String NewPassword = etNewPassword.getText().toString();
            String NewAge = etNewAge.getText().toString();
            String NewAddress = etNewAddress.getText().toString();
            if (!NewPassword.equals("")) {
                boolean updatingPassword = db.updateOne(customerUsername, NewPassword);
                if (updatingPassword == true) {
                    Toast.makeText(ProfileActivity.this, "Password Updated", Toast.LENGTH_SHORT).show();
                    tvDisplayPassword.setText(NewPassword);
                } else {
                    Toast.makeText(ProfileActivity.this, "Password Not Updated", Toast.LENGTH_SHORT).show();
                }
                if (!NewAge.equals("")) {
                    int Age = Integer.valueOf(NewAge);
                    boolean isUpdate = db.updateAge(customerUsername, Age);
                    if (isUpdate == true) {
                        Toast.makeText(ProfileActivity.this, "Age Updated", Toast.LENGTH_SHORT).show();
                        tvDisplayAge.setText(NewAge);
                    } else {
                        Toast.makeText(ProfileActivity.this, "Age Not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
                if (!NewAddress.equals("")) {
                    boolean isUpdate = db.updateAddress(customerUsername, NewAddress);
                    if (isUpdate == true) {
                        Toast.makeText(ProfileActivity.this, "Address Updated", Toast.LENGTH_SHORT).show();
                        tvDisplayAddress.setText(NewAddress);
                    } else {
                        Toast.makeText(ProfileActivity.this, "Address Not Updated", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        }
    };
    private View.OnClickListener backListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(ProfileActivity.this, ShoppingActivity.class);
            startActivity(i);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvDisplayUsername = this.findViewById(R.id.tvDisplayUsername);
        tvDisplayPassword = this.findViewById(R.id.tvDisplayPassword);
        tvDisplayAge = this.findViewById(R.id.tvDisplayAge);
        tvDisplayAddress = this.findViewById(R.id.tvDisplayAddress);
        etNewAge = this.findViewById(R.id.etNewAge);
        etNewPassword = this.findViewById(R.id.etNewPassword);
        etNewAddress = this.findViewById(R.id.etNewAddress);
        btSave = this.findViewById(R.id.btSave);
        btBackShopping = this.findViewById(R.id.btBackShopping);
        btSave.setOnClickListener(saveListener);
        btBackShopping.setOnClickListener(backListener);

        Intent i = this.getIntent();
        String CustomerName = i.getStringExtra("customer_name_string");
        db = new DataBaseHelper(ProfileActivity.this);
        Cursor cursor = db.getPersonDatas(CustomerName);
        if (cursor.moveToFirst()) {
            do {
                customerUsername = cursor.getString(1);
                customerPassword = cursor.getString(2);
                customerAge = cursor.getInt(3);
                customerAddress = cursor.getString(4);
            } while (cursor.moveToNext());
        }
        customerAgeString = String.valueOf(customerAge);
        tvDisplayUsername.setText(customerUsername);
        tvDisplayAddress.setText(customerAddress);
        tvDisplayAge.setText(customerAgeString);
        tvDisplayPassword.setText(customerPassword);

    }
}