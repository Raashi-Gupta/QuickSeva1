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
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.HashMap;
import java.util.Map;


public class register_amb extends Fragment {

    FirebaseFirestore db ;
    FirebaseAuth auth ;
    
    EditText udname, udconin, uvnum, upassword, ucpassword,uemail;
    String dname, dconin, vnum, password, cpassword,email,userID;
    Button register;
    double con;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_register_amb, container, false);
        udname=v.findViewById(R.id.dnamein);
        udconin=v.findViewById(R.id.dconin);
        uemail=v.findViewById(R.id.userin);
        uvnum=v.findViewById(R.id.vnumin);
        upassword=v.findViewById(R.id.passin);
        ucpassword=v.findViewById(R.id.passcin);
        register=v.findViewById(R.id.sub);
        
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dname=udname.getText().toString();
                dconin=udconin.getText().toString();
                email=uemail.getText().toString();
                vnum=uvnum.getText().toString();
                password=upassword.getText().toString();
                cpassword=ucpassword.getText().toString();
                boolean err=err();
                if(err)
                {
                    System.out.println("Registered Successfully");
                    con=Double.parseDouble(dconin);
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
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser firebaseuser=auth.getCurrentUser();
                    userID=auth.getCurrentUser().getUid();
                    DocumentReference df=db.collection("Ambulance").document(email);
                    Map<String,Object> user=new HashMap<>();
                    user.put("contact",con);
                    user.put("Email",email);
                    user.put("Name",dname);
                    user.put("Vehicle Number",vnum);
                    df.set(user)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    //Opem User Profile after successful Registeration
                                    Toast.makeText(getActivity(),"Registered Successfully",Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(getActivity(),Login.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    getActivity().finish();

                                }
                            });
                }
                else
                {
                    Toast.makeText(getActivity(),"Email id already registered",Toast.LENGTH_LONG).show();
                }
            }
        });
    }





    boolean err()
    {
        System.out.println(password+"  "+cpassword);
        boolean r=true;
        if(dname.isEmpty())
        {
            udname.setError("This field cannot be empty");
            r=false;
        }
        else if (dname.length()<2 || dname.length()>20)
        {
            if(dname.length()<2)
                udname.setError("Name too short");
            else
                udname.setError("Name Too Long");
            r=false;
        }

        if(dconin.isEmpty())
        {
            udconin.setError("This field cannot be empty");
            r=false;
        }
        else if(!dconin.matches("[7,8,9][0-9]{9}"))
        {
            udconin.setError("Invalid Contact Number");
        }

        if(email.isEmpty())
        {
            uemail.setError("This field cannot be empty");
            r=false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            uemail.setError("Invalid Email");
            r=false;
        }



        if(vnum.isEmpty())
        {
            uvnum.setError("This field cannot be empty");
            r=false;
        }
        else if(!vnum.matches("^[A-Z,a-z]{2}+[0-9]{2}+[A-Z,a-z]{2}+[0-9]{4}$"))
        {
            uvnum.setError("Invalid Vehicle Number");
        }

        if(password.isEmpty())
        {
            upassword.setError("This field cannot be empty");
            r=false;
        }
        if(cpassword.isEmpty())
        {
            ucpassword.setError("This field cannot be empty");
            r=false;
        }
        else if(!password.matches(cpassword))
        {
            ucpassword.setError("Password doesnt match with confirm password");
            r=false;
        }
        return r;
    }
}