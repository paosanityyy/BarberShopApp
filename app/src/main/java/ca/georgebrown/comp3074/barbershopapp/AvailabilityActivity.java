package ca.georgebrown.comp3074.barbershopapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AvailabilityActivity extends AppCompatActivity {

    private List<String> weekdays;
    private List<String> presetNames;
    private Map<String, List<Boolean>> nameAvailabilityMap; // Mapping names to their availability for weekdays

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_availability);

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
