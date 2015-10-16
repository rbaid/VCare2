package com.dirtybit.model;
import java.util.ArrayList;
    import java.util.List;

    public class Feedback {

        private long FeebbackDate;
        private Integer DayRating;
        private List<FeedbackComp> FeedbackComp = new ArrayList<FeedbackComp>();

        /**
         *
         * @return
         * The FeebbackDate
         */
        public long getFeebbackDate() {
            return FeebbackDate;
        }

        /**
         *
         * @param FeebbackDate
         * The FeebbackDate
         */
        public void setFeebbackDate(long FeebbackDate) {
            this.FeebbackDate = FeebbackDate;
        }

        /**
         *
         * @return
         * The DayRating
         */
        public Integer getDayRating() {
            return DayRating;
        }

        /**
         *
         * @param DayRating
         * The DayRating
         */
        public void setDayRating(Integer DayRating) {
            this.DayRating = DayRating;
        }

        /**
         *
         * @return
         * The FeedbackComp
         */
        public List<FeedbackComp> getFeedbackComp() {
            return FeedbackComp;
        }

        /**
         *
         * @param FeedbackComp
         * The FeedbackComp
         */
        public void setFeedbackComp(List<FeedbackComp> FeedbackComp) {
            this.FeedbackComp = FeedbackComp;
        }

    }

