package com.myself.anjalimishra.zaqon.Model_clasa_recycle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.myself.anjalimishra.zaqon.R;

public class Model_cart {
    String title;
    String price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    String quantity;
    int image;
}
