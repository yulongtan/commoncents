package edu.washington.ischool.commoncents.commoncents.Activies;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import edu.washington.ischool.commoncents.commoncents.R;

public class LoginActivity extends AppCompatActivity {

    private Button signupButton, loginButton;
    private String email, password;
    private Context context;
    private LinearLayout layout;
    private EditText emailEditText, passwordEditText;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        context = getBaseContext();
        layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);

        emailEditText = new EditText(context);
        emailEditText.setHint("email");
        emailEditText.setHintTextColor(Color.GRAY);
        emailEditText.setTextColor(Color.BLACK);
        emailEditText.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);

        passwordEditText = new EditText(context);
        passwordEditText.setHint("password");
        passwordEditText.setHintTextColor(Color.GRAY);
        passwordEditText.setTextColor(Color.BLACK);
        passwordEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);

        builder = new AlertDialog.Builder(context);

        signupButton = (Button) findViewById(R.id.signupButton);
        loginButton = (Button) findViewById(R.id.loginButton);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth("Sign Up", "Create Account");

            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth("Log In", "Log In");
            }
        });
    }

    private void auth(String title, String action) {
        if (emailEditText.getParent() != null) {
            ((ViewGroup) emailEditText.getParent()).removeView(emailEditText);
        }
        layout.addView(emailEditText);

        if (passwordEditText.getParent() != null) {
            ((ViewGroup) passwordEditText.getParent()).removeView(passwordEditText);
        }
        layout.addView(passwordEditText);

        builder.setView(layout); // <== ISSUE IS WITH THIS LINE WHEN CALLED TWICE?
        builder.setTitle(title);
        builder.setPositiveButton(action, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                email = emailEditText.getText().toString();
                password = passwordEditText.getText().toString();
                Toast.makeText(getBaseContext(), "Email: " + email + "; Password: " +
                    password, Toast.LENGTH_SHORT).show();
            }
        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        })
            .setIcon(android.R.drawable.ic_dialog_email)
            .show();
    }
}
