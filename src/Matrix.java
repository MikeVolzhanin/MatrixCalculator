import java.util.Arrays;
import java.util.Scanner;

public class Matrix {
    ComplexNumber[][] matrix;
    int N, M;
    public Matrix(int N, int M) {
        this.matrix = new ComplexNumber[N][M];
        this.N = N;
        this.M = M;
    }

    public void fillMatrix(){
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < N; i++) {
            String[] line = scanner.nextLine().split(" ");
            for (int j = 0; j < M; j++) {
                matrix[i][j] = ComplexNumber.convertToComplexNumber(line[j]);
            }
        }
    }

    public static Matrix add(Matrix matrix, Matrix matrix1, int type){
        int N = matrix.N;
        int M = matrix.M;

        int K = matrix1.N;
        int L = matrix1.M;

        Matrix result = new Matrix(N,M);

        if(N == K && M == L){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(type == 1)
                        result.getMatrix()[i][j] = CalcComplexNumbers.add(matrix.getMatrix()[i][j], matrix1.getMatrix()[i][j]);
                    else
                        result.getMatrix()[i][j] = CalcComplexNumbers.sub(matrix.getMatrix()[i][j], matrix1.getMatrix()[i][j]);
                }
            }
            return result;
        }

        System.out.println("Нельзя складывать матрицы различной размерности!");
        return null;
    }

    public static Matrix multiply(Matrix matrix, Matrix matrix1){
        int N = matrix.N;
        int M = matrix.M;

        int K = matrix1.N;
        int L = matrix1.M;

        Matrix result = new Matrix(N,L);

        if(M == K){

            for(int i = 0; i < N; i++){
                for(int j = 0; j < L; j++){
                    result.getMatrix()[i][j] = new ComplexNumber(0, 0);
                    for(int k = 0; k < M; k++){
                        result.getMatrix()[i][j] = CalcComplexNumbers.add(result.getMatrix()[i][j],
                                CalcComplexNumbers.multiply(matrix.getMatrix()[i][k], matrix1.getMatrix()[k][j]));
                    }
                }
            }

            return result;
        }
        System.out.println("Ошибка: число столбцов матрицы K должно равнятся числу строк матрицы L");
        return null;
    }

    public static Matrix multiplyByNumber(Matrix matrix, ComplexNumber complexNumber){
        int N = matrix.N;
        int M = matrix.M;

        Matrix result = new Matrix(N, M);

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                result.getMatrix()[i][j] = CalcComplexNumbers.multiply(matrix.getMatrix()[i][j], complexNumber);
            }
        }

        return result;
    }

    public static Matrix transposeMatrix(Matrix matrix){
        Matrix result = new Matrix(matrix.M, matrix.N);

        for(int i = 0; i < matrix.N; i++){
            for(int j = 0; j < matrix.M; j++){
                result.getMatrix()[j][i] = matrix.getMatrix()[i][j];
            }
        }

        return result;
    }

    public static ComplexNumber det(ComplexNumber[][] matrix){
        ComplexNumber calcResult = new ComplexNumber(0 , 0);

        if (matrix.length==2){
            calcResult=
                    CalcComplexNumbers.sub(CalcComplexNumbers.multiply(matrix[0][0],matrix[1][1]),
                                            CalcComplexNumbers.multiply(matrix[1][0],matrix[0][1]));
        }
        else{
            int koeff=1;
            for(int i=0; i<matrix.length; i++){
                if(i%2==1){
                    koeff=-1;
                }
                else{
                    koeff=1;
                };
                ComplexNumber temp = new ComplexNumber(koeff, 0);
                calcResult = CalcComplexNumbers.add(calcResult,
                        CalcComplexNumbers.multiply(det(GetMinor(matrix,0,i)),
                                CalcComplexNumbers.multiply(temp, matrix[0][i])));
            }
        }

        return calcResult;
    }

    private static ComplexNumber[][] GetMinor(ComplexNumber[][] matrix, int row, int column) {
        int minorLength = matrix.length - 1;
        ComplexNumber[][] minor = new ComplexNumber[minorLength][minorLength];
        int dI = 0;//эти переменные для того, чтобы "пропускать" ненужные нам строку и столбец
        int dJ = 0;
        for (int i = 0; i <= minorLength; i++) {
            dJ = 0;
            for (int j = 0; j <= minorLength; j++) {
                if (i == row) {
                    dI = 1;
                } else {
                    if (j == column) {
                        dJ = 1;
                    } else {
                        minor[i - dI][j - dJ] = matrix[i][j];
                    }
                }
            }
        }

        return minor;
    }


    public ComplexNumber[][] getMatrix(){
        return matrix;
    }

    public void setMatrix(ComplexNumber[][] matrix){
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                stringBuilder.append(matrix[i][j]).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
