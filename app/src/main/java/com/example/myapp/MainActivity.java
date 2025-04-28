package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    double num1 = 0;
    double num2 = 0;
    double result = 0;
    String operator = "";
    boolean isEqualClicked = false;
    double lastSecondVal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.textViewResult);
    }

    public void printDigit(View view) {
        Button clickedButton = (Button) view;

        if(isEqualClicked){
            clear(view);
            textView.setText(clickedButton.getText());
        }
        else {
            textView.append(clickedButton.getText());

        }
    }

    public void clear(View view){
        textView.setText("");
        num1 = 0;
        operator = "";
        isEqualClicked = false;
    }

    public void calc(View view) {
        Button actionButton = (Button) view;
        operator = actionButton.getText().toString();

        num1 = Integer.parseInt(textView.getText().toString());
        textView.setText("");
    }

    public void special(View view){
        num1 = Integer.parseInt(textView.getText().toString());
        result = Math.sqrt(num1);
        printResult();
        isEqualClicked = true;
    }

    public void printResult(){
        if (result == (int) result) {
            textView.setText(String.valueOf((int) result));
        } else {
            textView.setText(String.format("%.3f", result));
        }
    }

    public void equal(View view){
        if(num1 != 0){
            if(isEqualClicked){
                num2 = lastSecondVal;
            }

            else{
                num2 = Integer.parseInt(textView.getText().toString());
                lastSecondVal = num2;
            }

            isEqualClicked = true;

            switch (operator){
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "X":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0){
                        if(num1 % num2 != 0){
                            result =  num1 / num2;
                        }
                        else{
                            result = (int) (num1 / num2);
                        }
                    }
                    else{
                        textView.setText("Error");
                        return;
                    }
                    break;

                case "xⁿ":
                    result = Math.pow(num1,num2);
                    break;

                case "√x":
                    result = Math.sqrt(num1);
                    break;

                case "%":
                    result = num1 * (num2/100);
                    break;

            }

            printResult();
            num1 = result;
        }
        else textView.setText("Error");

    }


    public void deleteChar(View view) {
        String text = textView.getText().toString();

        if(!text.isEmpty()){
            text = text.substring(0,text.length()-1);
            textView.setText(text);
        }

    }
}
