package com.project.quickseva;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.project.quickseva.databinding.ActivityMainAmbulanceBinding;
import com.project.quickseva.databinding.ActivityMainHospitalBinding;

public class MainActivity_Ambulance extends AppCompatActivity {
    ActivityMainAmbulanceBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityMainAmbulanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ambulance_home());
        binding.bottomNavigationView3.setOnItemSelectedListener(item -> {

            switch(item.getItemId())
            {
                case R.id.home:
                    replaceFragment(new ambulance_home());
                case R.id.map:
                    replaceFragment(new ambulance_map());
                case R.id.details:
                    replaceFragment(new ambulance_accdetail());


            }

            return true;
        });
    }
    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentmanager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentmanager.beginTransaction();
        fragmentTransaction.replace(R.id.ambulancefragment,fragment);
        fragmentTransaction.commit();
    }
    }
