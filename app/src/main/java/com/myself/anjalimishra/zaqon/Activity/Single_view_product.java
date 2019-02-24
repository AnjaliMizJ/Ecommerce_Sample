package com.myself.anjalimishra.zaqon.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.myself.anjalimishra.zaqon.Adapter.Rcycler_adapter;
import com.myself.anjalimishra.zaqon.Adapter.Single_view_Adapter;
import com.myself.anjalimishra.zaqon.Fragments.*;
import com.myself.anjalimishra.zaqon.Fragments.Specification;
import com.myself.anjalimishra.zaqon.Model_clasa_recycle.Model_home_recycle;
import com.myself.anjalimishra.zaqon.R;

import java.util.ArrayList;
import java.util.List;

public class Single_view_product extends AppCompatActivity {

    Toolbar toolbar;
    LinearLayout descrpt, specificaton, review ,vndor;
    TextView t1, t2, t3, t4 ,wish1, wish2 ,cart , notify;
    RecyclerView product_recycle;
    Single_view_Adapter apAdapter;
    String titles ,pricee;
    int image;
    ImageView product_image;
    CardView add_cart;
    private static String quant="1";
    TextView titlt, prict , quantity , inre , decre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_view_product);



        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_single);
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
                Intent n=new Intent(Single_view_product.this,Cart.class);
                startActivity(n);
            }
        });

        toolbar.setLogo(R.drawable.logo_edit);
        product_image=(ImageView)findViewById(R.id.product_image);
        titlt=(TextView)findViewById(R.id.heading2_single_view);
        prict=(TextView)findViewById(R.id.price_single_view);

        titles=getIntent().getStringExtra("title");
        pricee=getIntent().getStringExtra("price");
        image=getIntent().getIntExtra("image",image);

        product_image.setImageResource(image);
        titlt.setText(titles);
        prict.setText(pricee);
        inre=(TextView)findViewById(R.id.quantity_add);
        decre=(TextView)findViewById(R.id.quantity_subtract);
        quantity=(TextView)findViewById(R.id.quantity);
        add_cart=(CardView)findViewById(R.id.add_to_cart);
        add_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n=new Intent(Single_view_product.this,Cart.class);
                n.putExtra("image",image);
                n.putExtra("title",titles);
                n.putExtra("price",pricee);
                startActivity(n);
            }
        });
       quant =quantity.getText().toString();

//        inre.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                quant=quant+1;
//                quantity.setText(quant);
//
//            }
//        });



        product_recycle = (RecyclerView)findViewById(R.id.recyclerview_single_view_product);
        apAdapter = new Single_view_Adapter(this,getData());
        product_recycle.setAdapter(apAdapter);
        LinearLayoutManager llm=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        product_recycle.setLayoutManager(llm);

        descrpt=(LinearLayout)findViewById(R.id.lay1);
        specificaton=(LinearLayout)findViewById(R.id.lay2);
        review=(LinearLayout)findViewById(R.id.lay3);
        vndor=(LinearLayout)findViewById(R.id.lay4);

        t1=(TextView)findViewById(R.id.Descritpion) ;
        t2=(TextView)findViewById(R.id.Specificaion);
        t3=(TextView)findViewById(R.id.review);
        t4=(TextView)findViewById(R.id.vendor);
        wish1=(TextView)findViewById(R.id.product_add_wish);
        wish2=(TextView)findViewById(R.id.wishproduct_added);
        wish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wish1.setVisibility(View.GONE);
                wish2.setVisibility(View.VISIBLE);
            }
        });
        wish2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wish2.setVisibility(View.GONE);
                wish1.setVisibility(View.VISIBLE);
            }
        });


        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                description();
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                specification();
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reviews();
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vendorinfo();
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

            Intent n=new Intent(Single_view_product.this,Login.class);
            startActivity(n);
            return true;
        }
        if (id == R.id.action_profile) {

            Intent n=new Intent(Single_view_product.this,Profile.class);
            startActivity(n);
            return true;
        }
        if (id == R.id.action_settings) {

            Intent n=new Intent(Single_view_product.this,Cart.class);
            startActivity(n);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void description()
    {
        t4.setTextColor(Color.parseColor("#000000"));
        t3.setTextColor(Color.parseColor("#000000"));
        t2.setTextColor(Color.parseColor("#000000"));
        t1.setTextColor(Color.parseColor("#52A1F9"));
        descrpt.setVisibility(View.VISIBLE);
        specificaton.setVisibility(View.GONE);
        review.setVisibility(View.GONE);
        vndor.setVisibility(View.GONE);
    }
    private void specification() {

        t4.setTextColor(Color.parseColor("#000000"));
        t3.setTextColor(Color.parseColor("#000000"));
        t1.setTextColor(Color.parseColor("#000000"));
        t2.setTextColor(Color.parseColor("#52A1F9"));
        descrpt.setVisibility(View.GONE);
        specificaton.setVisibility(View.VISIBLE);
        review.setVisibility(View.GONE);
        vndor.setVisibility(View.GONE);}

    public void reviews()
    {

        t4.setTextColor(Color.parseColor("#000000"));
        t2.setTextColor(Color.parseColor("#000000"));
        t1.setTextColor(Color.parseColor("#000000"));
        t3.setTextColor(Color.parseColor("#52A1F9"));
        descrpt.setVisibility(View.GONE);
        specificaton.setVisibility(View.GONE);
        review.setVisibility(View.VISIBLE);
        vndor.setVisibility(View.GONE);
    }
    private void vendorinfo() {

        t2.setTextColor(Color.parseColor("#000000"));
        t3.setTextColor(Color.parseColor("#000000"));
        t1.setTextColor(Color.parseColor("#000000"));
        t4.setTextColor(Color.parseColor("#52A1F9"));
        descrpt.setVisibility(View.GONE);
        specificaton.setVisibility(View.GONE);
        review.setVisibility(View.GONE);
        vndor.setVisibility(View.VISIBLE);
    }

    public List<Model_home_recycle> getData(){
        List<Model_home_recycle> availablePlacesList = new ArrayList<>();
        Model_home_recycle data;

        int apImages[] = {R.drawable.prod_2,R.drawable.prod_3,R.drawable.prod_4,R.drawable.prod_5,R.drawable.prod_6};

        String apTitle[] = {"New Black Frame Round Sunglasses For Men","Dark Blue Sunglasses present to ZAQON"
                ,"Royal Black Sunglasses for Men ","New Reading Sunslasses for men","New Blue Sunglasses for unisex"};
        String apNumber[] = {"₹ 100","₹ 140","₹ 210","₹ 870","₹ 990"};

        for (int i=0; i<apImages.length&&i<apTitle.length;i++){
            data = new Model_home_recycle();
            data.setImage(apImages[i]);
            data.setTitle(apTitle[i]);
            data.setPrice(apNumber[i]);
            availablePlacesList.add(data);
        }

        return availablePlacesList;
    }

}
