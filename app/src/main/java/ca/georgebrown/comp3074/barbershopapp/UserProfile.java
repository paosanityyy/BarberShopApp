package ca.georgebrown.comp3074.barbershopapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class UserProfile extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navView;
    ActionBarDrawerToggle toggle;

    TextView firstnameTextView, lastnameTextView, emailTextView, phoneTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this, android.R.color.black));

        navView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                Intent intent = new Intent(UserProfile.this, MainActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_barbers) {
                Intent intent = new Intent(UserProfile.this, PortfolioActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_bookings) {
                Intent intent = new Intent(UserProfile.this, BookingActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_availability) {
                Intent intent = new Intent(UserProfile.this, AvailabilityActivity.class);
                startActivity(intent);

            } else if (itemId == R.id.nav_profile) {
                Intent intent = new Intent(UserProfile.this, UserProfile.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_consultation) {
                Intent intent = new Intent(UserProfile.this, ConsultationActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_review) {
                Intent intent = new Intent(UserProfile.this, ReviewsActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_logout) {
                Intent intent = new Intent(UserProfile.this, LoginActivity.class);
                startActivity(intent);
            }

            Toast.makeText(UserProfile.this, " clicked", Toast.LENGTH_SHORT).show();
            return false;
        });
        
    }



    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
