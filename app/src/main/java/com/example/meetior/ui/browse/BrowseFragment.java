package com.example.meetior.ui.browse;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.meetior.Adapters.MyPostAdapter;
import com.example.meetior.Adapters.PostAdapter;
import com.example.meetior.Models.MyPostModel;
import com.example.meetior.Models.PostModel;
import com.example.meetior.R;
import com.example.meetior.ui.home.HomeViewModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BrowseFragment extends Fragment {

    private HomeViewModel homeViewModel;
    DatabaseReference reference;
    FirebaseDatabase firebaseDatabase;
    RecyclerView rvArticles;
    ArrayList<MyPostModel> postlist;
    MyPostAdapter adapter;
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_browse, container, false);

        rvArticles = fragmentView.findViewById(R.id.rvArticle);
        rvArticles.setHasFixedSize(true);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rvArticles.setLayoutManager(layoutManager);
        //recyclerView.setHasFixedSize(true);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Browse");
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

                    MyPostModel list = postsnap.getValue(MyPostModel.class);
                    postlist.add(list) ;

                }
                adapter = new MyPostAdapter(getActivity(),postlist);
                rvArticles.setAdapter(adapter);
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