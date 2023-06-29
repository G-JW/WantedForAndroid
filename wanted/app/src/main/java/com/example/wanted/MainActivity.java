package com.example.wanted;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Sqlite mSqlite;
    private TextView tv_home, tv_my;
    private ImageView iv_home,iv_my;
    private RelativeLayout rl_home,rl_my,rl_add,rl_chat;
    private HomeFragment homeFragment;
    private MyFragment myFragment;
    //test
    Bundle user_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSqlite=new Sqlite(MainActivity.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initEvent();

        initFragment(1);
    }

    private void initView() {
        rl_home=findViewById(R.id.home);
        rl_my=findViewById(R.id.my);
        rl_add=findViewById(R.id.add);
        rl_chat=findViewById(R.id.chat);
        tv_home=findViewById(R.id.home_txt);
        tv_my=findViewById(R.id.my_txt);
        iv_home=findViewById(R.id.home_img);
        iv_my=findViewById(R.id.my_img);
        iv_home.setImageResource(R.drawable.home_clicked);
        tv_home.setTextColor(Color.rgb(255,255,0));
        Intent getintend = getIntent();
        user_data = getintend.getBundleExtra("data");
    }

    private void initEvent() {
        rl_home.setOnClickListener(this);
        rl_my.setOnClickListener(this);
        rl_chat.setOnClickListener(this);
        rl_add.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //刷新底部图标和标题的颜色
        refreshBottom();

        if (view == rl_home) {
            iv_home.setImageResource(R.drawable.home_clicked);
            tv_home.setTextColor(Color.rgb(255,255,0));
            initFragment(1);
        } else if (view == rl_my) {
            iv_my.setImageResource(R.drawable.my_clicked);
            tv_my.setTextColor(Color.rgb(255, 255, 0));
            initFragment(2);
        }else if (view == rl_add) {
            Intent intent =new Intent(MainActivity.this,AddActivity.class);
            intent.putExtra("data",user_data);
            startActivity(intent);
        }else if (view == rl_chat) {
            Intent intent =new Intent(MainActivity.this,ChatActivity.class);
            intent.putExtra("data",user_data);
            startActivity(intent);
        }
    }

    private void refreshBottom() {
        iv_home.setImageResource(R.drawable.home);
        tv_home.setTextColor(Color.rgb(192, 192, 192));
        iv_my.setImageResource(R.drawable.my);
        tv_my.setTextColor(Color.rgb(192, 192, 192));
    }

    private void initFragment(int num) {
        FragmentTransaction transaction=getSupportFragmentManager().beginTransaction();
        hideFragment(transaction);
        switch (num){
            case 1:
                if(homeFragment==null){
                    homeFragment=new HomeFragment();
                    List<WantedBean> projects=mSqlite.SelectAll();
                    System.out.println(projects);
                    Bundle bundle=new Bundle();
                    bundle.putSerializable("list", (Serializable) projects);
                    homeFragment.setArguments(bundle);
                    transaction.add(R.id.tab_fragment,homeFragment);
                }
                else{
                    transaction.show(homeFragment);
                }
                break;
            case 2:
                if(myFragment==null){
                    myFragment=new MyFragment();
                    myFragment.setArguments(user_data);
                    transaction.add(R.id.tab_fragment,myFragment);
                }
                else{
                    transaction.show(myFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if(homeFragment!=null)
            transaction.hide(homeFragment);
        if(myFragment!=null)
            transaction.hide(myFragment);
    }

}