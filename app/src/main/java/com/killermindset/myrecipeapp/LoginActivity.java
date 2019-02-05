package com.killermindset.myrecipeapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText mEmail;
    private EditText mPassword;
    private Button LoginBtn;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        LoginBtn = findViewById(R.id.LoginButton);
        mEmail = findViewById(R.id.email);
        mPassword = findViewById(R.id.password);

        LoginBtn.setOnClickListener((View.OnClickListener) this);
    }

    private void loginUser() {


        email = mEmail.getText().toString();
        password = mPassword.getText().toString();

        if (!dataValidate(email, password)) {
            return;
        }
        if (email.equals("uzadaku96@gmail.com") && password.equals("12345")){
            Toast.makeText(this, "CORRECT", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }else{
            Toast.makeText(this, "invalid details", Toast.LENGTH_SHORT).show();
        }

    }
    private boolean dataValidate(String email, String password) {

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("Re-enter email address");
            return false;
        }


        if (password.isEmpty() || password.length() < 2 ) {
            mPassword.setError("Incorrect password");
            return false;
        }

        return true;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.LoginButton:
                loginUser();
                break;
        }
    }

    }
