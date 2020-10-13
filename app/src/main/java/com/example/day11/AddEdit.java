package com.example.day11;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.day11.dbhelper.DbHelper;

public class AddEdit extends AppCompatActivity {

    EditText Id, Name, Address;
    Button btnSubmit, btnCancel;
    DbHelper SQLite = new DbHelper(this);
    String id, name, address;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_add_edit);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Id = (EditText) findViewById(R.id.txtId);
        Name = (EditText) findViewById(R.id.txtNama);
        Address = (EditText) findViewById(R.id.txtAlamat);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        name = getIntent().getStringExtra(MainActivity.TAG_NAME);
        address = getIntent().getStringExtra(MainActivity.TAG_ADDRESS);

        if(id==null || id==""){
            setTitle("Add Data");
        }else {
            setTitle("Edit Data");
            Id.setText(id);
            Name.setText(name);
            Address.setText(address);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if(Id.getText().toString().equals("")){
                        save();
                    }else {
                        edit();
                    }
                }catch (Exception e){
                    Log.e("Submit", e.toString());

                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blank();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed(){
        finish();
    }

//   public boolean onOp

    public void blank(){
        Name.requestFocus();
        Name.setText("");
        Id.setText("");
        Address.setText("");
    }

    private void save(){
//        if(Name.getText().toString().equals(null) || Name.getText().equals("") || String.valueOf(Address.getText().equals(null)) || String.valueOf(Address.getText().equals(""))){
//            Toast.makeText(getApplicationContext(), "Masukan Nama atau Alamat", Toast.LENGTH_LONG).show();
//
//        }else {
            SQLite.insert(Name.getText().toString().trim(), Address.getText().toString().trim());
            blank();
            finish();
//        }
    }

    private void edit(){
//        if(Name.getText().toString().equals(null) || Name.getText().equals("") || String.valueOf(Address.getText().equals(null)) || String.valueOf(Address.getText().equals(""))){
//            Toast.makeText(getApplicationContext(), "Masukan Nama atau Alamat", Toast.LENGTH_LONG).show();
//
//        }else {
            SQLite.update(Integer.parseInt(Id.getText().toString().trim()) , Name.getText().toString().trim(), Address.getText().toString().trim());
            blank();
            finish();
//        }
    }
}