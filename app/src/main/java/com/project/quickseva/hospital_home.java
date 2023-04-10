package com.project.quickseva;

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


public class hospital_home extends Fragment {
    FirebaseFirestore db=FirebaseFirestore.getInstance();

    TextView yname,uemail,uadd;
    String email;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_hospital_home, container, false);
        yname=v.findViewById(R.id.Yname);
        uemail=v.findViewById(R.id.uemail);
        uadd=v.findViewById(R.id.uadd);
//        email=getArguments().getString("email");
        DocumentReference docRef = db.collection("Hospitals").document("hospital1@gmail.com");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
        {
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("R1", "DocumentSnapshot data: " + document.getData());
                        yname.setText((CharSequence) document.get("name"));
                        uadd.setText((CharSequence) document.get("address"));
                        uemail.setText((CharSequence) document.get("email"));
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