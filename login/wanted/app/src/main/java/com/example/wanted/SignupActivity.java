package com.example.wanted;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    Userdata user = new Userdata(SignupActivity.this,"user.db",null ,1);
    private EditText id;
    private EditText password;
    private EditText nickname_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        initView();
    }
    private void initView()
    {
        id=findViewById(R.id.user_id);
        password=findViewById(R.id.Password);
        nickname_id=findViewById(R.id.nickname_id);
    }
    public void signup(View view){
        if(nickname_id.length()==0)
        {
            Toast.makeText(SignupActivity.this, "请输入昵称！", Toast.LENGTH_SHORT).show();
            return;
        }
        String nickname_id_text=nickname_id.getText().toString();
        if(id.length()==0)
        {
            Toast.makeText(SignupActivity.this, "请输入邮箱！", Toast.LENGTH_SHORT).show();
            return;
        }
        String id_text=id.getText().toString();
        if(password.length()==0)
        {
            Toast.makeText(SignupActivity.this, "请输入密码！", Toast.LENGTH_SHORT).show();
            return;
        }
        String password_text=password.getText().toString();

        SQLiteDatabase db=user.getWritableDatabase();
        Cursor cursor =	db.rawQuery("select * from user",null);
        if (cursor.moveToFirst() == false) {
            System.out.println("无数据");
        }
        else {
            do {
                String Finding_userid = cursor.getString(cursor.getColumnIndex("email"));
                String Finding_id = cursor.getString(cursor.getColumnIndex("id"));
                System.out.println("查询到用户：" + Finding_userid);
                if (!Finding_userid.equals(id_text)) {
                    if (cursor.isAfterLast())//是否最后一行
                    {
                        cursor.close();
                        ContentValues values = new ContentValues();//临时变量
                        values.put("email",id_text);
                        values.put("displayname",nickname_id_text);
                        values.put("password",password_text);
                        db.insert("user",null,values);// 插入数据
                        //db.execSQL("INSERT INTO user(email,password,displayname) VALUES (?,?,?)",new Object[]{id_text,nickname_id_text,password_text});
                        System.out.println("输入成功"+id_text+"   "+nickname_id_text+"    "+password_text);
                        Intent signup = new Intent(SignupActivity.this,MainActivity.class);
                        //signup.setClass(SignupActivity.this,MainActivity.class);
                        Bundle bundle = new Bundle();
                        //bundle.putSerializable("Userdata",user);
                        bundle.putString("userid",id_text);
                        bundle.putString("password",password_text);
                        bundle.putString("disname",nickname_id_text);
                        bundle.putString("id",Integer.toString(Integer.valueOf(Finding_id)+1));
                        signup.putExtra("data",bundle);
                        startActivity(signup);
                        db.close();
                        finish();
                    }
                } else {
                    Toast.makeText(SignupActivity.this, "该邮箱已被注册！", Toast.LENGTH_SHORT).show();
                    cursor.close();
                    break;
                }
            } while (cursor.moveToNext());
        }
    }
}