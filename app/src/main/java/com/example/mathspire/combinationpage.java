package com.example.mathspire;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class combinationpage extends AppCompatActivity {
    private EditText editTextN;
    private EditText editTextR;
    private TextView textViewResult;
    private Button stepsButton;
    private TextView textViewSteps;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_combinationpage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextN = findViewById(R.id.combinationeditTextN);
        editTextR = findViewById(R.id.combinationeditTextR);
        textViewResult = findViewById(R.id.combinationtextViewResult);
        textViewSteps = findViewById(R.id.combinationtextViewSteps);
        Button calculatecombination=findViewById(R.id.combinationcalculateButton);
        calculatecombination.setOnClickListener(v -> {
            String nStr = editTextN.getText().toString();
            String rStr = editTextR.getText().toString();

            if (nStr.isEmpty() || rStr.isEmpty()) {
                Toast.makeText(combinationpage.this, "Please enter values for both n and r", Toast.LENGTH_SHORT).show();
                return;
            }

            int n = Integer.parseInt(nStr);
            int r = Integer.parseInt(rStr);

            if (n < 0 || r < 0) {
                Toast.makeText(combinationpage.this, "Values for n and r must be positive integers", Toast.LENGTH_SHORT).show();
                return;
            }

            if (r > n) {
                Toast.makeText(combinationpage.this, "Value of r cannot be greater than n", Toast.LENGTH_SHORT).show();
                return;
            }

            long combination = calculateCombination(n, r);

            textViewResult.setText("Result: " + combination);
            textViewResult.setVisibility(View.VISIBLE);

            stepsButton = findViewById(R.id.combinationstepsButton);
            stepsButton.setVisibility(View.VISIBLE);
            stepsButton.setOnClickListener(v1 -> displaySteps(n, r, combination));
        });

        }
    private long calculateCombination(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n - r));
    }

    private long factorial(int num) {
        long result = 1;
        for (int i = 1; i <= num; i++) {
            result *= i;
        }
        return result;
    }
    @SuppressLint("SetTextI18n")
    private void displaySteps(int n, int r, long combination) {
        textViewSteps.setText("Steps: \n" +
                "Step 1: Calculate the factorial of n: n! = " + n + "! = " + factorial(n) + "\n" +
                "Step 2: Calculate the factorial of r: r! = " + r + "! = " + factorial(r) + "\n" +
                "Step 3: Calculate the factorial of n - r: (n - r)! = " + (n - r) + "! = " + factorial(n - r) + "\n" +
                "Step 4: Calculate the combination: C(n, r) = n! / (r! * (n - r)!) = " + combination);
        textViewSteps.setVisibility(View.VISIBLE);
    }
    }




