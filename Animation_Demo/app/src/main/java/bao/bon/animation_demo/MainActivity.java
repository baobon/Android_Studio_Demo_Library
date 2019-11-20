package bao.bon.animation_demo;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Animation animationAlpha;
    ImageView imageView;
    ConnectivityManager conManager;
    NetworkInfo _wifi;
    //WiFi

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         conManager = (ConnectivityManager) this.getSystemService(CONNECTIVITY_SERVICE);
         _wifi = conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        imageView = findViewById(R.id.imageView);
        animationAlpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);
        imageView.startAnimation(animationAlpha);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkWiFi_status();
            }
        });
    }

    private void checkWiFi_status() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        MainActivity.this.startActivity(intent);
        animationAlpha.cancel();
//        wiFiCheck();
    }
    private void wiFiCheck() {
        _wifi = conManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (!_wifi.isAvailable()) {
            imageView.setImageResource(R.drawable.wifi_connect_one);
            imageView.startAnimation(animationAlpha);
            Toast.makeText(this, "Not connect", Toast.LENGTH_SHORT).show();
        } else if(_wifi.isAvailable()){
            animationAlpha.cancel();
            imageView.setImageResource(R.drawable.wifi_ok);
//            animationAlpha.cancel();
            Toast.makeText(this, "WiFi Connected !!!", Toast.LENGTH_SHORT).show();
        }
    }

    private void showNetworkSettingAlert() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Setting your WiFi");
        alertDialog.setMessage("No WiFi connection? Go to configuration interface WiFi!");
        alertDialog.setPositiveButton("Configuration", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                MainActivity.this.startActivity(intent);
                animationAlpha.cancel();
            }
        });
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
//                imageView.startAnimation(animationAlpha);
            }
        });
        alertDialog.show();
//        wiFiCheck();

    }

    @Override
    protected void onResume() {
        super.onResume();
        wiFiCheck();
    }

//    @Override
//    protected void onStart() {
//        checkWiFi_status();
//        super.onStart();
//    }
}

