package com.dirtybit.HR;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import com.dirtybit.model.EmpInfo;
import com.dirtybit.model.Feedback;
import com.dirtybit.model.FeedbackComp;
import com.dirtybit.model.FeedbackData;
import com.dirtybit.model.FeedbackDetail;
import com.dirtybit.vcare.R;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class HRMainActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    final static String DEPT_HR = "HR";
    final static String DEPT_IT = "IT";
    final static String DEPT_MARKETING = "Marketing";
    final static String DEPT_FINANCE = "finance";
    final static String DEPT_SALES = "Sales";
    final static String DEPT_ADMIN = "Admin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrmain);
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);
        //parsing Data
        parseDatafromJson();
        // preparing list data
        prepareListData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
        // setting list adapter
        expListView.setAdapter(listAdapter);
    }

    /*
        * Preparing the list data
        */
    FeedbackData feedbackData;

    private void prepareListData() {

        listDataHeader = new ArrayList<>();
        listDataChild = new HashMap<String, List<String>>();
        // Adding child data
        listDataHeader.add("General");
        listDataHeader.add("Policy");
        listDataHeader.add("Event");

        // Adding child data
        List<String> general = new ArrayList<String>();
        List<String> policy = new ArrayList<String>();
        List<String> event = new ArrayList<String>();

        List<FeedbackDetail> list = feedbackData.getFeedbackDetails();
        for (FeedbackDetail feedbackDetail : list) {
            for (Feedback feedback1 : feedbackDetail.getFeedback()) {
                for (FeedbackComp feedbackComp : feedback1.getFeedbackComp()) {
                    int feedbackType = feedbackComp.getFeedbackType();
                    switch (feedbackType) {
                        case 3:
                            event.add(feedbackDetail.getEmpName()
                                    + " : " + feedbackComp.getFeedbackDescription());
                            break;
                        case 2:
                            general.add(feedbackDetail.getEmpName()
                                    + " : " + feedbackComp.getFeedbackDescription());
                            break;
                        case 1:
                            policy.add(feedbackDetail.getEmpName()
                                    + " : " + feedbackComp.getFeedbackDescription());
                            break;
                        default:
                            break;
                    }
                }
            }
        }

        listDataChild.put(listDataHeader.get(0), general); // Header, Child data
        listDataChild.put(listDataHeader.get(1), policy);
        listDataChild.put(listDataHeader.get(2), event);
    }

    private void parseDatafromJson() {
        Resources resources = getApplicationContext().getResources();
        InputStream inputStream = resources.openRawResource(R.raw.feedback_detail);
        String FeedbackDetailString = convertStreamToString(inputStream);
        Gson gson = new Gson();
        feedbackData = gson.fromJson(FeedbackDetailString, FeedbackData.class);
    }


    public HashMap getdata() {
        HashMap<String, Integer> DeptRatingMap = new HashMap<String, Integer>();

        // Adding child data
        List<String> general = new ArrayList<String>();
        List<String> policy = new ArrayList<String>();
        List<String> event = new ArrayList<String>();

        int ratingArray[] = new int[6];
        int EmpCount[] = new int[6];

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
            ratingArray[i] = ratingArray[i]/EmpCount[i];
        }
        DeptRatingMap.put("HR", ratingArray[0]);
        DeptRatingMap.put("IT", ratingArray[1]);
        DeptRatingMap.put("Marketing", ratingArray[2]);
        DeptRatingMap.put("Sales", ratingArray[3]);
        DeptRatingMap.put("finance", ratingArray[4]);
        DeptRatingMap.put("Admin", ratingArray[5]);
        return  DeptRatingMap;
    }

    public String convertStreamToString(InputStream is) {
        /*
         * To convert the InputStream to String we use the
		 * BufferedReader.readLine() method. We iterate until the BufferedReader
		 * return null which means there's no more data to read. Each line will
		 * appended to a StringBuilder and returned as String.
		 */
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hrmain, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
