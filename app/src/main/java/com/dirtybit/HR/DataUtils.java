package com.dirtybit.HR;

import android.content.res.Resources;

import com.dirtybit.model.Feedback;
import com.dirtybit.model.FeedbackComp;
import com.dirtybit.model.FeedbackData;
import com.dirtybit.model.FeedbackDetail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

public class DataUtils {

    final static int POLICY_TIME = 0;
    final static int POLICY_DRESS = 1;
    final static int POLICY_LEAVE = 2;
    final static int POLICY_TRAVEL = 3;
    final static int POLICY_FOOD = 4;
    final static int POLICY_COMPOFF = 5;

    final static int EVENT_DANCE = 0;
    final static int EVENT_PAINTING = 1;
    final static int EVENT_EVENT_MUSIC = 2;
    final static int EVENT_SPORTS = 3;
    final static int EVENT_GAME = 4;
    final static int EVENT_DIWALI = 5;

    final static String DEPT_HR = "HR";
    final static String DEPT_IT = "IT";
    final static String DEPT_MARKETING = "Marketing";
    final static String DEPT_FINANCE = "finance";
    final static String DEPT_SALES = "Sales";
    final static String DEPT_ADMIN = "Admin";



    public HashMap getDeptBarData(FeedbackData feedbackData) {
        HashMap<String, Integer> DeptRatingMap = new HashMap<String, Integer>();

        int ratingArray[] = new int[6];
        int EmpCount[] = new int[6];
        for (int i = 0; i < 6; i++) {
            ratingArray[i] = 0;
            EmpCount[i] = 0;
        }
        if (feedbackData == null)
            return null;

        List<FeedbackDetail> list = feedbackData.getFeedbackDetails();
        for (FeedbackDetail feedbackDetail : list) {
            for (Feedback Dayfeedback : feedbackDetail.getFeedback()) {
                String department = feedbackDetail.getDepartment();
                if (DEPT_HR.equals(department)) {
                    ratingArray[0] = ratingArray[0] + Dayfeedback.getDayRating().intValue();
                    EmpCount[0]++;
                } else if (DEPT_IT.equals(department)) {
                    ratingArray[1] = ratingArray[1] + Dayfeedback.getDayRating().intValue();
                    EmpCount[1]++;
                } else if (DEPT_MARKETING.equals(department)) {
                    ratingArray[2] = ratingArray[2] + Dayfeedback.getDayRating().intValue();
                    EmpCount[2]++;
                } else if (DEPT_SALES.equals(department)) {
                    ratingArray[3] = ratingArray[3] + Dayfeedback.getDayRating().intValue();
                    EmpCount[3]++;
                } else if (DEPT_FINANCE.equals(department)) {
                    ratingArray[4] = ratingArray[4] + Dayfeedback.getDayRating().intValue();
                    EmpCount[4]++;
                } else if (DEPT_ADMIN.equals(department)) {
                    ratingArray[5] = ratingArray[5] + Dayfeedback.getDayRating().intValue();
                    EmpCount[5]++;
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            ratingArray[i] = ratingArray[i] / EmpCount[i];
        }
        DeptRatingMap.put("HR", ratingArray[0]);
        DeptRatingMap.put("IT", ratingArray[1]);
        DeptRatingMap.put("Marketing", ratingArray[2]);
        DeptRatingMap.put("Sales", ratingArray[3]);
        DeptRatingMap.put("finance", ratingArray[4]);
        DeptRatingMap.put("Admin", ratingArray[5]);
        return DeptRatingMap;
    }


    public int[] getCompanyPieChartData(FeedbackData feedbackData) {
        if (feedbackData == null)
            return null;
        int dayRatingArray[] = new int[6];
        for (int i = 0; i < 6; i++) {
            dayRatingArray[i] = 0;
        }
        List<FeedbackDetail> list = feedbackData.getFeedbackDetails();
        for (FeedbackDetail feedbackDetail : list) {
            for (Feedback Dayfeedback : feedbackDetail.getFeedback()) {
                int dayRating = Dayfeedback.getDayRating().intValue();
                if (dayRating > 0)
                    dayRatingArray[dayRating - 1]++;
            }
        }
        return dayRatingArray;
    }


    public int[] getPolicyBarData(FeedbackData feedbackData) {

        int policyRatingArray[] = new int[6];
        int EmpCount[] = new int[6];

        if (feedbackData == null)
            return null;

        for (int i = 0; i < 6; i++) {
            policyRatingArray[i] = 0;
            EmpCount[i] = 0;
        }

        List<FeedbackDetail> list = feedbackData.getFeedbackDetails();
        for (FeedbackDetail feedbackDetail : list) {
            for (Feedback feedback1 : feedbackDetail.getFeedback()) {
                for (FeedbackComp feedbackComp : feedback1.getFeedbackComp()) {
                    int policyNo = feedbackComp.getPolicyNo();
                    if (policyNo > 0) {
                        policyRatingArray[policyNo - 1] = policyRatingArray[policyNo - 1] + feedbackComp.getRating();
                        EmpCount[policyNo - 1]++;
                    }
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            policyRatingArray[i] = policyRatingArray[i] / EmpCount[i];
        }
        return policyRatingArray;
    }

    public int[] getEventBarData(FeedbackData feedbackData) {

        int eventRatingArray[] = new int[6];
        int EmpCount[] = new int[6];


        for (int i = 0; i < 6; i++) {
            eventRatingArray[i] = 0;
            EmpCount[i] = 0;
        }
        if (feedbackData == null)
            return null;
        List<FeedbackDetail> list = feedbackData.getFeedbackDetails();
        for (FeedbackDetail feedbackDetail : list) {
            for (Feedback feedback1 : feedbackDetail.getFeedback()) {
                for (FeedbackComp feedbackComp : feedback1.getFeedbackComp()) {
                    int eventNo = feedbackComp.getEventNo();
                    if (eventNo > 0) {
                        eventRatingArray[eventNo - 1] = eventRatingArray[eventNo - 1] + feedbackComp.getRating();
                        EmpCount[eventNo - 1]++;
                    }
                }
            }
        }

        for (int i = 0; i < 6; i++) {
            eventRatingArray[i] = eventRatingArray[i] / EmpCount[i];
        }
        return eventRatingArray;
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }
}
