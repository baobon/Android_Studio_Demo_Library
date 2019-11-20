package bao.bon.sceneform;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private final String SHARED_PREFERENCES_NAME = "baobon";
    private Button btnSaveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        save_Data();
    }

    private void save_Data() {
        SharedPreferences sharedPreferences =  getSharedPreferences(SharedPreferences)
    }
}
