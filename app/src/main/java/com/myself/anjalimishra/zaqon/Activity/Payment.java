package com.myself.anjalimishra.zaqon.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.myself.anjalimishra.zaqon.R;

public class Payment extends AppCompatActivity {

    TextView continue_btn;
    Toolbar toolbar;
    TextView t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        toolbar=(Toolbar)findViewById(R.id.toolbar_payment);
        t=(TextView)findViewById(R.id.toolbar_text);

        setSupportActionBar(toolbar);

        continue_btn=(TextView)findViewById(R.id.continue_payment);
        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n= new Intent(Payment.this,Captcha.class);
                startActivity(n);
            }
        });
    }
}
