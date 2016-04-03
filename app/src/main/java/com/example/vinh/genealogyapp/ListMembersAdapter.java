package com.example.vinh.genealogyapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinh.genealogyapp.Model.Member;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by VINH on 4/3/2016.
 */
public class ListMembersAdapter extends BaseAdapter {

    Context context;
    List<Member> members;
    int width;
    int height;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

    public ListMembersAdapter(Context context, List<Member> members, int width, int height) {
        this.context = context;
        this.members = members;
        this.width = width;
        this.height = height;
    }

    @Override
    public int getCount() {
        return members.size();
    }

    @Override
    public Object getItem(int position) {
        return members.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        // Get view
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.member_activity_list_members, null);

            holder = new ViewHolder();
            holder.img_avatar = (ImageView) convertView.findViewById(R.id.mem_avatar);
            holder.txt_user_name = (TextView) convertView.findViewById(R.id.mem_name);
            holder.txt_email = (TextView) convertView.findViewById(R.id.mem_email);

            convertView.setTag(holder);
        }
        else holder = (ViewHolder) convertView.getTag();

        // Inflate member to mem.xml
        Member member = (Member) getItem(position);

        // Set avatar
        holder.img_avatar.getLayoutParams().height = width / 6;
        holder.img_avatar.getLayoutParams().width = width / 6;
        holder.img_avatar.setBackgroundColor(Color.WHITE);
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageOnLoading(R.drawable.ic_loading)
                .build();
        imageLoader.displayImage(member.getAvatar(), holder.img_avatar, options);

        // Set Username & Email
        holder.txt_user_name.setText(member.getName());
        holder.txt_email.setText(formatter.format(member.getBirthDate()));

        return convertView;
    }

    static class ViewHolder{
        ImageView img_avatar;
        TextView txt_user_name;
        TextView txt_email;
    }
}
