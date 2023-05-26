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

//            switch(item.getItemId())
//            {
//                case R.id.home1:
//                    replaceFragment(new ambulance_home());
//                case R.id.map1:
//                    replaceFragment(new ambulance_map());
//                case R.id.details1:
//                    replaceFragment(new ambulance_accdetail());
//
//
//            }
            if(item.getItemId()==R.id.home1)
            {
                replaceFragment(new ambulance_home());
            } else if (item.getItemId()==R.id.map1) {
                replaceFragment(new ambulance_map());
            } else if (item.getItemId()==R.id.details1) {
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
