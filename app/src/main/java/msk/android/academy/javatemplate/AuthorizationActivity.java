package msk.android.academy.javatemplate;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.EditorInfo;

public class AuthorizationActivity extends AppCompatActivity {

    private static final String EXIST_NAME = "ANDROID";
    private static final int MIN_TEXT_LENGTH = 8;
    private static final String EMPTY_STRING = "";
    private TextInputEditText mPassword;
    private TextInputEditText mUsername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);
        TextInputLayout mContainerUsername = findViewById(R.id.container_username);
        TextInputLayout mContainerPassword = findViewById(R.id.container_password);
        mUsername = findViewById(R.id.username);
        mPassword = findViewById(R.id.password);
        mUsername.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (textView.getContext() != null) {
                if (mUsername.getText() != null) {
                    String username = mUsername.getText().toString();
                    if (i == EditorInfo.IME_ACTION_GO && username.equals(EXIST_NAME)) {
                        mContainerUsername.setError("This username alredy exist!");
                    } else {
                        mContainerUsername.setError(EMPTY_STRING);
                    }
                }
            }
            return true;
        });
        mPassword.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (textView.getContext() != null) {
                if (mPassword.getText() != null) {
                    int textLength = mPassword.getText().length();
                    if (i == EditorInfo.IME_ACTION_DONE && textLength > 0 && textLength < MIN_TEXT_LENGTH) {
                        mContainerPassword.setError("Input password more then 8 characters!");
                    } else {
                        mContainerPassword.setError(EMPTY_STRING);
                    }
                }
            }
            return true;
        });
    }
}

