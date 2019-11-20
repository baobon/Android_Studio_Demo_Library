package bao.bon.shared_preferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String TAG = getClass().getSimpleName();

    private final String SHARED_PREFERSENCES_NAME = "baobon";
    private final String MY_NAME = "my_name";
    private final String AGE = "age";
    private final String IS_SINGLE = "is_single";
    private final String WEIGHT = "weight";
    private Button btnSaveData;
    private Button btnReadData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSaveData = findViewById(R.id.buttonSave);
        btnReadData = findViewById(R.id.buttonRead);

        btnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addData();
            }
        });

        btnReadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readData();
            }
        });
    }

    public void addData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERSENCES_NAME, MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPreferences.edit(); //Open file shared
        editor.putString(MY_NAME, "Bui Quoc Bao");
        editor.putInt(AGE, 25);
        editor.putBoolean(IS_SINGLE, false);
        editor.putLong(WEIGHT, 60);

        editor.apply();
//                editor.commit();
        Toast.makeText(MainActivity.this, "Save sucessfully", Toast.LENGTH_SHORT).show();
    }

    public void readData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERSENCES_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(MY_NAME, "");
        int age = sharedPreferences.getInt(AGE, 0);
        boolean is_signle = sharedPreferences.getBoolean(IS_SINGLE, false);
        long weight = sharedPreferences.getLong(WEIGHT, 0);
        String address = sharedPreferences.getString("ADDRESS", "Ho Chi Minh");

        Log.d(TAG, "BaoBon : Name: " + name
                + " Tuoi " + age
                + " Singel " + is_signle
                + " Weight " + weight
                + " Address " + address);

    }
}
