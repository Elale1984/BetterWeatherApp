package edu.gcu.betterweather;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import edu.gcu.betterweather.databinding.ActivityRegisterUserBinding;

public class RegisterUser extends AppCompatActivity {

    private ActivityRegisterUserBinding binding;
    private String name, email, password, city;
    private FirebaseAuth mAuth;


    // Progress Dialogue
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterUserBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // init firebase auth
        mAuth = FirebaseAuth.getInstance();

        // configure progress dialogue
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Account Action");
        progressDialog.setMessage("Creating your account...");
        progressDialog.setCanceledOnTouchOutside(false);

        // onclick for the sign up button
        binding.btnSignUp.setOnClickListener(v -> validateData());

        // onclick for sign in text button launches to the sign in activity
        binding.tbtnSignIn.setOnClickListener(v -> {
            startActivity(new Intent(RegisterUser.this, BWALoginView.class));
            finish();
        });
    }

    private void validateData() {
        name = binding.etFName.getText().toString().trim();
        email = binding.etEmail.getText().toString().trim();
        password = binding.etMagicWord.getText().toString().trim();
        city = binding.etCityZipCode.getText().toString().trim();
        String re_password = binding.etRePass.getText().toString().trim();

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmail.setError("Invalid Email Format");
        } else if (TextUtils.isEmpty(password)) {
            // password not entered in field
            binding.etMagicWord.setError("Password Field is empty");
        } else if (!isValidPassword(password)) {
            // Password does not meet format requirements
            binding.etMagicWord.setError("Password must contain One Uppercase letter, one number, " +
                    "one special character, and be at least 8 characters long.");
        } else if (!re_password.equals(password)) {
            binding.etRePass.setError("The second password you entered does not match the first");
        } else {


            // Create Local Cache Entry
            UserData userData;
            CacheDatabase cacheDatabase;
            try {
                userData = new UserData(-1, email, name, city);

            } catch (Exception e) {
                Toast.makeText(this, "Error Creating Local Cache", Toast.LENGTH_SHORT).show();
                userData = new UserData(-1, "tester", "a", "81001");
            }
            cacheDatabase = new CacheDatabase(RegisterUser.this);
            boolean success = cacheDatabase.addOne(userData);
            Toast.makeText(this, "Success = " + success, Toast.LENGTH_SHORT).show();
            // Data was validated
            firebaseSignUp();
        }
    }


    private void firebaseSignUp() {

        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    // Register New Account
                    progressDialog.dismiss();

                    // get the users info
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    assert firebaseUser != null;
                    String userEmail = firebaseUser.getEmail();
                    Toast.makeText(RegisterUser.this, "Account Created\n" + userEmail, Toast.LENGTH_SHORT).show();

                    // log in user and launch weather app
                    startActivity(new Intent(RegisterUser.this, MainUI.class));
                    finish();
                })
                .addOnFailureListener(e -> {
                    // Failed Registration of new user
                    Toast.makeText(RegisterUser.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

    // Method to check for standard requirements of passwords. Returns true if password meets
    // these requirements
    public static boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        // using regex.... The password must have one special char, one uppercase letter,
        // one number, and minimum of 8 chars
        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@_#$%^&+=!])(?=\\S+$).{8,}$";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

}