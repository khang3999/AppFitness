package com.example.homefitness.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.homefitness.R;
import com.example.homefitness.databases.MyDatabase;
import com.example.homefitness.databinding.AppDrawerLayoutBinding;
import com.example.homefitness.databinding.MyListviewLayoutBinding;
import com.example.homefitness.fragments.AbstractFragment;
import com.example.homefitness.fragments.CalculateFragment;
import com.example.homefitness.fragments.ExerciseFragment;
import com.example.homefitness.fragments.HomeFragment;
import com.example.homefitness.fragments.ProfileFragment;
import com.example.homefitness.models.Account;
import com.google.android.material.navigation.NavigationView;

public class AppDrawerActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {

    private  int currentFragment = 0;
    private AbstractFragment fragment;
    private AppDrawerLayoutBinding binding;

    private FragmentTransaction transaction; // doi tuong dung de dan fragment vao khung man hinh

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    private View preView;
    private Account account;
    private MyDatabase myDatabase;
    private boolean doubleBackToExitPressedOnce = false;

    private static final long BACK_PRESS_DELAY = 2000; // Thời gian giữa 2 lần nhấn nút back
    private long backPressTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // dieu kien man hinh app_drawer_layout
        setContentView(R.layout.app_drawer_layout);
myDatabase = new MyDatabase(this);
        account = myDatabase.getAccount().get(0);
        //Khoi tao binding
        binding = AppDrawerLayoutBinding.inflate(getLayoutInflater());
        // Gán view cho binding
        setContentView(binding.getRoot());


        // Khoi tạo lan dau
        fragment = new HomeFragment();
        updateUI();

        // khoi tao cho drawer
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(this,binding.appDrawerLayout, R.string.openDrawer, R.string.closeDrawer);
        binding.appDrawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(this);

        // List view slide bar


        // Khoi tao background button mac dinh khi vao app trang home
        binding.mainScreen.findViewById(R.id.btnHome).setBackgroundColor(getColor(R.color.selected));
        preView = binding.mainScreen.findViewById(R.id.btnHome);
        // Bat su kien
        // Btn home
        binding.mainScreen.findViewById(R.id.btnHome).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Thay doi currentQuestion theo man hinh tiep theo
                if (currentFragment != 0){
                    // đoi mau
                    binding.mainScreen.findViewById(R.id.btnHome).setBackgroundColor(getColor(R.color.selected));
                    // Set mau mac dinh cua btn cho nut duoc chon truoc do
                    preView.setBackgroundColor(getColor(R.color.background_nav));
                    // Set lai preview tại nút dang chon
                    preView = view;
                    currentFragment = 0;
                }
                //Update UI
                updateUI();
            }
        });

        binding.mainScreen.findViewById(R.id.btnWorkout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Thay doi currentQuestion theo man hinh tiep theo
                if (currentFragment != 1){
                    // đoi mau
                    binding.mainScreen.findViewById(R.id.btnWorkout).setBackgroundColor(getColor(R.color.selected));
                    // Set mau mac dinh cua btn cho nut duoc chon truoc do
                    preView.setBackgroundColor(getColor(R.color.background_nav));
                    // Set lai preview tại nút dang chon
                    preView = view;
                    currentFragment = 1;
                }
                //Update UI
                updateUI();
            }
        });

        binding.mainScreen.findViewById(R.id.btnBMI).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Thay doi currentQuestion theo man hinh tiep theo
                if (currentFragment != 2){
                    // đoi mau
                    binding.mainScreen.findViewById(R.id.btnBMI).setBackgroundColor(getColor(R.color.selected));
                    // Set mau mac dinh cua btn cho nut duoc chon truoc do
                    preView.setBackgroundColor(getColor(R.color.background_nav));
                    // Set lai preview tại nút dang chon
                    preView = view;
                    currentFragment = 2;
                }
                //Update UI
                updateUI();
            }
        });

        binding.mainScreen.findViewById(R.id.btnProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Thay doi currentQuestion theo man hinh tiep theo
                if (currentFragment != 3){
                    // đoi mau
                    binding.mainScreen.findViewById(R.id.btnProfile).setBackgroundColor(getColor(R.color.selected));
                    // Set mau mac dinh cua btn cho nut duoc chon truoc do
                    preView.setBackgroundColor(getColor(R.color.background_nav));
                    // Set lai preview tại nút dang chon
                    preView = view;
                    currentFragment = 3;
                }
                //Update UI
                updateUI();
            }
        });

    }

    private  void updateUI(){
        // Set title
        if (currentFragment == 0){
            setTitle("Home");
        }else if (currentFragment == 1){
            setTitle("All exercise");
        }else if (currentFragment == 2){
            setTitle("BMI");
        }else if (currentFragment == 3){
            setTitle("Profile");
        }

        // Neu da ton tai thi tai su dung
        if (getSupportFragmentManager().findFragmentByTag(currentFragment+"") != null){
            fragment = (AbstractFragment) getSupportFragmentManager().findFragmentByTag(currentFragment+"");
        }
        // Neu chua ton tai thi tao moi fragment
        else {
            // Tao doi tuong fragment tuong ung voi cau hoi tai questionId
            if (currentFragment == 0) {
                fragment = new HomeFragment(); // test multi choices
            } else if (currentFragment == 1) {
                fragment = new ExerciseFragment();
            } else if (currentFragment == 2) {
                fragment = new CalculateFragment();
            } else {
                fragment = new ProfileFragment();
            }
        }
//        // Thiet lap cau hoi tuong ung cho Fragment voi du lieu lay tu doi tuong Questions
//        fragment.setQuestion(Questions.questions.get(currentQuestion));
//
        // CHUAN BI CHO TRANSACTION
        //Lay doi tuong fragment transaction
        transaction = getSupportFragmentManager().beginTransaction();
        // Do du lieu vao doi tuong va dan doi tuong fragment vao khung man hinh,
        // voi tham so dau tien la id cua khung chua fragment o layout )
        transaction.replace(R.id.fragmentContainer,fragment,currentFragment+"");
        // Dua fragment vao trong Stack neu chua ton tai
        if (getSupportFragmentManager().findFragmentByTag(currentFragment+"") == null){
            transaction.addToBackStack(null);
        }
        // Yeu cau thuc hien transaction
        transaction.commit();

    }
    // Xu ly nut drawerToggle
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuSave){
//            Log.d("test", "Save");
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        return true;
    }

@Override
public void onBackPressed() {
    if (doubleBackToExitPressedOnce) {
        super.finishAffinity();
        return;
    }

    this.doubleBackToExitPressedOnce = true;
    Toast.makeText(this, "Nhấn back thêm một lần nữa để thoát", Toast.LENGTH_SHORT).show();

    // Đặt thời gian chờ để reset cờ khi không có lần nhấn nào xảy ra trong khoảng thời gian quy định
    new android.os.Handler().postDelayed(
            new Runnable() {
                @Override
                public void run() {
                    doubleBackToExitPressedOnce = false;
                }
            },
            2000 // Thời gian chờ (đơn vị: milliseconds)
    );

}

}