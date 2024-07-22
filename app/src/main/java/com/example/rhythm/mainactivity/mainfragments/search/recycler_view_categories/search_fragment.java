package com.example.rhythm.mainactivity.mainfragments.search.recycler_view_categories;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhythm.R;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class search_fragment extends Fragment {

    ArrayList<model> categoriesarr;
    categories_adaptor categoriesAdaptor;
    FirebaseFirestore db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_fragment, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerviewcontact);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        db = FirebaseFirestore.getInstance();
        categoriesarr = new ArrayList<model>();
        categoriesAdaptor = new categories_adaptor(getContext(),categoriesarr );
        recyclerView.setAdapter(categoriesAdaptor);
        EventChangeListner();
        return view;
    }

    private void EventChangeListner() {
        db.collection("category")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException error) {

                        if(error !=null){

                            Log.e("Firestore error",error.getMessage());
                            return;

                        }

                        for(DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                categoriesarr.add(dc.getDocument().toObject(model.class));
                            }

                            categoriesAdaptor.notifyDataSetChanged();
                        }

                    }
                });
    }
}