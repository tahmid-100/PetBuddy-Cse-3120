package com.example.my_pet_shop;

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
import com.example.my_pet_shop.databinding.ActivityJsonParseBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonParse extends DrawerBase {
    ListView listView;
    public ArrayList<String> nam=new ArrayList<String>();
    ActivityJsonParseBinding activityJsonParseBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       setContentView(R.layout.activity_json_parse);

        listView=findViewById(R.id.json_list);
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
                //JSONArray jsonArray1=jsonObject1.getJSONArray("items");
                names.add(uname);
                nam.add(uname);

            }
            ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,names);
            listView.setAdapter(arrayAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                    Intent intent = new Intent(JsonParse.this, Json_details.class);
                    intent.putExtra("Name",nam.get(pos));
                    startActivity(intent);
                }
            });



        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

    }
}