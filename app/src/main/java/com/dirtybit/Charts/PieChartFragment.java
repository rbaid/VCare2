package com.dirtybit.Charts;

import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dirtybit.vcare.R;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;



public class PieChartFragment extends SimpleFragment {

    public static Fragment newInstance() {
        return new PieChartFragment();
    }

    private PieChart mChart;
    private Typeface tf;

    AnalyticData analyticData = AnalyticData.getInstance();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().invalidateOptionsMenu();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_pie_chart, container, false);

        mChart = (PieChart) v.findViewById(R.id.pieChart1);
        mChart.setDescriptionTextSize(14f);
        mChart.setDescription("");
//      Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Light.ttf");
        mChart.setCenterTextTypeface(tf);
        mChart.setCenterText("Happiness\n Distribution");
        mChart.setCenterTextSize(18f);
        mChart.setCenterTextTypeface(tf);

        // radius of the center hole in percent of maximum radius
        mChart.setHoleRadius(45f);
        mChart.setTransparentCircleRadius(50f);

        Legend l = mChart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setTextSize(12f);
        mChart.setData(generatePieData());

        return v;
    }
}
