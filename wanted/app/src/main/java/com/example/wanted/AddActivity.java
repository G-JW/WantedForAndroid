package com.example.wanted;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class AddActivity extends AppCompatActivity implements View.OnClickListener{
    private Sqlite msqlite;
    private Button button;
    private EditText title;
    private EditText position;
    private EditText num_min;
    private EditText num_max;
    private EditText introduction;
    Bundle user_data;
    String tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        button=findViewById(R.id.submit);
        button.setOnClickListener(this);
        title=findViewById(R.id.edit_title);
        position=findViewById(R.id.position);
        num_min=findViewById(R.id.num_min);
        num_max=findViewById(R.id.num_max);
        introduction=findViewById(R.id.introduction);
        Intent get_intend = getIntent();
        user_data = get_intend.getBundleExtra("data");

        //System.out.println(user_data.getString("id"));

        String[] options = {"游戏", "旅游", "学习", "娱乐"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedOption = (String) parent.getItemAtPosition(position);
                // 选择下拉框的某个选项之后，获得该选项对应的文本。可以作为标签添加到帖子内容中
                tag=selectedOption;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 当没有选项被选择时执行的操作
            }
        });
        msqlite = new Sqlite(AddActivity.this);
    }

    @Override
    public void onClick(View view)
    {
        if(view==button)
        {
            String Title = title.getText().toString().trim();
            String Position = position.getText().toString().trim();
            String Introduction = introduction.getText().toString().trim();
            String Coverurl = "res/drawable/add.png";
            String Imageurl = null;String Portraiturl = "res/drawable/add.png";
            String Learderid = user_data.getString("id");
            String User1id = null;String User2id = null;String User3id = null;
            String Num = num_min.getText().toString().trim();
            String Tag = tag;
            msqlite.add(Title,Introduction,Position,Coverurl,Imageurl,Portraiturl,
                    Learderid,User1id,User2id,User3id,Num,Tag);
            Toast.makeText(AddActivity.this, "上传成功", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

}