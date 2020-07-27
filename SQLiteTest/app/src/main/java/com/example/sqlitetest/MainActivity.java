package com.example.sqlitetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText et_name, et_age, et_height, et_weight;
    private Button btn_commit, btn_display, btn_update, btn_delete;
    private LinearLayout ll;
    private UserDBHelper user_db_helper;
    private SQLiteDatabase user_db;
    List<User> user_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        et_height = findViewById(R.id.et_height);
        et_weight =  findViewById(R.id.et_weight);

        btn_commit = findViewById(R.id.btn_commit);
        btn_display =  findViewById(R.id.btn_display);
        btn_update = findViewById(R.id.btn_update);
        btn_delete = findViewById(R.id.btn_delete);
        
        btn_commit.setOnClickListener(this);
        btn_display.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);

        ll = findViewById(R.id.ll);
        user_list = new ArrayList<User>();

        user_db_helper = new UserDBHelper(this);
        user_db = user_db_helper.getWritableDatabase();
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btn_commit){

            insert();

        }
        if(view.getId() == R.id.btn_display){

            ll.removeAllViews();
            user_list.clear();
            query();

        }
        if(view.getId() == R.id.btn_update){

            update();

        }
        if(view.getId() == R.id.btn_delete){

            delete();

        }
    }

    public void insert(){
        //user_db.beginTransaction();

        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", et_name.getText().toString());
        contentValues.put("Age", et_age.getText().toString());
        contentValues.put("Height", et_height.getText().toString());
        contentValues.put("Weight", et_weight.getText().toString());

        user_db.insert("user_info", null, contentValues);
        //user_db.setTransactionSuccessful();

        Toast.makeText(this, "add success", Toast.LENGTH_SHORT).show();
    }

    public void query(){
        Cursor cursor = user_db.query("user_info", null,null,null,null,null,null);

        while(cursor.moveToNext()){
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String age = cursor.getString(2);
            String height = cursor.getString(3);
            String weight = cursor.getString(4);
            //String info =  String.format("Id: %s\nName: %s\nAge: %s\nHeight: %s\nWeight: %s\n", id, name, age, height, weight);
            User user = new User(id, name, age, height, weight);
            user_list.add(user);
        }

        for(User user: user_list){
            TextView tv = new TextView(this);
            tv.setText(user.toString());
            tv.setTextSize(18);
            tv.setGravity(Gravity.CENTER);
            ll.addView(tv);
        }
    }

    public void update(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", et_name.getText().toString());
        contentValues.put("Age", et_age.getText().toString());
        contentValues.put("Height", et_height.getText().toString());
        contentValues.put("Weight", et_weight.getText().toString());

        String[] args = {et_name.getText().toString()};

        user_db.update("user_info", contentValues, "Name=?", args);
        Toast.makeText(this, "update finish", Toast.LENGTH_SHORT).show();
    }

    public void delete(){
        String[] args =  {et_name.getText().toString()};
        user_db.delete("user_info", "Name=?", args);

    }

}