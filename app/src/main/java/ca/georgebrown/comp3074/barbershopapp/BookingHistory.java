package ca.georgebrown.comp3074.barbershopapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.google.android.material.navigation.NavigationView;


public class BookingHistory extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navView;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

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
                Intent intent = new Intent(BookingHistory.this, MainActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_barbers) {
                Intent intent = new Intent(BookingHistory.this, PortfolioActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_bookings) {
                // Navigate to BookingActivity
                Intent intent = new Intent(BookingHistory.this, BookingActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_availability) {
                // Navigate to BookingActivity
                Intent intent = new Intent(BookingHistory.this, AvailabilityActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_profile) {
                Intent intent = new Intent(BookingHistory.this, UserProfile.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_consultation) {
                Intent intent = new Intent(BookingHistory.this, ConsultationActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_review) {
                Intent intent = new Intent(BookingHistory.this, ReviewsActivity.class);
                startActivity(intent);
            }

            Toast.makeText(BookingHistory.this, " clicked", Toast.LENGTH_SHORT).show();
            return false;
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        // Retrieve booking information from the intent
        Intent intent = getIntent();
        if (intent != null) {
            Booking booked = (Booking) intent.getSerializableExtra("booking");

            // Display booking details in your UI
            if (booked != null) {
                displayBookingDetails(booked);
            }
        }



    }

    // Handle navigation drawer item clicks
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Close navigation drawer when back button is pressed
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    // Display booking details in your UI
    private void displayBookingDetails(Booking booking) {
        TextView dateTextView = findViewById(R.id.bookingDateTextView);
        TextView serviceTextView = findViewById(R.id.bookingServiceTextView);
        TextView barberTextView = findViewById(R.id.bookingBarberTextView);
        TextView timeTextView = findViewById(R.id.bookingTimeTextView);

        // Set the booking information in TextViews
        dateTextView.setText("Date: " + booking.getDate());
        serviceTextView.setText("Service: " + booking.getService());
        barberTextView.setText("Barber: " + booking.getBarber());
        timeTextView.setText("Time: " + booking.getTime());
    }

}