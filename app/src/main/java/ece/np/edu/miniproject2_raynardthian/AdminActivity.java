package ece.np.edu.miniproject2_raynardthian;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AdminActivity extends AppCompatActivity {
    Button btShow,btNextPage;
    ListView lvNameList;
    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;
    private View.OnClickListener showlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // To get all the customer data to output in the admin activity when called
            DataBaseHelper dataBaseHelper = new DataBaseHelper(AdminActivity.this);
            List<Customer> everyone = dataBaseHelper.getEveryone();
            customerArrayAdapter = new ArrayAdapter<Customer>(AdminActivity.this, android.R.layout.simple_list_item_1, everyone);
            lvNameList.setAdapter(customerArrayAdapter);
        }
    };
    private AdapterView.OnItemClickListener nameListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            // When the listview button is being selected by the admin, it will delete the customer data in the database
            Customer clickedCustomer = (Customer)parent.getItemAtPosition(position);
            dataBaseHelper.deleteOne(clickedCustomer);
            List<Customer> everyone = dataBaseHelper.getEveryone();
            customerArrayAdapter = new ArrayAdapter<Customer>(AdminActivity.this, android.R.layout.simple_list_item_1, everyone);
            lvNameList.setAdapter(customerArrayAdapter);
            Toast.makeText(AdminActivity.this,"Deleted " + clickedCustomer.toString(),Toast.LENGTH_SHORT).show();
        }
    };
    private View.OnClickListener nextListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(AdminActivity.this,AdminInsertFood.class);
            startActivity(i);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btShow = this.findViewById(R.id.btShow);
        btNextPage = this.findViewById(R.id.btNextPage);
        lvNameList = this.findViewById(R.id.lvNameList);
        dataBaseHelper = new DataBaseHelper(AdminActivity.this);
        List<Customer> everyone = dataBaseHelper.getEveryone();
        customerArrayAdapter = new ArrayAdapter<Customer>(AdminActivity.this, android.R.layout.simple_list_item_1, everyone);
        lvNameList.setAdapter(customerArrayAdapter);
        btShow.setOnClickListener(showlistener);
        lvNameList.setOnItemClickListener(nameListener);
        btNextPage.setOnClickListener(nextListener);
    }
}