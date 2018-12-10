package com.example.justin.chemicalequationbalancer;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import flanagan.complex.ComplexMatrix;

import static java.lang.String.valueOf;

//Thomas Flanagan at www.ee.ucl.ac.uk/~mflanaga


public class ChemicalEquationBalancer extends AppCompatActivity {

    private EditText unbalancedEquation;
    private TextView balancedEquation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chemical_equation_balancer);

        unbalancedEquation = (EditText) findViewById(R.id.unbalancedEquation);
        Button button = (Button) findViewById(R.id.balanceButton);

        balancedEquation = (TextView) findViewById(R.id.balancedEquation);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                balance();
            }
        });
    }

    private void balance() {
        String result = "";
        String string = valueOf(unbalancedEquation.getText());

        String[] arr = string.split("\\), \\(");
        arr[0] = arr[0].substring(1);
        arr[arr.length - 1] = arr[arr.length - 1].substring(0, arr[arr.length - 1].length() - 1);
        String[] findlen = arr[0].split(", ");
        int xlen = findlen.length;
        int ylen = arr.length;
        double[][] mainarr = new double[xlen][ylen];

        for (int i = 0; i < arr.length; i++) {
            String[] subarr = arr[i].split(", ");
            if (subarr.length != xlen) {
                result = "Make the array rectangular";
                balancedEquation.setText(result);
                return;
            }
            for (int j = 0; j < xlen; j++) {
                mainarr[i][j] = Double.parseDouble(subarr[j]);
            }
        }

        ComplexMatrix matrix = new ComplexMatrix(mainarr);
        matrix.reducedRowEchelonForm();

        for (int y = 0; y < ylen; y++) {
            for (int x = 0; x < xlen; x++) {
                result += "" + matrix.getElementReference(x, y);
                result = result.substring(0, result.length() - 7) + "    ";
                if (x == xlen - 1) {
                    result += "\n";
                }
            }
        }


        /*
        String[] loc = string.split(" = ");
        String left = loc[0];
        String right = loc[1];
        String[] leftArr = left.split(" + ");
        String[] rightArr = right.split(" + ");

        ArrayList<String> elements = new ArrayList<>();
        String newOriginal = string.replaceAll("[^A-Za-z]+", " ");
        String[] listOfElements = newOriginal.split(" ");
        for (String item : listOfElements) {
            String newItem = item.trim();
            if (!elements.contains(newItem)) {
                elements.add(newItem);
            }
        }

        double[][] aMatrix = new double[listOfElements.length][leftArr.length + rightArr.length];
        Map<String, Integer> elementToNum = new HashMap<>();
        for (int i = 0; i < elements.size(); i++) {
            elementToNum.put(elements.get(i), i);
        }

        for (int i = 0; i < leftArr.length; i++) { ///////////////////////////////////////////////////////////////
            Pattern elementPattern = Pattern.compile("([A-Z][a-z]?)(\\s)([0-9]*)");
            Matcher elementMatcher = elementPattern.matcher(leftArr[i]);
            while (elementMatcher.find()) {
                String element = elementMatcher.group(1);
                String count = "1";
                count += elementMatcher.group(3);
                //result += " . " + elementToNum.get(element) + "," + count;
                aMatrix[elementToNum.get(element)][i] = Integer.parseInt(count);
            }
        }

        for (int i = 0; i < rightArr.length; i++) { ///////////////////////////////////////////////////////////////
            Pattern elementPattern = Pattern.compile("([A-Z][a-z]?)(\\s)([0-9]*)");
            Matcher elementMatcher = elementPattern.matcher(rightArr[i]);
            while (elementMatcher.find()) {
                String element = elementMatcher.group(1);
                String count = "1";
                count += elementMatcher.group(3);
                //result += " . " + elementToNum.get(element) + "," + count;
                aMatrix[elementToNum.get(element)][i + leftArr.length] = Integer.parseInt(count);
            }
        }
        for (int i = 0; i < aMatrix.length; i++) {
            for (int x = 0; x < aMatrix[0].length; x++) {
                result += " " + aMatrix[i][x];
            }
        }


        ComplexMatrix matrix = new ComplexMatrix(aMatrix);

        matrix.reducedRowEchelonForm();
        for (int i = 0; i < aMatrix.length; i++) {
            for (int j = 0; j < aMatrix[0].length; j++) {
                result += "" + matrix.getElementReference(0, 0);
            }
        }

        for (int i = 0; i < aMatrix.length; i++) {
            result += "" + (matrix.getElementCopy(i, aMatrix[0].length) + "\n" );
        }
        result += "" + (matrix.getElementCopy(0, 0) + "\n" );
*/
        balancedEquation.setText(result);
    }
}
