package edu.gcu.betterweather;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import edu.gcu.betterweather.databinding.ActivityBwaloginViewBinding;

public class BWALoginView extends AppCompatActivity {


    private ActivityBwaloginViewBinding binding;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityBwaloginViewBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        setContentView(view);

        mAuth = FirebaseAuth.getInstance();



        // Login Button
        binding.btnLogIn.setOnClickListener(v -> ValidateDataAndLogIn());

        // Text button that takes user to register account page
        binding.tbnRegisterAccount.setOnClickListener(v -> NavigateToRegistration());

    }

    private void NavigateToRegistration() {

        startActivity(new Intent(BWALoginView.this, RegisterUser.class));
        finish();
    }


    // navigate after login

    public void ValidateDataAndLogIn(){

        String userEmail = binding.etEmail.getText().toString();
        String userPassword = binding.etPass.getText().toString();

        //Error Handling for user miss entry of login credentials
        if (!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
            binding.etEmail.setError("Invalid Email Format");
        }
        else if (TextUtils.isEmpty(userPassword)) {
            // password not entered in field
            binding.etPass.setError("Password Field is empty");
        }
        else {
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
    }

    @Override
    protected void onStart() {
        super.onStart();

        //check if user is signed in and update ui
        FirebaseUser currUser = mAuth.getCurrentUser();

        if (currUser != null) {

            // Navigate to main UI if user is already signed in
            startActivity(new Intent(BWALoginView.this, MainUI.class));
            finish();
        }
    }
}