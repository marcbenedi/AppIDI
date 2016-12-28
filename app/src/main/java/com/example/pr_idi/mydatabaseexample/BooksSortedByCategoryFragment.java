package com.example.pr_idi.mydatabaseexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BooksSortedByCategoryFragment extends Fragment {

    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private BookData myBookData;
    private List<Book> myBooks;
    private static final String TAG = "BooksSortedByCategory..";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.books_sorted_by_category_layout,container,false);
        myRecyclerView = (RecyclerView) v.findViewById(R.id.book_recycler_view);
        myLayoutManager = new LinearLayoutManager(inflater.getContext());
        myRecyclerView.setLayoutManager(myLayoutManager);

        myBookData = new BookData(getActivity().getApplicationContext());
        myBookData.open();


        String[] newBook = new String[] { "Miguel Strogoff", "Jules Verne", "Ulysses", "James Joyce", "Don Quijote", "Miguel de Cervantes", "Metamorphosis", "Kafka" };
        int nextInt = new Random().nextInt(4);
        // save the new book to the database
        Book book =myBookData.createBook(newBook[nextInt*2], newBook[nextInt*2 + 1]);
        nextInt = new Random().nextInt(4);
        book =myBookData.createBook(newBook[nextInt*2], newBook[nextInt*2 + 1]);
        nextInt = new Random().nextInt(4);
        book =myBookData.createBook(newBook[nextInt*2], newBook[nextInt*2 + 1]);
        nextInt = new Random().nextInt(4);
        book =myBookData.createBook(newBook[nextInt*2], newBook[nextInt*2 + 1]);
        nextInt = new Random().nextInt(4);
        book =myBookData.createBook(newBook[nextInt*2], newBook[nextInt*2 + 1]);
        nextInt = new Random().nextInt(4);
        book =myBookData.createBook(newBook[nextInt*2], newBook[nextInt*2 + 1]);


        myBooks = myBookData.getAllBooks();
        myBookData.close();
        ArrayList<Book> temp = new ArrayList<>(myBooks);
        for (Book b:temp
             ) {
            System.out.println(b.getAuthor());
            System.out.println(b.getPersonal_evaluation());
        }
        myAdapter = new BookRecyclerViewAdapter(temp);
        myRecyclerView.setAdapter(myAdapter);

        return v;
    }
}
