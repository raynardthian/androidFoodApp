package ece.np.edu.miniproject2_raynardthian;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgotActivity extends AppCompatActivity {
    EditText etUserN, etPassW;
    Button btChanges;
    DataBaseHelper db;
    private View.OnClickListener changeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            Customer customerList;
//            DataBaseHelper dataBaseHelper = new DataBaseHelper(ForgotActivity.this);
//            customerList = new Customer(-1,etUserN.getText().toString(),etPassW.getText().toString(),0,"None");
//            Boolean checkUpdateData = dataBaseHelper.updateToDataBase(customerList);
//            if(checkUpdateData==true){
//                Toast.makeText(ForgotActivity.this,"Entry Updated",Toast.LENGTH_SHORT).show();
//            }
//            else{
//                Toast.makeText(ForgotActivity.this,"Entry Not Updated",Toast.LENGTH_SHORT).show();
//            }
            boolean isUpdate = db.updateOne(etUserN.getText().toString(),etPassW.getText().toString());
            if(isUpdate==true){
                Toast.makeText(ForgotActivity.this,"Entry Updated",Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(ForgotActivity.this,"Entry Not Updated",Toast.LENGTH_SHORT).show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        etUserN = this.findViewById(R.id.etUserN);
        etPassW = this.findViewById(R.id.etPassW);
        btChanges = this.findViewById(R.id.btChanges);
        db = new DataBaseHelper(ForgotActivity.this);
        btChanges.setOnClickListener(changeListener);
    }
}