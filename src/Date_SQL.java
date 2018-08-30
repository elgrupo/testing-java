import java.util.ArrayList;
import java.util.Date;

public class Date_SQL
{

    private int year;
    private int month;
    private int day;

    //remember that the order is yyyy-mm-dd same as in sql
    public Date_SQL(String sql_unformated) {
        int[] temp_date = convertStringtoDate(sql_unformated);
        year = temp_date[0];
        month = temp_date[1];
        day = temp_date[2];
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

    //returns an int array in the order: year, month, day

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
