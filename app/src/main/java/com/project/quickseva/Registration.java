package com.project.quickseva;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.project.quickseva.databinding.ActivityMainBinding;
import com.project.quickseva.databinding.ActivityRegistrationBinding;

public class Registration extends AppCompatActivity {
    ActivityRegistrationBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new register_amb());
        binding.bottomNavigationView2.setOnItemSelectedListener(item -> {
        if(item.getItemId()==R.id.hos_reg)
            replaceFragment(new register_hosp());
        else if (item.getItemId()==R.id.amb_reg)
            replaceFragment(new register_amb());

//        switch(item.getItemId())
//        {
//            case R.id.hos_reg:
//                replaceFragment(new register_hosp());
//            case R.id.amb_reg:
//                replaceFragment(new register_amb());
//        }
            return true;
        });

    }
    private void replaceFragment(Fragment frag)
    {
        FragmentManager fragmentmanager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentmanager.beginTransaction();
        fragmentTransaction.replace(R.id.registerpage,frag);
        fragmentTransaction.commit();
    }
    //    Side menu at top for login,registration and homepage
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