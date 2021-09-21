package com.elijahcorp.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button memorySaveBtn, memoryPlusBtn, memoryMinusBtn, memoryRemoveBtn, zeroBtn, pointBtn,
            equalsBtn, minusBtn, oneBtn, twoBtn, threeBtn, plusBtn, fourBtn, fiveBtn, sixBtn, divideBtn, sevenBtn,
            eightBtn, nineBtn, multiplyBtn, removeBtn;
    private TextView outputLineTv, historyColumnTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        inputSymbols();
        removeSymbols();
        solveExpression();
    }

    private void initViews() {
        memorySaveBtn = findViewById(R.id.memory_save_btn);
        memoryPlusBtn = findViewById(R.id.memory_plus_btn);
        memoryMinusBtn = findViewById(R.id.memory_minus_btn);
        memoryRemoveBtn = findViewById(R.id.memory_remove_btn);
        zeroBtn = findViewById(R.id.zero_btn);
        pointBtn = findViewById(R.id.point_btn);
        equalsBtn = findViewById(R.id.equals_btn);
        minusBtn = findViewById(R.id.minus_btn);
        oneBtn = findViewById(R.id.one_btn);
        twoBtn = findViewById(R.id.two_btn);
        threeBtn = findViewById(R.id.three_btn);
        plusBtn = findViewById(R.id.plus_btn);
        fourBtn = findViewById(R.id.four_btn);
        fiveBtn = findViewById(R.id.five_btn);
        sixBtn = findViewById(R.id.six_btn);
        divideBtn = findViewById(R.id.divide_btn);
        sevenBtn = findViewById(R.id.seven_btn);
        eightBtn = findViewById(R.id.eight_btn);
        nineBtn = findViewById(R.id.nine_btn);
        multiplyBtn = findViewById(R.id.multiply_btn);
        outputLineTv = findViewById(R.id.output_line_tv);
        historyColumnTv = findViewById(R.id.history_column_tv);
        removeBtn = findViewById(R.id.remove_btn);
    }

    private void inputSymbols() {
        zeroBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), "0")));
        oneBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), "1")));
        twoBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), "2")));
        threeBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), "3")));
        fourBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), "4")));
        fiveBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), "5")));
        sixBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), "6")));
        sevenBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), "7")));
        eightBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), "8")));
        nineBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), "9")));
        pointBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), ".")));
        multiplyBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), "*")));
        divideBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), "/")));
        plusBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), "+")));
        minusBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), "-")));
    }

    private String checkInsertSymbol(String pastSymbol, String newSymbol) {
        boolean pastSymbolIsOperation = pastSymbol.equals("+") || pastSymbol.equals("-") || pastSymbol.equals("*") || pastSymbol.equals("/");
        boolean newSymbolIsOperation = newSymbol.equals("+") || newSymbol.equals("-") || newSymbol.equals("*") || newSymbol.equals("/");
        if (pastSymbolIsOperation && newSymbolIsOperation) {
            Toast.makeText(MainActivity.this, "Введите другой символ", Toast.LENGTH_SHORT).show();
        } else if (pastSymbolIsOperation && newSymbol.equals(".")) {
            Toast.makeText(MainActivity.this, "Введите другой символ", Toast.LENGTH_SHORT).show();
        } else if (pastSymbol.equals(".") && newSymbolIsOperation) {
            Toast.makeText(MainActivity.this, "Введите другой символ", Toast.LENGTH_SHORT).show();
        } else if (outputLineTv.getText().length() == 1 && !pastSymbol.equals("0")) {
            return outputLineTv.getText() + newSymbol;
        } else if (outputLineTv.getText().length() == 1 && newSymbol.equals("0")) {
            return String.valueOf(outputLineTv.getText());
        } else if (outputLineTv.getText().length() == 1 && !newSymbol.equals("0") && (newSymbolIsOperation || newSymbol.equals("."))) {
            return outputLineTv.getText() + newSymbol;
        } else if (outputLineTv.getText().length() == 1 && !newSymbol.equals("0") && !newSymbol.equals(".")) {
            return newSymbol;
        } else if (newSymbol.equals(".")) {
            int countPoint = 0;
            for (int i = outputLineTv.getText().length() - 1; i >= 0; i--) {
                if (outputLineTv.getText().charAt(i) == '.') {
                    countPoint++;
                }
                if (outputLineTv.getText().charAt(i) == '+' || outputLineTv.getText().charAt(i) == '-' || outputLineTv.getText().charAt(i) == '*' || outputLineTv.getText().charAt(i) == '/') {
                    if (countPoint > 0) {
                        Toast.makeText(MainActivity.this, "Введите другой символ", Toast.LENGTH_SHORT).show();
                        return String.valueOf(outputLineTv.getText());
                    } else {
                        return outputLineTv.getText() + newSymbol;
                    }
                } else if (i == 0 && countPoint > 0) {
                    Toast.makeText(MainActivity.this, "Введите другой символ", Toast.LENGTH_SHORT).show();
                    return String.valueOf(outputLineTv.getText());
                }
            }
            return outputLineTv.getText() + newSymbol;
        } else if (newSymbol.equals("0")) {
            for (int i = outputLineTv.getText().length() - 1; i >= 0; i--) {
                if ((outputLineTv.getText().charAt(i) == '+' || outputLineTv.getText().charAt(i) == '-' || outputLineTv.getText().charAt(i) == '*' || outputLineTv.getText().charAt(i) == '/') &&
                        (i + 1 < outputLineTv.getText().length()) && outputLineTv.getText().charAt(i + 1) == '0') {
                    Toast.makeText(MainActivity.this, "Введите другой символ", Toast.LENGTH_SHORT).show();
                    return String.valueOf(outputLineTv.getText());
                } else if (outputLineTv.getText().charAt(i) == '.') {
                    return outputLineTv.getText() + newSymbol;
                }
            }
            return outputLineTv.getText() + newSymbol;
        } else {
            return outputLineTv.getText() + newSymbol;
        }
        return String.valueOf(outputLineTv.getText());
    }

    private void removeSymbols() {
        removeBtn.setOnClickListener(l -> outputLineTv.setText("0"));
    }

    @SuppressLint("SetTextI18n")
    private void solveExpression() {
        equalsBtn.setOnClickListener(l -> {
            Calculation calculation = new Calculation(outputLineTv.getText().toString());
            historyColumnTv.setText(historyColumnTv.getText().toString() + "\n" + outputLineTv.getText() + "\n" + "=" + calculation.calculate());
            outputLineTv.setText("0");
        });
    }
}