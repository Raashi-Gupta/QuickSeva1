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

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new user_home());
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if(item.getItemId()==R.id.home)
            {
                replaceFragment(new user_home());
            }
            else if (item.getItemId()==R.id.map)
            {
                replaceFragment(new user_map());
            }
            else if(item.getItemId()==R.id.details)
            {
                replaceFragment(new user_accdetail());
            }
            else if(item.getItemId()==R.id.details)
            {
                replaceFragment(new user_firstaid());
            }
//            switch(item.getItemId())
//            {
//                case R.id.home:
//                    replaceFragment(new user_home());
//                case R.id.map:
//                    replaceFragment(new user_map());
//                case R.id.details:
//                    replaceFragment(new user_accdetail());
//                case R.id.first_aid:
//                    replaceFragment(new user_firstaid());
//            }

            return true;
        });
    }
    private void replaceFragment(Fragment fragment)
    {
        FragmentManager fragmentmanager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentmanager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout,fragment);
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

