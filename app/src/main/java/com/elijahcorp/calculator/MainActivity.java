package com.elijahcorp.calculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private MaterialButton memorySaveButton,
            memoryPlusButton,
            memoryMinusButton,
            memoryReadButton,
            memoryClearButton,
            zeroButton,
            pointButton,
            equalsButton,
            minusButton,
            oneButton,
            twoButton,
            threeButton,
            plusButton,
            fourButton,
            fiveButton,
            sixButton,
            divideButton,
            sevenButton,
            eightButton,
            nineButton,
            multiplyButton,
            removeButton;
    private ShapeableImageView settingsImageView;
    private TextView outputLineTextView,
            historyColumnTextView;
    private Calculation calculation;
    private final String KEY_CALCULATIONS = "key_calculations";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        calculation.setStringExpression(outputLineTextView.getText().toString());
        calculation.setColumnHistoryCalculations(historyColumnTextView.getText().toString());
        outState.putParcelable(KEY_CALCULATIONS, calculation);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.getDefaultNightMode());
        setContentView(R.layout.activity_main);
        initViews();
        if (savedInstanceState != null) {
            calculation = savedInstanceState.getParcelable(KEY_CALCULATIONS);
            historyColumnTextView.setText(calculation.getColumnHistoryCalculations());
            outputLineTextView.setText(calculation.getStringExpression());
        } else {
            calculation = new Calculation(this);
        }
        initialiseOnClickListenerSymbolsBtn();
        initialiseOnClickListenerRemoveBtn();
        initialiseOnClickListenerEqualsBtn();
        initialiseOnClickListenerMemoryBtn();
        initialiseOnClickListenerSettingButtonImageView();
        ChangerTheme.initialiseTheme(this);
        getExpressionFromIntent();
    }

    @Override
    public void onBackPressed() {
        Intent intentResult = new Intent();
        intentResult.putExtra(KEY_CALCULATIONS, outputLineTextView.getText());
        setResult(RESULT_OK, intentResult);
        super.onBackPressed();
    }

    private void initViews() {
        memorySaveButton = findViewById(R.id.memory_save_button);
        memoryPlusButton = findViewById(R.id.memory_plus_button);
        memoryMinusButton = findViewById(R.id.memory_minus_button);
        memoryReadButton = findViewById(R.id.memory_read_button);
        memoryClearButton = findViewById(R.id.memory_clear_button);
        zeroButton = findViewById(R.id.zero_button);
        pointButton = findViewById(R.id.point_button);
        equalsButton = findViewById(R.id.equals_button);
        minusButton = findViewById(R.id.minus_button);
        oneButton = findViewById(R.id.one_button);
        twoButton = findViewById(R.id.two_button);
        threeButton = findViewById(R.id.three_button);
        plusButton = findViewById(R.id.plus_button);
        fourButton = findViewById(R.id.four_button);
        fiveButton = findViewById(R.id.five_button);
        sixButton = findViewById(R.id.six_button);
        divideButton = findViewById(R.id.divide_button);
        sevenButton = findViewById(R.id.seven_button);
        eightButton = findViewById(R.id.eight_button);
        nineButton = findViewById(R.id.nine_button);
        multiplyButton = findViewById(R.id.multiply_button);
        outputLineTextView = findViewById(R.id.output_line_text_view);
        historyColumnTextView = findViewById(R.id.history_column_text_view);
        removeButton = findViewById(R.id.remove_button);
        settingsImageView = findViewById(R.id.settings_button_image_view);
    }

    private void initialiseOnClickListenerSymbolsBtn() {
        zeroButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.ZERO.getSymbol(this))));
        oneButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.ONE.getSymbol(this))));
        twoButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.TWO.getSymbol(this))));
        threeButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.THREE.getSymbol(this))));
        fourButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.FOUR.getSymbol(this))));
        fiveButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.FIVE.getSymbol(this))));
        sixButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.SIX.getSymbol(this))));
        sevenButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.SEVEN.getSymbol(this))));
        eightButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.EIGHT.getSymbol(this))));
        nineButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.NINE.getSymbol(this))));
        pointButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.POINT.getSymbol(this))));
        multiplyButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.MULTIPLE.getSymbol(this))));
        divideButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.DIVIDE.getSymbol(this))));
        plusButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.PLUS.getSymbol(this))));
        minusButton.setOnClickListener(l -> outputLineTextView.setText(insertSymbol(l, Symbols.MINUS.getSymbol(this))));
    }

    private String insertSymbol(View l, String symbol) {
        return checkInsertSymbol(l, String.valueOf(outputLineTextView.getText().charAt(outputLineTextView.getText().length() - 1)), symbol);
    }

    private String checkInsertSymbol(View v, String pastSymbol, String newSymbol) {
        boolean pastSymbolIsOperation = calculation.isOperator(pastSymbol.charAt(0));
        boolean newSymbolIsOperation = calculation.isOperator(newSymbol.charAt(0));
        boolean pastSymbolIsPoint = pastSymbol.equals(Symbols.POINT.getSymbol(this));
        boolean newSymbolIsPoint = newSymbol.equals(Symbols.POINT.getSymbol(this));
        boolean pastSymbolIsZero = pastSymbol.equals(Symbols.ZERO.getSymbol(this));
        boolean newSymbolIsZero = newSymbol.equals(Symbols.ZERO.getSymbol(this));
        if (pastSymbolIsOperation && newSymbolIsOperation) {
            Snackbar.make(v, R.string.warning_message, Snackbar.LENGTH_SHORT).show();
        } else if (pastSymbolIsOperation && newSymbolIsPoint) {
            Snackbar.make(v, R.string.warning_message, Snackbar.LENGTH_SHORT).show();
        } else if (pastSymbolIsPoint && newSymbolIsOperation) {
            Snackbar.make(v, R.string.warning_message, Snackbar.LENGTH_SHORT).show();
        } else if (outputLineTextView.getText().length() == 1 && !pastSymbolIsZero) {
            return outputLineTextView.getText() + newSymbol;
        } else if (outputLineTextView.getText().length() == 1 && newSymbolIsZero) {
            return String.valueOf(outputLineTextView.getText());
        } else if (outputLineTextView.getText().length() == 1 && !newSymbolIsZero && (newSymbolIsOperation || newSymbolIsPoint)) {
            return outputLineTextView.getText() + newSymbol;
        } else if (outputLineTextView.getText().length() == 1 && !newSymbolIsZero && !newSymbolIsPoint) {
            return newSymbol;
        } else if (newSymbolIsPoint) {
            int countPoint = 0;
            for (int i = outputLineTextView.getText().length() - 1; i >= 0; i--) {
                if (outputLineTextView.getText().charAt(i) == Symbols.POINT.getSymbol(this).charAt(0)) {
                    countPoint++;
                }
                if (calculation.isOperator(outputLineTextView.getText().charAt(i))) {
                    if (countPoint > 0) {
                        Snackbar.make(v, R.string.warning_message, Snackbar.LENGTH_SHORT).show();
                        return String.valueOf(outputLineTextView.getText());
                    } else {
                        return outputLineTextView.getText() + newSymbol;
                    }
                } else if (i == 0 && countPoint > 0) {
                    Snackbar.make(v, R.string.warning_message, Snackbar.LENGTH_SHORT).show();
                    return String.valueOf(outputLineTextView.getText());
                }
            }
            return outputLineTextView.getText() + newSymbol;
        } else if (newSymbolIsZero) {
            for (int i = outputLineTextView.getText().length() - 1; i >= 0; i--) {
                if ((calculation.isOperator(outputLineTextView.getText().charAt(i))) && (i + 1 < outputLineTextView.getText().length()) && outputLineTextView.getText().charAt(i + 1) == Symbols.ZERO.getSymbol(this).charAt(0)) {
                    Snackbar.make(v, R.string.warning_message, Snackbar.LENGTH_SHORT).show();
                    return String.valueOf(outputLineTextView.getText());
                } else if (outputLineTextView.getText().charAt(i) == Symbols.POINT.getSymbol(this).charAt(0)) {
                    return outputLineTextView.getText() + newSymbol;
                }
            }
            return outputLineTextView.getText() + newSymbol;
        } else {
            return outputLineTextView.getText() + newSymbol;
        }
        return String.valueOf(outputLineTextView.getText());
    }

    private void initialiseOnClickListenerRemoveBtn() {
        removeButton.setOnClickListener(l -> outputLineTextView.setText(Symbols.ZERO.getSymbol(this)));
    }

    @SuppressLint("SetTextI18n")
    private void initialiseOnClickListenerEqualsBtn() {
        equalsButton.setOnClickListener(l -> {
            double solve = solveExpression();
            outputLineTextView.setText(Double.toString(solve));
        });
    }

    private void initialiseOnClickListenerMemoryBtn() {
        memorySaveButton.setOnClickListener(l -> {
            double solve = solveExpression();
            displayHistory(solve);
            outputLineTextView.setText(Symbols.ZERO.getSymbol(this));
        });

        memoryReadButton.setOnClickListener(l -> outputLineTextView.setText(calculation.getMemoryCell()));

        memoryPlusButton.setOnClickListener(l -> {
            outputLineTextView.setText(insertSymbol(l, Symbols.PLUS.getSymbol(this)));
            outputLineTextView.setText(insertSymbol(l, calculation.getMemoryCell()));
            double solve = solveExpression();
            displayHistory(solve);
            outputLineTextView.setText(Symbols.ZERO.getSymbol(this));
        });

        memoryMinusButton.setOnClickListener(l -> {
            outputLineTextView.setText(insertSymbol(l, Symbols.MINUS.getSymbol(this)));
            outputLineTextView.setText(insertSymbol(l, calculation.getMemoryCell()));
            double solve = solveExpression();
            displayHistory(solve);
            outputLineTextView.setText(Symbols.ZERO.getSymbol(this));
        });

        memoryClearButton.setOnClickListener(l -> {
            historyColumnTextView.setText("");
            calculation.setMemoryCell(Symbols.ZERO.getSymbol(this));
        });
    }

    private void initialiseOnClickListenerSettingButtonImageView() {
        settingsImageView.setOnClickListener(l -> SettingsActivity.lunchSettingActivity(this));
    }

    private void getExpressionFromIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (!(bundle == null)) {
            outputLineTextView.setText(bundle.getString(KEY_CALCULATIONS, "0"));
        }
    }

    private double solveExpression() {
        return calculation.calculate(outputLineTextView.getText().toString());
    }

    @SuppressLint("SetTextI18n")
    private void displayHistory(double solve) {
        calculation.setMemoryCell(Double.toString(solve));
        if (historyColumnTextView.getText().toString().isEmpty()) {
            historyColumnTextView.setText(outputLineTextView.getText() + "\n" + Symbols.EQUALS.getSymbol(this) + solve);
        } else {
            historyColumnTextView.setText(historyColumnTextView.getText().toString() + "\n" + outputLineTextView.getText() + "\n" + Symbols.EQUALS.getSymbol(this) + solve);
        }
    }
}