package com.example.pr_idi.mydatabaseexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AddNewBookFragment extends Fragment {

    private static final String TAG = "AddNewBookFragment";
    private FragmentManager myFragmentManager;
    private FragmentTransaction myFragmentTransaction;
    private FragmentWithInterface currentFragment;
    private ArrayList<Book> myBooks = new ArrayList<>();
    private BookData myBookData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.add_new_element,container,false);
        FloatingActionButton floatingActionButton = (FloatingActionButton) v.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                myBookData = new BookData(getActivity().getApplicationContext());
                myBookData.open();
                myBookData.insertBook(new Book("titol","author",1231,"publisher","AAAAA","dolent"));
                myBooks.clear();
                myBooks.addAll(myBookData.getAllBooks());
                myBookData.close();

                currentFragment.updateList(myBooks);

                Snackbar snackbar = Snackbar
                        .make(view, "New book added!", Snackbar.LENGTH_LONG);

                snackbar.show();
            }
        });

        myFragmentManager = getActivity().getSupportFragmentManager();
        myFragmentTransaction = myFragmentManager.beginTransaction();
        currentFragment = new BooksSortedByCategoryFragment();
        myFragmentTransaction.replace(R.id.content_frame_2, currentFragment);
        myFragmentTransaction.commit();


        return v;
    }
}
