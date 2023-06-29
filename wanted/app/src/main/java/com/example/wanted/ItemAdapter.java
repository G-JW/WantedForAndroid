package com.example.wanted;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{

    private List<WantedBean> wantedList;
    private Context context;
    private LayoutInflater layoutInflater;

    public ItemAdapter(Context context,List<WantedBean> wantedList){
        this.context = context;
        this.wantedList = wantedList;
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(
                R.layout.item_wanted, viewGroup, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
        WantedBean wantedBean=wantedList.get(i);
        itemViewHolder.tv_title.setText(wantedBean.getTitle());
        itemViewHolder.iv_pic1.setImageURI(Uri.parse(wantedBean.getImageUrl()));
        itemViewHolder.tv_username.setText("组长id：" + wantedBean.getUsername());
        itemViewHolder.iv_avatar.setImageURI(Uri.parse(wantedBean.getAvatarUrl()));
        itemViewHolder.tv_num.setText("项目人数"+String.valueOf(wantedBean.getNum()));
    }

    @Override
    public int getItemCount() {
        return wantedList.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title,tv_username,tv_num;
        ImageView iv_pic1,iv_avatar;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title=itemView.findViewById(R.id.item_title);
            tv_username=itemView.findViewById(R.id.item_username);
            tv_num=itemView.findViewById(R.id.item_num);
            iv_pic1=itemView.findViewById(R.id.item_pic);
            iv_avatar=itemView.findViewById(R.id.item_avatar);
        }
    }

}
