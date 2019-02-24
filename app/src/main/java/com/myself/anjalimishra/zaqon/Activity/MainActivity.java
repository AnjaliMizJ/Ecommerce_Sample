package com.myself.anjalimishra.zaqon.Activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.myself.anjalimishra.zaqon.Adapter.Rcycler_adapter;
import com.myself.anjalimishra.zaqon.Adapter.Recycler_advert_home;
import com.myself.anjalimishra.zaqon.Adapter.SlidingImage_Adapter;
import com.myself.anjalimishra.zaqon.Helper.MySingleton;
import com.myself.anjalimishra.zaqon.Model_clasa_recycle.Model_home_recycle;
import com.myself.anjalimishra.zaqon.Model_clasa_recycle.Model_recycle_advert;
import com.myself.anjalimishra.zaqon.R;
import com.squareup.picasso.Picasso;
import com.viewpagerindicator.CirclePageIndicator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static ViewPager mPager;

    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.a,R.drawable.c,R.drawable.c};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    RecyclerView product_recycler, advertisement_recycle;
    Rcycler_adapter apAdapter;
    Recycler_advert_home advertHome;
    String url="http://localhost/zaclab/user.php";
    private static List<String> listId= null;
    private static List<String> listImage= null;
    Context context;
    TextView cart , notify;

    String imageurl="http://zaqon.in/wp-content/uploads/2017/10/https-_nozzle.s3-ap-southeast-1.amazonaws.com_shop_65814_product_img_65814_1493562251-300x300.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ImageView ivBasicImage3 = (ImageView) findViewById(R.id.adv1);
        Picasso.with(this).load(R.drawable.per).fit().into(ivBasicImage3);
        ImageView ivBasicImage1 = (ImageView) findViewById(R.id.adv2);
        Picasso.with(this).load(R.drawable.adv).fit().into(ivBasicImage1);
        ImageView ivBasicImage2 = (ImageView) findViewById(R.id.adv3);
        Picasso.with(this).load(R.drawable.best_one).fit().into(ivBasicImage2);

        ImageView ivBasicImage_adv= (ImageView) findViewById(R.id.adv_image);
        Picasso.with(this).load("http://zaqon.in/wp-content/uploads/2016/02/f1-1024x549.jpg").fit().into(ivBasicImage_adv);

        ImageView ivBasicImage_adv1= (ImageView) findViewById(R.id.adv_image2);
        Picasso.with(this).load("http://zaqon.in/wp-content/uploads/2016/02/ads_upto.jpg").fit().into(ivBasicImage_adv1);

        listId = new ArrayList<>();
        listImage = new ArrayList<>();

        CardView btn_view=(CardView)findViewById(R.id.view_all);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n=new Intent(MainActivity.this, Product_view.class);
                startActivity(n);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                Intent n=new Intent(MainActivity.this,Cart.class);
                startActivity(n);
            }
        });


        init();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);

        product_recycler = (RecyclerView)findViewById(R.id.recycler_view);
        apAdapter = new Rcycler_adapter(this,getData());
        product_recycler.setAdapter(apAdapter);
        LinearLayoutManager llm=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        product_recycler.setLayoutManager(llm);

        advertisement_recycle = (RecyclerView)findViewById(R.id.recycler_view_advert);
        advertHome = new Recycler_advert_home(this,getDataadvert());
        advertisement_recycle.setAdapter(advertHome);
        LinearLayoutManager llm1=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        advertisement_recycle.setLayoutManager(llm1);
        responseUsers();
    }

    private List<Model_recycle_advert> getDataadvert() {

        List<Model_recycle_advert> availablePlacesList = new ArrayList<>();
        Model_recycle_advert data;

        int apImages[] = {R.drawable.prod_2,R.drawable.prod_3,R.drawable.prod_4,R.drawable.prod_5,R.drawable.prod_6};
        String apTitle[] = {"New Black Frame Round Sunglasses For Men","Dark Blue Sunglasses present to ZAQON"
                ,"Royal Black Sunglasses for Men ","New Reading Sunslasses for men","New Blue Sunglasses for unisex"};
        String apNumber[] = {"₹ 100","₹ 140","₹ 210","₹ 870","₹ 990"};

        for (int i=0; i<apImages.length&&i<apTitle.length;i++){
            data = new Model_recycle_advert();
            data.setImages_ad(apImages[i]);
            data.setTitle_ad(apTitle[i]);
            data.setPrice_ad(apNumber[i]);
            availablePlacesList.add(data);
        }

        return availablePlacesList;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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

            Intent n=new Intent(MainActivity.this,Login.class);
            startActivity(n);
            return true;
        }
        if (id == R.id.action_profile) {

            Intent n=new Intent(MainActivity.this,Profile.class);
            startActivity(n);
            return true;
        }
        if (id == R.id.action_settings) {

            Intent n=new Intent(MainActivity.this,Cart.class);
            startActivity(n);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_man) {

        } else if (id == R.id.nav_woman) {

        } else if (id == R.id.nav_offfer) {

        } else if (id == R.id.nav_arrival) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private void init() {
        for(int i=0;i<IMAGES.length;i++)
            //Picasso.with(context).load(IMAGES[i]).fit().into();

        ImagesArray.add(IMAGES[i]);

        mPager = (ViewPager) findViewById(R.id.pager);

        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this,ImagesArray));


        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES =IMAGES.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

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


    private void responseUsers(){
//        String url = "http://localhost/zaclab/user.php";


        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.e("HA", "onResponsek:Array " );

                try {
                    JSONArray jArray = new JSONArray(response);
                    Log.e("HA", "onResponse:Array "+jArray );
                    for (int a = 0; a < jArray.length(); a++) {
                        JSONObject jObject = jArray.getJSONObject(a);
                        String id = jObject.getString("id");
                        listId.add(id);
                        String image = jObject.getString("images");
                        listImage.add(image);
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) ;
        MySingleton.getInstance(getApplication()).addToRequestque(stringRequest);
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
