package edu.gcu.betterweather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class BWALoginView extends AppCompatActivity {

    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bwalogin_view);

        btnLogin = findViewById(R.id.btnLogIn);

        btnLogin.setOnClickListener(view -> {
            Toast.makeText(BWALoginView.this, "Log In Successful", Toast.LENGTH_SHORT).show();
            LoginSuccess();
        });
    }

    public void LoginSuccess(){


        Intent intent = new Intent(BWALoginView.this, MainUI.class);
        startActivity(intent);
    }
}