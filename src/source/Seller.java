package source;

/* Created by AMXPC on 2017/6/9. */

import java.text.DecimalFormat;

public class Seller {
    public static double commission(double sale, int offday, double rate) {
        double commission = 0.0;
        if(sale > 200 && offday <= 10) {
            if(rate >= 60) {
                commission = sale / 7;
            }
        }
        else  if(rate <= 85) {
            commission = sale / 6;
        }
        else {
            commission = sale / 5;
        }
        DecimalFormat df = new DecimalFormat("#.00");
        return Double.valueOf(df.format(commission));
    }
}
