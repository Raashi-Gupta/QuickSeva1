package com.project.quickseva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.project.quickseva.databinding.ActivityMainBinding;
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

            switch(item.getItemId())
            {
                case R.id.home:
                    replaceFragment(new hospital_home());
                case R.id.map:
                    replaceFragment(new hospital_map());
                case R.id.details:
                    replaceFragment(new hospital_accdetail());


            }

            return true;
        });
    }
    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentmanager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentmanager.beginTransaction();
        fragmentTransaction.replace(R.id.hospitalfragment,fragment);
        fragmentTransaction.commit();
    }
}