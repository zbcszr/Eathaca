package com.example.eathaca;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.afollestad.materialdialogs.MaterialDialog;
//
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.firestore.DocumentReference;
//import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


/**
 * A login screen that offers login and Registration via username/password.
 */
public class LoginActivity extends AppCompatActivity {


    /*
     * Keep track of the login task to ensure we can cancel it if requested.
     */

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mLoginFormView;
    private View mRelativeLayout;
    private MaterialDialog dialog;
    ProgressDialog progressDialog;
    private RadioGroup mRadioGroup;
    private String mEmail;
    private String mPassword;

    // Firebase Variables
//    private FirebaseAuth mAuth;
//    private FirebaseFirestore db;

    private int accountType;//1 =farm users; 0 =ordinary users


    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }

    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        hideDialog();
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        // Set up the login form.

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("wait ...");


        mEmailView = findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button signInButton = (Button) findViewById(R.id.btn_link_login);
        signInButton.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {
                if (checkInput())
                    attemptLogin();
            }
        });
        Button mRegisterButton = (Button) findViewById(R.id.btn_signup);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.P)
            @Override
            public void onClick(View view) {
                if (checkInput())
                    attemptRegister();
            }
        });

        mRadioGroup = (RadioGroup) findViewById(R.id.acount_radio_group);

        mRelativeLayout = findViewById(R.id.relative_progress);
        mLoginFormView = findViewById(R.id.login_form);


    }

    private boolean checkInput() {

        int selectedId = mRadioGroup.getCheckedRadioButtonId();

        if (selectedId == R.id.regular_radio_btn)
            accountType =0 ;
        else
            accountType =1;

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        mEmail = mEmailView.getText().toString();
        mPassword = mPasswordView.getText().toString();

        boolean cancel=false;
        View focusView = null;


        // Check for a valid email address.
        if (TextUtils.isEmpty(mEmail)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }else if (!isEmailValid(mEmail)) { // Check for a valid username.

            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }else if (TextUtils.isEmpty(mPassword)) {    // Check for a valid password, if the user entered one.

            mPasswordView.setError(getString(R.string.error_field_required));
            focusView = mPasswordView;
            cancel = true;
        } else if (!isPasswordValid(mPassword)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }
        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        }
        else {
            return  true;
        }
        return false;

    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    private void attemptRegister() {

        showDialog();
//        mAuth.createUserWithEmailAndPassword(mEmail,mPassword)
//                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()){
//                            // Store User into Firestore
//                            db = FirebaseFirestore.getInstance();
//                            String userId = task.getResult().getUser().getUid();
//                            DocumentReference documentReference = db.collection("users").document(userId);
//                            Map<String, Object> user = new HashMap<>();
//                            user.put("email", mEmail);
//                            user.put("accountType", accountType);
//
//                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    Toast.makeText(getApplicationContext(), "Hi " + mEmail +
//                                            ". You are successfully logged in!", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                                    finish();
//                                }
//                            });
//                        }else{
//                            Toast.makeText(LoginActivity.this, ""+
//                                    task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });

        Handler handler = new Handler();
        handler.postDelayed(this::hideDialog, LoginActivity.this, 1000);

    }


    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @RequiresApi(api = Build.VERSION_CODES.P)
    private void attemptLogin() {
        showDialog();

//        mAuth.signInWithEmailAndPassword(mEmail,mPassword)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if(task.isSuccessful()){
//
//                        }else{
//                            Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });
        Toast.makeText(getApplicationContext(),"You are logged in!",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            GlobalVar.saveLoginInfo(getApplicationContext(),mEmail,mPassword,accountType);
                            finish();

        Handler handler = new Handler();
        handler.postDelayed(this::hideDialog, LoginActivity.this, 1000);
    }


    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.

        dialog = new MaterialDialog.Builder(this)
                .title("Logging In")
                .content("Please wait for a while...")
                .progress(true, 0)
                .show();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
            mRelativeLayout.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
                    mRelativeLayout.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
                }
            });


        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mLoginFormView.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
            mRelativeLayout.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
        }
    }


}

