package com.example.rhythm.mainactivity.mainfragments.search.recycler_view_categories;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.rhythm.R;

import java.util.ArrayList;

public class categories_adaptor extends RecyclerView.Adapter<categories_adaptor.ViewHolder> {
    Context context;
    ArrayList<model> categoriesarr;

    public categories_adaptor(Context context, ArrayList<model> categoriesarr) {
        this.context = context;
        this.categoriesarr = categoriesarr;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.categories_cards_recycler_row, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull categories_adaptor.ViewHolder holder, int position) {

        model model = categoriesarr.get(position);
        Glide.with(context).load(model.coverURL).into(holder.imgbtn);
        holder.itemView.setOnClickListener(view->{

            Intent intent = new Intent(context, category_view.class);
            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return categoriesarr.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgbtn;

        public ViewHolder(View itemView) {
            super(itemView);
            imgbtn = itemView.findViewById(R.id.cover_image_view);




        }
    }
}



