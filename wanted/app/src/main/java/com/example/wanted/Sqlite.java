package com.example.wanted;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Sqlite extends SQLiteOpenHelper implements Serializable {
    private SQLiteDatabase db;

    public Sqlite(Context context) {
        super(context, "db_test", null, 2);
        db = getReadableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS activity(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "title TEXT," +
                "detail TEXT," +
                "position TEXT," +
                "coverurl TEXT," +
                "imageurl TEXT," +
                "portraiturl TEXT," +
                "leaderid TEXT," +
                "user1id TEXT," +
                "user2id TEXT," +
                "user3id TEXT," +
                "num TEXT," +
                "tag TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS activity");
        onCreate(db);
    }
//    添加一个新项目
    public void add(String title,String detail,String position,String coverurl,String imageurl,String portraiturl,
                    String leaderid,String user1id,String user2id,String user3id,String num,String tag){
        db.execSQL("INSERT INTO activity(title,detail,position,coverurl,imageurl,portraiturl,leaderid," +
                "user1id,user2id,user3id,num,tag)VALUES(?,?,?,?,?,?,?,?,?,?,?,?)",new Object[]{title,detail,position,
                coverurl,imageurl,portraiturl,leaderid,user1id,user2id,user3id,num,tag});
    }
//    更改小组成员
    public void alteruser(String user1id,String user2id,String user3id,String title){
        db.execSQL("update activity set 'user1id' = ?,'user2id'=?,'user3id'=?where title=?",
                new Object[]{user1id,user2id,user3id,title});
    }
//    删除一个项目
    public void delete(String title){
        db.execSQL("delete from activity where title=?",new Object[]{title});
    }
//    按照项目title查询指定项目内容
    public Projects SelectByTitle(String title){
        String[] Title = {title};
        Cursor cursor = db.query("activity",null,"title=?",Title,null,null,null);
        @SuppressLint("Range") String detail = cursor.getString(cursor.getColumnIndex("detail"));
        @SuppressLint("Range") String position = cursor.getString(cursor.getColumnIndex("position"));
        @SuppressLint("Range") String coverurl = cursor.getString(cursor.getColumnIndex("coverurl"));
        @SuppressLint("Range") String imageurl = cursor.getString(cursor.getColumnIndex("imageurl"));
        @SuppressLint("Range") String portraiturl = cursor.getString(cursor.getColumnIndex("portraiturl"));
        @SuppressLint("Range") String leaderid = cursor.getString(cursor.getColumnIndex("leaderid"));
        @SuppressLint("Range") String user1id = cursor.getString(cursor.getColumnIndex("user1id"));
        @SuppressLint("Range") String user2id = cursor.getString(cursor.getColumnIndex("user2id"));
        @SuppressLint("Range") String user3id = cursor.getString(cursor.getColumnIndex("user3id"));
        @SuppressLint("Range") String num = cursor.getString(cursor.getColumnIndex("num"));
        @SuppressLint("Range") String tag = cursor.getString(cursor.getColumnIndex("tag"));
        cursor.close();
        return new Projects(title,detail,position,coverurl,imageurl,portraiturl,leaderid,user1id,user2id,user3id,num,tag);
    }
//    按照项目tag查询指定项目内容
    public List<WantedBean> SelectByTag(String tag){
        List<WantedBean> list = new ArrayList<WantedBean>();
        String[] Tag = {tag};
        Cursor cursor = db.query("activity",null,"tag=?",Tag,null,null,null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String index = cursor.getString(cursor.getColumnIndex("_id"));
            @SuppressLint("Range") String detail = cursor.getString(cursor.getColumnIndex("detail"));
            @SuppressLint("Range") String position = cursor.getString(cursor.getColumnIndex("position"));
            @SuppressLint("Range") String coverurl = cursor.getString(cursor.getColumnIndex("coverurl"));
            @SuppressLint("Range") String imageurl = cursor.getString(cursor.getColumnIndex("imageurl"));
            @SuppressLint("Range") String portraiturl = cursor.getString(cursor.getColumnIndex("portraiturl"));
            @SuppressLint("Range") String leaderid = cursor.getString(cursor.getColumnIndex("leaderid"));
            @SuppressLint("Range") String user1id = cursor.getString(cursor.getColumnIndex("user1id"));
            @SuppressLint("Range") String user2id = cursor.getString(cursor.getColumnIndex("user2id"));
            @SuppressLint("Range") String user3id = cursor.getString(cursor.getColumnIndex("user3id"));
            @SuppressLint("Range") String num = cursor.getString(cursor.getColumnIndex("num"));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            int Index = Integer.valueOf(index);
            int Num = Integer.valueOf(num);
//            list.add(new Projects(title,detail,position,coverurl,imageurl,portraiturl,leaderid,user1id,user2id,user3id,num,tag));
            list.add(new WantedBean(Index,coverurl,title,portraiturl,leaderid,Num));
        }
        cursor.close();
        return list;
    }
//    按照用户userid查找指定项目
public List<Projects> SelectByUserid(String userid){
    List<Projects> list = new ArrayList<Projects>();
    String[] Userid = {userid};
    Cursor cursor = db.query("activity",null,"leaderid=? or user1id=? or user2id=? or user3id=?",Userid,null,null,null);
    while(cursor.moveToNext()){
        @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
        @SuppressLint("Range") String position = cursor.getString(cursor.getColumnIndex("position"));
        @SuppressLint("Range") String detail = cursor.getString(cursor.getColumnIndex("detail"));
        @SuppressLint("Range") String coverurl = cursor.getString(cursor.getColumnIndex("coverurl"));
        @SuppressLint("Range") String imageurl = cursor.getString(cursor.getColumnIndex("imageurl"));
        @SuppressLint("Range") String portraiturl = cursor.getString(cursor.getColumnIndex("portraiturl"));
        @SuppressLint("Range") String leaderid = cursor.getString(cursor.getColumnIndex("leaderid"));
        @SuppressLint("Range") String user1id = cursor.getString(cursor.getColumnIndex("user1id"));
        @SuppressLint("Range") String user2id = cursor.getString(cursor.getColumnIndex("user2id"));
        @SuppressLint("Range") String user3id = cursor.getString(cursor.getColumnIndex("user3id"));
        @SuppressLint("Range") String num = cursor.getString(cursor.getColumnIndex("num"));
        @SuppressLint("Range") String tag = cursor.getString(cursor.getColumnIndex("tag"));
        list.add(new Projects(title,detail,position,coverurl,imageurl,portraiturl,leaderid,user1id,user2id,user3id,num,tag));
    }
    cursor.close();
    return list;
}
//    查询所有项目
    public List<WantedBean> SelectAll(){
        List<WantedBean> list = new ArrayList<WantedBean>();
        Cursor cursor = db.query("activity",null,null,null,null,null,null);
        while(cursor.moveToNext()){
            @SuppressLint("Range") String index = cursor.getString(cursor.getColumnIndex("_id"));
            @SuppressLint("Range") String title = cursor.getString(cursor.getColumnIndex("title"));
            @SuppressLint("Range") String detail = cursor.getString(cursor.getColumnIndex("detail"));
            @SuppressLint("Range") String position = cursor.getString(cursor.getColumnIndex("position"));
            @SuppressLint("Range") String coverurl = cursor.getString(cursor.getColumnIndex("coverurl"));
            @SuppressLint("Range") String imageurl = cursor.getString(cursor.getColumnIndex("imageurl"));
            @SuppressLint("Range") String portraiturl = cursor.getString(cursor.getColumnIndex("portraiturl"));
            @SuppressLint("Range") String leaderid = cursor.getString(cursor.getColumnIndex("leaderid"));
            @SuppressLint("Range") String user1id = cursor.getString(cursor.getColumnIndex("user1id"));
            @SuppressLint("Range") String user2id = cursor.getString(cursor.getColumnIndex("user2id"));
            @SuppressLint("Range") String user3id = cursor.getString(cursor.getColumnIndex("user3id"));
            @SuppressLint("Range") String num = cursor.getString(cursor.getColumnIndex("num"));
            @SuppressLint("Range") String tag = cursor.getString(cursor.getColumnIndex("tag"));
            int Index = Integer.valueOf(index);
            int Num = Integer.valueOf(num);
//            list.add(new Projects(title,detail,position,coverurl,imageurl,portraiturl,leaderid,user1id,user2id,user3id,num,tag));
            list.add(new WantedBean(Index,coverurl,title,portraiturl,leaderid,Num));
        }
        cursor.close();
        return list;
    }
}
