package ca.georgebrown.comp3074.barbershopapp;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import ca.georgebrown.comp3074.barbershopapp.R;

public class UserProfile extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navView;
    ActionBarDrawerToggle toggle;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openConsultationActivity(View view) {
        Intent intent = new Intent(this, ConsultationActivity.class);
        startActivity(intent);
    }

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
                // Navigate to RegistrationActivity
                Intent intent = new Intent(UserProfile.this, MainActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_barbers) {
                Intent intent = new Intent(UserProfile.this, PortfolioActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_bookings) {
                // Navigate to BookingActivity
                Intent intent = new Intent(UserProfile.this, BookingActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_availability) {
                // Navigate to BookingActivity
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

