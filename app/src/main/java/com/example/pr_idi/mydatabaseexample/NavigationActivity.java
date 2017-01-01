package com.example.pr_idi.mydatabaseexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager myFragmentManager;
    FragmentTransaction myFragmentTransaction;
    NavigationView navigationView;
    MenuItem searchItem;
    boolean firstCharge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        myFragmentManager = getSupportFragmentManager();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
//            firstCharge = true;
            onNavigationItemSelected(navigationView.getMenu().getItem(0));
            navigationView.getMenu().getItem(0).setChecked(true);
//            firstCharge = false;
        } else {
            setTitle(savedInstanceState.getCharSequence("AppBarTitle"));
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation, menu);
//        MenuItem item = menu.findItem(R.id.menuSearch);
//        searchItem = item;
//        SearchView searchView = (SearchView) item.getActionView();
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                BooksSortedByTitleFragment frag = (BooksSortedByTitleFragment)
//                        getSupportFragmentManager().findFragmentById(R.id.content_frame);
//                if (frag != null) {
////                    frag.adapter.getFilter().filter(query);
//                    frag.filtrarPerAutor(query);
//                }
//                else {
//                    Log.v("Navigation","frag is null");
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                BooksSortedByTitleFragment frag = (BooksSortedByTitleFragment)
//                        getSupportFragmentManager().findFragmentById(R.id.content_frame);
//                if (frag != null) {
////                    frag.adapter.getFilter().filter(newText);
//                    frag.filtrarPerAutor(newText);
//                }
//                else {
//                    Log.v("Navigation","frag is null");
//                }
//                return false;
//            }
//        });
//
//        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
//            @Override
//            public boolean onClose() {
//                BooksSortedByTitleFragment frag = (BooksSortedByTitleFragment)
//                        getSupportFragmentManager().findFragmentById(R.id.content_frame);
//                if (frag != null) {
////                    frag.adapter.getFilter().filter(newText);
//                    frag.filtrarPerAutor("");
//                }
//                else {
//                    Log.v("Navigation","frag is null");
//                }
//                return false;
//            }
//        });

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
////            searchItem.setEnabled(!searchItem.isEnabled());
//            searchItem.setVisible(!searchItem.isVisible());
////            Log.v("searchItem is enabled? ", String.valueOf(searchItem.isEnabled()));
//            Log.v("searchItem is visible? ", String.valueOf(searchItem.isVisible()));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        myFragmentTransaction = myFragmentManager.beginTransaction();

        if (id == R.id.title_sorting) {
            BooksSortedByTitleFragment f = new BooksSortedByTitleFragment();
            myFragmentTransaction.replace(R.id.content_frame,f);
            navigationView.getMenu().getItem(0).setChecked(true);

//            if (!firstCharge) setSearchIcon(true);
        }
        else if (id == R.id.category_sorting) {
            BooksSortedByCategoryFragment f = new BooksSortedByCategoryFragment();
            myFragmentTransaction.replace(R.id.content_frame, f);
            navigationView.getMenu().getItem(1).setChecked(true);
//            invalidateOptionsMenu();
//            setSearchIcon(false);
        }
        else if (id == R.id.esborrar_llibre) {
            navigationView.getMenu().getItem(2).setChecked(true);
//            invalidateOptionsMenu();
//            setSearchIcon(false);
        }
        else if (id == R.id.help_menu) {
            navigationView.getMenu().getItem(3).setChecked(true);
//            setSearchIcon(false);
        }
        else if (id == R.id.about_menu) {
            navigationView.getMenu().getItem(4).setChecked(true);
//            setSearchIcon(false);
        }
        myFragmentTransaction.commit();
        setTitle(item.getTitle());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

//    public void setSearchIcon(boolean value) {
////        searchItem.setEnabled(value);
//        searchItem.setVisible(value);
////        Log.v("searchItem is ", String.valueOf(searchItem.getTitle()));
////        Log.v("searchItem is enabled? ", String.valueOf(searchItem.isEnabled()));
//        Log.v("searchItem is visible? ", String.valueOf(searchItem.isVisible()));
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putCharSequence("AppBarTitle", getTitle());
        super.onSaveInstanceState(outState);
    }
}
