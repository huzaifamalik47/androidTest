package com.example.jahanaraapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textview.MaterialTextView;

import org.json.JSONException;
import org.json.JSONObject;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    JSONObject jsonObj, object;
    public static final String TAG = "mytag";
    MaterialTextView m1, m2, m3, m4, h;
    Button b1;
    String message1, message2, message3, message4, user, message5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        try {
            jsonObj = new JSONObject(getIntent().getStringExtra("json1"));
            String message = (String) jsonObj.get("message");
            Log.d(TAG, message + "profile");
            object = (JSONObject) jsonObj.get("user");
            // Log.d(TAG, message1);
            Log.d(TAG, object.toString());
            Log.d(TAG, String.valueOf(jsonObj.get("token")));

        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            message1 = (String) object.get("_id");
            message2 = (String) object.get("name");
            message3 = (String) object.get("phone");
            message4 = (String) object.get("userType");
            message5 = (String) object.get("userDetail");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Log.d(TAG, message1);
        m1 = findViewById(R.id.t1);
        m2 = findViewById(R.id.t2);
        m3 = findViewById(R.id.t3);
        m4 = findViewById(R.id.t4);
        b1 = findViewById(R.id.buttonprofile);
        h = findViewById(R.id.heading1);
        m1.setText(message5);
        m2.setText(message2);
        m3.setText(message3);
        m4.setText(message4);
        h.setText(message1);
        b1.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.buttonprofile){
            Intent intent=new Intent(getApplicationContext(),HomeUI.class);
            startActivity(intent);

        }
    }
}