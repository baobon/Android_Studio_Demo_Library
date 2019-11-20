package bao.bon.button_pressing;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAsyncTask extends AsyncTask<Void,Integer,Void> {

    Activity conTextParent;

    public MyAsyncTask(Activity conTextParent){
        this.conTextParent = conTextParent;
    }

    @Override
    protected void onPreExecute() {
        Toast.makeText(conTextParent, "Start AsysTask", Toast.LENGTH_SHORT).show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        for(int i=0;i<100;i++){
            SystemClock.sleep(100);
            publishProgress(i);
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
//        WebView webView = conTextParent.findViewById(R.id.webView);
        int number = values[0];
        TextView textView = conTextParent.findViewById(R.id.textViewcount);
        textView.setText(number);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        Toast.makeText(conTextParent, "FINISH", Toast.LENGTH_SHORT).show();
    }
}
