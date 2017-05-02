package source;

/* Created by AMXPC on 2017/5/2. */

import java.math.BigDecimal;

public class MobilePhone {
    public static double expense(int minute, int overdue) {
        double total;
        int basic = 25;
        double perMinute = 0.15;
        if(minute < 0 || overdue < 0 || minute > 44640 || overdue > 11) {
            return -1;
        }
        else if(minute <= 60 && overdue <= 1) {
            total = basic + 0.99 * perMinute * minute;
        }
        else if(minute > 60 && minute <= 120 && overdue <= 2) {
            total = basic + 0.985 * perMinute * minute;
        }
        else if(minute > 120 && minute <= 180 && overdue <= 3) {
            total = basic + 0.98 * perMinute * minute;
        }
        else if(minute > 180 && minute <= 300 && overdue <= 3) {
            total = basic + 0.975 * perMinute * minute;
        }
        else if(minute > 300 && overdue <= 6) {
            total = basic + 0.97 * perMinute * minute;
        }
        else {
            total = basic + perMinute * minute;
        }

        BigDecimal b = new BigDecimal(total);
        return b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
