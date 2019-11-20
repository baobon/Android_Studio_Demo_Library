package bao.bon.thread_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String TAG  = "MainAcitivty";
    private Button btn,btnStop;
    private TextView txt;
    private Handler mainHandler = new Handler();
    int i;
    WebView webView;
    String direction;
    private volatile boolean stopThread = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.button);
        txt = findViewById(R.id.textView);
        btnStop = findViewById(R.id.button2);
        webView = findViewById(R.id.webView);
//        String direction;

        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    direction = "1";
                    start_Thread();
                    Toast.makeText(MainActivity.this, "DONW", Toast.LENGTH_SHORT).show();
                }else if(motionEvent.getAction() == MotionEvent.ACTION_UP){
                    direction = "5";
                    send_Data();
                    stop_Thread();
                    Toast.makeText(MainActivity.this, "UP", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }


    private void send_Data() {
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://192.168.4.1/?Makershop=,Speed=100,Direction="+ direction +",");
    }




    void start_Thread(){
        stopThread = false;
        ExampleRunable runable = new ExampleRunable(10000);
//        runable.run();
        new Thread(runable).start();
    }

    void stop_Thread(){
        stopThread = true;
    }

//    public void startThread(View view){
//        stopThread = false;
//        Toast.makeText(this, "Start", Toast.LENGTH_SHORT).show();
//        ExampleRunable runable = new ExampleRunable(10000);
////        runable.run();
//        new Thread(runable).start();
//
//    }

//    public void stopThread(View view){
//        stopThread = true;
//
//    }

    class ExampleRunable  implements  Runnable{
        int seconds;

        ExampleRunable(int seconds){
            this.seconds = seconds;
        }
        @Override
        public void run() {
            for (i=0 ;i<seconds;i++){
                if(stopThread)
                    return;
                /*
                Handler threadHandler = new Handler();
                threadHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        txt.setText(String.valueOf(i));
                    }
                });
                */

                /*
                btn.post(new Runnable() {
                    @Override
                    public void run() {
                        txt.setText(String.valueOf(i));
                    }
                });

                */

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        txt.setText(String.valueOf(i));
                        send_Data();
                    }
                });

                Log.d(TAG,"startThread" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
