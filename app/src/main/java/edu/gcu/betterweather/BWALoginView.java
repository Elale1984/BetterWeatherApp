package edu.gcu.betterweather;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class BWALoginView extends AppCompatActivity {

    Button btnLogin, tbnRegisterAccount;
    EditText email;
    EditText password;

    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bwalogin_view);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.etEmail);
        password = findViewById(R.id.etPass);

        // Login Button
        btnLogin = findViewById(R.id.btnLogIn);
        btnLogin.setOnClickListener(view -> LoginSuccess());

        // Text button that takes user to register account page
        tbnRegisterAccount = findViewById(R.id.tbnRegisterAccount);
        tbnRegisterAccount.setOnClickListener(view -> NavigateToRegistration());

    }

    private void NavigateToRegistration() {

        Intent registrationIntent = new Intent(BWALoginView.this, RegisterUser.class);
        startActivity(registrationIntent);
    }


    // navigate after login

    public void LoginSuccess(){

        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        //checking our users inputs against firebase for correct login

        mAuth.signInWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener(this, task -> {
            if (task.isSuccessful() ) {
                Toast.makeText(BWALoginView.this, "Log In Successful",
                        Toast.LENGTH_SHORT).show();

                // Navigate to main UI
                Intent intent = new Intent(BWALoginView.this, MainUI.class);
                startActivity(intent);
            }
            else {
                // User login failed so error massage for incorrect credentials
                Toast.makeText(BWALoginView.this,
                        "Log Failed. Check your email and password and try again. ",
                        Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        //check if user is signed in and update ui
        FirebaseUser currUser = mAuth.getCurrentUser();

        if (currUser != null) {
            // Navigate to main UI if user is already signed in
            Intent intent = new Intent(BWALoginView.this, MainUI.class);
            startActivity(intent);
        }
    }
}