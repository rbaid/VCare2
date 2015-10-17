package com.dirtybit.Charts;

import com.dirtybit.HR.DataUtils;
import com.dirtybit.model.AllFeedBackDetail;

import java.util.Map;

public class AnalyticData {

    private static AnalyticData instance;
    public static AnalyticData getInstance() {
        if(instance == null){
            instance = new AnalyticData();
        }
        return instance;
    }
    private AnalyticData() {}

    private int[] rating;


    public int[] getRating() {

        DataUtils dataUtils = new DataUtils();
            return dataUtils.getCompanyPieChartData(AllFeedBackDetail.getInstance().getFeedbackData());

       // return rating;
    }

    public void setRating(int[] rating) {
        this.rating = rating;
    }
}
