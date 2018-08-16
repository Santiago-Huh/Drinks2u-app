package com.example.santiago_huh.drinks2u_v1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.santiago_huh.drinks2u_v1.R;
import com.example.santiago_huh.drinks2u_v1.model.Producto;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductosAdapter  extends RecyclerView.Adapter<ProductosAdapter.ViewHolder> {
    ArrayList<Producto> arrayproducs = new ArrayList<>();
    private Context con;

    public ProductosAdapter(Context con, ArrayList<Producto> arrayproducs){
        this.con = con;
        this.arrayproducs = arrayproducs;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_row, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textprod1.setText(arrayproducs.get(position).getTitulo());
        holder.textprod2.setText(arrayproducs.get(position).getTitular());
        Picasso.get()
                .load("https://tecate.com/assets/images/Logo_agegate.png")
                //.load(arrayproducs.get(position).getRuta())
                //.placeholder(R.drawable.user_placeholder)
                //.error(R.drawable.user_placeholder_error)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return arrayproducs.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textprod1, textprod2;
        Button btn1, btn2;
        ImageView img;
        public ViewHolder(View itemView) {
            super(itemView);
            textprod1 = itemView.findViewById(R.id.textprod1);
            textprod2 = itemView.findViewById(R.id.textprod2);
            btn1 = itemView.findViewById(R.id.btn1);
            btn2 = itemView.findViewById(R.id.btn2);
            img = itemView.findViewById(R.id.img);
        }
    }
}
