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
    private boolean isFilled = false;
    private int EmployeeID = -1;

    public Shift(final DayOfWeek shiftDay, final LocalTime startTime, final LocalTime endTime, final int minimumShiftLength, final int maximumShiftLength, final ArrayList<Role> neededRoles, Calendar shiftDate) {
        this.shiftDay = shiftDay;
        this.startTime = startTime;
        this.endTime = endTime;
        this.minimumShiftLength = minimumShiftLength;
        this.maximumShiftLength = maximumShiftLength;
        this.neededRoles = neededRoles;
        this.shiftDate = shiftDate;
    }

    //To string method
    public String toString() {
        String holder = "";
        holder += shiftDay + " " + startTime + " " + endTime + " " + minimumShiftLength + " " + maximumShiftLength + " ^";
        for (int i = 0; i < neededRoles.size(); i++) {
            holder += neededRoles.get(i).getName() + "^";
        }
        holder += shiftDate.get(Calendar.DAY_OF_YEAR) + " " + EmployeeID;
        return holder;
    }

    public void setEmployeeID(int EmployeeID) {
        this.EmployeeID = EmployeeID;
    }

    public int getEmployeeID() {
        return EmployeeID;
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

    public int shiftLength() {
        //Return the length of the shift in hours
        return endTime.getHour() - startTime.getHour();
    }

    public Calendar getShiftDate() {
        return shiftDate;
    }
}
