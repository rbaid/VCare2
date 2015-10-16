package com.dirtybit.model;

public class AllFeedBackDetail {
    private FeedbackData feedbackData;
    private static AllFeedBackDetail allFeedBackDetail;
    private AllFeedBackDetail(){
    }

    public void setFeedbackData(FeedbackData feedbackData) {
        this.feedbackData = feedbackData;
    }


    public static AllFeedBackDetail getInstance() {
        if(allFeedBackDetail == null)
            allFeedBackDetail = new AllFeedBackDetail();
        return allFeedBackDetail;
    }

    public FeedbackData getFeedbackData() {
        return feedbackData;
    }
}
