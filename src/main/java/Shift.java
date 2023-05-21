import java.time.LocalTime;
import java.util.ArrayList;

public class Shift
{
    private LocalTime startTime;
    private LocalTime endTime;
    private int minimumShiftLength;
    private int maximumShiftLength;
    private ArrayList<String> role;
    private Employee employee;
    private boolean onCall;

    public Shift(final LocalTime startTime, final LocalTime endTime, final int minimumShiftLength,
                 final int maximumShiftLength, final ArrayList<String> role, final Employee employee)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.minimumShiftLength = minimumShiftLength;
        this.maximumShiftLength = maximumShiftLength;
        this.role = role;
        this.employee = employee;
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

    public ArrayList<String> getRole()
    {
        return role;
    }

    public void setRole(ArrayList<String> role)
    {
        this.role = role;
    }

    public Employee getEmployee()
    {
        return employee;
    }

    public void setEmployee(Employee employee)
    {
        this.employee = employee;
    }

    public boolean isOnCall()
    {
        return onCall;
    }

    public void setOnCall(boolean onCall)
    {
        this.onCall = onCall;
    }
}
