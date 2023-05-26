package com.project.quickseva;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

//import com.tomtom.online.sdk.map.MapView;
//import com.tomtom.online.sdk.map.TomtomMap;
public class user_map extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_user_map, container, false);
        return  v;
    }
}