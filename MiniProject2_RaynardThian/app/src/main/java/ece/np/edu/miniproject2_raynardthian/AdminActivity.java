package ece.np.edu.miniproject2_raynardthian;

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
    Button btShow;
    ListView lvNameList;
    ArrayAdapter customerArrayAdapter;
    DataBaseHelper dataBaseHelper;
    private View.OnClickListener showlistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(AdminActivity.this);
            List<Customer> everyone = dataBaseHelper.getEveryone();
            customerArrayAdapter = new ArrayAdapter<Customer>(AdminActivity.this, android.R.layout.simple_list_item_1, everyone);
            lvNameList.setAdapter(customerArrayAdapter);
        }
    };
    private AdapterView.OnItemClickListener nameListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Customer clickedCustomer = (Customer)parent.getItemAtPosition(position);
            dataBaseHelper.deleteOne(clickedCustomer);
            List<Customer> everyone = dataBaseHelper.getEveryone();
            customerArrayAdapter = new ArrayAdapter<Customer>(AdminActivity.this, android.R.layout.simple_list_item_1, everyone);
            lvNameList.setAdapter(customerArrayAdapter);
            Toast.makeText(AdminActivity.this,"Deleted " + clickedCustomer.toString(),Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        btShow = this.findViewById(R.id.btShow);
        lvNameList = this.findViewById(R.id.lvNameList);
        dataBaseHelper = new DataBaseHelper(AdminActivity.this);
        customerArrayAdapter = new ArrayAdapter<Customer>(AdminActivity.this, android.R.layout.simple_list_item_1, dataBaseHelper.getEveryone());
        btShow.setOnClickListener(showlistener);
        lvNameList.setOnItemClickListener(nameListener);
    }
}