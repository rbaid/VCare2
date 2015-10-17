package com.dirtybit.Charts;

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

    private int[] rating = {1,2,5,4,6};


    public int[] getRating() {
        return rating;
    }

    public void setRating(int[] rating) {
        this.rating = rating;
    }
}
