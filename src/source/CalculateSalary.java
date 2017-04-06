package source;

/**
 * Created by liuzhili on 2017/4/4.
 */
public class CalculateSalary {
    public static double calculateSalary(int a, int b, int c) {
        double sales;
        if (a < 1 || b < 1 || c < 1) {
            return 0;
        }
        try {
            sales = 25 * a + 30 * b + 45 * c;
        } catch (Exception e) {
            System.out.println("number is too large.");
            return 0;
        }
        if (sales < 1000)
            return sales * 0.05;
        else if (sales < 1800)
            return sales * 0.1;
        else
            return sales * 0.15;
    }
}
