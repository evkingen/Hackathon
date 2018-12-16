package msk.android.academy.javatemplate;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContactsFragment fragment = ContactsFragment.newInstance();
        onReplace(fragment, null);
    }

    @Override
    public void onReplace(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.test, fragment)
                .addToBackStack(tag)
                .commit();
    }
}
