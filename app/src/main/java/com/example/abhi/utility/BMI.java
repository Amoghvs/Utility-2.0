package com.example.abhi.utility;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static com.example.abhi.utility.R.id.editText;


public class BMI extends AppCompatActivity{

    //private Spinner weightSpinner, heightSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        overridePendingTransition(R.animator.left_in, R.animator.left_out);


        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

    }

    public void calculateClickHandler(View view) {
        if (view.getId() == R.id.calcBMI) {


            // get the references to the widgets


            EditText weightNum = (EditText)findViewById(R.id.weightNum);
            EditText heightNum = (EditText)findViewById(R.id.heightNum);
            TextView resultLabel = (TextView)findViewById(R.id.resultLabel);

            Spinner weightSpinner = (Spinner)findViewById(R.id.weightSpinner);
            Spinner heightSpinner = (Spinner)findViewById(R.id.heightSpinner);
            String weightSpinnerString = weightSpinner.getSelectedItem().toString();
            String heightSpinnerString = heightSpinner.getSelectedItem().toString();


            double weight;
            weight = 0;
            double height;
            height = 0;

            // get the users values from the widget references
            if (!(weightNum.getText().toString().equals(""))) {
                weight = Double.parseDouble(weightNum.getText().toString());
            }

            if (!(heightNum.getText().toString().equals(""))) {
                height = Double.parseDouble(heightNum.getText().toString());
            }

            double bmi;

            // calculate bmi value - pounds and inch
            if (weightSpinnerString.equals("Pounds") && heightSpinnerString.equals("Inch")) {
                bmi = calculateBMI(weight, height);
            } else if (weightSpinnerString.equals("Kilograms") &&
                    heightSpinnerString.equals("Inch")){
                weight = weight * 2.205;
                bmi = calculateBMI(weight, height);
            } else if (weightSpinnerString.equals("Pounds") && heightSpinnerString.equals("CM")){
                height = height / 2.54;
                bmi = calculateBMI(weight, height);
            } else {
                weight = weight * 2.205;
                height = height / 2.54;
                bmi = calculateBMI(weight, height);
            }

            // round to 2 digits
            double newBMI = Math.round(bmi*100.0)/100.0;
            DecimalFormat f = new DecimalFormat("##.00");

            // interpret the meaning of the bmi value
            String bmiInterpretation = interpretBMI(bmi);


            //Error handling
            TextInputLayout til1 = (TextInputLayout) findViewById(R.id.lay1);
            til1.setErrorEnabled(true);
            if (weightNum.getText().toString().isEmpty())
                til1.setError("Please input the weight.");
            else
                til1.setError(null);

            TextInputLayout til2 = (TextInputLayout) findViewById(R.id.lay2);
            til2.setErrorEnabled(true);
            if (weightNum.getText().toString().isEmpty())
            til2.setError("Please input the height.");
            else
                til1.setError(null);


            if (valuereturn(newBMI))
                resultLabel.setText("BMI Score = " + f.format(newBMI) + "\n" + bmiInterpretation);
            else
                resultLabel.setText("");
        }
    }

    // the formula to calculate the BMI index
    private double calculateBMI (double weight, double height) {
        // convert values to metric
        return (double) (((weight / 2.2046) / (height * 0.0254)) / (height * 0.0254));
    }

    public boolean valuereturn (double bmi)
    {
        if (bmi != 0)
            if (bmi <= 1000)
                return true;
        return false;
    }

    // interpret what BMI means
    private String interpretBMI(double bmi) {

        if (bmi < 16) {
            return "You are Severely Underweight";
        } else if (bmi < 18.5) {
            return "You are Underweight";
        } else if (bmi < 25) {
            return "You are Normal";
        }else if (bmi < 30) {
            return "You are Overweight";
        }else if (bmi < 40) {
            return "You are Obese";
        }else if (bmi >= 40) {
            return "You are Morbidly Obese";
        }else {
            return "Enter your Details";
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.right_in, R.animator.right_out);
    }


    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_bmi, container, false);
            return rootView;
        }
    }

}

