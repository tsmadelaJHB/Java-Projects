package za.co.wethinkcode.fizzbuzz;
import java.util.Arrays;

public class FizzBuzz {
    public String checkNumber(int number) {
        boolean divisibleBy3 = number % 3 == 0;
        boolean divisibleBy5 = number % 5 == 0;

        if ( divisibleBy3 && divisibleBy5) {
            return "FizzBuzz";
        }
        else if (divisibleBy3){
            return "Fizz";
        }
        else if(divisibleBy5){
            return "Buzz";
        }
        /*else if(!(divisibleBy3 && divisibleBy5)){
            return String.valueOf(number);
        }*/
        return String.valueOf(number);
    }

    public String countTo(int number) {
        String[] returnedString = new String[number];
        for(int i = 1; i <= number; i++){
            returnedString[i - 1] = checkNumber(i);
        }
        return Arrays.toString(returnedString);
    }
}

