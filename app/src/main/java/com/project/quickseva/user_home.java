package com.project.quickseva;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class user_home extends Fragment {



    public user_home() {
        // Required empty public constructor
    }
    FirebaseAuth auth ;
    FirebaseFirestore db ;
    String name,s_contact,location,road__accident,fire_accident,others,s_stoa;
    long contact;
    EditText uname,ucontact,ulocation;
    RadioGroup utoa;
    RadioButton aot_ra,aot_fa,uothers,stoa;
    TextView toa;
    Button button_notify;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_user_home, container, false);
        uname=v.findViewById(R.id.uname);
        ucontact=v.findViewById(R.id.ucontact);
        ulocation=v.findViewById(R.id.ulocation);
        utoa=v.findViewById(R.id.utoa);
        aot_ra=v.findViewById(R.id.aot_ra);
        aot_fa=v.findViewById(R.id.aot_fa);
        uothers=v.findViewById(R.id.others);
        button_notify=v.findViewById(R.id.button_notify);
        utoa=v.findViewById((R.id.utoa));


        button_notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stoa=v.findViewById(utoa.getCheckedRadioButtonId());
                name=uname.getText().toString();
                s_contact=ucontact.getText().toString();
                contact=Long.parseLong(s_contact);
                location=ulocation.getText().toString();
                s_stoa=stoa.getText().toString();

                boolean err=err();
                if(err) {
                    System.out.println("Registered Successfully");
                    db = FirebaseFirestore.getInstance();
//                    DocumentReference df=db.collection("AccidentsCase");
                    Map<String,Object> user=new HashMap<>();
                    user.put("Uname",name);
                    user.put("Ucontact",contact);
                    user.put("Location",location);
                    user.put("toa",s_stoa);
                    user.put("hospital_email","hospital1@gmail.com");
                    user.put("ambulance_email","ambulance1@gmail.com");
                    user.put("Status","Active");

                    db.collection("AccidentCases").document()
                            .set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            //Opem User Profile after successful Registeration
                            Toast.makeText(getActivity(),"Notified Successfully",Toast.LENGTH_LONG).show();
//                            Intent intent=new Intent(getActivity(),Login.class);
//                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                            startActivity(intent);
//                            getActivity().finish();

                        }
                    });
                }
            }
        });
        return v;
    }
    Boolean err()
    {
        Boolean r=true;
        int check=utoa.getCheckedRadioButtonId();
        System.out.println(name.length());

        if(name.isEmpty())
        {
            uname.setError("This field cannot be empty");
            r=false;
        }
        else if (name.length()<2 || name.length()>20)
        {
            if(name.length()<2)
                uname.setError("Name too short");
            else
                uname.setError("Name Too Long");
            r=false;
        }



        if (s_contact.isEmpty())
        {
            ucontact.setError("This field cannot be empty");
            r=false;
        }
        else if(!s_contact.matches("[7-9][0-9]{9}"))
        {
            ucontact.setError("Invalid Contact Number");
            r=false;
        }

        if(location.isEmpty())
        {
            ulocation.setError("This field cannot be empty");
            r=false;
        }
        else if (location.length()<2)
        {
            ulocation.setError("Invalid Location");
            r=false;
        }

        if(check<=0)
        {
            Toast.makeText(getActivity(),"Please select type of accident", Toast.LENGTH_LONG).show();
            r=false;
        }
        return r;
    }
}