package com.myself.anjalimishra.zaqon.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.myself.anjalimishra.zaqon.Activity.Cart;
import com.myself.anjalimishra.zaqon.Activity.Single_view_product;
import com.myself.anjalimishra.zaqon.Model_clasa_recycle.Model_cart;
import com.myself.anjalimishra.zaqon.Model_clasa_recycle.Model_home_recycle;
import com.myself.anjalimishra.zaqon.R;
import com.squareup.picasso.Picasso;

import java.util.Collections;
import java.util.List;

/**
 * Created by Anjali Mishra on 17/11/2017.
 */

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.MyViewHolder> {
    private LayoutInflater inflater;
    Context context;
    List<Model_cart> data = Collections.emptyList();
    String title = null;
    String price = null;


    public Cart_Adapter(Context context, List<Model_cart> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
        this.context = context;
    }


    @Override
    public Cart_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_model_cart, parent, false);
        Cart_Adapter.MyViewHolder holder = new Cart_Adapter.MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final Cart_Adapter.MyViewHolder holder, final int position) {
        final Model_cart current = data.get(position);
        Picasso.with(context).load(current.getImage()).fit().into(holder.propertyImage);
        holder.tvPropertyTitle.setText(current.getTitle());
        holder.tvPropertyPrice.setText(current.getPrice());

//        holder.cvProperty.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int image=current.getImage();
//                String title=current.getTitle();
//                String price=current.getPrice();
//                Intent singleView = new Intent(context, Single_view_product.class);
//
//                singleView.putExtra("image",image);
//                singleView.putExtra("title",title);
//                singleView.putExtra("price",price);
//
//                context.startActivity(singleView);
//            }
//        });
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
        // TextView tvPropertyViews;
        // TextView tvSaveForLater;
        TextView tvPropertyTitle;
        TextView tvPropertyPrice;

        CardView cvProperty;

        public MyViewHolder(View itemView) {
            super(itemView);
            //cvProperty = (CardView) itemView.findViewById(R.id.cv_product);
            propertyImage = (ImageView) itemView.findViewById(R.id.image_cart);
            tvPropertyTitle = (TextView) itemView.findViewById(R.id.title_cart_model);
            tvPropertyPrice = (TextView) itemView.findViewById(R.id.price_model_cart);
        }
    }
}
