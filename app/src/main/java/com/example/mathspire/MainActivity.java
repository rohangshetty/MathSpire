package com.example.mathspire;

import static androidx.core.view.ViewCompat.setOnApplyWindowInsetsListener;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        Button permutationbtn=findViewById(R.id.PermutationButton);
        permutationbtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent permutationIntend=new Intent(MainActivity.this,permutationpage.class);
                startActivity(permutationIntend);
            }
        });
        Button combinationbtn=findViewById(R.id.CombinationButton);
        combinationbtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent permutationIntend=new Intent(MainActivity.this,combinationpage.class);
                startActivity(permutationIntend);
            }
        });
        Button arithmeticbtn=findViewById(R.id.arithmeticButton);
        arithmeticbtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent permutationIntend=new Intent(MainActivity.this,arithmaticpage.class);
                startActivity(permutationIntend);
            }
        });
        Button quadraticbtn=findViewById(R.id.quadraticbutton);
        quadraticbtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                Intent permutationIntend=new Intent(MainActivity.this,quadraticpage.class);
                startActivity(permutationIntend);
            }
        });


    }

}