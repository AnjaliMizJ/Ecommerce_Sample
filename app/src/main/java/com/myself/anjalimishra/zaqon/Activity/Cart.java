package com.myself.anjalimishra.zaqon.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.myself.anjalimishra.zaqon.Adapter.Cart_Adapter;
import com.myself.anjalimishra.zaqon.Adapter.Rcycler_adapter;
import com.myself.anjalimishra.zaqon.Model_clasa_recycle.Model_cart;
import com.myself.anjalimishra.zaqon.Model_clasa_recycle.Model_home_recycle;
import com.myself.anjalimishra.zaqon.Model_clasa_recycle.Model_recycle_advert;
import com.myself.anjalimishra.zaqon.R;

import java.util.ArrayList;
import java.util.List;

public class Cart extends AppCompatActivity {

    Cart_Adapter apAdapter;
    Toolbar toolbar;
    RecyclerView recyclerView ,product_recycler;
    String titles ,pricee;
    Button cart_button;
    Rcycler_adapter rcycler_adapter;
    int image;
    List<String>list_cart = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        toolbar=(Toolbar)findViewById(R.id.toolbar_cart);
        setSupportActionBar(toolbar);
        toolbar.setTitle(null);
        List<Model_cart> availableList = new ArrayList<>();
        cart_button=(Button)findViewById(R.id.cart_button);
        cart_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent n=new Intent(Cart.this,Payment.class);
                startActivity(n);
            }
        });

        titles=getIntent().getStringExtra("title");
        pricee=getIntent().getStringExtra("price");
        image=getIntent().getIntExtra("image",image);
        Log.e("FG", "onCreate: "+titles );
        Log.e("FG", "onCreate: "+pricee );
        //list_cart.add(titles);
        Log.e("FG", "onCreate: "+list_cart );

        product_recycler = (RecyclerView)findViewById(R.id.recyclerview_cart);
        rcycler_adapter = new Rcycler_adapter(this,getData());
        product_recycler.setAdapter(rcycler_adapter);
        LinearLayoutManager llm1=new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        product_recycler.setLayoutManager(llm1);



        recyclerView=(RecyclerView)findViewById(R.id.recyclerview_cart_add);
        apAdapter = new Cart_Adapter(this,getDataadvert());
        recyclerView.setAdapter(apAdapter);
        LinearLayoutManager llm=new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(llm);
    }
    private List<Model_cart> getDataadvert() {

        Log.e("DS", "getDataadvert: "+list_cart );
        List<Model_cart> availablePlacesList = new ArrayList<>();
        Model_cart data;
        int apImages[] = {R.drawable.prod_2,R.drawable.prod_3};
        String apTitle[] = {"New Black Frame Round Sunglasses For Men","Dark Blue Sunglasses present to ZAQON"
                };
        String apNumber[] = {"₹ 100","₹ 140"};


        for (int i=0; i<apImages.length&&i<apTitle.length;i++){
            data = new Model_cart();
            data.setImage(apImages[i]);
            data.setTitle(apTitle[i]);
            data.setPrice(apNumber[i]);
            availablePlacesList.add(data);
        }

        return availablePlacesList;
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
