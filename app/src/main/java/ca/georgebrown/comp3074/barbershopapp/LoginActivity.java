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

public class LoginActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navView;
    ActionBarDrawerToggle toggle;

    public void openRegistrationActivity(View view) {
        Intent intent = new Intent(this, RegistrationActivity.class);
        startActivity(intent);
    }

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
        setContentView(R.layout.activity_login);

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
                Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_barbers) {
                Toast.makeText(LoginActivity.this, "Barbers Selected", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.nav_bookings) {
                // Navigate to BookingActivity
                Intent intent = new Intent(LoginActivity.this, BookingActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_profile) {
                Toast.makeText(LoginActivity.this, "My Account Selected", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.nav_consultation) {
                Toast.makeText(LoginActivity.this, "Consultation Selected", Toast.LENGTH_SHORT).show();
            } else if (itemId == R.id.nav_review) {
                Toast.makeText(LoginActivity.this, "Review Selected", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(LoginActivity.this, " clicked", Toast.LENGTH_SHORT).show();
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

