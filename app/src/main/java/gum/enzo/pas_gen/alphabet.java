package gum.enzo.pas_gen;

public class alphabet {

    int[] numbers;
    int[] low_reg;
    int[] high_reg;

    alphabet(){

        // Массив для хранения кодов символов "0 - 9"
        numbers = new int [10];
        for (int i = 48; i < 58; i++){
           numbers[i] = i;
        }

        // Массив для хранения кодов символов "a - z"
        low_reg = new int [26];
        for (int i = 97; i < 123; i++) {
            low_reg[i] = i;
        }

        // Массив для хранения кодов символов "A - Z"
        high_reg = new int [26];
        for (int i = 97; i < 123; i++) {
            high_reg[i] = i;
        }
    }

}
