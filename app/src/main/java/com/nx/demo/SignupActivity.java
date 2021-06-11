package com.nx.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nx.demo.ApiUtil.Cls_common;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edit_name, edit_email, edit_password;
    TextView txtlogin;
    Button btn_signup;
    Cls_common cls_common;
    boolean error = false;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        cls_common = new Cls_common(this);
        intiView();
    }

    private void intiView() {
        edit_email = findViewById(R.id.edit_email);
        edit_name = findViewById(R.id.edit_name);
        edit_password = findViewById(R.id.edit_password);
        txtlogin = findViewById(R.id.txtlogin);

        btn_signup = findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(this::onClick);
        txtlogin.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_signup:

                Validattion();
                if (error) {
                    try {
                        Cls_common.setUsername(SignupActivity.this, edit_name.getText().toString());
                        Cls_common.setEmailID(SignupActivity.this, edit_email.getText().toString());
                        Cls_common.setPassword(SignupActivity.this, edit_password.getText().toString());
                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.txtlogin:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                break;

        }
    }

    private boolean Validattion() {
        if (edit_name.getText().toString().isEmpty()) {
            edit_name.requestFocus();
            edit_name.setError("FIELD CANNOT BE EMPTY");
            error = false;
        } else if (edit_email.getText().toString().isEmpty()) {
            edit_email.requestFocus();
            edit_email.setError("FIELD CANNOT BE EMPTY");
            error = false;
        } else if (!edit_email.getText().toString().trim().matches(emailPattern)) {
            edit_email.requestFocus();
            edit_email.setError(getResources().getString(R.string.validemail));
            error = false;
        } else if (edit_password.getText().toString().isEmpty()) {
            edit_password.requestFocus();
            edit_password.setError("FIELD CANNOT BE EMPTY");
            error = false;
        } else {
            error = true;
            //   Toast.makeText(this, "H2", Toast.LENGTH_SHORT).show();
        }

        return false;
    }
}