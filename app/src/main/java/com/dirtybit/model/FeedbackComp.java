package com.dirtybit.model;
import java.util.ArrayList;
import java.util.List;

public class FeedbackComp {

    private int FeedbackType;
    private String Name;
    private Integer EmpID;
    private Integer Rating;
    private String FeedbackDescription;
    private int EventNo;
    private int PolicyNo;

    public int getPolicyNo() {
        return PolicyNo;
    }

    public void setPolicyNo(int policyNo) {
        PolicyNo = policyNo;
    }

    /**
     *
     * @return
     * The FeedbackType
     */
    public int getFeedbackType() {
        return FeedbackType;
    }

    /**
     *
     * @param FeedbackType
     * The FeedbackType
     */
    public void setFeedbackType(int FeedbackType) {
        this.FeedbackType = FeedbackType;
    }

    /**
     *
     * @return
     * The Name
     */
    public String getName() {
        return Name;
    }

    /**
     *
     * @param Name
     * The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     *
     * @return
     * The EmpID
     */
    public Integer getEmpID() {
        return EmpID;
    }

    /**
     *
     * @param EmpID
     * The EmpID
     */
    public void setEmpID(Integer EmpID) {
        this.EmpID = EmpID;
    }

    /**
     *
     * @return
     * The Rating
     */
    public Integer getRating() {
        return Rating;
    }

    /**
     *
     * @param Rating
     * The Rating
     */
    public void setRating(Integer Rating) {
        this.Rating = Rating;
    }


    /**
     *
     * @return
     * The FeedbackDescription
     */
    public String getFeedbackDescription() {
        return FeedbackDescription;
    }

    /**
     *
     * @param FeedbackDescription
     * The GeneralFeedbackD
     */
    public void setFeedbackDescription(String FeedbackDescription) {
        this.FeedbackDescription = FeedbackDescription;
    }

    /**
     *
     * @return
     * The EventName
     */
    public int getEventNo() {
        return EventNo;
    }

    /**
     *
     * @param EventNo
     * The EventName
     */
    public void setEventNo(int EventNo) {
        this.EventNo = EventNo;
    }


}