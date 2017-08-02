package com.assignment.hansi.photoschoppe;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;

import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

/**
 * Created by Hansi on 25/07/2017.
 */

@ContentView(R.layout.activity_create_account)
public class CreateAccountActivity extends ValidatorActivity implements View.OnClickListener {

    @NotEmpty(message = "user name is a required field")
    @InjectView(R.id.CA_txt_user_name)
    private EditText user_name;

    @NotEmpty(message = "email is a required field")
    @Email(message = "Please enter valid email address")
    @InjectView(R.id.CA_txt_email)
    private EditText email;

    @NotEmpty(message = "password is a required field")
    @Password(min = 8, message = "Password must at lest 8 characters", scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE_SYMBOLS)
    @InjectView(R.id.CA_txt_password)
    private EditText password;

    @NotEmpty(message = "enter password again")
    @ConfirmPassword
    @InjectView(R.id.CA_txt_confirm_password)
    private EditText confirm_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void onClick(View view) {
        super.onClick(view);
        if (isValidate) {
            SharedPreferences preferences = getSharedPreferences("com.assignment.hansi.userdetail", 0);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(getString(R.string.user_name_SP), user_name.getText().toString());
            editor.putString(getString(R.string.email_SP), email.getText().toString());
            if (password.getText().toString().equals(confirm_password.getText().toString())) {
                editor.putString(getString(R.string.password_SP), password.getText().toString());
                editor.putBoolean(getString(R.string.is_login), Boolean.TRUE);
                boolean flag = editor.commit();
                if (flag) {
                    Toast.makeText(getApplicationContext(), "user account has been created", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    }


}
