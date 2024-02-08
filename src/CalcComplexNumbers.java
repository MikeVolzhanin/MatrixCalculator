public class CalcComplexNumbers {
    public static ComplexNumber add(ComplexNumber firstNumber, ComplexNumber secondNumber){
        return new ComplexNumber(firstNumber.realPart + secondNumber.realPart,
                firstNumber.imaginaryPart + secondNumber.imaginaryPart);
    }

    public static ComplexNumber sub(ComplexNumber firstNumber, ComplexNumber secondNumber){
        return new ComplexNumber(firstNumber.realPart - secondNumber.realPart,
                firstNumber.imaginaryPart - secondNumber.imaginaryPart );
    }

    public static ComplexNumber multiply(ComplexNumber firstNumber, ComplexNumber secondNumber){
        return new ComplexNumber(
                firstNumber.realPart * secondNumber.realPart - firstNumber.imaginaryPart* secondNumber.imaginaryPart,
                firstNumber.imaginaryPart * secondNumber.realPart + firstNumber.realPart * secondNumber.imaginaryPart
        );
    }

    public static ComplexNumber divide(ComplexNumber firstNumber, ComplexNumber secondNumber) {
        ComplexNumber division = new ComplexNumber();
        double tempReal, tempImaginary;

        if (Math.abs(secondNumber.realPart) >= Math.abs(secondNumber.imaginaryPart)) {
            tempReal = secondNumber.imaginaryPart/ secondNumber.realPart;
            tempImaginary = secondNumber.realPart + tempReal * secondNumber.imaginaryPart;
            division.realPart = (firstNumber.realPart + tempReal * firstNumber.imaginaryPart) / tempImaginary;
            division.imaginaryPart = (firstNumber.imaginaryPart - tempReal * firstNumber.realPart) / tempImaginary;
        } else {
            tempReal = secondNumber.realPart / secondNumber.imaginaryPart;
            tempImaginary = secondNumber.imaginaryPart + tempReal * secondNumber.realPart;
            division.realPart = (tempReal * firstNumber.realPart + firstNumber.imaginaryPart) / tempImaginary;
            division.imaginaryPart = (tempReal * firstNumber.imaginaryPart - firstNumber.realPart) / tempImaginary;
        }

        return division;
    }
}
