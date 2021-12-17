package com.angmadera.tipsplit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText billTotal;
    private TextView tipAmount;
    private TextView totalTip;
    private EditText numPeople;
    private TextView totPerPerson;
    private TextView over;
    private RadioGroup radioGroup;
    private Button button1;
    double total;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        billTotal = findViewById(R.id.editTextNumberDecimal);
        tipAmount = findViewById(R.id.TipAmountView);
        totalTip = findViewById(R.id.TotalwTipView);
        numPeople = findViewById(R.id.editTextNumberPeople);
        totPerPerson = findViewById(R.id.totalPerPersonTextView);
        over = findViewById(R.id.overageTextView);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        button1 = (Button)findViewById(R.id.button);
        button1.setOnClickListener(this::buttonClicked);
    }

    public void radioClicked(View v) {
        String bill = billTotal.getText().toString();
        if(TextUtils.isEmpty(bill)) {
            radioGroup.clearCheck();
            button1.setSelected(false);
        }
        else {
            double percent = 0;
            if (v.getId() == R.id.radioButton) {
                percent = .12;
            } else if (v.getId() == R.id.radioButton2) {
                percent = .15;
            } else if (v.getId() == R.id.radioButton3) {
                percent = .18;
            } else if (v.getId() == R.id.radioButton4) {
                percent = .20;
            }
            double doubleBill = Double.parseDouble(bill);
            double tip = doubleBill * percent;
            String output = String.format("%.2f", tip);
            tipAmount.setText("$" + output);
            total = tip + doubleBill;
            String outputTwo = String.format("%.2f", total);
            totalTip.setText("$" + outputTwo);
        }
        }

    public void buttonClicked(View v) {
        NumberFormat n = NumberFormat.getCurrencyInstance(Locale.US);
        n.setRoundingMode(RoundingMode.UP);
        String people = numPeople.getText().toString();
        if(TextUtils.isEmpty(people)) {
            button1.setSelected(false);
        }
        else {
            Double doublePeople = Double.parseDouble(people);
            double newTotal = total / doublePeople;
            String output = n.format(newTotal);
            totPerPerson.setText(output);
            double totalNewTotal = Math.ceil(newTotal * 100.0) / 100.0;
            double totalCalculation = doublePeople * totalNewTotal;
            double overage = totalCalculation - total;
            n.setRoundingMode(RoundingMode.HALF_EVEN);
            String output3 = n.format(overage);
            over.setText(output3);
        }
    }

    public void buttonClicked2(View v) {
        billTotal.setText("");
        tipAmount.setText("");
        totalTip.setText("");
        numPeople.setText("");
        totPerPerson.setText("");
        over.setText("");
        radioGroup.clearCheck();
        button1.setSelected(false);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("BILL TOTAL", billTotal.getText().toString());
        outState.putString("TOTAL TIP", totalTip.getText().toString());
        outState.putString("TIP AMOUNT", tipAmount.getText().toString());
        outState.putString("NUMBER PEOPLE", numPeople.getText().toString());
        outState.putString("TOTAL PER PERSON", totPerPerson.getText().toString());
        outState.putString("OVERAGE", over.getText().toString());
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        billTotal.setText(savedInstanceState.getString("BILL TOTAL"));
        totalTip.setText(savedInstanceState.getString("TOTAL TIP"));
        tipAmount.setText(savedInstanceState.getString("TIP AMOUNT"));
        numPeople.setText(savedInstanceState.getString("NUMBER PEOPLE"));
        totPerPerson.setText(savedInstanceState.getString("TOTAL PER PERSON"));
        over.setText(savedInstanceState.getString("OVERAGE"));
    }
}