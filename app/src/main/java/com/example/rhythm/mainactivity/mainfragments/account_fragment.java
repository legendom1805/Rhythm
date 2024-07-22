package com.example.rhythm.mainactivity.mainfragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rhythm.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class account_fragment extends Fragment {

    TextView unameaccview, emailaccview;

    FirebaseAuth auth;
    FirebaseFirestore fstore;
    String userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account_fragment, container, false);

        unameaccview = (TextView) view.findViewById(R.id.usernameacc);
        emailaccview = (TextView) view.findViewById(R.id.emailacc);

        auth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();

        userId = auth.getCurrentUser().getUid();

        DocumentReference documentReference = fstore.collection("user").document(userId);
        documentReference.addSnapshotListener(requireActivity(), new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                emailaccview.setText(documentSnapshot.getString("Email"));
                unameaccview.setText(documentSnapshot.getString("Username"));
            }
        });







        return view;
    }
}