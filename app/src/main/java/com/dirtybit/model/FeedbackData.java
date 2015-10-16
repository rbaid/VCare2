package com.dirtybit.model;
import java.util.ArrayList;
import java.util.List;

public class FeedbackData {

    private List<FeedbackDetail> FeedbackDetails = new ArrayList<FeedbackDetail>();

    /**
     *
     * @return
     * The FeedbackDetails
     */
    public List<FeedbackDetail> getFeedbackDetails() {
        return FeedbackDetails;
    }

    /**
     *
     * @param FeedbackDetails
     * The FeedbackDetails
     */
    public void setFeedbackDetails(List<FeedbackDetail> FeedbackDetails) {
        this.FeedbackDetails = FeedbackDetails;
    }

}