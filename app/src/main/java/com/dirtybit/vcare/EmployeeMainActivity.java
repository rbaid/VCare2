package com.dirtybit.vcare;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.dirtybit.model.AllFeedBackDetail;
import com.dirtybit.model.Feedback;
import com.dirtybit.model.FeedbackComp;
import com.dirtybit.model.FeedbackData;
import com.dirtybit.model.FeedbackDetail;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EmployeeMainActivity extends AppCompatActivity implements View.OnClickListener {

    RatingBar ratingBar;
    Spinner eventSpinner;
    Spinner policySpinner;
    TextView policy_description;
    TextView event_description;
    TextView general_description;
    RatingBar eventRating;
    RatingBar policyRating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        ratingBar = (RatingBar) findViewById(R.id.ratingbar);
        eventSpinner = (Spinner) findViewById(R.id.event_spinner);
        policySpinner = (Spinner) findViewById(R.id.policy_spinner);
        policy_description = (TextView) findViewById(R.id.policy_description);
        event_description = (TextView) findViewById(R.id.event_description);
        general_description = (TextView) findViewById(R.id.general_description);
        eventRating = (RatingBar) findViewById(R.id.rating_event);
        policyRating = (RatingBar) findViewById(R.id.rating_policy);

        findViewById(R.id.submit_btn).setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submit_btn:
                FeedbackData feedbackData = AllFeedBackDetail.getInstance().getFeedbackData();
                if (feedbackData == null) {
                    feedbackData = new FeedbackData();
                }
                List<FeedbackDetail> feedbackDetailList = feedbackData.getFeedbackDetails();
                FeedbackDetail feedbackDetail = new FeedbackDetail();
                feedbackDetail.setEmpName("Ritu");
                feedbackDetail.setEmpId(1);
                feedbackDetail.setDepartment("HR");
                List<Feedback> feedbacks = new ArrayList<>();
                Feedback feedback = new Feedback();
                Calendar calendar = Calendar.getInstance();
                long currentTime = calendar.getTimeInMillis();
                feedback.setFeebbackDate(currentTime);
                feedback.setDayRating((int) ratingBar.getRating());
                List<FeedbackComp> feedbackComps = new ArrayList<>();
                int policyNo = policySpinner.getSelectedItemPosition();
                if (policyNo > 0) {
                    FeedbackComp policyFeedbackComp = new FeedbackComp();
                    policyFeedbackComp.setPolicyNo(policyNo);
                    if (policy_description.getText() != null)
                        policyFeedbackComp.setFeedbackDescription(policy_description.getText().toString());
                    policyFeedbackComp.setRating((int) policyRating.getRating());
                    policyFeedbackComp.setFeedbackType(1);
                    feedbackComps.add(policyFeedbackComp);
                }

                int eventNo = eventSpinner.getSelectedItemPosition();
                if (eventNo > 0) {
                    FeedbackComp eventFeedbackComp = new FeedbackComp();
                    eventFeedbackComp.setFeedbackType(3);
                    eventFeedbackComp.setEventNo(eventNo);
                    if (event_description.getText() != null)
                        eventFeedbackComp.setFeedbackDescription(event_description.getText().toString());
                    eventFeedbackComp.setRating((int) eventRating.getRating());
                    feedbackComps.add(eventFeedbackComp);
                }
                if (!isNullOrEmpty(general_description.getText().toString())) {
                    FeedbackComp generalFeedbackComp = new FeedbackComp();
                    generalFeedbackComp.setFeedbackType(2);
                    generalFeedbackComp.setFeedbackDescription(general_description.getText().toString());
                    feedbackComps.add(generalFeedbackComp);
                }

                feedback.setFeedbackComp(feedbackComps);
                feedbacks.add(feedback);
                feedbackDetail.setFeedback(feedbacks);
                feedbackDetailList.add(feedbackDetail);
                Gson gson = new Gson();
                String feedbackJson = gson.toJson(feedbackData, FeedbackData.class);
                SharedPreferences sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("FeedbackDetail", feedbackJson);
                editor.commit();
                Log.i("Ritu", "json writing = " + feedbackData);
                break;
            default:
                break;
        }
    }
    public boolean isNullOrEmpty(final String str) {
        if (str == null) {
            return true;
        }
        if (str.trim().equals("")) {
            return true;
        }
        return false;
    }

}
