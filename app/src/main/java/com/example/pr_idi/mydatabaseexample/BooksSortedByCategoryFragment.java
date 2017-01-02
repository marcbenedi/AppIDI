package com.example.pr_idi.mydatabaseexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class BooksSortedByCategoryFragment extends FragmentWithInterface{

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private BookData myBookData;
    private ArrayList<Book> myBooks = new ArrayList<>();
    private static final String TAG = "BooksSortedByCategory..";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.books_sorted_by_category_layout,container,false);

        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE){
            myLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
        }
        else{
            myLayoutManager = new LinearLayoutManager(inflater.getContext());
        }

        myRecyclerView = (RecyclerView) v.findViewById(R.id.book_recycler_view);
        myRecyclerView.setLayoutManager(myLayoutManager);

        myAdapter = new BookRecyclerViewAdapter(myBooks);
        myRecyclerView.setAdapter(myAdapter);
        myBookData = new BookData(getActivity().getApplicationContext());
        myBookData.open();
        myBooks.clear();
        myBooks.addAll(myBookData.getAllBooks());
        myBookData.close();
        sortByCategory(myBooks);
        myAdapter.notifyDataSetChanged();

        return v;
    }

    private void sortByCategory(ArrayList<Book> books){

        Collections.sort(books, new Comparator<Book>() {
            @Override
            public int compare(Book b1, Book b2)
            {
                return  b1.getCategory().compareTo(b2.getCategory());
            }
        });
    }

    @Override
    public void updateList(ArrayList<Book> b) {
        myBooks.clear();
        myBooks.addAll(b);
        sortByCategory(myBooks);
        myAdapter.notifyDataSetChanged();
    }
}
