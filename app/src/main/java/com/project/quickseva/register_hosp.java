package com.project.quickseva;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class register_hosp extends Fragment {

    FirebaseFirestore db ;
    FirebaseAuth auth ;
    EditText hnamein,hconin,uidin,addhin,latin,lonin,emailin,passhin,passhcin;
    RadioGroup toh;
    CheckBox checkBox1,checkBox2,checkBox3,checkBox4,checkBox5;
    Button reg;
    String hname,hcon,uid,addh,lat,lon,email,passh,passhc,userID,type_of_hospital;
    Long contact;
    int iuid;
    double ilat,ilon;
    List<String> healthcare=new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_register_hosp, container, false);
        hnamein=v.findViewById(R.id.hnamein);
        hconin=v.findViewById(R.id.hconin);
        uidin=v.findViewById(R.id.uidin);
        addhin=v.findViewById(R.id.addhin);
        latin=v.findViewById(R.id.latin);
        lonin=v.findViewById(R.id.lonin);
        emailin=v.findViewById(R.id.emailin);
        passhin=v.findViewById(R.id.passhin);
        passhcin=v.findViewById(R.id.passhcin);
        checkBox1=v.findViewById(R.id.checkBox1);
        checkBox2=v.findViewById(R.id.checkBox2);
        checkBox3=v.findViewById(R.id.checkBox3);
        checkBox4=v.findViewById(R.id.checkBox4);
        checkBox5=v.findViewById(R.id.checkBox5);
        toh=v.findViewById(R.id.radiogrph);
        reg=v.findViewById(R.id.sub);
        reg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v1)
            {
                boolean err=err();
                if(err)
                {
//                    int id=toh.getCheckedRadioButtonId();
//                    type_of_hospital=v.findViewById(id);
                    contact=Long.parseLong(hcon);
                    iuid=Integer.parseInt(uid);
                    ilat=Double.parseDouble(lat);
                    ilon=Double.parseDouble(lon);
                    System.out.println("Registered Successfully");
                    register();
                }
            }
        });

        return v;
    }

    void register()
    {
        auth= FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        auth.createUserWithEmailAndPassword(email,passh).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
//                    Toast.makeText(getActivity(),"Registered Successfully",Toast.LENGTH_LONG).show();

                    FirebaseUser firebaseuser=auth.getCurrentUser();
//                    firebaseuser.sendEmailVerification();
                    userID=auth.getCurrentUser().getUid();
                    DocumentReference df=db.collection("Hospitals").document(email);
                    Map<String,Object> user=new HashMap<>();
                    user.put("address",addh);
                    user.put("contact_number",contact);
                    user.put("email",email);
                    user.put("name",hname);
                    user.put("hospital_type",type_of_hospital);
                    user.put("health_care_type",healthcare);
                    user.put("Location", new GeoPoint(ilat,ilon));
                    df.set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(getActivity(),"Registered Successfully",Toast.LENGTH_LONG).show();
                                    //Opem User Profi;e after successful Registeration
                                    Intent intent=new Intent(getActivity(),Login.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            });

                }
                else
                {
                    Toast.makeText(getActivity(),"Emailid already registered",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    boolean err()
    {
        boolean r=true;
        int check=toh.getCheckedRadioButtonId();

        if(checkBox1.isChecked())
        {
            healthcare.add(checkBox1.getText().toString());
        }
        if(checkBox2.isChecked())
        {
            healthcare.add(checkBox2.getText().toString());
        }
        if(checkBox3.isChecked())
        {
            healthcare.add(checkBox3.getText().toString());
        }
        if(checkBox4.isChecked())
        {
            healthcare.add(checkBox4.getText().toString());

        }
        if(checkBox5.isChecked())
        {
            healthcare.add(checkBox5.getText().toString());
        }
//        String s_toh=srbtn.getText().toString();

        hname=hnamein.getText().toString();
        hcon=hconin.getText().toString();

        uid=uidin.getText().toString();

        lat=latin.getText().toString();
        lon=lonin.getText().toString();

        addh=addhin.getText().toString();
        email=emailin.getText().toString();
        passh=passhin.getText().toString();
        passhc=passhcin.getText().toString();

        if(hname.isEmpty())
        {
            hnamein.setError("This field cannot be empty\n Hello");
            r=false;
        }
        else if (hname.length()<2 || hname.length()>20)
        {
            if(hname.length()<2)
                hnamein.setError("Name too short");
            else
                hnamein.setError("Name Too Long");
            r=false;
        }


        if(hcon.isEmpty())
        {
            hconin.setError("This field cannot be empty");
            r=false;
        }
        else if(!hcon.matches("[7,8,9][0-9]{9}"))
        {
            hconin.setError("Invalid Contact Number");
            r=false;
        }



        if(uid.isEmpty())
        {
            uidin.setError("This field cannot be empty");
            r=false;
        }
        else if (!uid.matches("[0-9]{6}"))
        {
            uidin.setError("Invalid UID number");
            r=false;
        }

        if(addh.isEmpty())
        {
            addhin.setError("This field cannot be empty");
            r=false;
        }


        if (lat.isEmpty())
        {
            latin.setError("This field cannot be empty");
            r=false;

        }
//        else if(lat.matches("[0-9]{2}+[.]+[0-9]{2}"))
//        {
//            latin.setError("Invalid lat");
//            r=false;
//        }
        if (lon.isEmpty())
        {
            lonin.setError("This field cannot be empty");
            r=false;
        }
//        else if(lat.matches("[0-9]{2}+[.]+[0-9]{2}"))
//        {
//            lonin.setError("Invalid lat");
//            r=false;
//        }
        if(check<=0)
        {
            Toast.makeText(getActivity(),"Please select type of hospital",Toast.LENGTH_SHORT).show();
        }


        if(healthcare.isEmpty())
        {
            Toast.makeText(getActivity(),"Please select atleast one health care type",Toast.LENGTH_SHORT).show();
        }

        if(email.isEmpty())
        {
            emailin.setError("This field cannot be empty");
            r=false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            emailin.setError("Invalid Email");
            r=false;
        }
        if(passh.isEmpty())
        {
            passhin.setError("This field cannot be empty");
            r=false;
        }

        if(passhc.isEmpty())
        {
            passhcin.setError("This field cannot be empty");
            r=false;
        }
        else if(!passh.matches(passhc))
        {
            addhin.setError("Password and Confirm Password doen't match");
            r=false;
        }

        return r;
    }
}