package com.myself.anjalimishra.zaqon.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.myself.anjalimishra.zaqon.Helper.MySingleton;
import com.myself.anjalimishra.zaqon.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Register extends AppCompatActivity {
    EditText reg_username,reg_password;
    private int progressStatus = 0;
    String message, code;
    private Handler handler = new Handler();
    TextView tvregis;
    String pass,email;
    String url="http://zaqon.in/Zaqonn/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        reg_username=(EditText)findViewById(R.id.reg_username);
        reg_password=(EditText)findViewById(R.id.reg_pasword);
        tvregis=(TextView)findViewById(R.id.tvregis);
        tvregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getdata();
            }
        });
    }

    public void getdata()
    {

        email = reg_username.getText().toString();
        pass = reg_password.getText().toString();


        if (email.length() == 0) {
            Toast.makeText(this, "please fill", Toast.LENGTH_SHORT).show();
        }
        else    if (pass.length() == 0) {
            Toast.makeText(this, "please fill", Toast.LENGTH_SHORT).show();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    JSONObject jsonObject = jsonArray.getJSONObject(0);
                    Log.e("TAG","response"+response);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
                final ProgressDialog pd = new ProgressDialog(Register.this);
                pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);

                pd.setTitle("Loading");
                pd.setMessage("Please wait for admin to access permission");

                pd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FFD4D9D0")));

                pd.setIndeterminate(false);

                pd.show();



                // Start the lengthy operation in a background thread
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressStatus < 100) {
                            // Update the progress status
                            progressStatus += 1;

                            // Try to sleep the thread for 20 milliseconds
                            try {
                                Thread.sleep(20);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            // Update the progress bar
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    // Update the progress status
                                    pd.setProgress(progressStatus);
                                    // If task execution completed
                                    if (progressStatus == 100) {
                                        Intent ipp = new Intent(getApplicationContext(), Login.class);
                                        startActivity(ipp);
                                        finish();
                                        // Dismiss/hide the progress dialog
                                        pd.dismiss();
                                    }
                                }
                            });
                        }
                    }
                }).start(); // Start the operation


//                                Intent reg = new Intent(Registration.this, Login.class);
//                                startActivity(reg);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Register.this, "Check Your Internet Connection...", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email", email);
                params.put("pass", pass);

                return params;
            }
        };


        MySingleton.getInstance(getApplication()).addToRequestque(stringRequest);
    }
}
