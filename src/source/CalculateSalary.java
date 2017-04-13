package source;

/**
 * Created by liuzhili on 2017/4/4.
 */
public class CalculateSalary {
    public static double calculateSalary(int a, int b, int c) {
        double sales;
        if (a < 1 || b < 1 || c < 1 || a > 90 || b > 70 || c > 80) {
            return 0;
        }
        sales = 25 * a + 45 * b + 30 * c;
        if (sales < 1000)
            return sales * 0.05;
        else if (sales < 1800)
            return sales * 0.1;
        else
            return sales * 0.15;
    }
}
