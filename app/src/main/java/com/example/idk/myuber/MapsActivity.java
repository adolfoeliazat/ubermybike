package com.example.idk.myuber;

import android.os.Build;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


public class MapsActivity extends Fragment {


    private static GoogleMap mMap = null;
    private static Marker position = null;
    private SupportMapFragment fragment;


    public  static View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        if(container == null)
            return null;
        view = (FrameLayout) inflater.inflate(R.layout.activity_maps, container, false);
        SetupMapIfNeeded();
        return view;
    }

    public void SetupMapIfNeeded()
    {
        if(mMap == null)
        {
            mMap = getMapFragment().getMap();
            if(mMap != null)
                SetupMap();

        }

    }
    private SupportMapFragment getMapFragment() {
        FragmentManager fm = null;

        Log.d("DEBUG", "sdk: " + Build.VERSION.SDK_INT);
        Log.d("DEBUG", "release: " + Build.VERSION.RELEASE);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Log.d("DEUG", "using getFragmentManager");
            fm = getFragmentManager();
        } else {
            Log.d("DEBUG", "using getChildFragmentManager");
            fm = getChildFragmentManager();
        }

        if(fm == null)
            Log.d("DEBUG", "fm is null");
        else
            Log.d("DEBUG", "fm is not null");

        SupportMapFragment t = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);


        return t;
}

    private static void SetupMap()
    {
        position = mMap.addMarker(new MarkerOptions().position(new LatLng(36.999 , -122)).title("Origin"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(37.7833,-122.4167), 8));

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(mMap != null) {
            Main_Activity.fragmentManager.beginTransaction().remove(Main_Activity.fragmentManager.findFragmentById(R.id.map)).commit();
            mMap = null;
        }
    }
}
