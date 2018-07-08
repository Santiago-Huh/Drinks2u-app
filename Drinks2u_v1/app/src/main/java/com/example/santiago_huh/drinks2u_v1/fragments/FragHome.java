package com.example.santiago_huh.drinks2u_v1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.example.santiago_huh.drinks2u_v1.R;

import java.util.DuplicateFormatFlagsException;
import java.util.Timer;
import java.util.TimerTask;


public class FragHome extends Fragment {

    private ImageSwitcher imageSwitcher;
    private int[] galeria = { R.drawable.banner, R.drawable.tequila, R.drawable.botanasyextras };
    private int posicion;
    private static final int DURACION = 3000;
    private Timer timer = null;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v  =inflater.inflate(R.layout.fragment_frag_home, container, false);
        imageSwitcher = (ImageSwitcher) v.findViewById(R.id.slide);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory()
        {
            public View makeView()
            {
                ImageView imageView = new ImageView(getActivity().getApplicationContext());
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

                return imageView;
            }
        });
        Animation fadeIn = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.fade_out);
        imageSwitcher.setInAnimation(fadeOut);
        imageSwitcher.setOutAnimation(fadeIn);



        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                if (getActivity() == null)return;
                getActivity().runOnUiThread(new Runnable()
                {
                    public void run()
                    {
                        imageSwitcher.setImageResource(galeria[posicion]);
                        posicion++;
                        if (posicion == galeria.length)
                            posicion = 0;
                    }
                });
            }
        },0, DURACION);
    }

    public void onStop(){
        super.onStop();
        if (timer != null){
            timer.cancel();
        }
    }
}
