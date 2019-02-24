package com.myself.anjalimishra.zaqon.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.myself.anjalimishra.zaqon.R;

/**
 * Created by Anjali Mishra on 15/11/2017.
 */

public class Description extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_description, container, false);

        return rootView;
    }
}
