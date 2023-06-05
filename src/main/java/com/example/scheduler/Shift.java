package com.example.scheduler;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;

public class Shift
{
    private LocalTime startTime;
    private LocalTime endTime;
    private int minimumShiftLength;
    private int maximumShiftLength;
    private DayOfWeek shiftDay;
    private Calendar shiftDate;
    private ArrayList<Role> neededRoles;
    private boolean onCall;
    private boolean isFilled = false;
    private int amountOfEmployees = 1;

    public Shift(final DayOfWeek shiftDay, final LocalTime startTime, final LocalTime endTime, final int minimumShiftLength, final int maximumShiftLength, final ArrayList<Role> neededRoles, Calendar shiftDate) {
        this.shiftDay = shiftDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minimumShiftLength = minimumShiftLength;
        this.maximumShiftLength = maximumShiftLength;
        this.neededRoles = neededRoles;
        this.shiftDate = shiftDate;
    }

    public Shift(final DayOfWeek shiftDay, final LocalTime startTime, final LocalTime endTime, final int minimumShiftLength, final int maximumShiftLength, final ArrayList<Role> neededRoles, Calendar shiftDate, int amountOfEmployees) {
        this.shiftDay = shiftDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minimumShiftLength = minimumShiftLength;
        this.maximumShiftLength = maximumShiftLength;
        this.neededRoles = neededRoles;
        this.shiftDate = shiftDate;
        this.amountOfEmployees = amountOfEmployees;
    }

    public LocalTime getStartTime()
    {
        return startTime;
    }

    public void setStartTime(LocalTime startTime)
    {
        this.startTime = startTime;
    }

    public LocalTime getEndTime()
    {
        return endTime;
    }

    public void setEndTime(LocalTime endTime)
    {
        this.endTime = endTime;
    }
    public DayOfWeek getShiftDay()
    {
        return shiftDay;
    }

    public void setShiftDay(DayOfWeek shiftDay)
    {
        this.shiftDay = shiftDay;
    }

    public int getMinimumShiftLength()
    {
        return minimumShiftLength;
    }

    public void setMinimumShiftLength(int minimumShiftLength)
    {
        this.minimumShiftLength = minimumShiftLength;
    }

    public int getMaximumShiftLength()
    {
        return maximumShiftLength;
    }

    public void setMaximumShiftLength(int maximumShiftLength)
    {
        this.maximumShiftLength = maximumShiftLength;
    }

    public ArrayList<Role> getNeededRoles() {
        return neededRoles;
    }

    public void setNeededRoles(ArrayList<Role> neededRoles) {
        this.neededRoles = neededRoles;
    }

    public boolean isOnCall()
    {
        return onCall;
    }

    public void setOnCall(boolean onCall)
    {
        this.onCall = onCall;
    }

    public int shiftLength() {
        //Return the length of the shift in hours
        return endTime.getHour() - startTime.getHour();
    }

    public int getAmountOfEmployees() {
        return amountOfEmployees;
    }

    public Calendar getShiftDate() {
        return shiftDate;
    }
}
