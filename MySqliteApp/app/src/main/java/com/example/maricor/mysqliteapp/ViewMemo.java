package com.example.maricor.mysqliteapp;

import android.content.Intent;
import android.app.AlertDialog;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewMemo extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editTitle, editDesc, editTextId;
    Button btnviewAll;
    Button btnviewUpdate;
    Button btnDelete;
    Button btnReset;
    Button btnFetchedData;
    Button btnNew;
    Button btnViewMemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_memo);

        myDb = new DatabaseHelper(this);

        editTitle = (EditText)findViewById(R.id.editText_title);
        editDesc = (EditText)findViewById(R.id.editText_description);
        editTextId = (EditText)findViewById(R.id.editText_id);
        btnviewAll = (Button)findViewById(R.id.button_viewAll);
        btnviewUpdate = (Button)findViewById(R.id.button_update);
        btnDelete = (Button)findViewById(R.id.button_delete);
        btnReset = (Button)findViewById(R.id.button_reset);
        btnFetchedData = (Button)findViewById(R.id.button_fetch);
        btnNew = (Button)findViewById(R.id.button_new);
        btnViewMemo = (Button)findViewById(R.id.button_view);


        updateData();
        deleteData();
        resetFields();
        fetchData();
        viewAll();
    }

    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();
                        if (res.getCount() == 0){
                            showMessage("Error", "No data found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("Id:" + res.getString(0) + "\n");
                            buffer.append("Title:" + res.getString(1) + "\n");
                            buffer.append("Details:" + res.getString(2) + "\n");
                        }

                        showMessage("Data", buffer.toString());
                    }
                }
        );

    }



    public void resetFields(){
        btnReset.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        editTitle.setText("");
                        editDesc.setText("");
                        editTextId.setText("");
                    }
                }
        );

    }


    public void deleteData(){
        btnDelete.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        Integer deleteRows = myDb.deleteData(editTextId.getText().toString());
                        if (deleteRows > 0){
                            Toast.makeText(ViewMemo.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(ViewMemo.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }



    public void updateData(){
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(editTextId.getText().toString(), editTitle.getText().toString(),
                                editDesc.getText().toString());
                        if(isUpdate == true) {
                            Toast.makeText(ViewMemo.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(ViewMemo.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }

        );
    }





    public void fetchData() {
        btnFetchedData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.fetchData(editTextId.getText().toString());
                        if (res.getCount() == 0) {
                            editTextId.setError("No data found");
                            return;
                        }

                        while (res.moveToNext()){
                            editTitle.setText(res.getString(1));
                            editDesc.setText((res.getString(2)));
                        }
                    }
                }
        );

    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_memo, menu);
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
