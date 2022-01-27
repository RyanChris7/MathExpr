public class MathExpr {

    public static Double parse(String str){
        if (str == null || str.length() == 0) {
            return null;
        }
        return count(str.replace(" ", ""));
    }
    public static Double count(String str) {

        if (str.startsWith("(") && str.endsWith(")")) {
            return count(str.substring(1, str.length() - 1));
        }

        else if (str.startsWith("sin(") && str.endsWith(")")) {
            return Math.sin(count(str.substring(1, str.length() - 1)));
        } else if (str.startsWith("cos(") && str.endsWith(")")) {
            return Math.cos(count(str.substring(1, str.length() - 1)));
        } else if (str.startsWith("tan(") && str.endsWith(")")) {
            return Math.tan(count(str.substring(1, str.length() - 1)));
        } else if (str.startsWith("sqrt(") && str.endsWith(")")) {
            return Math.sqrt(count(str.substring(1, str.length() - 1)));
        }

        String[] Arr = new String[]{str};
        double lhs = getoperand(Arr);
        str = Arr[0];
        if (str.length() == 0) {
            return lhs;
        }
        char operator = str.charAt(0);
        str = str.substring(1);
        
        // if (operator == 's' || operator == 'c'||operator == 't' ){
        //     return count(lhs);
        // }
        
        while (operator == '*' || operator == '/') {
            Arr[0] = str;
            double rhs = getoperand(Arr);
            str = Arr[0];
            if (operator == '*') {
                lhs = lhs * rhs;
            } else {
                lhs = lhs / rhs;
            }
            if (str.length() > 0) {
                operator = str.charAt(0);
                str = str.substring(1);
            } else {
                return lhs;
            }
        }
        if (operator == '+') {
            return lhs + count(str);
        } else {
            return lhs - count(str);
        }
    }

    private static double getoperand(String[] indx){
        double res;
        if (indx[0].startsWith("(")) {
            int open = 1;
            int i = 1;
            while (open != 0) {
                if (indx[0].charAt(i) == '(') {
                    open++;
                } else if (indx[0].charAt(i) == ')') {
                    open--;
                }
                i++;
            }
            res = count(indx[0].substring(1, i - 1));
            indx[0] = indx[0].substring(i);
        } else {
            int i = 1;
            if (indx[0].charAt(0) == '-') {
                i++;
            }
            while (indx[0].length() > i && isNumber((int) indx[0].charAt(i))) {
                i++;
            }
            res = Double.parseDouble(indx[0].substring(0, i));
            indx[0] = indx[0].substring(i);
        }
        return res;
    }


    private static boolean isNumber(int c) {
        int zero = (int) '0';
        int nine = (int) '9';
        return (c >= zero && c <= nine) || c =='.';
    }
}
