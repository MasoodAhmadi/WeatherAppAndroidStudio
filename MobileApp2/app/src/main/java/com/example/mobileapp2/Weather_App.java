package com.example.mobileapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Weather_App extends AppCompatActivity {
EditText et;
TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather__app);
        String messageFromFirstScreen = getIntent().getStringExtra("STRING_EXTRA");
//        if (messageFromFirstScreen !=null){
//            TextView secondScreenTextView =findViewById(R.id.TextViewFromMotherScreen);
//            secondScreenTextView.setText(messageFromFirstScreen);
//        }
        et=findViewById(R.id.et);
        tv=findViewById(R.id.tv);
    }

    public void get(View view) {
        String apikey="ef92cd611ae451d1b7efb9cf50002de5";
        String city=et.getText().toString();
        String url="https://api.openweathermap.org/data/2.5/weather?q="+city+"&APPID=ef92cd611ae451d1b7efb9cf50002de5&units=metric";
        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject object=response.getJSONObject("main");
                    String temperature=object.getString("temp");
                    tv.setText("Tamp: "+temperature);
                } catch (JSONException e) {
                   // e.printStackTrace();
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
                }

            }
        },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error){
                    Toast.makeText(Weather_App.this,error.toString(),Toast.LENGTH_SHORT).show();
                }
             });
queue.add(request);
    }
}