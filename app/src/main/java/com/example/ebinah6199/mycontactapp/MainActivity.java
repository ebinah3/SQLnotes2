package com.example.ebinah6199.mycontactapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);

        myDb = new DatabaseHelper(this);
        Log.d("MyContactApp", "MainActivity: instantiated mydb");
    }

    public void addData(View view){
        Log.d("MyContactApp", "MainActivity: Add Contact button pressed");

        boolean isInserted = myDb.insertData(editName.getText().toString());
        if(isInserted == true){
            Toast.makeText(MainActivity.this, "Success - contact inserted", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Failed - contact not inserted", Toast.LENGTH_LONG).show();
        }
    }

    public void viewData (View view){
        Cursor res = myDb.getAllData();
        Log.d("MyContactApp", "MainActivity: viewData: received cursor");
        if(res.getCount() == 0){
            showMessage("Error", "No data found in database");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            //append res column 0,1,2,3 to the buffer - see StringBuffer and Cursor apl's
            //Delimit each of the "appends" with line feed "\n"


        }
        showMessage("Data", buffer.toString());

    }

    private void showMessage(String title, String message) {
        Log.d("MyContactApp", "MainActivity: showMessage: assembling AlertDialog");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

}
