package Authenticating;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.moja.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;

import Authenticating.SignupLoginFragments.SignupLoginTabAdapter;

public class SignupLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_login);
        TabLayout tabLayout =(TabLayout)findViewById(R.id.tabs);
        Toolbar toolbar=findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);


        ViewPager viewPager=(ViewPager)findViewById(R.id.viewLoginandSignup);
        viewPager.setAdapter(new SignupLoginTabAdapter(getSupportFragmentManager()));

        tabLayout.setupWithViewPager(viewPager);


    }


}

