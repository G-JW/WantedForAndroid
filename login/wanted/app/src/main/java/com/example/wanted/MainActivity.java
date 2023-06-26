package com.example.wanted;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String text_email;
    String text_password;
    String text_disame;
    String text_id;

    //test
    private TextView email;
    private TextView user_id;
    private TextView disname;
    private TextView password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        user_id = findViewById(R.id.user_id);
        disname = findViewById(R.id.disname);
        password = findViewById(R.id.password);

        Intent getIntend=getIntent();
        Bundle bundle=getIntend.getBundleExtra("data");
        text_email = bundle.getString("userid");
        text_password = bundle.getString("password");
        text_disame = bundle.getString("disname");
        text_id = bundle.getString("id");

        Toast.makeText(MainActivity.this, "欢迎，"+text_disame, Toast.LENGTH_SHORT).show();
        email.setText(text_email);
        user_id.setText(text_id);
        disname.setText(text_disame);
        password.setText(text_password);
    }
}