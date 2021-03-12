package com.example.myschedule;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;


public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{


    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    //Fregment

    private DashBoardFragment dashBoardFragment;
    private  AppointmentFragment appointmentFragment;
    private LeaveFragment leaveFragment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle("Expense Manager");
        setSupportActionBar(toolbar);

        bottomNavigationView=findViewById(R.id.bottomNavigationbar);
        frameLayout=findViewById(R.id.main_frame);



        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close
        ) ;

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView=findViewById(R.id.naView);
        navigationView.setNavigationItemSelectedListener(this);

        dashBoardFragment=new DashBoardFragment();
        appointmentFragment=new AppointmentFragment();
        leaveFragment=new LeaveFragment();

        setFragment(dashBoardFragment);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){

                    case R.id.dashboard:
                        setFragment(dashBoardFragment);
                        bottomNavigationView.setItemBackgroundResource(R.color.dashboard_color);
                        return true;

                    case R.id.appointment:
                        setFragment(appointmentFragment);
                        bottomNavigationView.setItemBackgroundResource(R.color.appointment_color);
                        return  true;

                    case R.id.leave:
                        setFragment(leaveFragment);
                        bottomNavigationView.setItemBackgroundResource(R.color.leave_color);
                        return true;

                    default:
                        return false;
                }
            }
        });




    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame,fragment);
        fragmentTransaction.commit();



    }


    @Override
    public void onBackPressed() {

        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);

        if (drawerLayout.isDrawerOpen(GravityCompat.END)){
            drawerLayout.closeDrawer(GravityCompat.END);
        }else {
            super.onBackPressed();
        }


    }


    public void displaySelectedListener(int itemId){

        Fragment fragment=null;

        switch (itemId){
            case R.id.dashboard:
                fragment=new DashBoardFragment();
                break;

            case R.id.appointment:
                fragment=new AppointmentFragment();
                break;

            case R.id.leave:
                fragment=new LeaveFragment();
                break;
        }

        if (fragment!=null){

            FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_frame,fragment);
            ft.commit();
        }

        DrawerLayout drawerLayout=findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        displaySelectedListener(item.getItemId());

        return true;
    }
}