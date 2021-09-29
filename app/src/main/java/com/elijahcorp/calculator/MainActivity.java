package com.elijahcorp.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    private MaterialButton
            memorySaveBtn, memoryPlusBtn,
            memoryMinusBtn, memoryReadBtn,
            memoryClearBtn, zeroBtn,
            pointBtn, equalsBtn,
            minusBtn, oneBtn,
            twoBtn, threeBtn,
            plusBtn, fourBtn,
            fiveBtn, sixBtn,
            divideBtn, sevenBtn,
            eightBtn, nineBtn,
            multiplyBtn, removeBtn;
    private TextView outputLineTv, historyColumnTv;
    private Calculation calculation;
    private final String KEY_CALCULATIONS = "key_calculations";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        calculation.setStringExpression(outputLineTv.getText().toString());
        calculation.setColumnHistoryCalculations(historyColumnTv.getText().toString());
        outState.putParcelable(KEY_CALCULATIONS, calculation);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        if (savedInstanceState != null) {
            calculation = savedInstanceState.getParcelable(KEY_CALCULATIONS);
            historyColumnTv.setText(calculation.getColumnHistoryCalculations());
            outputLineTv.setText(calculation.getStringExpression());
        } else {
            calculation = new Calculation(this);
        }
        initialiseOnClickListenerSymbolsBtn();
        initialiseOnClickListenerRemoveBtn();
        initialiseOnClickListenerEqualsBtn();
        initialiseOnClickListenerMemoryBtn();
    }

    private void initViews() {
        memorySaveBtn = findViewById(R.id.memory_save_button);
        memoryPlusBtn = findViewById(R.id.memory_plus_button);
        memoryMinusBtn = findViewById(R.id.memory_minus_button);
        memoryReadBtn = findViewById(R.id.memory_read_button);
        memoryClearBtn = findViewById(R.id.memory_clear_button);
        zeroBtn = findViewById(R.id.zero_button);
        pointBtn = findViewById(R.id.point_button);
        equalsBtn = findViewById(R.id.equals_button);
        minusBtn = findViewById(R.id.minus_button);
        oneBtn = findViewById(R.id.one_button);
        twoBtn = findViewById(R.id.two_button);
        threeBtn = findViewById(R.id.three_button);
        plusBtn = findViewById(R.id.plus_button);
        fourBtn = findViewById(R.id.four_button);
        fiveBtn = findViewById(R.id.five_button);
        sixBtn = findViewById(R.id.six_button);
        divideBtn = findViewById(R.id.divide_button);
        sevenBtn = findViewById(R.id.seven_button);
        eightBtn = findViewById(R.id.eight_button);
        nineBtn = findViewById(R.id.nine_button);
        multiplyBtn = findViewById(R.id.multiply_button);
        outputLineTv = findViewById(R.id.output_line_text_view);
        historyColumnTv = findViewById(R.id.history_column_text_view);
        removeBtn = findViewById(R.id.remove_button);
    }

    private void initialiseOnClickListenerSymbolsBtn() {
        zeroBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.ZERO.getSymbol(this))));
        oneBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.ONE.getSymbol(this))));
        twoBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.TWO.getSymbol(this))));
        threeBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.THREE.getSymbol(this))));
        fourBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.FOUR.getSymbol(this))));
        fiveBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.FIVE.getSymbol(this))));
        sixBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.SIX.getSymbol(this))));
        sevenBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.SEVEN.getSymbol(this))));
        eightBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.EIGHT.getSymbol(this))));
        nineBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.NINE.getSymbol(this))));
        pointBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.POINT.getSymbol(this))));
        multiplyBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.MULTIPLE.getSymbol(this))));
        divideBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.DIVIDE.getSymbol(this))));
        plusBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.PLUS.getSymbol(this))));
        minusBtn.setOnClickListener(l -> outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.MINUS.getSymbol(this))));
    }

    private String checkInsertSymbol(View v, String pastSymbol, String newSymbol) {
        boolean pastSymbolIsOperation = calculation.isOperator(pastSymbol.charAt(0));
        boolean newSymbolIsOperation = calculation.isOperator(newSymbol.charAt(0));
        if (pastSymbolIsOperation && newSymbolIsOperation) {
            Snackbar.make(v, R.string.warning_message, Snackbar.LENGTH_SHORT).show();
        } else if (pastSymbolIsOperation && newSymbol.equals(Symbols.POINT.getSymbol(this))) {
            Snackbar.make(v, R.string.warning_message, Snackbar.LENGTH_SHORT).show();
        } else if (pastSymbol.equals(Symbols.POINT.getSymbol(this)) && newSymbolIsOperation) {
            Snackbar.make(v, R.string.warning_message, Snackbar.LENGTH_SHORT).show();
        } else if (outputLineTv.getText().length() == 1 && !pastSymbol.equals(Symbols.ZERO.getSymbol(this))) {
            return outputLineTv.getText() + newSymbol;
        } else if (outputLineTv.getText().length() == 1 && newSymbol.equals(Symbols.ZERO.getSymbol(this))) {
            return String.valueOf(outputLineTv.getText());
        } else if (outputLineTv.getText().length() == 1 && !newSymbol.equals(Symbols.ZERO.getSymbol(this)) && (newSymbolIsOperation || newSymbol.equals(Symbols.POINT.getSymbol(this)))) {
            return outputLineTv.getText() + newSymbol;
        } else if (outputLineTv.getText().length() == 1 && !newSymbol.equals(Symbols.ZERO.getSymbol(this)) && !newSymbol.equals(Symbols.POINT.getSymbol(this))) {
            return newSymbol;
        } else if (newSymbol.equals(Symbols.POINT.getSymbol(this))) {
            int countPoint = 0;
            for (int i = outputLineTv.getText().length() - 1; i >= 0; i--) {
                if (outputLineTv.getText().charAt(i) == Symbols.POINT.getSymbol(this).charAt(0)) {
                    countPoint++;
                }
                if (calculation.isOperator(outputLineTv.getText().charAt(i))) {
                    if (countPoint > 0) {
                        Snackbar.make(v, R.string.warning_message, Snackbar.LENGTH_SHORT).show();
                        return String.valueOf(outputLineTv.getText());
                    } else {
                        return outputLineTv.getText() + newSymbol;
                    }
                } else if (i == 0 && countPoint > 0) {
                    Snackbar.make(v, R.string.warning_message, Snackbar.LENGTH_SHORT).show();
                    return String.valueOf(outputLineTv.getText());
                }
            }
            return outputLineTv.getText() + newSymbol;
        } else if (newSymbol.equals(Symbols.ZERO.getSymbol(this))) {
            for (int i = outputLineTv.getText().length() - 1; i >= 0; i--) {
                if ((calculation.isOperator(outputLineTv.getText().charAt(i))) && (i + 1 < outputLineTv.getText().length()) && outputLineTv.getText().charAt(i + 1) == Symbols.ZERO.getSymbol(this).charAt(0)) {
                    Snackbar.make(v, R.string.warning_message, Snackbar.LENGTH_SHORT).show();
                    return String.valueOf(outputLineTv.getText());
                } else if (outputLineTv.getText().charAt(i) == Symbols.POINT.getSymbol(this).charAt(0)) {
                    return outputLineTv.getText() + newSymbol;
                }
            }
            return outputLineTv.getText() + newSymbol;
        } else {
            return outputLineTv.getText() + newSymbol;
        }
        return String.valueOf(outputLineTv.getText());
    }

    private void initialiseOnClickListenerRemoveBtn() {
        removeBtn.setOnClickListener(l -> outputLineTv.setText(Symbols.ZERO.getSymbol(this)));
    }

    @SuppressLint("SetTextI18n")
    private void initialiseOnClickListenerEqualsBtn() {
        equalsBtn.setOnClickListener(l -> {
            double solve = solveExpression();
            outputLineTv.setText(Double.toString(solve));
        });
    }

    private void initialiseOnClickListenerMemoryBtn() {
        memorySaveBtn.setOnClickListener(l -> {
            double solve = solveExpression();
            displayHistory(solve);
            outputLineTv.setText(Symbols.ZERO.getSymbol(this));
        });

        memoryReadBtn.setOnClickListener(l -> outputLineTv.setText(calculation.getMemoryCell()));

        memoryPlusBtn.setOnClickListener(l -> {
            outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.PLUS.getSymbol(this)));
            outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), calculation.getMemoryCell()));
            double solve = solveExpression();
            displayHistory(solve);
            outputLineTv.setText(Symbols.ZERO.getSymbol(this));
        });

        memoryMinusBtn.setOnClickListener(l -> {
            outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), Symbols.MINUS.getSymbol(this)));
            outputLineTv.setText(checkInsertSymbol(l, String.valueOf(outputLineTv.getText().charAt(outputLineTv.getText().length() - 1)), calculation.getMemoryCell()));
            double solve = solveExpression();
            displayHistory(solve);
            outputLineTv.setText(Symbols.ZERO.getSymbol(this));
        });

        memoryClearBtn.setOnClickListener(l -> {
            historyColumnTv.setText("");
            calculation.setMemoryCell(Symbols.ZERO.getSymbol(this));
        });
    }

    private double solveExpression() {
        return calculation.calculate(outputLineTv.getText().toString());
    }

    @SuppressLint("SetTextI18n")
    private void displayHistory(double solve) {
        calculation.setMemoryCell(Double.toString(solve));
        if (historyColumnTv.getText().toString().isEmpty()) {
            historyColumnTv.setText(outputLineTv.getText() + "\n" + Symbols.EQUALS.getSymbol(this) + solve);
        } else {
            historyColumnTv.setText(historyColumnTv.getText().toString() + "\n" + outputLineTv.getText() + "\n" + Symbols.EQUALS.getSymbol(this) + solve);
        }
    }
}