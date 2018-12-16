package msk.android.academy.javatemplate;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity implements FragmentListener, AuthorizationCallback {

    private BottomNavigationView bottomNavigationView;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth = FirebaseAuth.getInstance();
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setVisibility(View.INVISIBLE);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                Fragment selectedFragment = null;
                switch (menuItem.getItemId()) {
                    case R.id.home_btn:
                        selectedFragment = ListEventFragment.newInstance();
                        break;
                    case R.id.create_btn:
                        selectedFragment = CreateEventFragment.newInstance();
                        break;
                    case R.id.favorites_btn:
                        selectedFragment = FavoritesFragment.newInstance();
                        break;
                    case R.id.contacts_btn:
                        selectedFragment = ContactsFragment.newInstance();
                        break;
                    case R.id.map_btn:
                        selectedFragment = MapFragment.newInstance();
                }

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, selectedFragment);
                transaction.commit();
                return true;
            }
        });
        AuthorizationFragment authorizationFragment = AuthorizationFragment.newInstance();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, authorizationFragment);
        transaction.commit();
    }

    @Override
    public void onReplace(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(tag)
                .commit();
    }

    @Override
    public void onChangeVisibility(int mode) {
        bottomNavigationView.setVisibility(mode);
    }

    @Override
    public void onReplace(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        firebaseAuth.signOut();
    }
}

