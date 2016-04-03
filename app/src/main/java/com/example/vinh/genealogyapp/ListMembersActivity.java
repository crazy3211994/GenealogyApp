package com.example.vinh.genealogyapp;

import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.vinh.genealogyapp.Model.Member;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListMembersActivity extends AppCompatActivity {

    int Measuredwidth = 0;
    int Measuredheight = 0;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_members);

        // Get window width/height
        Point size = new Point();
        WindowManager w = getWindowManager();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            w.getDefaultDisplay().getSize(size);
            Measuredwidth = size.x;
            Measuredheight = size.y;
        } else {
            Display d = w.getDefaultDisplay();
            Measuredwidth = d.getWidth();
            Measuredheight = d.getHeight();
        }
        //

        final ListView lv = (ListView) findViewById(R.id.listview);
        GenealogyClient.getAllMembers(new GenealogyAPI() {
            @Override
            public void OnGetAllMembersListener(List<Member> members) {
                super.OnGetAllMembersListener(members);

                if( members.size() == 0 ){
                    TextView txt=(TextView)findViewById(R.id.txt_no);
                    txt.setVisibility(View.VISIBLE);
                    return;
                }

                ListMembersAdapter adapter = new ListMembersAdapter(getApplicationContext(), members,
                        Measuredwidth, Measuredheight);
                lv.setAdapter(adapter);

            }
        });


        // Set on item in listview listener
        assert lv != null;
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Member member = (Member) lv.getItemAtPosition(position);
                Intent intent = new Intent(ListMembersActivity.this, MemberDetailActivity.class);
                intent.putExtra("MEMBER_INFO", member);
                startActivity(intent);
            }
        });
    }
}
