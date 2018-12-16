package msk.android.academy.javatemplate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.ActionProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthorizationFragment extends Fragment {

    private static final String TAG = AuthorizationFragment.class.getSimpleName();
    private static final String EXIST_NAME = "ANDROID";
    private static final int MIN_TEXT_LENGTH = 8;
    private static final String EMPTY_STRING = "";
    private TextInputEditText mPassword;
    private TextInputEditText mUsername;
    private TextInputLayout mContainerUsername;
    private TextInputLayout mContainerPassword;
    private Button mSignIn;
    private Button mSignUp;

    private FirebaseAuth mAuth;
    private AuthorizationCallback activity;

    public AuthorizationFragment() {

    }

    public static AuthorizationFragment newInstance() {
        return new AuthorizationFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof AuthorizationCallback) {
            activity = (AuthorizationCallback) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_authorization, container, false);
        mAuth = FirebaseAuth.getInstance();
        mContainerUsername = view.findViewById(R.id.container_username);
        mContainerPassword = view.findViewById(R.id.container_password);
        mUsername = view.findViewById(R.id.username);
        mPassword = view.findViewById(R.id.password);
        mSignIn = view.findViewById(R.id.button_sign_in);
        mSignUp = view.findViewById(R.id.button_sign_up);
        mUsername.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (textView.getContext() != null) {
                if (mUsername.getText() != null) {
                    String username = mUsername.getText().toString();
                    if (i == EditorInfo.IME_ACTION_GO && username.equals(EXIST_NAME)) {
                        mContainerUsername.setError("This username already exist!");
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
        mSignUp.setOnClickListener(v -> {
                    String email = mUsername.getText().toString();
                    String password = mPassword.getText().toString();

                    if (!email.equals("") && !password.equals("")) {
                        createUserWithEmailAndPassword(email, password);
                    }
                }
        );
        mSignIn.setOnClickListener(v -> {
            String email = mUsername.getText().toString();
            String password = mPassword.getText().toString();

            if (!email.equals("") && !password.equals("")) {
                signInWithEmailAndPassword(email, password);
            }
        });
        return view;
    }

    private void createUserWithEmailAndPassword(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "createUserWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            } else {
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Toast.makeText(getActivity(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                updateUI(null);
            }
        });
    }

    private void signInWithEmailAndPassword(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(getActivity(), task -> {
            if (task.isSuccessful()) {
                Log.d(TAG, "signInWithEmail:success");
                FirebaseUser user = mAuth.getCurrentUser();
                updateUI(user);
            } else {
                Log.w(TAG, "signInWithEmail:failure", task.getException());
                Toast.makeText(getActivity(), "Authentication failed", Toast.LENGTH_SHORT).show();
                updateUI(null);
            }
        });
    }

    private void signOut() {
        mAuth.signOut();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        if(user==null) {
            mPassword.setText("");
            mUsername.setText("");
        }else{
            activity.onChangeVisibility(View.VISIBLE);
            activity.onReplace(ListEventFragment.newInstance());
        }
    }


}

