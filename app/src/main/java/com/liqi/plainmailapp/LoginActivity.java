package com.liqi.plainmailapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText account = findViewById(R.id.et_account);
        EditText password = findViewById(R.id.et_password);
        CheckBox rememberPassword = findViewById(R.id.cb_remember_password);
        Button button = findViewById(R.id.btn_login);

        SharedPreferences preferences = this.getSharedPreferences("loginInfo", Context.MODE_PRIVATE);

        if (preferences.getBoolean("rememberPassword", false)) {
            account.setText(preferences.getString("account",""));
            password.setText(preferences.getString("password",""));
            rememberPassword.setChecked(true);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String accountValue = account.getText().toString();
                String passwordValue = password.getText().toString();

                if ("".equals(accountValue) || "".equals(passwordValue)) {
                    Toast.makeText(LoginActivity.this, "帐号或密码不允许为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor editor = preferences.edit();

                if ("17317693790".equals(accountValue) && "123456".equals(passwordValue)) {
                    Toast.makeText(LoginActivity.this, "密码不正确", Toast.LENGTH_SHORT).show();
                    editor.clear();
                    editor.apply();
                    return;
                }

                if (rememberPassword.isChecked()) {
                    editor.putString("account",accountValue);
                    editor.putString("password",passwordValue);
                    editor.putBoolean("rememberPassword",true);
                    editor.commit();
                } else {
                    editor.clear();
                    editor.apply();
                }

                Intent intent = new Intent();
                intent.setClass(getApplicationContext(), HomeScreenActivity.class);
                startActivity(intent);
            }
        });
    }
}