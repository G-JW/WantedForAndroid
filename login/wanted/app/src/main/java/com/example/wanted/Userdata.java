package com.example.wanted;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.Serializable;

public class Userdata extends SQLiteOpenHelper implements Serializable {
    private String User_id;//邮箱
    private String Displayname;//昵称
    private String Password;//密码
    private String ID;//id
    public static final String CREATE_User= "create table if not exists user ("
            + "id integer primary key autoincrement, "
            + "email text,"
            + "displayname text,"
            + "password text)";
    //构造方法
    private  Context mContext;
    public Userdata( Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_User);
        Toast.makeText(mContext, "创建成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void Put_User_id(String user_id)
    {
        User_id=user_id;
    }
    public String Get_User_id()
    {
        return User_id;
    }

    public void Put_Displayname(String displayname)
    {
        Displayname=displayname;
    }
    public String Get_Displayname()
    {
        return Displayname;
    }

    public void Put_Password(String password)
    {
        Password=password;
    }
    public String Get_Password()
    {
        return Password;
    }

    public void Put_ID(String id)
    {
        ID=id;
    }
    public String Get_ID()
    {
        return ID;
    }
}
