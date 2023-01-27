package com.example.meetior.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.meetior.Models.PostModel;
import com.example.meetior.R;
import java.util.ArrayList;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    Context context;
    ArrayList<PostModel> posts;

    public PostAdapter(Context c , ArrayList<PostModel> p)
    {
        this.context = c;
        this.posts = p;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.postrow,parent,false));
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

       // holder.posttitle.setText(posts.get(position).getPosttitle());
        Glide.with(context).load(posts.get(position).getPostcover()).into(holder.postcover);
        }
    

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        //TextView  posttitle;
        ImageView postcover;
        //RatingBar r;
        public MyViewHolder(View itemView) {
            super(itemView);
            //posttitle = (TextView) itemView.findViewById(R.id.postTitle);
            postcover = (ImageView) itemView.findViewById(R.id.postCover);

        }
        public void onClick(final int position)
        {
     
        }
    }
}
