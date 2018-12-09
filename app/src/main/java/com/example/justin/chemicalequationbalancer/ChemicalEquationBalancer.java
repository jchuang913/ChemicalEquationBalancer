package com.example.justin.chemicalequationbalancer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ChemicalEquationBalancer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemical_equation_balancer);

        final EditText unbalanced = findViewById(R.id.UnbalancedEquation);
        final Button balanceButton = findViewById(R.id.BalanceButton);
        final TextView balancedCoeff = findViewById(R.id.BalancedCoeff);

        balanceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String unbalancedEquation = unbalanced.getText().toString();
                // do balancing magic
                balancedCoeff.setText(unbalancedEquation);
            }
        });
    }
}
