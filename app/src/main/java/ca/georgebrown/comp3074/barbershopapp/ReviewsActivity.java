package ca.georgebrown.comp3074.barbershopapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class ReviewsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

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

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerBarbers.setAdapter(adapter);
        spinnerBarbers.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedBarber = parent.getItemAtPosition(position).toString();
        Toast.makeText(this, selectedBarber, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}