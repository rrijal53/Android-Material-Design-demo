package com.sochware.e_agrovet;



import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sochware.e_agrovet.ui.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignupActivity";
    private static final int REQUEST_SIGNUP = 1;
    private static final String regix="(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    @BindView(R.id.signup_fullname) EditText _fullName;
    @BindView(R.id.signup_password) EditText _password;
    @BindView(R.id.signup_confirmpassword) EditText _confirmpassword;
    @BindView(R.id.signup_username) EditText _username;
    @BindView(R.id.signup_contact) EditText _contact;
    @BindView(R.id.signup_email) EditText _email;
    @BindView(R.id.signup_address) EditText _address;
    @BindView(R.id.btn_signup) Button _signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);

        _signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

     public void signUp()
        {
            Log.d(TAG, "Sign Up");

            if (!validate())
            {
                onSignUpFail();
                return;
            }

            _signup.setEnabled(false);

            final ProgressDialog progressDialog = new ProgressDialog(SignUpActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Signing Up");
            progressDialog.show();

            new android.os.Handler().postDelayed(new Runnable() {
                public void run() {
                    // On complete call either onLoginSuccess or onLoginFailed
                    onSignUpSuccess();
                    // onLoginFailed();
                    progressDialog.dismiss();
                }
            }, 1200);
        }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onSignUpSuccess()
    {
        _signup.setEnabled(true);
    }

    public void onSignUpFail()
    {
        Toast.makeText(getBaseContext(), "Sign Up failed", Toast.LENGTH_LONG).show();

        _signup.setEnabled(true);
    }

    public boolean validate()
    {
        boolean valid = true;


        String fullname=_fullName.getText().toString();
        String password=_password.getText().toString();
        String confirmpassword=_confirmpassword.getText().toString();
        String username=_username.getText().toString();
        String contact=_contact.getText().toString();
        String email=_email.getText().toString();
        String address=_address.getText().toString();

        if (fullname.isEmpty()){
            _fullName.setError("Enter you Full Name");
            valid = false;
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10){
        _password.setError("Enter the password between 4 to 10");
            valid = false;
        }

        if (confirmpassword.isEmpty()){
            _confirmpassword.setError("Confirm you password");
            valid = false;
        }

        if (username.isEmpty()){
            _username.setError("Enter unique username");
            valid = false;
        }

        if (contact.isEmpty()){
            _contact.setError("Provide you contact detail");
            valid = false;
        }

        if (email.isEmpty()){
            _email.setError("Enter your Email");
            valid = false;
        }

        if (address.isEmpty()){
            _address.setError("Enter your Address");
            valid = false;
        }

        if (!confirmpassword.equals(password)){
            _confirmpassword.setError("Confirm the correct Password");
            valid = false;
        }

        //if (!email.matches(regix))
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            _email.setError("Enter Valid Email Adress");
            valid=false;
        }
        else
        {
            _fullName.setError(null);
            _password.setError(null);
            _confirmpassword.setError(null);
            _username.setError(null);
            _contact.setError(null);
            _email.setError(null);
            _address.setError(null);
        }
        return  valid;

    }

}


