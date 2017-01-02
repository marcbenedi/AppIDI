package com.example.pr_idi.mydatabaseexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BooksSortedByTitleFragment extends Fragment {

    private BookData myBookData;
    ArrayAdapter<String> adapter;
    List<Book> myBooks;
    ListView lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_books_sorted_by_title, container, false);


        myBookData = new BookData(getActivity().getApplicationContext());
        myBookData.open();

        lv = (ListView) v.findViewById(R.id.list_view_titles);

        ArrayList<String> titles = new ArrayList<>();

        myBooks = myBookData.getAllBooks();

        for (Book b : myBooks) {
            titles.add(b.getTitle());
        }

        Collections.sort(titles, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });

        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.item_title_list, titles);

        lv.setAdapter(adapter);

        return v;
    }

}
