package com.myself.anjalimishra.zaqon.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.myself.anjalimishra.zaqon.Helper.MySingleton;
import com.myself.anjalimishra.zaqon.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity {
    String name, email;
    TextView reg_text, tv_login;
    EditText login_username, login_password;

    String getname, getpass1;
    String urll = "http://zaqon.in/Zaqonn/alldata.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //android:text="If not registered then create your account."
        //android:text="If not registered then create your account."
        reg_text = (TextView) findViewById(R.id.reg_textview);
        tv_login = (TextView) findViewById(R.id.tvvlogin);

        login_username = (EditText) findViewById(R.id.login_username);
        login_password = (EditText) findViewById(R.id.login_pasword);


        ClickableSpan termsOfServicesClick = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent n = new Intent(Login.this, Register.class);
                startActivity(n);
            }
        };
        makeLinks(reg_text, new String[]{"Create account"}, new ClickableSpan[]{
                termsOfServicesClick
        });

        tv_login = (TextView) findViewById(R.id.tvvlogin);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertdata();
            }
        });
    }

    public void makeLinks(TextView textView, String[] links, ClickableSpan[] clickableSpans) {
        SpannableString spannableString = new SpannableString(textView.getText());
        for (int i = 0; i < links.length; i++) {
            ClickableSpan clickableSpan = clickableSpans[i];
            String link = links[i];

            int startIndexOfLink = textView.getText().toString().indexOf(link);
            spannableString.setSpan(clickableSpan, startIndexOfLink, startIndexOfLink + link.length(),
                    Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            // spannableString.setSpan(new StyleSpan(Typeface.BOLD),startIndexOfLink,startIndexOfLink + link.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            spannableString.setSpan(new RelativeSizeSpan(1.2f), startIndexOfLink, startIndexOfLink + link.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
    }

    public void insertdata() {
        getpass1 = login_password.getText().toString();
        getname = login_username.getText().toString();

        if (getname.length() == 0) {
            login_username.setError("UserName is must not be empty");
            login_username.requestFocus();
        }
        if (getpass1.length() == 0) {
            login_password.setError("Password is must not be empty");
            login_password.requestFocus();
        } else {

            StringRequest stringRequest = new StringRequest(Request.Method.POST, urll, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {


                        String s = encryptmd5(getpass1);

                        Log.e("Tag", "getname" + getname);
                        Log.e("Tag", "getpass" + getpass1);
                        Log.e("TAGG", "pass encrypted" + s);

//                        JSONObject jsonObjects = new JSONObject(response);
//                        JSONArray jsonArrays = jsonObjects.getJSONArray("server_response");
                        JSONArray jsonArrays=new JSONArray(response);
                        for (int i = 0; i < jsonArrays.length(); i++) {
                            JSONObject jsonObject = jsonArrays.getJSONObject(i);

                            name = jsonObject.getString("user_email");
                            email = jsonObject.getString("user_pass");
                            Log.e("Tag", "All password" + email);

                            if ((getname.equalsIgnoreCase(name)) && (s.equalsIgnoreCase(email))) {

                                Toast.makeText(Login.this, "doneee", Toast.LENGTH_SHORT).show();
                            }
                        }//for

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Login.this, "some error", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            });


            MySingleton.getInstance(getApplication()).addToRequestque(stringRequest);


        }
    }



    private  static String encryptmd5(String data) {
        try {


            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messagedigest = md.digest(data.getBytes());
            BigInteger num = new BigInteger(1, messagedigest);
            String hashtext = num.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;

            }

            return hashtext;

        } catch (NoSuchAlgorithmException e)
        {
            throw  new RuntimeException(e);
        }
    }
}



