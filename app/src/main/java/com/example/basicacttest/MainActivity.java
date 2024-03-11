package com.example.basicacttest;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.basicacttest.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.toolbar);

        RadioGroup radioGroup = binding.getRoot().findViewById(R.id.radioGroup);
        ConstraintLayout constraintLayout = binding.getRoot().findViewById(R.id.constraintLayout);

        radioGroup.setOnCheckedChangeListener((rg, checkId) -> {

            RadioButton radioButton = findViewById(checkId);

            if(checkId == R.id.radioButton) constraintLayout.setBackgroundColor(Color.GRAY);
            else if(checkId == R.id.radioButton2) constraintLayout.setBackgroundColor(Color.RED);
            else if(checkId == R.id.radioButton3) constraintLayout.setBackgroundColor(Color.MAGENTA);

            radioButton.setChecked(true);

        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();

        if(id == R.id.calendar_icon){

            Calendar cal = Calendar.getInstance();

            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            int year = cal.get(Calendar.YEAR);


            TextView dateText = binding.getRoot().findViewById(R.id.textView);

            DatePickerDialog datePicker = new DatePickerDialog(this,(view, year1, month1, day1) -> {
                dateText.setText("");
                dateText.setText("Date : " + (month1 + 1) + "/" + day1 +"/" + year1);
            }, year, month, day);

            datePicker.show();
            return true;
        }

        else if(id == R.id.time){

            TextView timeText = binding.getRoot().findViewById(R.id.textView2);
            SimpleDateFormat sdf = new SimpleDateFormat("'Time' hh:mm a");
            timeText.setText(sdf.format(new Date()));
            return true;
        }
        else if(id == R.id.exit){
            AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
            alertBox.setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", (dialog, i) -> finishAffinity())
                .setNegativeButton("No", (dialog, i) -> {
                    dialog.dismiss();
                    Toast.makeText(this, "Good choice!", Toast.LENGTH_LONG).show();
                });

            alertBox.show();
            return true;
        }
        return false;
    }

}