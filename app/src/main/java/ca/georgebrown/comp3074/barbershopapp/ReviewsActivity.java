package ca.georgebrown.comp3074.barbershopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;


public class ReviewsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
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

    public void openReviewListActivity(View view) {
        Intent intent = new Intent(this, ReviewListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        Spinner spinnerBarbers =  findViewById(R.id.barbers_spinner);

        ArrayAdapter <CharSequence> adapter = ArrayAdapter.createFromResource(
        this,
        R.array.barbers_array,
        android.R.layout.simple_spinner_item
        );

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toggle.getDrawerArrowDrawable().setColor(ContextCompat.getColor(this, android.R.color.black));

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerBarbers.setAdapter(adapter);
        spinnerBarbers.setOnItemSelectedListener(this);

        navView.setNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                // Navigate to RegistrationActivity
                Intent intent = new Intent(ReviewsActivity.this, MainActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_barbers) {
                Intent intent = new Intent(ReviewsActivity.this, PortfolioActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_bookings) {
                // Navigate to BookingActivity
                Intent intent = new Intent(ReviewsActivity.this, BookingActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_availability) {
                // Navigate to BookingActivity
                Intent intent = new Intent(ReviewsActivity.this, AvailabilityActivity.class);
                startActivity(intent);

            } else if (itemId == R.id.nav_profile) {
                Intent intent = new Intent(ReviewsActivity.this, UserProfile.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_consultation) {
                Intent intent = new Intent(ReviewsActivity.this, ConsultationActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_review) {
                Intent intent = new Intent(ReviewsActivity.this, ReviewsActivity.class);
                startActivity(intent);
            } else if (itemId == R.id.nav_logout) {
                Intent intent = new Intent(ReviewsActivity.this, LoginActivity.class);
                startActivity(intent);
            }

            Toast.makeText(ReviewsActivity.this, " clicked", Toast.LENGTH_SHORT).show();
            return false;
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String selectedBarber = parent.getItemAtPosition(position).toString();
//        Toast.makeText(this, selectedBarber, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


}