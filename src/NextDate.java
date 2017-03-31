/* Created by AMXPC on 2017/3/15. */

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NextDate {
    public static String getNextDate(String time) {
            Calendar calendar = Calendar.getInstance();
            Date date;
            SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                // is right?
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (!time.equals(sdf.format(sdf.parse(time)))) {
                    return "0000-00-00";
                }
                // get next date
                date = bartDateFormat.parse(time);
                calendar.setTime(date);
                calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DATE) + 1);
                date = calendar.getTime();
                bartDateFormat.format(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return "0000-00-00";
            }
            return bartDateFormat.format(date);
        }
    }
