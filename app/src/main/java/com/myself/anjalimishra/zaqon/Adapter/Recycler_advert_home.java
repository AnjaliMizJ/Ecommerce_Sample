package com.myself.anjalimishra.zaqon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.myself.anjalimishra.zaqon.Activity.Single_view_product;
import com.myself.anjalimishra.zaqon.Model_clasa_recycle.Model_home_recycle;
import com.myself.anjalimishra.zaqon.Model_clasa_recycle.Model_recycle_advert;
import com.myself.anjalimishra.zaqon.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by Anjali Mishra on 08/11/2017.
 */

public class Recycler_advert_home extends RecyclerView.Adapter<Recycler_advert_home.MyViewHolder> {
    private LayoutInflater inflater;
    Context context;
    List<Model_recycle_advert> data= Collections.emptyList();
    int image_ad;

    public Recycler_advert_home(Context context, List<Model_recycle_advert> data) {
        inflater= LayoutInflater.from(context);
        this.data=data;
        this.context=context;
    }



    @Override
    public Recycler_advert_home.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.activity_model_recycle_advert, parent, false);
        Recycler_advert_home.MyViewHolder holder=new Recycler_advert_home.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Recycler_advert_home.MyViewHolder holder, final int position) {
        final Model_recycle_advert current=data.get(position);
        Picasso.with(context).load(current.getImages_ad()).fit().into(holder.propertyImage);
         holder.price.setText(current.getPrice_ad());
        holder.title.setText(current.getTitle_ad());
       holder.cvProperty.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               int image=current.getImages_ad();
               String title=current.getTitle_ad();
               String price=current.getPrice_ad();
               Intent singleView = new Intent(context, Single_view_product.class);

               singleView.putExtra("image",image);
               singleView.putExtra("title",title);
               singleView.putExtra("price",price);

               context.startActivity(singleView);
            }
    });
//        holder.cvProperty.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ARPojo click = data.get(position);
//                warden = click.getWarden();
//                location = click.getLocation();
//                availability = click.getAvailability();
//                price = click.getPrice();
//                room_number = click.getRoomsNumber();
//                room_size = "855 sqft";
//                room_type = click.getType();
//                room_for = "Boys/Girls";
//                room_image = "image";
//
//
//                SRDatabase entry = new SRDatabase(context);
//                entry.open();
//                entry.deleteEntry();
//                entry.replaceOrThrow(warden,location,availability,price,room_number,room_type,room_image,room_size,room_for);
//                entry.close();
//
//                Intent i = new Intent(context, SingleRoomViewActivity.class);
//                context.startActivity(i);
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView propertyImage ;
        CardView cvProperty;
        TextView title ,price;

        public MyViewHolder(View itemView) {
            super(itemView);
            cvProperty=(CardView)itemView.findViewById(R.id.cv_product_advert);
            title=(TextView)itemView.findViewById(R.id.title_advert);
            price=(TextView)itemView.findViewById(R.id.price_advert);
            propertyImage = (ImageView)itemView.findViewById(R.id.image_product_advert);
              }
    }
}
