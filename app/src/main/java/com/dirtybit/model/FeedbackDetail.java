package com.dirtybit.model;
import java.util.ArrayList;
import java.util.List;

public class FeedbackDetail {

    private Integer EmpId;
    private List<Feedback> Feedback = new ArrayList<Feedback>();

    /**
     *
     * @return
     * The EmpId
     */
    public Integer getEmpId() {
        return EmpId;
    }

    /**
     *
     * @param EmpId
     * The EmpId
     */
    public void setEmpId(Integer EmpId) {
        this.EmpId = EmpId;
    }

    /**
     *
     * @return
     * The Feedback
     */
    public List<Feedback> getFeedback() {
        return Feedback;
    }

    /**
     *
     * @param Feedback
     * The Feedback
     */
    public void setFeedback(List<Feedback> Feedback) {
        this.Feedback = Feedback;
    }

}