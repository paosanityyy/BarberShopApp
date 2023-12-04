package ca.georgebrown.comp3074.barbershopapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import android.widget.TextView;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.recyclerview.widget.RecyclerView;



public class BookingHistory extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        drawerLayout = findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



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
        // Example: Assuming you have TextViews with ids 'dateTextView', 'serviceTextView', etc.
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