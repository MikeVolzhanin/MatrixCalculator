import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N, M, K, L, action;
        Matrix matrix, matrix1 = null;
        System.out.println("""
                Действия над матрицами:\s
                1) Cумма матриц
                2) Разность матриц
                3) Умножение матриц
                4) Умножить матрицу на число
                5) Посчитать детерминант матрицы
                6) Транспонировать матрицу
                """
        );

        action = scanner.nextInt();

        if(action < 4){
            System.out.println("Введите размерность первой матрицы: ");
            N = scanner.nextInt();
            M = scanner.nextInt();

            System.out.println("Введите размерность второй матрицы");
            K = scanner.nextInt();
            L = scanner.nextInt();

            System.out.println("Введите значения первой матрицы: ");
            matrix = new Matrix(N, M);
            matrix.fillMatrix();

            System.out.println("\nМатрица #1\n" + matrix);

            System.out.println("Введите значения второй матрицы");
            matrix1 = new Matrix(K, L);
            matrix1.fillMatrix();

            System.out.println("Матрица #2\n" + matrix1);
        } else{
            System.out.println("Введите размерность матрицы: ");
            N = scanner.nextInt();
            M = scanner.nextInt();

            System.out.println("Введите значения матрицы: ");
            matrix = new Matrix(N, M);
            matrix.fillMatrix();

            System.out.println("\nМатрица\n" + matrix);
        }


        Matrix answer = null;

        if(action == 1){
            System.out.println("Сумма матриц: ");
            answer = Matrix.add(matrix, matrix1, 1); // Если сложение то тип 1, если разность, то 0
        }
        else if(action == 2){
            System.out.println("Разность матриц: ");
            answer = Matrix.add(matrix, matrix1, 0);
        }
        else if(action == 3){
            System.out.println("Произведение матриц: ");
            answer = Matrix.multiply(matrix, matrix1);
        }
        else if(action == 4){
            System.out.println("Введите число: ");
            String strNumber = scanner.next();
            ComplexNumber number = ComplexNumber.convertToComplexNumber(strNumber);
            answer = Matrix.multiplyByNumber(matrix, number);
            System.out.println("Матрица умноженная на число: ");
        }
        else if(action == 5){
            System.out.println("Детрминант матрицы: ");
            ComplexNumber complexNumber = Matrix.det(matrix.getMatrix());
            System.out.println(complexNumber);
            return;
        }
        else if(action == 6){
            System.out.println("Транспонированная матрица: ");
            answer = Matrix.transposeMatrix(matrix);
        }
        else{
            System.out.println("Ошибка!");
        }

        System.out.println(answer);
    }
}