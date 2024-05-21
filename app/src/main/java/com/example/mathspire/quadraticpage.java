package com.example.mathspire;

import static android.widget.Toast.LENGTH_SHORT;
import static android.widget.Toast.makeText;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class quadraticpage extends AppCompatActivity {
    private EditText editTextA, editTextB, editTextC;
    private TextView textViewResult, textViewSteps;
    private Button calculateButton, stepsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quadraticpage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        editTextA = findViewById(R.id.quadraticeditTextA);
        editTextB = findViewById(R.id.quadraticeditTextB);
        editTextC = findViewById(R.id.quadraticeditTextC);
        textViewResult = findViewById(R.id.quadratictextViewResult);
        textViewSteps = findViewById(R.id.quadratictextViewSteps);
        calculateButton = findViewById(R.id.quadraticcalculateButton);
        stepsButton = findViewById(R.id.quadraticstepsButton);
        calculateButton.setOnClickListener(v -> calculateQuadraticEquation());
        stepsButton.setOnClickListener(v -> displaySteps());

    }
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    private void calculateQuadraticEquation() {
        String aStr = editTextA.getText().toString();
        String bStr = editTextB.getText().toString();
        String cStr = editTextC.getText().toString();

        if (aStr.isEmpty() || bStr.isEmpty() || cStr.isEmpty()) {
            makeText(quadraticpage.this, "Please enter values for a, b, and c", LENGTH_SHORT).show();
            return;
        }

        double a = Double.parseDouble(aStr);
        double b = Double.parseDouble(bStr);
        double c = Double.parseDouble(cStr);

        double[] roots = solveQuadraticEquation(a, b, c);

        if (roots == null) {
            textViewResult.setText("No real roots found");
        } else {
            textViewResult.setText(String.format("Roots: x1 = %.2f, x2 = %.2f", roots[0], roots[1]));
            stepsButton.setVisibility(View.VISIBLE);
        }

        textViewResult.setVisibility(View.VISIBLE);
    }

    private double[] solveQuadraticEquation(double a, double b, double c) {
        double discriminant = b * b - 4 * a * c;

        if (discriminant < 0) {
            return null;
        } else if (discriminant == 0) {
            double root = -b / (2 * a);
            return new double[]{root};
        } else {
            double root1 = (-b + Math.sqrt(discriminant)) / (2 * a);
            double root2 = (-b - Math.sqrt(discriminant)) / (2 * a);
            return new double[]{root1, root2};
        }
    }
    private void displaySteps() {
        String aStr = editTextA.getText().toString();
        String bStr = editTextB.getText().toString();
        String cStr = editTextC.getText().toString();

        double a = Double.parseDouble(aStr);
        double b = Double.parseDouble(bStr);
        double c = Double.parseDouble(cStr);

        double[] roots = solveQuadraticEquation(a, b, c);

        StringBuilder steps = new StringBuilder();
        steps.append("Quadratic Equation: ").append(a).append("x^2 + ").append(b).append("x + ").append(c).append(" = 0\n\n");

        steps.append("Step 1: Calculate the discriminant\n");
        steps.append("Discriminant = b^2 - 4ac = ").append(b * b).append(" - 4 * ").append(a).append(" * ").append(c).append(" = ").append(b * b - 4 * a * c).append("\n\n");

        if (roots == null) {
            steps.append("Step 2: The discriminant is negative, so there are no real roots.\n");
        } else if (roots.length == 1) {
            steps.append("Step 2: The discriminant is 0, so there is one real root.\n");
            steps.append("Root = -b / (2a) = -").append(b).append(" / (2 * ").append(a).append(") = ").append(roots[0]).append("\n\n");
        } else {
            steps.append("Step 2: The discriminant is positive, so there are two real roots.\n");
            steps.append("Root 1 = (-b + sqrt(discriminant)) / (2a) = (-").append(b).append(" + sqrt(").append(b * b - 4 * a * c).append(")) / (2 * ").append(a).append(") = ").append(roots[0]).append("\n");
            steps.append("Root 2 = (-b - sqrt(discriminant)) / (2a) = (-").append(b).append(" - sqrt(").append(b * b - 4 * a * c).append(")) / (2 * ").append(a).append(") = ").append(roots[1]).append("\n\n");
        }

        textViewSteps.setText(steps.toString());
        textViewSteps.setVisibility(View.VISIBLE);
    }
}

