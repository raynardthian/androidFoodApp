package ece.np.edu.miniproject2_raynardthian;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText etUsername, etPassword, etAge, etAddress;
    Button btRegister; //btViewAll;
    ListView lvNames;
    DataBaseHelper db;
    private View.OnClickListener registerListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Customer customerList;
            String name = etUsername.getText().toString();
//            String pass = etPassword.getText().toString();
//            Integer age = Integer.parseInt(etAge.getText().toString());
//            String address = etAddress.getText().toString();
            Cursor cursor = db.getAllData();
            cursor.moveToFirst();
            boolean exist = false;
            for (int i = 0; i < cursor.getCount(); i++) {
                String nameData = cursor.getString(1);
                if (name.equals(nameData)) {
                    exist=true;
                } else {
                    cursor.moveToNext();
                }
            }
            if(exist){
                Toast.makeText(RegisterActivity.this, "Username is taken", Toast.LENGTH_SHORT).show();
            }
            else{
                try {
                    customerList = new Customer(-1, etUsername.getText().toString(), etPassword.getText().toString(), Integer.parseInt(etAge.getText().toString()), etAddress.getText().toString()); // Address not passing through
                    Toast.makeText(RegisterActivity.this, customerList.toString(), Toast.LENGTH_SHORT).show();
                    db.addOne(customerList);
                    Toast.makeText(RegisterActivity.this, "Created Successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(RegisterActivity.this, "Error Creating Customer", Toast.LENGTH_SHORT).show();
                }
            }
        }

    };
//    private View.OnClickListener viewListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            DataBaseHelper dataBaseHelper = new DataBaseHelper(RegisterActivity.this);
//            List<Customer> everyone = dataBaseHelper.getEveryone();
//            ArrayAdapter customerArrayAdapter = new ArrayAdapter<Customer>(RegisterActivity.this, android.R.layout.simple_list_item_1, everyone);
//            lvNames.setAdapter(customerArrayAdapter);
//
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etPassword = this.findViewById(R.id.etPassword);
        etUsername = this.findViewById(R.id.etUsername);
        etAge = this.findViewById(R.id.etAge);
        btRegister = this.findViewById(R.id.btRegister);
        etAddress = this.findViewById(R.id.etAddress);
        lvNames = this.findViewById(R.id.lvNames);
        db = new DataBaseHelper(RegisterActivity.this);
//        btViewAll = this.findViewById(R.id.btViewAll);

//        btViewAll.setOnClickListener(viewListener);
        btRegister.setOnClickListener(registerListener);
    }
}