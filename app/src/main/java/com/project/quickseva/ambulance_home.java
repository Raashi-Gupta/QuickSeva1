package com.project.quickseva;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class ambulance_home extends Fragment {
    FirebaseFirestore db=FirebaseFirestore.getInstance();
    TextView yname,uemail,uvno;
    String email;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_ambulance_home, container, false);
        yname=v.findViewById(R.id.Aname);
        uemail=v.findViewById(R.id.uemail);
        uvno=v.findViewById(R.id.uvno);
//        email=getArguments().getString("email");

        DocumentReference docRef = db.collection("Ambulance").document("ambulance1@gmail.com");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
        {
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("R1", "DocumentSnapshot data: " + document.getData());
                        yname.setText((CharSequence) document.get("Name"));
                        uvno.setText((CharSequence) document.get("Vehicle Number"));
                        uemail.setText((CharSequence) document.get("Email"));
//                        Ycontact.setText((Integer) document.get("contact"));
                    } else {
                        Log.d("R", "No such document");
                    }
                } else {
                    Log.d("R2", "get failed with ", task.getException());
                }
            }
        });
        return v;
    }
}