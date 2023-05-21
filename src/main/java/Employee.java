import java.time.LocalTime;
import java.util.ArrayList;

public class Employee
{
    private LocalTime Availability;
    private LocalTime Preferences;
    private int priority;
    private String firstName;
    private String lastName;
    private int ID;
    private LocalTime birthDate;
    private ArrayList<String> roles;
    private int maximumDesiredHours;
    private int MaximumHours;

    //Employee Constructor
    public Employee(final String firstName, final String lastName, final int ID,
                    final LocalTime birthDate)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ID = ID;
        this.birthDate = birthDate;
    }


    //Getters and setters for Employee
    public void setAvailability(final LocalTime availability)
    {
        this.Availability = availability;
    }

    public LocalTime getAvailability()
    {
        return this.Availability;
    }

    public void setPreferences(final LocalTime preferences)
    {
        this.Preferences = preferences;
    }

    public LocalTime getPreferences()
    {
        return this.Preferences;
    }

    public int getPriority()
    {
        return priority;
    }

    public void setPriority(int priority)
    {
        this.priority = priority;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public int getID()
    {
        return ID;
    }

    public void setID(int ID)
    {
        this.ID = ID;
    }

    public LocalTime getBirthDate()
    {
        return birthDate;
    }

    public void setBirthDate(LocalTime birthDate)
    {
        this.birthDate = birthDate;
    }

    public ArrayList<String> getRoles()
    {
        return roles;
    }

    public void setRoles(ArrayList<String> roles)
    {
        this.roles = roles;
    }

    public int getMaximumDesiredHours()
    {
        return maximumDesiredHours;
    }

    public void setMaximumDesiredHours(int maximumDesiredHours)
    {
        this.maximumDesiredHours = maximumDesiredHours;
    }

    public int getMaximumHours()
    {
        return MaximumHours;
    }

    public void setMaximumHours(int maximumHours)
    {
        MaximumHours = maximumHours;
    }
}
