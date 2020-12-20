package com.example.mobileapp2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View.OnClickListener;


public class MainActivity extends AppCompatActivity implements
        SensorEventListener, LocationListener {
    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private LocationManager locationManager;
    private Location lastKnownLocaction;

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
        public void StartSesnsorMonitoring(View view) {
        sensorManager=(SensorManager)getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometerSensor !=null){
            sensorManager.registerListener(this,accelerometerSensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
    }


    public void onSensorChanged(SensorEvent event) {

        float xSensorValue= event.values[0];
        float ySensorValue=event.values[1];
        TextView xSensorTextView=(TextView) findViewById(R.id.xSensorTextView1);
        TextView ySensorTextView=(TextView)findViewById(R.id.ySensorTextView1);

        xSensorTextView.setText("X:" +xSensorValue);
        ySensorTextView.setText("Y:" +ySensorValue);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void StartGPS(View view) {
        locationManager =(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        if ( checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,new String[] {
                    Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},0);

            return;

        }
        lastKnownLocaction=locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);

    }

    @Override
    public void onLocationChanged(@NonNull Location location) {
        lastKnownLocaction=location;
        double latitude=location.getLatitude();
        double longitude=location.getLongitude();
        TextView latTextView =(TextView)findViewById(R.id.latTextView1);
        TextView longTextView=(TextView) findViewById(R.id.lngTextView1);

        latTextView.setText("Lat:" +latitude);
        longTextView.setText("Long:" +longitude);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

//    public void seeweatherapp(View view) {
//        Intent intent=new Intent(context,WeatherApp.class);
//    }


    public void seeweatherapp(View view) {
        Intent openSecondScreen = new Intent(this,Weather_App.class);
        openSecondScreen.putExtra("STRING_EXTRA","Hello from Masood!");
        startActivity(openSecondScreen);
    }

}