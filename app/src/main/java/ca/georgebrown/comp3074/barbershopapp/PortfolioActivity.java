package ca.georgebrown.comp3074.barbershopapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class PortfolioActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navView;
    ActionBarDrawerToggle toggle;

    private ImageView sampleImageView;
    private int [] imageArray = {R.drawable.c1, R.drawable.c2, R.drawable.c3};
    private int currentIndex = 0;

    private ImageView cutsSampleImageView;
    private int[] imageArray2 = {R.drawable.jc1, R.drawable.jc2};
    private int currentIndex2 = 0;


    public void openPortfolioActivity(View view) {
        Intent intent = new Intent(this, PortfolioActivity.class);
        startActivity(intent);
    }
    public void openConsultationActivity(View view) {
        Intent intent = new Intent(this, ConsultationActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item); // if the hamburger icon is clicked, the drawer will open
    }

    public void openBookingActivity(View view) {
        Intent intent = new Intent(this, BookingActivity.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

        sampleImageView = findViewById(R.id.sampleImages);
        startSlideShow();

        cutsSampleImageView = findViewById(R.id.CutsSample);
        updateImageView();

        findViewById(R.id.btnPrev).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPrev();
            }
        });

        findViewById(R.id.btnNextpic).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNext();
            }

        });


        findViewById(R.id.btnPrevious).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPreviousImage();
            }
        });

        findViewById(R.id.btnNext).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showNextImage();
            }
        });

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState(); // syncs the state of the drawer with the state of the hamburger icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // enables the hamburger icon
        toggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this, android.R.color.black));
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    // Navigate to RegistrationActivity
                    Intent intent = new Intent(PortfolioActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_barbers) {
                    Intent intent = new Intent(PortfolioActivity.this, PortfolioActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_bookings) {
                    // Navigate to BookingActivity
                    Intent intent = new Intent(PortfolioActivity.this, BookingActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_availability) {
                    // Navigate to BookingActivity
                    Intent intent = new Intent(PortfolioActivity.this, AvailabilityActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_profile) {
                    Intent intent = new Intent(PortfolioActivity.this, UserProfile.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_consultation) {
                    Intent intent = new Intent(PortfolioActivity.this, ConsultationActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_review) {
                    Intent intent = new Intent(PortfolioActivity.this, ReviewsActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_logout) {
                    Intent intent = new Intent(PortfolioActivity.this, LoginActivity.class);
                    startActivity(intent);
                }


                Toast.makeText(PortfolioActivity.this, " clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }

    private void startSlideShow() {
        sampleImageView.setImageResource(imageArray[currentIndex]);
    }

    private void showPrev() {
        currentIndex = (currentIndex - 1 + imageArray.length) % imageArray.length;
        startSlideShow();
    }

    private void showNext() {
        currentIndex = (currentIndex + 1) % imageArray.length;
        startSlideShow();
    }

    private void updateImageView() {
        cutsSampleImageView.setImageResource(imageArray2[currentIndex2]);
    }

    private void showPreviousImage() {
        currentIndex2 = (currentIndex2 - 1 + imageArray2.length) % imageArray2.length;
        updateImageView();
    }

    private void showNextImage() {
        currentIndex2 = (currentIndex2 + 1) % imageArray2.length;
        updateImageView();
    }
}
