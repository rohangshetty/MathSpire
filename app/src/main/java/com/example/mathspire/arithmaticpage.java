package com.example.mathspire;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;



public class arithmaticpage extends AppCompatActivity {
    private EditText arithmeticExpression;
    private TextView textViewResult;
    private Button stepsButton;
    private TextView textViewSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_arithmaticpage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        arithmeticExpression = findViewById(R.id.arithmeticExpression);
        textViewResult = findViewById(R.id.arithmetictextViewResult);
        textViewSteps = findViewById(R.id.arithmetictextViewSteps);
        Button calculateButton = findViewById(R.id.arithmeticcalculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                String expression = arithmeticExpression.getText().toString();


                if (expression.isEmpty()) {
                    Toast.makeText(arithmaticpage.this, "Please enter an arithmetic expression", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    double result = evaluateExpression(expression);
                    textViewResult.setText("Result: " + result);
                    textViewResult.setVisibility(View.VISIBLE);

                    stepsButton = findViewById(R.id.arithmeticstepsButton);
                    stepsButton.setVisibility(View.VISIBLE);
                    stepsButton.setOnClickListener(v1 -> displaySteps(expression, result));
                } catch (Exception e) {
                    Toast.makeText(arithmaticpage.this, "Invalid expression: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private double evaluateExpression(String expression) {
        Context context = Context.enter();
        try {
            context.setOptimizationLevel(-1);
            Scriptable scope = context.initStandardObjects();

            Object result = context.evaluateString(scope, expression, "Math-spire", 1, null);
            if (result instanceof Number) {
                return ((Number) result).doubleValue();
            } else {
                throw new IllegalArgumentException("Invalid result type");
            }
        } catch (Exception e) {
            throw new IllegalArgumentException("Error evaluating expression: " + e.getMessage());
        } finally {
            Context.exit();
        }
    }

    @SuppressLint("SetTextI18n")
    private void displaySteps(String expression, double result) {
        textViewSteps.setText("Steps: \n" +
                "Step 1: Entered expression: " + expression + "\n" +
                "Step 2: Evaluated result: " + result);
        textViewSteps.setVisibility(View.VISIBLE);
    }
}