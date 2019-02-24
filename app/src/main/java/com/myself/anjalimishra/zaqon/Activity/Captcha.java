package com.myself.anjalimishra.zaqon.Activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.myself.anjalimishra.zaqon.R;

public class Captcha extends AppCompatActivity {
TextView textrandom,tv_refresh;
    TextView btn;
    EditText ed;

    String s1,s2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha);
        btn=(TextView) findViewById(R.id.btn);
        ed=(EditText)findViewById(R.id.ed);
        tv_refresh=(TextView)findViewById(R.id.im);
        textrandom=(TextView)findViewById(R.id.tvrandom);
        final String []names={"azxedf","1234oju","909prQ090","ansQEjaP0li","hxshxgsh","Kkolp","89989655","8uiAqw","LSPoi","Pra20Som13"};
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");

        tv_refresh.setTypeface(font);
        tv_refresh.setText("\uf021");

        tv_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rando=(int)(Math.random()*10);
                textrandom.setText(names[rando]);
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1=ed.getText().toString();
                s2=textrandom.getText().toString();
                if(s1.equals(s2))
                {
                    Toast.makeText(Captcha.this, " Order confirmed", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(Captcha.this, "Try again", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}
