package com.example.jahanaraapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText e1, e2;
    Button b1;
    public static final String TAG = "mytag";
    String message;
    private RequestQueue requestQueue;
    String savedata;

    //RequestQueue requestQueue = Volley.newRequestQueue(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.button);
        e1 = findViewById(R.id.user_id);
        e2 = findViewById(R.id.pass);
        b1.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button) {

            try {
                postMethod();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private void postMethod() throws IOException {

        final String userid = e1.getText().toString();
        final String pass = e2.getText().toString();

        String url = "https://devbaskyt.raft.com.pk/api/v1/user/login/";
        //"{\r\n\"userId\":\"+923004416110\",\"password\":\"hello12\",\"verificationCode\":4942\r\n}"
        String dataSend = "{\r\n\"userId\":\"" + userid + "\",\"password\":\"" + pass + "\",\"verificationCode\":4942\r\n}";
        final String savedata= dataSend;
        String URL=url;

        requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject objres=new JSONObject(response);
                    String message= (String) objres.get("message");
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(),HomeUI.class);
                    intent.putExtra("json",objres.toString());
                    startActivity(intent);

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(),"Server Error",Toast.LENGTH_LONG).show();

                }
                //Log.i("VOLLEY", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

                //Log.v("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return savedata == null ? null : savedata.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    //Log.v("Unsupported Encoding while trying to get the bytes", data);
                    return null;
                }
            }

        };
        requestQueue.add(stringRequest);




    }
}


 /*   String body = "{\r\n\"userId\":\"+923004416110\",\"password\":\"hello12\",\"verificationCode\":4942\r\n}";
    String dataSend = "{\r\n\"userId\":\"" + userid + "\",\"password\":\"" + pass + "\",\"verificationCode\":4942\r\n}";
    String res = StringUtils.difference(dataSend, body);
        Log.d(TAG, String.valueOf(body.compareTo(dataSend)));
                Log.d(TAG, String.valueOf(res));
                Log.d(TAG, dataSend);
                Log.d(TAG, body);
                if (dataSend.equals(body) ) {

                savedata = body;
                } else {
                savedata = dataSend;
                }*/