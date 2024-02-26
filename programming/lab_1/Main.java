public class Main {

    public static void main(String[] args) {
        int[] c = new int[18];
        for (int i = 20; i >= 3; i--){
            c[20 - i] = i;
        }

        double [] x = new double[20];
        for (int i = 0; i < 20; i++){
            x[i] = Math.random() * 23 - 10;
        }

        double[][] y = new double[18][];

        for (int i = 0; i < 18; i++){
            y[i] = new double[20];
                for (int j = 0; j < 20; j++){
                    switch (c[i]){
                        case 1: y[i][j] = Math.asin(Math.pow(Math.exp(-Math.abs(x[j])), 2));
                            break;
                        case 3, 5, 9, 10, 13, 14, 16, 17, 18: y[i][j] = Math.pow(Math.asin(Math.sin(x[j])), (Math.exp(Math.pow(x[j]/2, x[j])) / (1/3 + Math.pow(Math.cos(x[j]) + 1, 2))));
                            break;
                        default: y[i][j] = Math.log(Math.abs(Math.asin(1/Math.exp(Math.acos(1/Math.exp(Math.abs(x[j])))))));
                            break;
                    }
                }
            }

            for (double[] element: y){
                for (double el: element){
                    System.out.printf("%9.3f ", el);
                }
                System.out.println();
            }
        }
}
