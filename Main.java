import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws  Exception {

            Scanner scanner = new Scanner(System.in);
            System.out.println("Введите два числа (арабских или римских): ");
            String expression = scanner.nextLine();
            System.out.println(parse(expression));
        }

        public static String parse(String expression) throws Exception {
            int num1;
            int num2;
            String oper;
            String result;
            boolean isRoman;
            String[] operands = expression.split("[+\\-*/]");
            if (operands.length !=2) throw new Exception("Формат математической операции не удовлетворяет заданию");
            oper = detectOperation(expression);
                       //елси оба числа римские
            if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[1])) {
                //конвертируем оба числа в арабские для вычисления действий
               num1 = Roman.convertToArabian(operands[0]);
               num2 = Roman.convertToArabian(operands[1]);
               isRoman = true;
        }
            //если оба числа арабские
            else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[1])) {
                num1 = Integer.parseInt(operands[0]);
                num2 = Integer.parseInt(operands[1]);
                isRoman = false;
            }
            //если одни число римское, а другое - арабское
            else {
                throw new Exception("Используются одновременно разные системы счисления");
            }
            if (num1>10|| num2>10) {
                throw new Exception("Числа должны быть от 1 до 10");
            }
            int arabian = calc(num1,num2,oper);
            if (isRoman){
                //если римское число получилось меньше либо равно нуля, генерируем ошибку, согласно ТЗ
                if (arabian <=0){
                    throw new Exception("в римской системе нет отрицательных чисел");
                }
                //конвертуем результат операции из арабского в римское
                result = Roman.convertToRoman(arabian);
            } else {
                //Конвертируем арабское число в строку
                result = String.valueOf(arabian);
            }
            //возвращаем результат
            return result;
        }

        static String detectOperation(String expression) {
        if (expression.contains("+")) return "+";
        else  if (expression.contains("-")) return "-";
        else  if (expression.contains("*")) return "*";
        else  if (expression.contains("/")) return "/";
        else return null;
    }

    static int calc(int a, int b, String oper) {

        if (oper.equals("+")) return a+b;
        else if (oper.equals("-")) return a-b;
        else if (oper.equals("*")) return a*b;
        else return a/b;
    }

}

class Roman {
    static String[] romanArray = new String[]{"0", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII","XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX",
            "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII","LXXIV","LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX",
            "XC","XCI", "XCII","XCIII","XCIV","XCV","XCVI", "XCVII", "XCVIII", "XCIX", "C"};

    public static boolean isRoman(String val) {
        for (int i = 0; i < romanArray.length; i++) {
            if (val.equals(romanArray[i])) {
                return true;
            }
        }
        return false;
    }

    public static int convertToArabian(String roman) {
        for (int i = 0; i < romanArray.length; i++) {
            if (roman.equals(romanArray[i])) {
                return i;
            }
        }
        return -1;
    }

    public static String convertToRoman(int arabian) {
        return romanArray[arabian];
    }
}

//Создай консольное приложение “Калькулятор”. Приложение должно читать из консоли введенные пользователем строки,
// числа, арифметические операции проводимые между ними и выводить в консоль результат их выполнения.
//Реализуй класс Main с методом public static String calc(String input). Метод должен принимать строку с
// арифметическим выражением между двумя числами и возвращать строку с результатом их выполнения.
// Ты можешь добавлять свои импорты, классы и методы. Добавленные классы не должны иметь модификаторы доступа
// (public или другие)
//Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами:
// a + b, a - b, a * b, a / b. Данные передаются в одну строку (смотри пример)!
// Решения, в которых каждое число и арифмитеческая операция передаются с новой строки считаются неверными.
//Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
// Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.
// На выходе числа не ограничиваются по величине и могут быть любыми.
//Калькулятор умеет работать только с целыми числами. Калькулятор умеет работать только с арабскими или
// римскими цифрами одновременно, при вводе пользователем строки вроде 3 + II калькулятор должен выбросить
// исключение и прекратить свою работу. При вводе римских чисел, ответ должен быть выведен римскими цифрами,
// соответственно, при вводе арабских - ответ ожидается арабскими. При вводе пользователем неподходящих чисел
// приложение выбрасывает исключение и завершает свою работу. При вводе пользователем строки, не соответствующей
// одной из вышеописанных арифметических операций, приложение выбрасывает исключение и завершает свою работу.
//Результатом операции деления является целое число, остаток отбрасывается.
// Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль. Результатом работы
// калькулятора с римскими числами могут быть только положительные числа, если результат работы меньше единицы,
// выбрасывается исключение