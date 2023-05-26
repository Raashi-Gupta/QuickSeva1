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
import android.widget.Toast;


import com.project.quickseva.databinding.ActivityMainHospitalBinding;

public class MainActivity_Hospital extends AppCompatActivity {
    ActivityMainHospitalBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainHospitalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new hospital_home());
        binding.bottomNavigationView3.setOnItemSelectedListener(item -> {

//            switch(item.getItemId())
//            {
//                case R.id.home1:
//                    replaceFragment(new hospital_home());
//                case R.id.map1:
//                    replaceFragment(new hospital_map());
//                case R.id.details1:
//                    replaceFragment(new hospital_accdetail());
//
//
//            }
            if(item.getItemId()==R.id.home1)
            {
                replaceFragment(new hospital_home());
            } else if (item.getItemId()==R.id.map1) {
                replaceFragment(new hospital_map());
            } else if (item.getItemId()==R.id.details1) {
                replaceFragment(new hospital_accdetail());
            }
            return true;
        });
    }
    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentmanager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentmanager.beginTransaction();
        fragmentTransaction.replace(R.id.hospitalfragment1,fragment);
        fragmentTransaction.commit();
    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Toast.makeText(this,"There is no back action",Toast.LENGTH_SHORT).show();
        return;
    }


    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.logout, menu);
        return true;
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.logout)
        {
            Intent intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}