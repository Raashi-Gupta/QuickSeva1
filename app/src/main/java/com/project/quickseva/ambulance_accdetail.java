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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class ambulance_accdetail extends Fragment {


    FirebaseFirestore db;
    TextView uhname,uhemail,uadd,uuname,uucontact,uutoa;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_ambulance_accdetail, container, false);
        uhname=v.findViewById(R.id.uhname);
        uhemail=v.findViewById(R.id.uhemail);
        uadd=v.findViewById(R.id.uadd);
        uuname=v.findViewById(R.id.uuname);
        uucontact=v.findViewById(R.id.uucontact);
        uutoa=v.findViewById(R.id.uutoa);
        db=FirebaseFirestore.getInstance();
        db.collection("AccidentCases").whereEqualTo("hospital_email", "hospital1@gmail.com")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                               Log.d("R1", document.getId() + " => " + document.get("Uname"));
                                uhemail.setText((CharSequence) document.get("ambulance_email"));
                                uuname.setText((CharSequence) document.get("Uname"));
//                            uucontact.setText( document.get("Ucontact"));
                                uutoa.setText((CharSequence) document.get("toa"));
                            }
                        } else {
                            Log.d("R2", "Error getting documents: ", task.getException());
                        }
                    }
                });
        db.collection("Hospitals").whereEqualTo("email", "hospital1@gmail.com")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("R1", document.getId() + " => " + document.get("Name"));
                                uhname.setText((CharSequence) document.get("name"));
                                uadd.setText((CharSequence) document.get("address"));
//                            uucontact.setText( document.get("Ucontact"));
//                                uutoa.setText((CharSequence) document.get(""));
                            }
                        } else {
                            Log.d("R2", "Error getting documents: ", task.getException());
                        }
                    }
                });
        return v;
    }
}