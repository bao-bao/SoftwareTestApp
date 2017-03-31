/* Created by AMXPC on 2017/3/15. */

public class JudgeTriangle {
    public static String judgeTriangle(double a, double b, double c) {
        if(a <= 0 || b <= 0 || c <= 0) {
            return "Error: Less than 0";
        }

        if((a + b) <= c && (b + c) <= a && (c + a) <= b) {
            return "Error: Can't be triangle";
        }

        int side_relation;
        int angle_relation;

        double side_max = (a >= b ? a : b) >= c ? (a >= b ? a : b) : c;
        if(side_max == b) {
            b = a;
            a = side_max;
        } else if(side_max == c) {
            c = a;
            a = side_max;
        }
        if(a * a < b * b + c * c) {
            angle_relation = 1;
        } else if(a * a > b * b + c * c) {
            angle_relation = 3;
        } else {
            angle_relation = 2;
        }

        if(a == b || a == c || b == c) {
            if(a == b && b == c) {
                side_relation = 5;
            }
            else {
                side_relation = 4;
            }
        }
        else {
            side_relation = 3;
        }

        switch (angle_relation * side_relation) {
            case 3: return "锐角三角形";
            case 4: return "等腰锐角三角形";
            case 5: return "等边三角形";
            case 6: return "直角三角形";
            case 8: return "等腰直角三角形";
            case 9: return "钝角三角形";
            case 12: return "等腰钝角三角形";
            default:
                return "Error: Can't recognize";
        }
    }
}
