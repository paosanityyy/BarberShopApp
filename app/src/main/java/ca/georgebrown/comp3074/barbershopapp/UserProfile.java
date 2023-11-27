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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

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
                Toast.makeText(UserProfile.this, "Home Selected", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.nav_barbers) {
                Toast.makeText(UserProfile.this, "Barbers Selected", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.nav_bookings) {
                Toast.makeText(UserProfile.this, "Bookings Selected", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.nav_profile) {
                Toast.makeText(UserProfile.this, "My Account Selected", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.nav_consultation) {
                Toast.makeText(UserProfile.this, "Consultation Selected", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.nav_review) {
                Toast.makeText(UserProfile.this, "Review Selected", Toast.LENGTH_SHORT).show();
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

