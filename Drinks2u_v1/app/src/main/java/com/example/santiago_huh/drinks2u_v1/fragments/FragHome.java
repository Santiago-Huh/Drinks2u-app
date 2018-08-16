package com.example.santiago_huh.drinks2u_v1.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

import com.android.volley.VolleyError;
import com.example.santiago_huh.drinks2u_v1.R;
import com.example.santiago_huh.drinks2u_v1.adapter.ProductosAdapter;
import com.example.santiago_huh.drinks2u_v1.data.ApiServiceController;
import com.example.santiago_huh.drinks2u_v1.model.Producto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class FragHome extends Fragment {

    private ImageSwitcher imageSwitcher;
    private int[] galeria = { R.drawable.banner, R.drawable.tequila, R.drawable.botanasyextras };
    private int posicion;
    private static final int DURACION = 3000;
    private Timer timer = null;
    private ArrayList<Productos> productos;
    private RecyclerView rvProductos;
    private ProductosAdapter mAdapter;
    private LinearLayoutManager mLayaoutManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v  =inflater.inflate(R.layout.fragment_frag_home, container, false);

        rvProductos = (RecyclerView) v.findViewById(R.id.mrecyclerview);
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


        inicializarDatos();
        inicializarAdaptador();

        return v;
    }

    public void inicializarDatos(){
        productos = new ArrayList<>();
        productos.add(new Productos());
    }

    public void inicializarAdaptador(){
        
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

        ApiServiceController.getInstance().getProductos(getActivity(), new ApiServiceController.ApiWebListener() {
            @Override
            public void onSuccess(String s) {
                Gson gson = new Gson();
                TypeToken<ArrayList<Producto>> ty = new TypeToken<ArrayList<Producto>>() {};
                ArrayList<Producto> arrayP = new ArrayList<>();
                //arrayP = gson.fromJson(s,new ArrayList<Producto>().getClass());
                arrayP = gson.fromJson(s,ty.getType());
                Log.d("drinksdebug", arrayP.size()+ " elementos");
                mAdapter = new ProductosAdapter(getActivity(), arrayP);
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                llm.setOrientation(LinearLayout.VERTICAL);
                rvProductos.setLayoutManager(llm);
                rvProductos.setAdapter(mAdapter);
            }

            @Override
            public void onError(VolleyError error) {

            }
        });


    }

    public void onStop(){
        super.onStop();
        if (timer != null){
            timer.cancel();
        }
    }
}
