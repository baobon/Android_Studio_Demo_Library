package bao.bon.button_pressing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
    WebView webView_send;
    Button btn;
    TextView tv,txt;
    int count = 0;
    String chuoi = "Voltage=9.90,IPAddress=192.168.4.2";
    MyAsyncTask myAsyncTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView)findViewById(R.id.tvHttp);
        txt = findViewById(R.id.textView);
        webView_send = findViewById(R.id.webView);
        btn = findViewById(R.id.button);

        readVoltage_API();

        // Thread doc du lieu tu web API
    }

    private void readVoltage_API() {
        final Thread readVol = new Thread(){
            @Override
            public  void run(){
                while (!isInterrupted()){
                    try {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Ion.with(getApplicationContext()).load("http://192.168.4.1").asString().setCallback(new FutureCallback<String>() {
                                    @Override
                                    public void onCompleted(Exception e, String result) {
//                                        tv.setText(result);
                                        txt.setText(voltage(result));
                                        tv.setText(readAddess(result));
                                    }
                                });
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        readVol.start();
    }

    //    String chuoi = "Voltage=9.90,IPAddress=192.168.4.2,";
    public static String voltage(String b_voltage){
        b_voltage.trim();
        int vtCuoi1 = b_voltage.lastIndexOf("e=");
        int vtCuoi2 = b_voltage.lastIndexOf(",");
        String voltage = b_voltage.substring(vtCuoi1+2,vtCuoi2);
        return voltage;
    }
    public static String readAddess(String b_ipadress_){
        b_ipadress_.trim();
        int vtCuoi1 = b_ipadress_.lastIndexOf("s=");
//        int vtCuoi2 = b_ipadress_.lastIndexOf(",");
//        String address = b_ipadress_.substring(vtCuoi1+2,vtCuoi2);
        String address = b_ipadress_.substring(vtCuoi1+2);
        return address;
    }


    private void send_Data() {
        webView_send.setWebViewClient(new WebViewClient());
        webView_send.loadUrl("http://192.168.4.1");
    }


}
