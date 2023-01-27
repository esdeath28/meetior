package com.example.meetior.ui.trending;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetior.Adapters.PostAdapter;
import com.example.meetior.Adapters.TrendingPostAdapter;
import com.example.meetior.Models.PostModel;
import com.example.meetior.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TrendingFragment extends Fragment {

    private TrendingViewModel trendingViewModel;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    RecyclerView recyclerView;
    ArrayList<PostModel> postlist;
    TrendingPostAdapter trendingPostAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_trending, container, false);
        recyclerView  = fragmentView.findViewById(R.id.postRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Trendings");
        return  fragmentView;
    }

    @Override
    public void onStart() {
        super.onStart();

        // Get List Posts from the database

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                postlist = new ArrayList<>();
                for (DataSnapshot postsnap: dataSnapshot.getChildren()) {

                    PostModel post = postsnap.getValue(PostModel.class);
                    postlist.add(post) ;

                }

                trendingPostAdapter = new TrendingPostAdapter(getActivity(),postlist);
                recyclerView.setAdapter(trendingPostAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}