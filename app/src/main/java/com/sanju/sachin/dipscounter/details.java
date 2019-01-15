package com.sanju.sachin.dipscounter;

public class details {

    //mNumber_of_dips get total dips
    private String mNumberOfDips;
    private String mDate;
    private String mTime;

    //constractor
    public details(String NumberOfDips, String CurrentDate, String CurrentTime) {
        mNumberOfDips = NumberOfDips;
        mDate = CurrentDate;
        mTime = CurrentTime;
    }

    public String getNumberOfDips() {
        return mNumberOfDips;
    }

    public String getDate() {
        return mDate;
    }

    public String getTime() {
        return mTime;
    }


}
