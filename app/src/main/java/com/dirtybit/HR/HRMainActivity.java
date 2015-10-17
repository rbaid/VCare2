package com.dirtybit.HR;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.dirtybit.model.AllFeedBackDetail;
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
if(AllFeedBackDetail.getInstance().getFeedbackData() == null) {
    Toast.makeText(getApplicationContext(),"No data to analyze yet" , Toast.LENGTH_LONG).show();
    finish();
return;
}
        List<FeedbackDetail> list = AllFeedBackDetail.getInstance().getFeedbackData().getFeedbackDetails();
        for (FeedbackDetail feedbackDetail : list) {
            for (Feedback feedback1 : feedbackDetail.getFeedback()) {
                for (FeedbackComp feedbackComp : feedback1.getFeedbackComp()) {
                    int feedbackType = feedbackComp.getFeedbackType();
                    switch (feedbackType) {
                        case 3:
                            event.add("  "+feedbackDetail.getEmpName()
                                    + " : " + feedbackComp.getFeedbackDescription());
                            break;
                        case 2:
                            general.add("  "+feedbackDetail.getEmpName()
                                    + " : " + feedbackComp.getFeedbackDescription());
                            break;
                        case 1:
                            policy.add("  "+feedbackDetail.getEmpName()
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

    public void parseDatafromJson() {
        /*Resources resources = getApplicationContext().getResources();
        InputStream inputStream = resources.openRawResource(R.raw.feedback_detail);
        String FeedbackDetailString = DataUtils.convertStreamToString(inputStream);
        Gson gson = new Gson();
        feedbackData = gson.fromJson(FeedbackDetailString, FeedbackData.class);*/
        SharedPreferences sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        String FeedbackDetailString = sharedPreferences.getString("FeedbackDetail", null);
        Log.i("Ritu", "json reading = " + FeedbackDetailString);
        Gson gson = new Gson();
        FeedbackData feedbackData1 = gson.fromJson(FeedbackDetailString, FeedbackData.class);
        AllFeedBackDetail.getInstance().setFeedbackData(feedbackData1);
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
