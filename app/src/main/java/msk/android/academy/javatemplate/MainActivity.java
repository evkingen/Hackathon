package msk.android.academy.javatemplate;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity implements FragmentListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.navigation);


         bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected( MenuItem menuItem) {
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
    }

    @Override
    public void onReplace(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, fragment)
                .addToBackStack(tag)
                .commit();
    }

}
