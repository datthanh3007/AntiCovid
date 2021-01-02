package com.example.anticovid.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.anticovid.Data.LoadingDialog;
import com.example.anticovid.Fragment.fragmentDialogLogin;
import com.example.anticovid.Fragment.fragmentNews;
import com.example.anticovid.Fragment.fragmentStatistic;
import com.example.anticovid.Fragment.fragmentFaq;
import com.example.anticovid.Fragment.fragmentHome;
import com.example.anticovid.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView mBottomNavigationView;
    private long backPressedTime;
    LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
    private Fragment selectedFragment = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        fragmentDialogLogin fragmentDialogLogin = new fragmentDialogLogin();
        fragmentDialogLogin.show(getSupportFragmentManager(),"fragmentDialogLogin");
        LoadingDialog loadingDialog = new LoadingDialog(MainActivity.this);
        mBottomNavigationView.setOnNavigationItemSelectedListener(navListener);
        mBottomNavigationView.setSelectedItemId(R.id.tab1);
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime+2000>System.currentTimeMillis()){
            super.onBackPressed();
        }
        else {
            Toast.makeText(MainActivity.this,"Press back again to exit the application ",Toast.LENGTH_LONG).show();
        }
        backPressedTime = System.currentTimeMillis();


    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.tab1:
                            selectedFragment = new fragmentHome();
                            break;
                        case R.id.tab2:
                            selectedFragment = new fragmentNews();
                            break;
                        case R.id.tab3:
                            selectedFragment = new fragmentStatistic();
                            break;
                        case R.id.tab4:
                            selectedFragment = new fragmentFaq();
                            break;
                    }
                    if (selectedFragment != null)
                        getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.fragmentcontentner, selectedFragment)
                                .commit();
                    return true;
                }
            };

    private void init() {
        mBottomNavigationView = findViewById(R.id.BottomNavigation);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (selectedFragment != null) {
            ((fragmentHome) selectedFragment).getCurrentLocation();
        }
    }
}