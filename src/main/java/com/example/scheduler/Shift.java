package com.example.scheduler;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

public class Shift
{
    private LocalTime startTime;
    private LocalTime endTime;
    private int minimumShiftLength;
    private int maximumShiftLength;
    private DayOfWeek shiftDay;
    private ArrayList<String> neededRoles;
    //private Employee employee; don't need employee reference here
    private boolean onCall;

    public Shift(final DayOfWeek shiftDay, final LocalTime startTime, final LocalTime endTime, final int minimumShiftLength,
                 final int maximumShiftLength, final ArrayList<String> neededRoles, final Employee employee)
    {
        this.shiftDay = shiftDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minimumShiftLength = minimumShiftLength;
        this.maximumShiftLength = maximumShiftLength;
        this.neededRoles = neededRoles;
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

    public ArrayList<String> getneededRoles()
    {
        return neededRoles;
    }

    public void setneededRoles(ArrayList<String> neededRoles)
    {
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
        return (int) startTime.until(endTime, java.time.temporal.ChronoUnit.HOURS);
    }
}
