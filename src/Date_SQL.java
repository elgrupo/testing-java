import java.util.ArrayList;
import java.util.Date;

public class Date_SQL
{

    private int year;
    private int month;
    private int day;
    //needed for some comparisons.
    private int bike_index;
    private String unSepDate;

    //remember that the order is yyyy-mm-dd same as in sql
    public Date_SQL(String sql_unformated) {
        unSepDate = sql_unformated;
        int[] temp_date = convertStringtoDate(sql_unformated);
        year = temp_date[0];
        month = temp_date[1];
        day = temp_date[2];
    }

    public Date_SQL(String sql_unformated, int bike_index_arg) {
        unSepDate = sql_unformated;
        int[] temp_date = convertStringtoDate(sql_unformated);
        year = temp_date[0];
        month = temp_date[1];
        day = temp_date[2];

        bike_index = bike_index_arg;
    }

    public Date_SQL(int year_arg, int month_arg, int day_arg)
    {
        year = year_arg;
        month = month_arg;
        day = day_arg;
    }

    //only do this if the string is in the format: yyyy-mm-dd
    //Should only be in this format if coming from mysql but convert android statement
    //will happen if needed.
    static public int[] convertStringtoDate(String sql_unformated)
    {
        int[] tempIntArr = new int[3];
        String[] split = sql_unformated.split("-");
        for (int i =0; i < split.length; i++)
        {
            if (i < 3) {
                tempIntArr[i] = Integer.parseInt(split[i]);
            }
            }
            return tempIntArr;
    }

    //sorts from oldest to the newest.
    static public ArrayList<Date_SQL> sortArray(ArrayList<Date_SQL> arr_dates_temp)
    {

        ArrayList<Date_SQL> arr_dates = new ArrayList<>(arr_dates_temp);
        ArrayList<Date_SQL> sorted = new ArrayList<>();

        System.out.println(arr_dates.size());
        while (arr_dates.size() >0)
        {
            int temp_index = findOldestDate(arr_dates);
            Date_SQL oldest_temp = arr_dates.get(temp_index);
            sorted.add(oldest_temp);
            arr_dates.remove(temp_index);
            System.out.println(sorted.size());
        }
        return sorted;
    }

    //Linear searches for the oldest date (aka smallest date)
    static public int findOldestDate(ArrayList<Date_SQL> arr_dates) {
        int tempIndex = 0;
        for (int i = 0; i < arr_dates.size()-1; i++)
        {
            int temp2Index = i+1;
            if (compareDates(arr_dates.get(tempIndex),arr_dates.get(temp2Index)) > 0)
            {
                tempIndex = temp2Index;
            }
        }
        return tempIndex;
    }

    //returns 0 if equal, -1 if date1 is smaller or 1 if date2 is smaller
    static public int compareDates(Date_SQL date1, Date_SQL date2)
    {
        if (Integer.compare(date1.getY(),date2.getY()) == 0)
        {
            if (Integer.compare(date1.getM(),date2.getM()) != 0)
            {
                return (Integer.compare(date1.getM(),date2.getM())>0) ? 1: -1;
            }
            else
            {
                if ( Integer.compare(date1.getD(),date2.getD()) != 0)
                {
                    return (Integer.compare(date1.getD(),date2.getD())>0) ? 1: -1;
                }
                else
                {
                    return 0;
                }
            }
        }
            return (Integer.compare(date1.getY(),date2.getY())>0) ? 1: -1;

    }

    //Format yyyy-mm-dd
    public String getStringDate() {return unSepDate;}

    public int getY()
    {
        return year;
    }

    public int getM()
    {
        return month;
    }

    public int getD()
    {
        return day;
    }
}
