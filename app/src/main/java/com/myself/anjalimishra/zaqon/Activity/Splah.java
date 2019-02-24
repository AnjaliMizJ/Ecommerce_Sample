package com.myself.anjalimishra.zaqon.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

import com.myself.anjalimishra.zaqon.Helper.TypeWriter;
import com.myself.anjalimishra.zaqon.R;

public class Splah extends AppCompatActivity {

    Thread st;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splah);
        StartAnimation();
    }

    private void StartAnimation() {

        Animation anim = AnimationUtils.loadAnimation(this, R.anim.slideout);
        anim.reset();
        RelativeLayout l = (RelativeLayout) findViewById(R.id.linear_layout);
        l.clearAnimation();
        l.startAnimation(anim);
        final TypeWriter iv = (TypeWriter) findViewById(R.id.tv);
        iv.clearAnimation();
        iv.setText("");
        iv.setCharacterDelay(150);
        iv.animateText("Welcome To Zaqon..!!");
        st = new Thread() {
            public void run() {
                try {
                    int w = 0;
                    while (w < 3500) {
                        sleep(100);
                        w += 100;
                    }
                    Intent n=new Intent(Splah.this,Slider.class);
                    startActivity(n);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        };
        st.start();
    }
}
