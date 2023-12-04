package ca.georgebrown.comp3074.barbershopapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvailabilityActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navView;
    ActionBarDrawerToggle toggle;

    private List<String> weekdays;
    private List<String> presetNames;
    private Map<String, List<Boolean>> nameAvailabilityMap; // Mapping names to their availability for weekdays

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
        setContentView(R.layout.activity_availability);

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
                Intent intent = new Intent(AvailabilityActivity.this, MainActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_barbers) {
                Intent intent = new Intent(AvailabilityActivity.this, PortfolioActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_bookings) {
                // Navigate to BookingActivity
                Intent intent = new Intent(AvailabilityActivity.this, BookingActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_availability) {
                // Navigate to BookingActivity
                Intent intent = new Intent(AvailabilityActivity.this, AvailabilityActivity.class);
                startActivity(intent);

            } else if (itemId == R.id.nav_profile) {
                Intent intent = new Intent(AvailabilityActivity.this, UserProfile.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_consultation) {
                Intent intent = new Intent(AvailabilityActivity.this, ConsultationActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_review) {
                Intent intent = new Intent(AvailabilityActivity.this, ReviewsActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_logout) {
                Intent intent = new Intent(AvailabilityActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            Toast.makeText(AvailabilityActivity.this, " clicked", Toast.LENGTH_SHORT).show();
            return false;
        });

        LinearLayout checkboxContainer = findViewById(R.id.checkboxContainer);
        Button confirmButton = findViewById(R.id.confirmButton);
        Spinner namesSpinner = findViewById(R.id.namesSpinner);


        // Preset bank of names
        presetNames = Arrays.asList("John", "Emma", "Michael", "Sophia", "William");

        // Populate spinner with preset names
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, presetNames);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        namesSpinner.setAdapter(spinnerAdapter);

        // Weekdays
        weekdays = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday");

        // Create a map to store availability for each name and initialize with default values (false)
        nameAvailabilityMap = new HashMap<>();
        for (String name : presetNames) {
            List<Boolean> availabilityList = new ArrayList<>();
            for (int i = 0; i < weekdays.size(); i++) {
                availabilityList.add(false); // Fill availability list with default false values for each weekday
            }
            nameAvailabilityMap.put(name, availabilityList);
        }


        // Create checkboxes for each weekday for the selected name
        namesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = presetNames.get(position);
                updateCheckboxListForName(selectedName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle when nothing is selected
            }

        });
        confirmButton.setOnClickListener(v -> {
            // Handle confirm button click

            // For demonstration purposes, log the availability for each name
            for (String name : presetNames) {
                List<Boolean> availability = nameAvailabilityMap.get(name);
                Log.d("Availability", name + ": " + Arrays.toString(availability.toArray()));
            }

        });

    }
    // Update checkboxes for weekdays based on the selected name
    private void updateCheckboxListForName(String selectedName) {
        LinearLayout checkboxContainer = findViewById(R.id.checkboxContainer);
        checkboxContainer.removeAllViews(); // Clear existing checkboxes

        List<Boolean> availabilityList = nameAvailabilityMap.get(selectedName);

        // Create checkboxes for each weekday and associate with availability
        for (int i = 0; i < weekdays.size(); i++) {
            CheckBox checkBox = new CheckBox(this);
            checkBox.setText(weekdays.get(i));
            assert availabilityList != null;
            checkBox.setChecked(availabilityList.get(i)); // Set the checkbox state based on availability

            final int index = i;
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // Update availability when checkbox state changes
                    List<Boolean> availability = nameAvailabilityMap.get(selectedName);
                    assert availability != null;
                    availability.set(index, isChecked);
                    nameAvailabilityMap.put(selectedName, availability);
                }
            });
            checkboxContainer.addView(checkBox);
        }
    }
}
