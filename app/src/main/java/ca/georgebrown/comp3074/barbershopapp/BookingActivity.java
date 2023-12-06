package ca.georgebrown.comp3074.barbershopapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;


public class BookingActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navView;
    ActionBarDrawerToggle toggle;
    private String selectedBarber;
    private String selectedDate;
    private String selectedTime;
    private String selectedService;

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
        setContentView(R.layout.activity_booking);

        Spinner barberSpinner = findViewById(R.id.barberSpinner);
        Spinner serviceSpinner = findViewById(R.id.serviceSpinner);
        CalendarView calendarView = findViewById(R.id.calendarView);
        RadioGroup timeRadioGroup = findViewById(R.id.timeRadioGroup);


        timeRadioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            // Handle time selection
            RadioButton selectedRadioButton = findViewById(checkedId);
            if (selectedRadioButton != null) {
                selectedTime = selectedRadioButton.getText().toString();
            }
        });

        barberSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedBarber = (String) parentView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle case where nothing is selected (optional)
            }
        });

        // Set up the listener to capture the selected item from the serviceSpinner
        serviceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedService = (String) parentView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle case where nothing is selected (optional)
            }
        });

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Handle date selection
            selectedDate = String.format(Locale.getDefault(), "%04d-%02d-%02d", year, month + 1, dayOfMonth);
            Log.d("CalendarView", "Selected Date: " + selectedDate);
        });

        // Set up adapters for spinners and populate data as needed
        ArrayAdapter<String> barberAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getBarberData());
        barberAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        barberSpinner.setAdapter(barberAdapter);

        ArrayAdapter<String> serviceAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, getServiceData());
        serviceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        serviceSpinner.setAdapter(serviceAdapter);

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
                Intent intent = new Intent(BookingActivity.this, MainActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_barbers) {
                Intent intent = new Intent(BookingActivity.this, PortfolioActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_bookings) {
                // Navigate to BookingActivity
                Intent intent = new Intent(BookingActivity.this, BookingActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_availability) {
                // Navigate to BookingActivity
                Intent intent = new Intent(BookingActivity.this, AvailabilityActivity.class);
                startActivity(intent);

            } else if (itemId == R.id.nav_profile) {
                Intent intent = new Intent(BookingActivity.this, UserProfile.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_consultation) {
                Intent intent = new Intent(BookingActivity.this, ConsultationActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_review) {
                Intent intent = new Intent(BookingActivity.this, ReviewsActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_logout) {
                Intent intent = new Intent(BookingActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            Toast.makeText(BookingActivity.this, " clicked", Toast.LENGTH_SHORT).show();
            return false;
        });
    }
    public void onBookButtonClick(View view) {
        if (selectedBarber != null && !selectedBarber.isEmpty() &&
                selectedDate != null && !selectedDate.isEmpty() &&
                selectedService != null && !selectedService.isEmpty() &&
                selectedTime != null && !selectedTime.isEmpty()) {

            // User has selected all necessary details, proceed to the next activity

            // Create a new Booking instance with selected details
            Booking newBooking = new Booking(selectedDate, selectedService, selectedBarber, selectedTime);

            // Example: Navigating to BookingHistoryActivity
            Intent intent = new Intent(BookingActivity.this, BookingHistory.class);
            intent.putExtra("booking", newBooking); // Pass booking information to the next activity
            startActivity(intent);
        } else {
            // User has not selected all necessary details, show a message or take appropriate action
            Toast.makeText(this, "Please fill in all details", Toast.LENGTH_SHORT).show();
        }
    }

    // Helper method to provide dummy data for barber spinner
    private String[] getBarberData() {
        return new String[]{"Logan", "Jess", "Dean"};
    }

    // Helper method to provide dummy data for service spinner
    private String[] getServiceData() {
        return new String[]{"Haircut", "Braid", "Beard Trim"};
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
