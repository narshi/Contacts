package com.reciproci.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.reciproci.contacts.adapter.ViewPagerAdapter;
import com.reciproci.contacts.fragments.FragmentCalls;
import com.reciproci.contacts.fragments.FragmentContacts;
import com.reciproci.contacts.fragments.FragmentFav;

public class MainActivity extends AppCompatActivity {


    private final int[] ICONS = {R.drawable.ic_call_foreground,R.drawable.ic_star_foreground,R.drawable.ic_person_foreground};
    private TabLayout tabLayout;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragmet(new FragmentCalls(),"Calls");
        adapter.addFragmet(new FragmentContacts(),"Contacts");
        adapter.addFragmet(new FragmentFav(),"favorites");


        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(android.R.color.white,android.R.color.white);

        for(int i=0;i<tabLayout.getTabCount();i++){
            TabLayout.Tab tab = tabLayout.getTabAt(i);
            tab.setIcon(ICONS[i]);
        }

    }

    private void askPermissions(){
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG)
                        != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CALL_LOG},1);
        }
    }
}
