package modelo;

public class Operaciones {

    public static double operacionesBasicas(String num1, String num2){

        double n1 = Double.valueOf(num1.substring(0,num1.length()-1));
        double n2 = Double.valueOf(num2);
        char sign = num1.charAt(num1.length()-1);
        double solution = 0;

        switch (sign){
            case '+':
                solution = n1 + n2;
                break;
            case '-':
                solution = n1 - n2;
                break;
            case '*':
                solution = n1 * n2;
                System.out.println(solution);
                break;
            case '/':
                solution = n1 / n2;
                break;
            case '^':
                solution = Math.pow(n1, n2);
                break;

        }
        return solution;
    }

    public static double operacionesCientifica(String num1, char signo) {
        double n1 = Double.valueOf(num1);
        n1 = Math.toRadians(n1);
        char sign = signo;
        double solution = 0;
        switch (sign) {

            case 's':

                solution = Math.sin(n1);
                System.out.println(solution);
                break;
            case 'c':
                solution = Math.cos(n1);
                break;
            case 't':
                solution = Math.tan(n1);
                break;
        }
        return solution;
    }
    /**
    public static double operacionesComplejas(String num1, char sign){
        double n1 = Double.valueOf(num1.substring(0,num1.length()));
        switch (sign){
            case 'p':
                n1 = n1*n1;
                break;
            case 's':
                n1 = Math.sqrt(n1);
                break;
            case 'i':
                n1 = 1/n1;
                break;
        }
        return n1;
    }
     **/
}
