package ece.np.edu.miniproject2_raynardthian;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class AdminInsertFood extends AppCompatActivity {
    EditText etFoodName,etFoodDescription, etFoodCost;
    Button btFoodPicture,btFoodAdd,btShowFood;
    ImageView ivFoodPicture;
    String picturePath;
    DataBaseHelper db;
    ListView lvFoodList;
    ArrayAdapter FoodListArrayAdapter;
    private static final int PERMISSION_REQUEST = 3;
    private static final int RESULT_LOAD_IMAGE = 4;

    private View.OnClickListener addListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            FoodItem foodItemList;
            String FoodName = etFoodName.getText().toString();
            String FoodDescription = etFoodDescription.getText().toString();
            Float FoodCost = Float.parseFloat(etFoodCost.getText().toString());
            // Havent include Blob
            Cursor cursor = db.getAllFoodData();
            cursor.moveToFirst();
            boolean exist = false;
            for (int i = 0; i < cursor.getCount(); i++) {
                String nameData = cursor.getString(1);
                if (FoodName.equals(nameData)) {
                    exist=true;
                } else {
                    cursor.moveToNext();
                }
            }
            // if Food Name is found the same
            if(exist){
                Toast.makeText(AdminInsertFood.this, "Food Name is taken", Toast.LENGTH_SHORT).show();
            }
            // if Food Name is not taken
            else{
                try {
                    foodItemList = new FoodItem(-1,FoodName,FoodDescription,FoodCost,picturePath); // Address not passing through
                    Toast.makeText(AdminInsertFood.this, foodItemList.toString(), Toast.LENGTH_SHORT).show();
                    db.addOneFoodItem(foodItemList); // Add one data
                    Toast.makeText(AdminInsertFood.this, "Created Successfully", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(AdminInsertFood.this, "Error Creating Food Item", Toast.LENGTH_SHORT).show();
                }
            }
            
        }
    };
    private View.OnClickListener pictureListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i, RESULT_LOAD_IMAGE);
        }
    };
    private View.OnClickListener showListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DataBaseHelper dataBaseHelper = new DataBaseHelper(AdminInsertFood.this);
            List<FoodItem> everyone = dataBaseHelper.getAllFoodItem();
            FoodListArrayAdapter = new ArrayAdapter<FoodItem>(AdminInsertFood.this, android.R.layout.simple_list_item_1, everyone);
            lvFoodList.setAdapter(FoodListArrayAdapter);
        }
    };

    private AdapterView.OnItemClickListener nameListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            FoodItem clickedFoodItem = (FoodItem) parent.getItemAtPosition(position);
            db.deleteOneFoodItem(clickedFoodItem);
            List<FoodItem> everyone = db.getAllFoodItem();
            FoodListArrayAdapter = new ArrayAdapter<FoodItem>(AdminInsertFood.this, android.R.layout.simple_list_item_1, everyone);
            lvFoodList.setAdapter(FoodListArrayAdapter);
            Toast.makeText(AdminInsertFood.this,"Deleted " + clickedFoodItem.toString(),Toast.LENGTH_LONG).show();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RESULT_LOAD_IMAGE:
                if (resultCode == RESULT_OK) {
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    picturePath = cursor.getString(columnIndex);
                    cursor.close();
                    ivFoodPicture.setImageBitmap(BitmapFactory.decodeFile(picturePath));

                }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_insert_food);
        etFoodCost = this.findViewById(R.id.etFoodCost);
        etFoodDescription = this.findViewById(R.id.etFoodDescription);
        etFoodName = this.findViewById(R.id.etFoodName);
        btFoodAdd = this.findViewById(R.id.btFoodAdd);
        btShowFood = this.findViewById(R.id.btShowFood);
        btFoodPicture = this.findViewById(R.id.btFoodPicture);
        ivFoodPicture = this.findViewById(R.id.ivFoodPicture);
        lvFoodList = this.findViewById(R.id.lvFoodList);
        db = new DataBaseHelper(AdminInsertFood.this);
        btFoodAdd.setOnClickListener(addListener);
        btFoodPicture.setOnClickListener(pictureListener);
        btShowFood.setOnClickListener(showListener);
        lvFoodList.setOnItemClickListener(nameListener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST);
        }
    }
}