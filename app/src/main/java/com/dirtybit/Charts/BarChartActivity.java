package com.dirtybit.Charts;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.dirtybit.vcare.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Random;

public class BarChartActivity extends AppCompatActivity {

    Spinner spinner;

    private float [] f = {002.040f,002.600f,003.010f,004.002f,004.020f,005.000f,001.000f} ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BarChart chart = (BarChart) findViewById(R.id.chart);

        if(Helper.status==2) {
            spinner = (Spinner) findViewById(R.id.d_spinner);
            spinner.setVisibility(View.INVISIBLE);
            BarData data = new BarData(getYAxisValues(), getPolicyDataSet());
            chart.setData(data);
            chart.setDescription("Rating for Events");
            chart.animateXY(2000, 2000);
            chart.invalidate();

            return;
        }
        if(Helper.status==4) {
            spinner = (Spinner) findViewById(R.id.d_spinner);
            spinner.setVisibility(View.INVISIBLE);
            BarData data = new BarData(getYAxisValues(), getEventDataSet());
            chart.setData(data);
            chart.setDescription("Rating for Events");
            chart.animateXY(2000, 2000);
            chart.invalidate();

            return;
        }
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(BarChartActivity.this,
                R.array.department_spinner, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = (Spinner) findViewById(R.id.d_spinner);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                adapterView.getItemAtPosition(i);
                if(i>0) {
                    BarData data = new BarData(getXAxisValues(), getDataSet());
                    chart.setData(data);
                    chart.setDescription("");
                    chart.animateXY(2000, 2000);
                    chart.invalidate();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private ArrayList<BarDataSet> getEventDataSet() {
        ArrayList<BarDataSet> dataSets = null;
        Random random = new Random();

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(f[random.nextInt(6)], 0); // Jan
        valueSet1.add(v1e1);


        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(f[random.nextInt(6)], 2); // Jan
        valueSet2.add(v2e1);

        ArrayList<BarEntry> valueSet3 = new ArrayList<>();
        BarEntry v3e1 = new BarEntry(f[random.nextInt(6)], 4); // Jan
        valueSet3.add(v3e1);

        ArrayList<BarEntry> valueSet4 = new ArrayList<>();
        BarEntry v4e1 = new BarEntry(f[random.nextInt(6)], 6); // Jan
        valueSet4.add(v4e1);

        ArrayList<BarEntry> valueSet5 = new ArrayList<>();
        BarEntry v5e1 = new BarEntry(f[random.nextInt(6)], 8); // Jan
        valueSet5.add(v5e1);


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Dance");
        barDataSet1.setColor(Color.rgb(250, 155, 250));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Painting");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "Music");
        barDataSet3.setColor(Color.rgb(0, 100, 250));
        BarDataSet barDataSet4 = new BarDataSet(valueSet4, "Sports");
        barDataSet4.setColors(ColorTemplate.JOYFUL_COLORS);
        BarDataSet barDataSet5 = new BarDataSet(valueSet5, "Hackathon");
        barDataSet5.setColor(Color.rgb(150, 55, 0));


        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);
        dataSets.add(barDataSet4);
        dataSets.add(barDataSet5);

        return dataSets;
    }

    private ArrayList<BarDataSet> getPolicyDataSet() {
        ArrayList<BarDataSet> dataSets = null;
        Random random = new Random();

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(f[random.nextInt(6)], 0); // Jan
        valueSet1.add(v1e1);


        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(f[random.nextInt(6)], 2); // Jan
        valueSet2.add(v2e1);

        ArrayList<BarEntry> valueSet3 = new ArrayList<>();
        BarEntry v3e1 = new BarEntry(f[random.nextInt(6)], 4); // Jan
        valueSet3.add(v3e1);

        ArrayList<BarEntry> valueSet4 = new ArrayList<>();
        BarEntry v4e1 = new BarEntry(f[random.nextInt(6)], 6); // Jan
        valueSet4.add(v4e1);

        ArrayList<BarEntry> valueSet5 = new ArrayList<>();
        BarEntry v5e1 = new BarEntry(f[random.nextInt(6)], 8); // Jan
        valueSet5.add(v5e1);

        ArrayList<BarEntry> valueSet6 = new ArrayList<>();
        BarEntry v6e1 = new BarEntry(f[random.nextInt(6)], 10); // Jan
        valueSet6.add(v5e1);


        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Time");
        barDataSet1.setColor(Color.rgb(250, 155, 250));
        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Dress");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);
        BarDataSet barDataSet3 = new BarDataSet(valueSet3, "Leave");
        barDataSet3.setColor(Color.rgb(0, 100, 250));
        BarDataSet barDataSet4 = new BarDataSet(valueSet4, "Comp-off");
        barDataSet4.setColors(ColorTemplate.JOYFUL_COLORS);
        BarDataSet barDataSet5 = new BarDataSet(valueSet5, "Food");
        barDataSet5.setColor(Color.rgb(150, 55, 0));
        BarDataSet barDataSet6 = new BarDataSet(valueSet6, "Food");
        barDataSet6.setColor(Color.rgb(150, 155, 100));


        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);
        dataSets.add(barDataSet4);
        dataSets.add(barDataSet5);

        return dataSets;
    }

    private ArrayList<BarDataSet> getDataSet() {
        Random random = new Random();
        ArrayList<BarDataSet> dataSets;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();

        BarEntry v1e1 = new BarEntry(f[random.nextInt(6)], 0);
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(f[random.nextInt(6)], 1);
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(f[random.nextInt(6)], 2);
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(f[random.nextInt(6)], 3);
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(f[random.nextInt(6)], 4);
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(f[random.nextInt(6)], 5); 
        valueSet1.add(v1e6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "IT");
        barDataSet1.setColor(Color.rgb(0, 155, 0));

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);

        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();

        xAxis.add("MAY");
        xAxis.add("JUN");
        xAxis.add("JUL");
        xAxis.add("AUG");
        xAxis.add("SEP");
        xAxis.add("OCT");

        return xAxis;
    }

    private ArrayList<String> getYAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("");
        xAxis.add("");
        xAxis.add("");
        xAxis.add("");
        xAxis.add("");
        xAxis.add("");


        return xAxis;
    }
}
