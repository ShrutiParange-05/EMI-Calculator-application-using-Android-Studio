package com.example.emicalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Linking UI elements
        EditText etSalary = findViewById(R.id.etSalary);
        Button btnCalculateTax = findViewById(R.id.btnCalculateTax);
        TextView tvTaxResult = findViewById(R.id.tvTaxResult);

        EditText etLoanAmount = findViewById(R.id.etLoanAmount);
        EditText etInterestRate = findViewById(R.id.etInterestRate);
        EditText etTenure = findViewById(R.id.etTenure);
        Button btnCalculateEMI = findViewById(R.id.btnCalculateEMI);
        TextView tvEMIResult = findViewById(R.id.tvEMIResult);

        // Tax Calculation Logic
        btnCalculateTax.setOnClickListener(v -> {
            String salaryStr = etSalary.getText().toString();
            if (!salaryStr.isEmpty()) {
                double salary = Double.parseDouble(salaryStr);
                double tax = calculateTax(salary);
                tvTaxResult.setText("Tax Payable: ₹" + tax);
            } else {
                tvTaxResult.setText("Please enter a valid salary");
            }
        });

        // EMI Calculation Logic
        btnCalculateEMI.setOnClickListener(v -> {
            String loanStr = etLoanAmount.getText().toString();
            String rateStr = etInterestRate.getText().toString();
            String tenureStr = etTenure.getText().toString();

            if (!loanStr.isEmpty() && !rateStr.isEmpty() && !tenureStr.isEmpty()) {
                double principal = Double.parseDouble(loanStr);
                double rate = Double.parseDouble(rateStr);
                int tenure = Integer.parseInt(tenureStr);
                double emi = calculateEMI(principal, rate, tenure);
                tvEMIResult.setText("Monthly EMI: ₹" + emi);
            } else {
                tvEMIResult.setText("Please enter valid inputs");
            }
        });
    }

    // Function to calculate tax
    private double calculateTax(double salary) {
        if (salary <= 250000) return 0;
        if (salary <= 500000) return (salary - 250000) * 0.05;
        if (salary <= 1000000) return (250000 * 0.05) + (salary - 500000) * 0.2;
        return (250000 * 0.05) + (500000 * 0.2) + (salary - 1000000) * 0.3;
    }

    // Function to calculate Loan EMI
    private double calculateEMI(double principal, double rate, int years) {
        double monthlyRate = rate / (12 * 100);
        int months = years * 12;
        return (principal * monthlyRate * Math.pow(1 + monthlyRate, months)) /
                (Math.pow(1 + monthlyRate, months) - 1);
    }
}
