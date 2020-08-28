package com.example.jahanaraapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;
import com.google.android.material.textview.MaterialTextView;
import com.zcw.togglebutton.ToggleButton;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeUI extends AppCompatActivity {

    BottomNavigationBar bottomNavigationBar;
    public static final String TAG = "mytag";
    JSONObject jsonObj;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeui);
        BottomBarItem item1 = new BottomBarItem(R.drawable.ridersmall, R.string.title_home);
        BottomBarItem item2 = new BottomBarItem(R.drawable.user, R.string.profile);
        BottomBarItem item3 = new BottomBarItem(R.drawable.money, R.string.earnings);
        BottomBarItem item4 = new BottomBarItem(R.drawable.speed, R.string.performance);
        bottomNavigationBar = findViewById(R.id.bottom_bar);
        bottomNavigationBar.addTab(item1);
        bottomNavigationBar.addTab(item2);
        bottomNavigationBar.addTab(item3);
        bottomNavigationBar.addTab(item4);


        try {
            jsonObj = new JSONObject(getIntent().getStringExtra("json"));
            String message = (String) jsonObj.get("message");
            Log.d(TAG, message + "home");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        bottomNavigationBar.setOnSelectListener(new BottomNavigationBar.OnSelectListener() {
            @Override
            public void onSelect(int position) {
                String k = String.valueOf(position);
                switch (position) {
                    case 0:
                        Toast.makeText(getApplicationContext(), "Home", 0).show();
                        break;
                    case 1:
                        Intent intent = new Intent(getApplicationContext(), Profile.class);
                        intent.putExtra("json1", jsonObj.toString());
                        startActivity(intent);
                        break;
                }

            }
        });
        ToggleButton toggleBtn=findViewById(R.id.switch1);
        final MaterialTextView m=findViewById(R.id.status);

        //切换开关
        toggleBtn.toggle();
        //切换无动画
        //开关切换事件
        toggleBtn.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on){
                m.setText("Online");}
                else{
                    m.setText("Offline");}

            }


        });


    }
}