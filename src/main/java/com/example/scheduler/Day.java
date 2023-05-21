package com.example.scheduler;

import java.time.LocalTime;
import java.util.ArrayList;

public class Day {
    private ArrayList shiftArrayList;

    private LocalTime[] workingHours;

    public Day(ArrayList shiftArrayList, LocalTime[] workingHours) {
        this.shiftArrayList = shiftArrayList;
        this.workingHours = workingHours;
    }

    public ArrayList getShiftArrayList() {
        return shiftArrayList;
    }

    public LocalTime[] getWorkingHours() {
        return workingHours;
    }

    public void setShiftArray(ArrayList shiftArrayList) {
        this.shiftArrayList = shiftArrayList;
    }

    public void setWorkingHours(LocalTime[] workingHours) {
        this.workingHours = workingHours;
    }
}
