package gum.enzo.pas_gen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Button;
import android.widget.TextView;
import android.content.ClipboardManager;
import android.content.ClipData;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    EditText key_word, number;
    Button gen_pas, cpy;
    CheckBox check_num, check_lr, check_hr;
    TextView show, result;
    String rez = "";
    String show_str = "";
    String key = "";
    int[] alphabet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        key_word = (EditText) findViewById(R.id.key_word);
        number = (EditText) findViewById(R.id.number_of_sym);
        gen_pas = (Button) findViewById(R.id.gen_pas);
        cpy = (Button) findViewById(R.id.cpy);
        show = (TextView) findViewById(R.id.show);
        result = (TextView) findViewById(R.id.result);
        check_num = (CheckBox) findViewById(R.id.check_num);
        check_lr = (CheckBox) findViewById(R.id.check_low_reg);
        check_hr = (CheckBox) findViewById(R.id.check_high_reg);
/*
        // Массив для хранения кодов символов "0 - 9"
        int [] numeric;
        int j = 0;
        numeric = new int [10];
        for (int i = 48; i < 58; i++){
            numeric[j] = i;
            j++;
        }

        // Массив для хранения кодов символов "a - z"
        int [] low_reg;
        j = 0;
        low_reg = new int [26];
        for (int i = 97; i < 123; i++) {
            low_reg[j] = i;
            j++;
        }

        // Массив для хранения кодов символов "A - Z"
        int [] high_reg;
        j = 0;
        high_reg = new int [26];
        for (int i = 65; i < 91; i++) {
            high_reg[j] = i;
            j++;
        }
*/
        cpy.setOnClickListener(copy);
        gen_pas.setOnClickListener(generate);

    }

    View.OnClickListener generate = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            result.setText("");
            rez = "";
            show_str = "";
            show.setText("");
            alphabet = null;

            key = number.getText().toString();
            int num = Integer.parseInt(key);

            key = key_word.getText().toString();

            if (num < key.length()){
                Toast.makeText(MainActivity.this, "Password is shorter than key word. Try again.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                alphabet = new int[62]; // Просто максимально возможный размер (переделаю потом в Arraylist)
                int j = 0;
                if (check_num.isChecked()){
                    for (int i = 48; i < 58; i++){
                        alphabet[j] = i;
                        show_str += (char)alphabet[j];
                        j++;
                    }
                }

                if (check_hr.isChecked()){
                    for (int i = 65; i < 91; i++){
                        alphabet[j] = i;
                        show_str += (char)alphabet[j];
                        j++;
                    }
                }

                if (check_lr.isChecked()){
                    for (int i = 97; i < 123; i++){
                        alphabet[j] = i;
                        show_str += (char)alphabet[j];
                        j++;
                    }
                }

                show.setText(show_str);

                //Начальная позиция ключевого слова
                int enter_point = (int)(Math.random() * (num - key.length() + 1));
                int i = 0;
                while (i < num){

                    if (i != enter_point){
                        rez = rez + (char)alphabet[(int)(Math.random() * (j + 1))];
                        i++;
                    }
                    else{
                        rez = rez + key;
                        i += key.length();
                    }

                }

                result.setText(rez);
            }



        }
    };

    View.OnClickListener copy = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ClipboardManager clipboard = (ClipboardManager) getSystemService(MainActivity.this.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("TextView", result.getText().toString());
            clipboard.setPrimaryClip(clip);


            Toast.makeText(MainActivity.this, "Copied to clipboard", Toast.LENGTH_SHORT).show();
        }
    };

}
