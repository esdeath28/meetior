package com.example.meetior.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetior.Models.MyPostModel;
import com.example.meetior.Models.PostModel;
import com.example.meetior.R;

import java.util.ArrayList;

public class MyPostAdapter extends RecyclerView.Adapter<MyPostAdapter.ViewHolder> {
    ArrayList<MyPostModel> list;
    Context context;

    public MyPostAdapter(Context c , ArrayList<MyPostModel> p)
    {
        this.context = c;
        this.list = p;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.view_myposts, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.articletitle.setText(list.get(position).getArticletitle());
        holder.articledes.setText(list.get(position).getArticledes());
        holder.articleauthor.setText(list.get(position).getArticleauthor());
        holder.articletopic.setText(list.get(position).getArticletopic());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //public ImageView imgArticle;
        public TextView articledes,articletitle,articleauthor,articletopic;
        public Button btReadMore;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            articledes     = itemView.findViewById(R.id.tarticleDesc);
            articletitle    = itemView.findViewById(R.id.articleTitle);
            articletopic = itemView.findViewById(R.id.articleTopic);
            articleauthor = itemView.findViewById(R.id.articleAuthor);
            btReadMore  = itemView.findViewById(R.id.btReadMore);
        }
    }
}
