package com.myself.anjalimishra.zaqon.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.myself.anjalimishra.zaqon.Adapter.Product_view_adapter;
import com.myself.anjalimishra.zaqon.Model_clasa_recycle.Model_home_recycle;
import com.myself.anjalimishra.zaqon.Model_clasa_recycle.Model_view_all;
import com.myself.anjalimishra.zaqon.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Product_view extends AppCompatActivity
{
    GridLayoutManager lLayout;
    Spinner spinner_priority ;
    String spintext;
    Toolbar toolbar;
    RecyclerView rView;
    Product_view_adapter rcAdapter;
    TextView cart , notify;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_view);


        toolbar=(Toolbar)findViewById(R.id.toolbar_product);
        setSupportActionBar(toolbar);

        toolbar.setTitle(null);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        cart = (TextView) findViewById(R.id.message);
        notify = (TextView) findViewById(R.id.notification);
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/fontawesome-webfont.ttf");

        cart.setTypeface(font);
        cart.setText("\uf217");

        notify.setTypeface(font);
        notify.setText("\uf0f3");
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n=new Intent(Product_view.this,Cart.class);
                startActivity(n);
            }
        });

        lLayout = new GridLayoutManager(Product_view.this, 2);
        rView = (RecyclerView)findViewById(R.id.recycler_view_all);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);


        spinner_priority=(Spinner)findViewById(R.id.priority_price);

        lLayout = new GridLayoutManager(Product_view.this, 2);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);




        String arrayspin[]={"default sorting","price high to low","price low to high"};
        int size1=arrayspin.length;

        final ArrayAdapter<String> spinnerArray = new ArrayAdapter<String>(this,R.layout.spin1,arrayspin){

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArray.setDropDownViewResource(R.layout.spin1);
        spinner_priority.setAdapter(spinnerArray);

        spinner_priority.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spintext=spinner_priority.getItemAtPosition(i).toString();
                Log.e("ST", "onItemSelected: "+spintext );
                if (spintext.equals("price high to low"))
                {
                    List<Model_view_all> availablePlacesList = new ArrayList<>();
                    availablePlacesList=getData();
                    Collections.sort(availablePlacesList, new Comparator<Model_view_all>() {
                        @Override
                        public int compare(Model_view_all model_view_all, Model_view_all t1) {
                            return (t1.getPrice_view_all().compareTo(model_view_all.getPrice_view_all()));
                        }
                    });
                    Log.e("SA", "onItemSelected: "+availablePlacesList );
                    rcAdapter=new Product_view_adapter(Product_view.this,availablePlacesList);
                    rView.setAdapter(rcAdapter);

                    // rcAdapter.datalist(availablePlacesList);
                }
                if (spintext.equals("price low to high"))
                {
                    List<Model_view_all> availablePlacesList = new ArrayList<>();
                    availablePlacesList=getData();
                    Collections.sort(availablePlacesList, new Comparator<Model_view_all>() {
                        @Override
                        public int compare(Model_view_all model_view_all, Model_view_all t1) {
                            return (model_view_all.getPrice_view_all().compareTo(t1.getPrice_view_all()));
                        }
                    });
                    Log.e("SA", "onItemSelected: "+availablePlacesList );
                    rcAdapter=new Product_view_adapter(Product_view.this,availablePlacesList);
                    rView.setAdapter(rcAdapter);

                    // rcAdapter.datalist(availablePlacesList);
                }
                else   if (spintext.equals("default sorting"))
                {
                    rcAdapter = new Product_view_adapter(Product_view.this, getData());
                    rView.setAdapter(rcAdapter);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_login) {

            Intent n=new Intent(Product_view.this,Login.class);
            startActivity(n);
            return true;
        }
        if (id == R.id.action_profile) {

            Intent n=new Intent(Product_view.this,Profile.class);
            startActivity(n);
            return true;
        }
        if (id == R.id.action_settings) {

            Intent n=new Intent(Product_view.this,Cart.class);
            startActivity(n);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public List<Model_view_all> getData() {
        List<Model_view_all> availablePlacesList = new ArrayList<>();
        Model_view_all data;

        int apImages[] = {R.drawable.prod_2, R.drawable.prod_3, R.drawable.prod_4, R.drawable.prod_5, R.drawable.prod_6, R.drawable.prod_2, R.drawable.prod_3, R.drawable.prod_4, R.drawable.prod_5, R.drawable.prod_6};

        String apTitle[] = {"New Black Frame Round Sunglasses For Men", "Dark Blue Sunglasses present to ZAQON"
                , "Royal Black Sunglasses for Men ", "New Reading Sunslasses for men", "New Blue Sunglasses for unisex", "New Black Frame Round Sunglasses For Men", "Dark Blue Sunglasses present to ZAQON"
                , "Royal Black Sunglasses for Men ", "New Reading Sunslasses for men", "New Blue Sunglasses for unisex"};
        String apNumber[] = {"₹ 100", "₹ 140", "₹ 210", "₹ 870", "₹ 990", "₹ 100", "₹ 140", "₹ 210", "₹ 870", "₹ 990"};


        for (int i = 0; i < apImages.length && i < apTitle.length; i++) {
            data = new Model_view_all();
            data.setImage_viewall(apImages[i]);
            data.setTitle_viewall(apTitle[i]);
            data.setPrice_view_all(apNumber[i]);
            availablePlacesList.add(data);
        }

        return availablePlacesList;
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id;
//        id = item.getItemId();
//
//               if (id == R.id.action_login) {
//
//            Intent n=new Intent(Product_view.this,Login.class);
//            startActivity(n);
//            return true;
//        }
//        if (id == R.id.action_profile) {
//
//            Intent n=new Intent(Product_view.this,Profile.class);
//            startActivity(n);
//            return true;
//        }
//
//
//        return super.onOptionsItemSelected(item);
//    }
}
