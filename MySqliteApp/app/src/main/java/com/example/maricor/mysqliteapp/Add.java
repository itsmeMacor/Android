package com.example.maricor.mysqliteapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Add extends AppCompatActivity {

    DatabaseHelper myDb;
    Button btnSave;
    EditText editTitle;
    EditText editDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        myDb = new DatabaseHelper(this);

        btnSave = (Button)findViewById(R.id.button_save);
        editTitle = (EditText)findViewById(R.id.editText_title);
        editDesc = (EditText)findViewById(R.id.editText_Desc);

        addData();

    }

    public void addData() {
        btnSave.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        boolean isInserted = false;

                        String str = editTitle.getText().toString();

                        if (TextUtils.isEmpty(str)) {
                            editTitle.setError("Required");
                            editDesc.setError("Required");
                            return;
                        } else {

                            isInserted = myDb.insertData(editTitle.getText().toString(),
                                    editDesc.getText().toString());
                        }


                        if (isInserted == true)
                            Toast.makeText(Add.this, "Data Inserted", Toast.LENGTH_LONG).show();

                        else
                            Toast.makeText(Add.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
