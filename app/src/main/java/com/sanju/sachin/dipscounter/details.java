package com.sanju.sachin.dipscounter;

public class details {

    //mNumber_of_dips get total dips
    private String mNumberOfDips;

    private String mDate;

    //constractor
    public details(String NumberOfDips, String CurrentDate) {
        mNumberOfDips = NumberOfDips;
        mDate = CurrentDate;
    }

    public String getNumberOfDips() {
        return mNumberOfDips;
    }

    public String getDate() {
        return mDate;
    }
}
