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


public class user_accdetail extends Fragment {
    TextView udname, udemail, uvno, uhname, uhemail, uadd, uutoa;
    FirebaseFirestore db;
    String uname, hemail, aemail;
    long contact;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_user_accdetail, container, false);
//        uname=;
//        contact=;
        udname = v.findViewById(R.id.udname);
        udemail = v.findViewById(R.id.udemail);
        uvno = v.findViewById(R.id.uvno);
        uhname = v.findViewById(R.id.uhname);
        uhemail = v.findViewById(R.id.uhemail);
        uadd = v.findViewById(R.id.uadd);
        uutoa = v.findViewById(R.id.uutoa);
        db = FirebaseFirestore.getInstance();
        if(getArguments() ==null && getArguments().isEmpty())
        {
            uname="";
            contact=0;
        }
        else
        {
            uname=getArguments().getString("uname");
            contact=getArguments().getLong("contact");
        }
//        if (!(getArguments().getString("uname")).matches("NA") && (getArguments().getLong("contact"))!=0) {
//            uname = getArguments().getString("uname");
//            contact = getArguments().getLong("contact");
//            System.out.println(uname+"   "+contact);
//        uname="raashi";
//        contact=002426;
        db.collection("AccidentCases").whereEqualTo("Uname", "raashi").whereEqualTo("Ucontact", "8935002426").whereEqualTo("Status", "Active")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
//                               Log.d("R1", document.getId() + " => " + document.get("Uname"));
                                uhemail.setText((CharSequence) document.get("hospital_email"));
                                udemail.setText((CharSequence) document.get("ambulance_email"));
//                            uucontact.setText( document.get("Ucontact"));
                                uutoa.setText((CharSequence) document.get("toa"));
                                hemail = uhemail.getText().toString();
                                aemail = udemail.getText().toString();
                                db.collection("Hospitals").whereEqualTo("email", hemail)
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
                                db.collection("Ambulance").whereEqualTo("Email", udemail)
                                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                            @Override
                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                if (task.isSuccessful()) {
                                                    for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d("R1", document.getId() + " => " + document.get("Name"));
                                                        udname.setText((CharSequence) document.get("Name"));
                                                        uvno.setText((CharSequence) document.get("Vehicle Number"));
//                            uucontact.setText( document.get("Ucontact"));
                                                        uutoa.setText((CharSequence) document.get("toa"));
                                                    }
                                                } else {
                                                    Log.d("R2", "Error getting documents: ", task.getException());
                                                }
                                            }
                                        });

                            }
                        } else {
                            Log.d("R2", "Error getting documents: ", task.getException());
                        }
                    }
                });

//        }


            return v;

    }
}