package ca.georgebrown.comp3074.barbershopapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class RegistrationActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    NavigationView navView;
    ActionBarDrawerToggle toggle;

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
        setContentView(R.layout.activity_registration);

        drawerLayout = findViewById(R.id.drawer_layout);
        navView = findViewById(R.id.nav_view);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState(); // syncs the state of the drawer with the state of the hamburger icon
        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // enables the hamburger icon
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int itemId = item.getItemId();

                if (itemId == R.id.nav_home) {
                    Toast.makeText(RegistrationActivity.this, "Home Selected", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.nav_barbers) {
                    Toast.makeText(RegistrationActivity.this, "Barbers Selected", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.nav_bookings) {
                    Toast.makeText(RegistrationActivity.this, "Bookings Selected", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.nav_profile) {
                    Toast.makeText(RegistrationActivity.this, "My Account Selected", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.nav_consultation) {
                    Toast.makeText(RegistrationActivity.this, "Consultation Selected", Toast.LENGTH_SHORT).show();
                } else if (itemId == R.id.nav_review) {
                    Toast.makeText(RegistrationActivity.this, "Review Selected", Toast.LENGTH_SHORT).show();
                }

                Toast.makeText(RegistrationActivity.this, " clicked", Toast.LENGTH_SHORT).show();
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