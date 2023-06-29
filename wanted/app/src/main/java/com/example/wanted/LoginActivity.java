package com.example.wanted;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    Userdata user = new Userdata(LoginActivity.this,"user.db",null ,1);
    private EditText id;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //读数据
        id=findViewById(R.id.user_id);
        password=findViewById(R.id.Password);
    }
    public void login(View view){
        String id_text=id.getText().toString();
        String password_text=password.getText().toString();
        SQLiteDatabase db=user.getReadableDatabase();
        Cursor cursor =	db.rawQuery("select * from user",null);
        if (cursor.moveToFirst() == false)
        {
            Toast.makeText(LoginActivity.this, "无用户！", Toast.LENGTH_SHORT).show();
            System.out.println("无数据");
            cursor.close();
            return;
        }
        else
        {
            do{
                String Finding_userid = cursor.getString(cursor.getColumnIndex("email"));
                String Finding_password = cursor.getString(cursor.getColumnIndex("password"));
                String Finding_name = cursor.getString(cursor.getColumnIndex("displayname"));
                String Finding_id= cursor.getString(cursor.getColumnIndex("id"));
                System.out.println("查询用户"+Finding_userid+"        "+Finding_password+"        "+Finding_id+"        "+Finding_name);
                System.out.println("现用户"+id_text+"        "+password_text);
                if(Finding_userid.equals(id_text) && Finding_password.equals(password_text) )
                {
                    user.Put_User_id(Finding_userid);
                    user.Put_Displayname(Finding_name);
                    user.Put_Password(Finding_password);
                    user.Put_ID(Finding_id);
                    Intent login = new Intent(LoginActivity.this,MainActivity.class);
                    //login.setClass(LoginActivity.this,MainActivity.class);
                    Bundle bundle = new Bundle();
                    //bundle.putSerializable("Userdata",user);
                    bundle.putString("userid",Finding_userid);//email
                    bundle.putString("password",Finding_password);
                    bundle.putString("disname",Finding_name);
                    bundle.putString("id",Finding_id);//id
                    login.putExtra("data",bundle);
                    startActivity(login);
                    //cursor.close();
                    finish();
                }
                if(cursor.isAfterLast())//是否最后一行
                {
                    System.out.println("用户名或密码错误");
                    Toast.makeText(LoginActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
                    //cursor.close();
                    break;
                }
            }while(cursor.moveToNext());
            cursor.close();
        }
    }
    public void to_signup(View view)
    {
        Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
        startActivity(intent);
        finish();
    }
}