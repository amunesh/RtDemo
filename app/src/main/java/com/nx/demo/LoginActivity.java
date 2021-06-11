package com.nx.demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.nx.demo.ApiUtil.Cls_common;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edit_email, edit_password;
    TextView txtsingup;
    Button btn_login;
    boolean error = false;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if(!Cls_common.getUsername(this).equals(""))
//        {
//            Intent intent = new Intent(this, HomeActivity.class);
//            startActivity(intent);
//
//        }
        intiView();
    }

    private void intiView() {
        edit_email = findViewById(R.id.edit_email);
        edit_password = findViewById(R.id.edit_password);
        txtsingup = findViewById(R.id.txtsingup);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(this::onClick);
        txtsingup.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_login:
                Validattion();
                if (error) {
                    try {

                        if (Cls_common.getEmaild(this).equals(edit_email.getText().toString()) && Cls_common.getpassword(this).equals(edit_password.getText().toString())) {
                            Intent intent = new Intent(this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                            builder1.setMessage("Please Enter Valid ID  and Password");
                            builder1.setCancelable(true);

                            builder1.setPositiveButton(
                                    "OK",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });



                            AlertDialog alert11 = builder1.create();
                            alert11.show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                break;
            case R.id.txtsingup:
                Intent intent = new Intent(this, SignupActivity.class);
                startActivity(intent);
                break;

        }
    }

    private boolean Validattion() {
        if (edit_email.getText().toString().isEmpty()) {
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