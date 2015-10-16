package com.dirtybit.HR;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

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
import java.util.HashMap;
import java.util.List;

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

        Resources resources = getApplicationContext().getResources();
        InputStream inputStream = resources.openRawResource(R.raw.feedback_detail);
        String walletDetailString;
        walletDetailString = convertStreamToString(inputStream);
        Gson gson = new Gson();
        FeedbackData feedbackData = gson.fromJson(walletDetailString, FeedbackData.class);

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
        for (FeedbackDetail feedbackDetail: list ) {
            for(Feedback feedback1 : feedbackDetail.getFeedback()){
                for(FeedbackComp feedbackComp : feedback1.getFeedbackComp()){
                    int feedbackType = feedbackComp.getFeedbackType();
                    switch (feedbackType){
                        case 3 :  event.add(feedbackDetail.getEmpId()
                                +" : "+ feedbackComp.getFeedbackDescription() );
                            break;
                        case 2 :   general.add(feedbackDetail.getEmpId()
                                +" : "+ feedbackComp.getFeedbackDescription() );
                            break;
                        case 1 :   policy.add(feedbackDetail.getEmpId()
                                +" : "+feedbackComp.getFeedbackDescription() );
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
