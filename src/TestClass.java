import java.util.Scanner;

public class TestClass {
    public static void test(){
        Scanner scanner = new Scanner(System.in);

        ComplexNumber complexNumber = new ComplexNumber();
        ComplexNumber complexNumber1 = new ComplexNumber();

        System.out.println("Введите действительную часть: ");
        complexNumber.realPart = scanner.nextDouble();
        System.out.println("Введите мнимую часть: ");
        complexNumber.imaginaryPart = scanner.nextDouble();

        System.out.println("Введите действительную часть: ");
        complexNumber1.realPart = scanner.nextDouble();
        System.out.println("Введите мнимую часть: ");
        complexNumber1.imaginaryPart = scanner.nextDouble();

        System.out.println(CalcComplexNumbers.add(complexNumber, complexNumber1));
        System.out.println(CalcComplexNumbers.sub(complexNumber, complexNumber1));
        System.out.println(CalcComplexNumbers.divide(complexNumber, complexNumber1));
        System.out.println(CalcComplexNumbers.multiply(complexNumber, complexNumber1));
    }
}
