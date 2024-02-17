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
                matrix[i][j] = new ComplexNumber().convertToComplexNumber(line[j]);
            }
        }
    }

    public Matrix add(Matrix matrix1, int type){
        int K = matrix1.N;
        int L = matrix1.M;

        Matrix result = new Matrix(N,M);

        if(N == K && M == L){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(type == 1)
                        result.getMatrix()[i][j] = getMatrix()[i][j].add(matrix1.getMatrix()[i][j]);
                    else
                        result.getMatrix()[i][j] = getMatrix()[i][j].sub(matrix1.getMatrix()[i][j]);
                }
            }
            return result;
        }

        System.out.println("Нельзя складывать матрицы различной размерности!");
        return null;
    }

    public Matrix multiply(Matrix matrix1){
        int K = matrix1.N;
        int L = matrix1.M;

        Matrix result = new Matrix(N,L);

        if(M == K){

            for(int i = 0; i < N; i++){
                for(int j = 0; j < L; j++){
                    result.getMatrix()[i][j] = new ComplexNumber(0, 0);
                    for(int k = 0; k < M; k++){
                        result.getMatrix()[i][j] = result.getMatrix()[i][j].add(matrix[i][k].multiply(matrix1.getMatrix()[k][j]));
                    }
                }
            }

            return result;
        }
        System.out.println("Ошибка: число столбцов матрицы K должно равнятся числу строк матрицы L");
        return null;
    }

    public Matrix multiplyByNumber(ComplexNumber complexNumber){
        Matrix result = new Matrix(N, M);

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                result.getMatrix()[i][j] = matrix[i][j].multiply(complexNumber);
            }
        }

        return result;
    }

    public Matrix transposeMatrix(){
        Matrix result = new Matrix(M,N);

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                result.getMatrix()[j][i] = matrix[i][j];
            }
        }

        return result;
    }

    public ComplexNumber det(ComplexNumber[][] matrix){
        ComplexNumber calcResult = new ComplexNumber(0 , 0);

        if (matrix.length==2){
            calcResult = matrix[0][0].multiply(matrix[1][1]).sub(matrix[1][0].multiply(matrix[0][1]));
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
                calcResult = calcResult.add(det(GetMinor(matrix,0,i)).multiply(temp.multiply(matrix[0][i])));
            }
        }

        return calcResult;
    }

    private ComplexNumber[][] GetMinor(ComplexNumber[][] matrix, int row, int column) {
        int minorLength = matrix.length - 1;
        ComplexNumber[][] minor = new ComplexNumber[minorLength][minorLength];
        int dI = 0;
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
