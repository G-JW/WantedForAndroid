package com.example.wanted;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecommendFragment extends Fragment {
    private List<WantedBean> wantedList = new ArrayList<>();
    Bundle bundle;
    RecyclerView recyclerView;
    private Activity mAct;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_recommend, container, false);
        mAct=getActivity();
        recyclerView= view.findViewById(R.id.list_wanted);

        bundle = getArguments();
        wantedList = (List<WantedBean>) bundle.getSerializable("list");
        ItemAdapter adapter = new ItemAdapter(mAct, wantedList);
        recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(mAct));//这里用线性显示 类似于listview
        recyclerView.setLayoutManager(new GridLayoutManager(mAct, 2));//这里用线性宫格显示 类似于grid view
        return view;
    }

    private List<WantedBean> initData() {
        WantedBean storeInfo1 =
                new WantedBean(20123, "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "关于举办《经典音乐作品欣赏与人文审美》讲座的通知", "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "科学研究院", 1129);
        WantedBean storeInfo2 =
                new WantedBean(20123, "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "关于举办《经典音乐作品欣赏与人文审美》讲座的通知", "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "科学研究院", 1129);
        WantedBean storeInfo3 =
                new WantedBean(20123, "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "关于举办《经典音乐作品欣赏与人文审美》讲座的通知", "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "科学研究院", 1129);
        WantedBean storeInfo4 =
                new WantedBean(20123, "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "关于举办《经典音乐作品欣赏与人文审美》讲座的通知", "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "科学研究院", 1129);
        WantedBean storeInfo5 =
                new WantedBean(20123, "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "关于举办《经典音乐作品欣赏与人文审美》讲座的通知", "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "科学研究院", 1129 );
        WantedBean storeInfo6 =
                new WantedBean(20123, "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "关于举办《经典音乐作品欣赏与人文审美》讲座的通知", "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "科学研究院", 1129);
        WantedBean storeInfo7 =
                new WantedBean(20123, "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "关于举办《经典音乐作品欣赏与人文审美》讲座的通知", "http://i2.sinaimg.cn/ent/j/2012-05-20/U5912P28T3D3634984F328DT20120520152700.JPG", "科学研究院", 1129);
        wantedList.add(storeInfo1);
        wantedList.add(storeInfo2);
        wantedList.add(storeInfo3);
        wantedList.add(storeInfo4);
        wantedList.add(storeInfo5);
        wantedList.add(storeInfo6);
        wantedList.add(storeInfo7);
        return wantedList;
    }
}