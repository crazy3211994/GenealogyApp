package com.example.vinh.genealogyapp;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vinh.genealogyapp.Model.Member;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;

public class MemberDetailActivity extends AppCompatActivity {

    ImageView img_mem_avatar;
    TextView txt_mem_id, txt_mem_name , txt_mem_birthday, txt_mem_address, txt_mem_gender , txt_mem_birthplace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);

        // Find view
        txt_mem_id = (TextView) findViewById(R.id.txt_mem_id);
        txt_mem_name = (TextView) findViewById(R.id.txt_mem_name);
        txt_mem_birthday = (TextView) findViewById(R.id.txt_mem_birthday);
        txt_mem_address = (TextView) findViewById(R.id.txt_mem_address);
        txt_mem_gender = (TextView) findViewById(R.id.txt_mem_gender);
        txt_mem_birthplace = (TextView) findViewById(R.id.txt_mem_birthplace);
        img_mem_avatar = (ImageView) findViewById(R.id.img_mem_avatar);
        //

        // Date formatter
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        // Get extra data from caller 's activity
        Bundle bundle = getIntent().getExtras();
        Member member = (Member) bundle.getSerializable("MEMBER_INFO");

        txt_mem_id.setText(String.valueOf(member.getMemberID()));
        txt_mem_name.setText(member.getName());
        txt_mem_birthday.setText(formatter.format(member.getBirthDate()));
        txt_mem_birthplace.setText(member.getBirthPlace());
        txt_mem_address.setText(member.getAddress());
        txt_mem_gender.setText(member.getGender());

        // Set avatar
        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageOnLoading(R.drawable.ic_loading)
                .build();
        imageLoader.displayImage(member.getAvatar(), img_mem_avatar, options);

        //
        try {
            getActionBar().setDisplayHomeAsUpEnabled(true);

        } catch (NullPointerException e) {

        }
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
