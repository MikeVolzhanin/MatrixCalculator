import java.util.Arrays;

public class ComplexNumber {
    double realPart;
    double imaginaryPart;

    public ComplexNumber(double realPart, double imaginaryPart) {
        this.realPart = realPart;
        this.imaginaryPart = imaginaryPart;
    }

    public ComplexNumber() {}

    public static ComplexNumber convertToComplexNumber(String number) {
        ComplexNumber complexNumber = new ComplexNumber();

        if (number.contains("i")) {

            if(number.matches("-?\\w+")){
                String[] numberPart = number.split("i");
                complexNumber.imaginaryPart = Double.parseDouble(numberPart[0]);
                return complexNumber;
            }

            if (number.contains("+")) {
                String[] numberPart = number.split("\\+");
                complexNumber.realPart = Double.parseDouble(numberPart[0]);
                complexNumber.imaginaryPart = Double.parseDouble(numberPart[1].split("i")[0]);
                return complexNumber;
            }

            if (number.contains("-")) {
                if(number.charAt(0) == '-'){
                    number = number.substring(1);
                    String[] numberPart = number.split("-");
                    complexNumber.realPart = Double.parseDouble(numberPart[0]) * (-1);
                    complexNumber.imaginaryPart = Double.parseDouble(numberPart[1].split("i")[0]) * (-1);
                }
                else{
                    String[] numberPart = number.split("-");
                    complexNumber.realPart = Double.parseDouble(numberPart[0]);
                    complexNumber.imaginaryPart = Double.parseDouble(numberPart[1].split("i")[0]) * (-1);
                }
                return complexNumber;
            }
        }


        complexNumber.realPart = Double.parseDouble(number);

        return complexNumber;
    }

    @Override
    public String toString() {
        if (imaginaryPart == 0) {
            return String.valueOf(realPart);
        }
        if (realPart == 0) {
            return imaginaryPart + "i";
        }

        if (imaginaryPart >= 0) {
            return realPart + "+" + imaginaryPart + "i";
        } else {
            return realPart + "-" + -imaginaryPart + "i";
        }

    }
}
