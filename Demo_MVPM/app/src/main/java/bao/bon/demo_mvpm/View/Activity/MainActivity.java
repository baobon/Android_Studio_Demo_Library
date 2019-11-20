package bao.bon.demo_mvpm.View.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bao.bon.demo_mvpm.Presenter.MyPresenter;
import bao.bon.demo_mvpm.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtUserName,edtPassword;
    private Button btnLogin;
    private MyPresenter myPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        myPresenter = new MyPresenter();

    }

    private void initView() {
        edtUserName = findViewById(R.id.EditTextUserName);
        edtPassword = findViewById(R.id.EditTextPassword);
        findViewById(R.id.buttonLogin).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.buttonLogin:
                if(myPresenter.login(edtUserName.getText().toString(),edtPassword.getText().toString())){
                    Toast.makeText(this, "Login sucess!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(this, "Invaild Information!", Toast.LENGTH_SHORT).show();
            break;
        }
    }
}
