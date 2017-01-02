package com.example.pr_idi.mydatabaseexample;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FindBooksByAuthorFragment extends Fragment {

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
        View v = inflater.inflate(R.layout.fragment_find_books_by_author, container, false);

        myBookData = new BookData(getActivity().getApplicationContext());
        myBookData.open();

        lv = (ListView) v.findViewById(R.id.list_view_titles_by_author);

        ArrayList<String> titles = new ArrayList<>();

        myBooks = myBookData.getAllBooks();

        for (Book b : myBooks) {
            titles.add(b.getTitle());
        }

//        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
//                R.layout.item_title_list, titles);
//
//        lv.setAdapter(adapter);

        SearchView searchView = (SearchView) v.findViewById(R.id.search_bar_title_by_author);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                filtrarPerAutor(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
//                filtrarPerAutor(newText);
                return false;
            }
        });

        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                filtrarPerAutor("");
                return false;
            }
        });
        return v;
    }

    public void filtrarPerAutor(String autor) {
        ArrayList<String> filtered = new ArrayList<>();
        if (autor.equals("")) {
            for (Book b : myBooks) {
                filtered.add(b.getTitle());
            }
        }
        else {
            for (Book b : myBooks) {
                if (b.getAuthor().startsWith(autor)) filtered.add(b.getTitle());
            }
        }
        Collections.sort(filtered, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                R.layout.item_title_list, filtered);
        lv.setAdapter(adapter);
    }

}
