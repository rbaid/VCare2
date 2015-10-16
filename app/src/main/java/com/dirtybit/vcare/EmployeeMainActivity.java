package com.dirtybit.vcare;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EmployeeMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.event).setVisibility(View.GONE);
                findViewById(R.id.policy).setVisibility(View.GONE);
                findViewById(R.id.general).setVisibility(View.VISIBLE);
            }
        });
        FloatingActionButton fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.general).setVisibility(View.GONE);
                findViewById(R.id.policy).setVisibility(View.GONE);
                findViewById(R.id.event).setVisibility(View.VISIBLE);
                Spinner spinner = (Spinner) findViewById(R.id.event_spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EmployeeMainActivity.this,
                        R.array.event_array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        adapterView.getItemAtPosition(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });

        FloatingActionButton fa3 = (FloatingActionButton) findViewById(R.id.fab3);
        fa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.event).setVisibility(View.GONE);
                findViewById(R.id.general).setVisibility(View.GONE);
                findViewById(R.id.policy).setVisibility(View.VISIBLE);
                Spinner spinner = (Spinner) findViewById(R.id.policy_spinner);
                ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(EmployeeMainActivity.this,
                        R.array.policy_array, android.R.layout.simple_spinner_item);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(adapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        adapterView.getItemAtPosition(i);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }
        });

    }

}
