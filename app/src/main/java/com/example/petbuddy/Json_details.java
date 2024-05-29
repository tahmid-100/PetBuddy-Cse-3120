package com.example.petbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Json_details extends AppCompatActivity {
    ListView listView;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_details);
        listView=findViewById(R.id.new_list);
        Intent intent=getIntent();
        s=intent.getStringExtra("Name");
        extractData();
    }
    private void extractData() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url="https://api.myjson.online/v1/records/01625f24-b4c4-43a2-bae6-690fe1ade5d1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                paseJson(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.getStackTrace();

            }
        });

        requestQueue.add(stringRequest);
    }

    private void paseJson(String response){
        try {
            JSONObject jsonObject=new JSONObject(response);
            JSONArray jsonArray=jsonObject.getJSONArray("data");
            ArrayList<String> names = new ArrayList<>();
            for(int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject1=jsonArray.getJSONObject(i);
                String uname;
                uname=jsonObject1.getString("name");
                JSONArray jsonArray1=jsonObject1.getJSONArray("items");
                if(uname.equals(s)){
                    for(int j=0;j<jsonArray1.length();j++)
                    {
                        JSONObject jsonObject2=jsonArray1.getJSONObject(j);
                        String n=jsonObject2.getString("breed");
                        names.add(n);
                    }
                }

            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,names);
            listView.setAdapter(arrayAdapter);



        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}