package com.project.quickseva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Login extends AppCompatActivity {

    FirebaseAuth auth ;
    FirebaseFirestore db ;
    FirebaseUser user;
    EditText uemail,upassword;
    RadioGroup radiogrp;
    RadioButton amb_bttn,hosp_bttn,selected;
    Button login;
    String email,password,s,ambulance,hospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        uemail=findViewById(R.id.userid);
        upassword=findViewById(R.id.password);
        radiogrp=findViewById(R.id.radiogrp);
        amb_bttn=findViewById(R.id.amb_bttn);
        login=findViewById(R.id.login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected=findViewById(radiogrp.getCheckedRadioButtonId());
                s=selected.getText().toString();
                email=uemail.getText().toString();
                password=upassword.getText().toString();
                boolean err=err();
                if(err)
                {

                    login();

                }
            }
        });
    }

    void login()
    {
        auth= FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            user = auth.getCurrentUser();
                            if(s.matches("Ambulance"))
                            {
//                                        DocumentReference df=db.collection("Ambualnce").document(Uid);
                                db.collection("Ambulance").whereEqualTo("Email",email).limit(1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        try{
                                            if(task.isSuccessful())
                                            {
//                                                    updateUI(user,"Ambulance");
                                                QuerySnapshot ds=task.getResult();
                                                if(!ds.isEmpty())
                                                {
                                                    updateUI(user,"Ambulance",email);
                                                }
                                                else{
                                                    Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                                }
                                            }}
                                        catch (Exception e)
                                        {
                                            Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                            else if(s.matches("Hospital"))
                            {
//                                        DocumentReference df=db.collection("Hospitals").document(Uid);
                                db.collection("Hospitals").whereEqualTo("email",email).limit(1).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if(task.isSuccessful())
                                        {
//                                                    DocumentSnapshot ds=task.getResult();
                                            QuerySnapshot ds=task.getResult();
                                            if(!ds.isEmpty())
                                            {
                                                updateUI(user,"Hospital",email);
                                            }
                                            else{
                                                Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                });
                            }
                        }
                        else
                        {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    public void updateUI(FirebaseUser account, String s,String email){

        if(account != null) {
            if (s == "Ambulance") {
                Toast.makeText(this, "You Signed In successfully", Toast.LENGTH_LONG).show();
                Bundle bundle=new Bundle();
                bundle.putString("email",email);
                hospital_home obj=new hospital_home();
                obj.setArguments(bundle);
                Intent intent=new Intent(this, MainActivity_Ambulance.class);
//                intent.putExtra("email",email);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


            }
            else if(s == "Hospital")
            {
                Toast.makeText(this, "You Signed In successfully", Toast.LENGTH_LONG).show();
                Bundle bundle=new Bundle();
                bundle.putString("email",email);
                ambulance_home obj=new ambulance_home();
                obj.setArguments(bundle);
                Intent intent =new Intent(this, MainActivity_Hospital.class);
//                intent.putExtra("email",email);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);


            }
        }else {
            Toast.makeText(this,"You Didn't signed in",Toast.LENGTH_LONG).show();
        }

    }
    boolean err()
    {

        int check=radiogrp.getCheckedRadioButtonId();
        boolean r=true;
        if(email.isEmpty())
        {
            uemail.setError("Field Cannot be Empty");
            r=false;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            uemail.setError("Invalid Email");
            r=false;
        }

        if(password.isEmpty())
        {
            upassword.setError("Field Cannot be Empty");
            r=false;
        }
//        else if(password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{4,}$"))
//        {
//            upassword.setError("Invail Password ");
//        }

        if(check<=0)
        {
            Toast.makeText(this,"Please select user Type", Toast.LENGTH_SHORT).show();
            r=false;
        }
        return r;
    }













    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sidemenu_log_reg_main, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.login)
        {
            Intent intent=new Intent(this,Login.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.register)
        {
            Intent intent=new Intent(this,Registration.class);
            startActivity(intent);
            return true;
        }
        else if(id==R.id.home2)
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}