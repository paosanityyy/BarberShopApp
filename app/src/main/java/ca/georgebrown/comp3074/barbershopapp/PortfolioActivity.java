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
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class PortfolioActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navView;
    ActionBarDrawerToggle toggle;

    public void openPortfolioActivity(View view) {
        Intent intent = new Intent(this, PortfolioActivity.class);
        startActivity(intent);
    }
    public boolean openMainActivity(MenuItem item) {
        // Handle the menu item click here
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        return true; // Return true to indicate that the event has been handled
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item); // if the hamburger icon is clicked, the drawer will open
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);

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
                    Toast.makeText(PortfolioActivity.this, "My Account Selected", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.nav_consultation) {
                    Intent intent = new Intent(PortfolioActivity.this, ConsultationActivity.class);
                    startActivity(intent);
                } else if (itemId == R.id.nav_review) {
                    Intent intent = new Intent(PortfolioActivity.this, ReviewsActivity.class);
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
}
