package com.example.wanted;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeFragment extends Fragment {
    private Sqlite mSqlite;
    private List<WantedBean> wantedList = new ArrayList<>();
    RecyclerView recyclerView;
    private Activity mAct;
    Bundle bundle;

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    MainAdapter mainadapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout=view.findViewById(R.id.tab_layout);
        viewPager2=view.findViewById(R.id.view_pager);
        //tabLayout.addTab(tabLayout.newTab().setText("旅游"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        bundle = getArguments();
        wantedList = (List<WantedBean>) bundle.getSerializable("list");
        System.out.println(wantedList);

        mainadapter=new MainAdapter(requireActivity(),wantedList);
        viewPager2.setAdapter(mainadapter);

        Window window= requireActivity().getWindow();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {//为 ViewPager2 注册一个 onPageChangeCallback 回调对象，用于监听 ViewPager2 页面变化事件
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);//在页面切换完成后被调用，指示当前所选中的页面位置。
                Objects.requireNonNull(tabLayout.getTabAt(position)).select();//根据当前选中的页面位置，通过 TabLayout 的 getTabAt() 方法获取相应位置的 Tab，并使用 select() 方法选中该 Tab。
            }
        });

//        mAct=getActivity();
//        recyclerView=view.findViewById(R.id.list_wanted);
//        ItemAdapter adapter = new ItemAdapter(mAct, initData());
//        recyclerView.setAdapter(adapter);
//        //recyclerView.setLayoutManager(new LinearLayoutManager(mAct));//这里用线性显示 类似于listview
//        recyclerView.setLayoutManager(new GridLayoutManager(mAct, 2));//这里用线性宫格显示 类似于grid view
        return view;
    }


    private List<WantedBean> initData() {
//        WantedBean storeInfo1 =
//                new WantedBean(20123, "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "关于举办《经典音乐作品欣赏与人文审美》讲座的通知", "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "科学研究院", 1129);
//        WantedBean storeInfo2 =
//                new WantedBean(20123, "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "关于举办《经典音乐作品欣赏与人文审美》讲座的通知", "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "科学研究院", 1129);
//        WantedBean storeInfo3 =
//                new WantedBean(20123, "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "关于举办《经典音乐作品欣赏与人文审美》讲座的通知", "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "科学研究院", 1129);
//        WantedBean storeInfo4 =
//                new WantedBean(20123, "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "关于举办《经典音乐作品欣赏与人文审美》讲座的通知", "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "科学研究院", 1129);
//        WantedBean storeInfo5 =
//                new WantedBean(20123, "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "关于举办《经典音乐作品欣赏与人文审美》讲座的通知", "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "科学研究院", 1129 );
//        WantedBean storeInfo6 =
//                new WantedBean(20123, "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "关于举办《经典音乐作品欣赏与人文审美》讲座的通知", "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "科学研究院", 1129);
//        WantedBean storeInfo7 =
//                new WantedBean(20123, "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "关于举办《经典音乐作品欣赏与人文审美》讲座的通知", "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "科学研究院", 1129);

//        wantedList.add(storeInfo1);
//        wantedList.add(storeInfo2);
//        wantedList.add(storeInfo3);
//        wantedList.add(storeInfo4);
//        wantedList.add(storeInfo5);
//        wantedList.add(storeInfo6);
//        wantedList.add(storeInfo7);
        return wantedList;
    }



}