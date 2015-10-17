package com.dirtybit.vcare;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dirtybit.Charts.BarChartActivity;
import com.dirtybit.Charts.ChartActivity;
import com.dirtybit.Charts.PieChartFragment;
import com.dirtybit.HR.DataUtils;
import com.dirtybit.model.AllFeedBackDetail;
import com.dirtybit.model.FeedbackData;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.email_sign_in_button);
        loginButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent hr_intent = new Intent(LoginActivity.this, com.dirtybit.HR.HRMainActivity.class);
                Intent employee_intent = new Intent(LoginActivity.this,EmployeeMainActivity.class);
                Intent c_intent = new Intent(LoginActivity.this,BarChartActivity.class);
                Intent b_intent = new Intent(LoginActivity.this,ChartActivity.class);

                String email = ((EditText) findViewById(R.id.email)).getText().toString();
                String password = ((EditText) findViewById(R.id.password)).getText().toString();

                Log.i("com.LoginActivity" , "Email " + email + " Password " + password);
                if(email.equals("employee") && password.equals("e")) {
                    startActivity(employee_intent);
                }
                else if(email.equals("hr") && password.equals("hr")) {
                    startActivity(hr_intent);
                }
                else if(email.equals("c") && password.equals("c")) {
                    startActivity(c_intent);
                }
                else if(email.equals("b") && password.equals("b")) {
                    startActivity(b_intent);
                }
                else {
                    Toast.makeText(LoginActivity.this,"Wrong username or password",Toast.LENGTH_LONG).show();
                }
            }
        });
        parseData();
    }

    void parseData() {

        SharedPreferences sharedPreferences = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        String FeedbackDetailString = sharedPreferences.getString("FeedbackDetail", null);
        if (FeedbackDetailString == null) {
            Resources resources = getApplicationContext().getResources();
            InputStream inputStream = resources.openRawResource(R.raw.feedback_detail);
            String FeedbackDetailString1 = DataUtils.convertStreamToString(inputStream);
            Gson gson = new Gson();
            FeedbackData feedbackData = gson.fromJson(FeedbackDetailString1, FeedbackData.class);
            AllFeedBackDetail.getInstance().setFeedbackData(feedbackData);
        } else {
            Gson gson = new Gson();
            FeedbackData feedbackData = gson.fromJson(FeedbackDetailString, FeedbackData.class);
            Log.i("Ritu", "json login reading = " + FeedbackDetailString);
            AllFeedBackDetail.getInstance().setFeedbackData(feedbackData);

        }
    }
}

