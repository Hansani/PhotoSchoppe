package com.assignment.hansi.photoschoppe;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;

import java.util.List;

import roboguice.activity.RoboActivity;

/**
 * Created by Hansi on 02/08/2017.
 */

public class ValidatorActivity extends RoboActivity implements Validator.ValidationListener , View.OnClickListener{

    private Validator validator;
    protected boolean isValidate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    public boolean validate() {
        if (validator != null) {
            validator.validate();
        }
        return isValidate;
    }

    @Override
    public void onValidationSucceeded() {
        isValidate = Boolean.TRUE;
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        isValidate = Boolean.FALSE;
        for (ValidationError error : errors) {
            View view = error.getView();
            String error_message = error.getCollatedErrorMessage(this);

            if (view instanceof Spinner){
                Spinner spinner= (Spinner) view;
                view = ((LinearLayout) spinner.getSelectedView()).getChildAt(0);
            }
            if (view instanceof TextView){
                TextView textView = (TextView) view;
                textView.setError(error_message);
            }

        }
    }


    @Override
    public void onClick(View view) {
        validator.validate();
    }
}
