package com.example.pr_idi.mydatabaseexample;

import android.os.Bundle;
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


    private FragmentManager myFragmentManager;
    private FragmentTransaction myFragmentTransaction;
    NavigationView navigationView;

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
            onNavigationItemSelected(navigationView.getMenu().getItem(0));
            navigationView.getMenu().getItem(0).setChecked(true);
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
        }
        else if (id == R.id.category_sorting) {
            BooksSortedByCategoryFragment f = new BooksSortedByCategoryFragment();
            myFragmentTransaction.replace(R.id.content_frame,f);
            navigationView.getMenu().getItem(1).setChecked(true);
        }
        else if (id == R.id.books_by_author) {
            FindBooksByAuthorFragment f = new FindBooksByAuthorFragment();
            myFragmentTransaction.replace(R.id.content_frame,f);
            navigationView.getMenu().getItem(2).setChecked(true);
        }
        else if (id == R.id.new_book){
            AddNewBookFragment f = new AddNewBookFragment();
            myFragmentTransaction.replace(R.id.content_frame,f);
            navigationView.getMenu().getItem(3).setChecked(true);
        }
        else if (id == R.id.delete_book){
            DeleteBookFragment f = new DeleteBookFragment();
            myFragmentTransaction.replace(R.id.content_frame,f);
            navigationView.getMenu().getItem(4).setChecked(true);
        }
        else if (id == R.id.change_valoration_menu) {
            ChangeValorationFragment f = new ChangeValorationFragment();
            myFragmentTransaction.replace(R.id.content_frame,f);
            navigationView.getMenu().getItem(5).setChecked(true);
        }
        else if (id == R.id.help_menu) {
            HelpFragment f = new HelpFragment();
            myFragmentTransaction.replace(R.id.content_frame,f);
            navigationView.getMenu().getItem(6).setChecked(true);
        }
        else if (id == R.id.about_menu) {
            navigationView.getMenu().getItem(7).setChecked(true);
            AboutFragment f = new AboutFragment();
            myFragmentTransaction.replace(R.id.content_frame,f);
        }
        myFragmentTransaction.commit();
        setTitle(item.getTitle());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putCharSequence("AppBarTitle", getTitle());
        super.onSaveInstanceState(outState);
    }
}
