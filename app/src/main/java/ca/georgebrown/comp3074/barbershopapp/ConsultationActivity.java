package ca.georgebrown.comp3074.barbershopapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class ConsultationActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_consultation);

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this, android.R.color.black));

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    // Navigate to RegistrationActivity
                    Intent intent = new Intent(ConsultationActivity.this, MainActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_barbers) {
                    Intent intent = new Intent(ConsultationActivity.this, PortfolioActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_bookings) {
                    // Navigate to BookingActivity
                    Intent intent = new Intent(ConsultationActivity.this, BookingActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_availability) {
                    // Navigate to BookingActivity
                    Intent intent = new Intent(ConsultationActivity.this, AvailabilityActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_profile) {
                    Intent intent = new Intent(ConsultationActivity.this, UserProfile.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_consultation) {
                    Intent intent = new Intent(ConsultationActivity.this, ConsultationActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_review) {
                    Intent intent = new Intent(ConsultationActivity.this, ReviewsActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_logout) {
                    Intent intent = new Intent(ConsultationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }


                Toast.makeText(ConsultationActivity.this, " clicked", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
