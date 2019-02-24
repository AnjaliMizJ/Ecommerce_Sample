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
import com.myself.anjalimishra.zaqon.Model_clasa_recycle.Model_view_all;
import com.myself.anjalimishra.zaqon.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Anjali Mishra on 16/11/2017.
 */

public class Product_view_adapter extends RecyclerView.Adapter<Product_view_adapter.MyViewHolder> {
    private LayoutInflater inflater;
    Context context;
    List<Model_view_all> data = Collections.emptyList();
    String title = null;
    String price = null;


    public Product_view_adapter(Context context, List<Model_view_all> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }


    @Override
    public Product_view_adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.model_view_all, parent, false);
        Product_view_adapter.MyViewHolder holder = new Product_view_adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Product_view_adapter.MyViewHolder holder, final int position) {
        final Model_view_all current = data.get(position);
        Picasso.with(context).load(current.getImage_viewall()).fit().into(holder.propertyImage);
        holder.tvPropertyTitle.setText(current.getTitle_viewall());
        holder.tvPropertyPrice.setText(current.getPrice_view_all());
        holder.wish1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.wish1.setVisibility(View.GONE);
                holder.wish2.setVisibility(View.VISIBLE);
            }
        });
        holder.wish2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.wish2.setVisibility(View.GONE);
                holder.wish1.setVisibility(View.VISIBLE);
            }
        });
        holder.cvProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int image=current.getImage_viewall();
                String title=current.getTitle_viewall();
                String price=current.getPrice_view_all();
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

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView propertyImage;
        ImageView cart , wish1 , wish2;
        // TextView tvPropertyViews;
        // TextView tvSaveForLater;
        TextView tvPropertyTitle;
        TextView tvPropertyPrice;

        CardView cvProperty;

        public MyViewHolder(View itemView) {
            super(itemView);
            cart=(ImageView)itemView.findViewById(R.id.cart_view_all);
            cvProperty = (CardView) itemView.findViewById(R.id.cv_view_all);
            propertyImage = (ImageView) itemView.findViewById(R.id.image_view_all);
            tvPropertyTitle = (TextView) itemView.findViewById(R.id.title_view_all);
            tvPropertyPrice = (TextView) itemView.findViewById(R.id.price_view_all);
            wish1=(ImageView)itemView.findViewById(R.id.wish_view_all);
            wish2=(ImageView)itemView.findViewById(R.id.wish_view_all_added);
        }
    }

}
