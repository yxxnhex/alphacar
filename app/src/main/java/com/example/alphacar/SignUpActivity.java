package com.example.alphacar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity {

    EditText tvPhoneNum, tvCarNum, tvCarName, tvCarGas;
    Button btnSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        tvPhoneNum = findViewById(R.id.tvPhoneNum);
        tvCarNum = findViewById(R.id.tvCarNum);
        tvCarName = findViewById(R.id.tvCarName);
        tvCarGas = findViewById(R.id.tvCarGas);
        btnSignUp = findViewById(R.id.btnSignUp);

        tvPhoneNum.getText().toString();
        tvCarNum.getText().toString();
        tvCarName.getText().toString();
        tvCarGas.getText().toString();

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}