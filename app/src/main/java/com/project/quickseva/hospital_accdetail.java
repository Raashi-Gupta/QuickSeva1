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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class hospital_accdetail extends Fragment {

    TextView udname,udemail,uvno,uuname,uucontact,uutoa;
    FirebaseFirestore db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_hospital_accdetail, container, false);
        udname=v.findViewById(R.id.udname);
        udemail=v.findViewById(R.id.udemail);
        uvno=v.findViewById(R.id.uvno);
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
                               udemail.setText((CharSequence) document.get("ambulance_email"));
                               uuname.setText((CharSequence) document.get("Uname"));
//                            uucontact.setText( document.get("Ucontact"));
                            uutoa.setText((CharSequence) document.get("toa"));
                           }
                       } else {
                           Log.d("R2", "Error getting documents: ", task.getException());
                       }
                   }
               });
        db.collection("Ambulance").whereEqualTo("Email", "ambulance1@gmail.com")
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

//        DocumentReference docRef = db.collection("AccidentCases").document();
//        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>()
//        {
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot document = task.getResult();
//                    if (document.exists()) {
//                        Log.d("R1", "DocumentSnapshot data: " + document.getData());
//                        udemail.setText((CharSequence) document.get("ambulance_email"));
//                        uuname.setText((CharSequence) document.get("Uname"));
//                        uucontact.setText((CharSequence) document.get("Ucontact"));
//                        uutoa.setText((CharSequence) document.get("toa"));
//////                        Ycontact.setText((Integer) document.get("contact"));
//                    } else {
//                        Log.d("R", "No such document");
//                    }
//                } else {
//                    Log.d("R2", "get failed with ", task.getException());
//                }
//            }
//        });
        return v;
    }
}