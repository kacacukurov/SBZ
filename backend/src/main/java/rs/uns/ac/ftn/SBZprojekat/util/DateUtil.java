package rs.uns.ac.ftn.SBZprojekat.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date addDays(Date date, int days, int months, int years)
    {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if(days != 0)
            cal.add(Calendar.DATE, days);
        else if(months != 0)
            cal.add(Calendar.MONTH, months);
        else if(years != 0)
            cal.add(Calendar.YEAR, years);
        return cal.getTime();
    }
}
