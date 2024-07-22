package com.example.rhythm.mainactivity.mainfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.rhythm.R;
import com.example.rhythm.mainactivity.mainfragments.search.recycler_view_categories.categories_adaptor;
import com.example.rhythm.mainactivity.mainfragments.search.recycler_view_categories.model;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class   home_fragment extends Fragment {

    ArrayList<model> categoriesarr;
    categories_adaptor categoriesAdaptor;
    FirebaseFirestore db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_fragment, container, false);
        return view;
    }
}