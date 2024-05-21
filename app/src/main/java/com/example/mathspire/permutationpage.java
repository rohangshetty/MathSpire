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

public class permutationpage extends AppCompatActivity {
    private EditText editTextN;
    private EditText editTextR ;
    private TextView textViewResult ;

    private Button stepsButton;
    private TextView textViewSteps;




    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_permutationpage);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textViewSteps = findViewById(R.id.permutationtextViewSteps);
        textViewResult = findViewById(R.id.permutationtextViewResult);
        editTextR = findViewById(R.id.permutationeditTextR);
        editTextN = findViewById(R.id.permutationeditTextN);
        Button calculateButton = findViewById(R.id.permutationcalculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                // Get the values from the EditText fields
                String nStr = editTextN.getText().toString();
                String rStr = editTextR.getText().toString();

                // Check if the input fields are empty
                if (nStr.isEmpty() || rStr.isEmpty()) {
                    Toast.makeText(permutationpage.this, "Please enter values for both n and r", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Parse the input values to integers
                int n = Integer.parseInt(nStr);
                int r = Integer.parseInt(rStr);

                // Check if the values are positive integers
                if (n < 0 || r < 0) {
                    Toast.makeText(permutationpage.this, "Values for n and r must be positive integers", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if the value of r is less than or equal to the value of n
                if (r > n) {
                    Toast.makeText(permutationpage.this, "Value of r cannot be greater than n", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Perform permutation calculation
                long permutation = factorial(n) / factorial(n - r);


                textViewResult.setText("Result: " + permutation);
                textViewResult.setVisibility(View.VISIBLE);
                stepsButton=findViewById(R.id.permutationstepsButton);
                stepsButton.setVisibility(View.VISIBLE);
                stepsButton.setOnClickListener(v1 -> displaySteps( n,r,permutation)

                );


            }
        });
    }

        public long factorial(int num){
            long result = 1;
            for (int i = 1; i <= num; i++) {
                result *= i;
            }
            return result;
        }

        @SuppressLint("SetTextI18n")
        public void displaySteps(int n, int r, long permutation){
            textViewSteps.setText("Steps: \n" +
                    "Step 1: Calculate the factorial of n: n! = " + n + "! = " + factorial(n) + "\n" +
                    "Step 2: Calculate the factorial of n - r: (n - r)! = " + (n - r) + "! = " + factorial(n - r) + "\n" +
                    "Step 3: Calculate the permutation: P(n, r) = n! / (n - r)! = " + permutation);
            textViewSteps.setVisibility(View.VISIBLE);
        }


}