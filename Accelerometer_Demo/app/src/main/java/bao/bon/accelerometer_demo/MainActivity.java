package bao.bon.accelerometer_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private static final String TAG = "MainActivity";

    private SensorManager sensorManager;
    Sensor accelerometer;
    TextView txtValueX,txtValueY,txtValueZ;

    private float X_backward = (float) -2.5;
    private float X_forward = (float) 4;
    private float Y_left = (float)-3.5;
    private float Y_right = (float) 3.5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        txtValueX = findViewById(R.id.xVlue);
        txtValueY = findViewById(R.id.YValue);
        txtValueZ = findViewById(R.id.ZValue);

        Log.d(TAG, "onCreate: Initializing Sensor Services");

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(MainActivity.this,
                accelerometer,
                SensorManager.SENSOR_DELAY_NORMAL);

        Log.d(TAG, "onCreat: Register accelerometer listener");
    }


    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        float x = sensorEvent.values[0];
        float y = sensorEvent.values[1];
        float z = sensorEvent.values[2];
        if(x > X_forward){
            Toast.makeText(this, "Forward", Toast.LENGTH_SHORT).show();
        }else if(x < X_backward){
            Toast.makeText(this, "Backward", Toast.LENGTH_SHORT).show();
        }else if(y < Y_left){
            Toast.makeText(this, "Left", Toast.LENGTH_SHORT).show();
        }else if(y > Y_right){
            Toast.makeText(this, "Right", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Stop", Toast.LENGTH_SHORT).show();
        }
        Log.d(TAG,"On SensorChanged: X"+x + "Y:"+y+ "Z:"+z);
        txtValueX.setText("X Value: "+x);
        txtValueY.setText("Y Value: "+y);
        txtValueZ.setText("Z Value: "+z);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}



