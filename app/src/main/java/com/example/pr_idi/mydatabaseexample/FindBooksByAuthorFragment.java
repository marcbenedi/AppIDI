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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FindBooksByAuthorFragment extends Fragment {

    private BookData myBookData;
    ArrayAdapter<String> adapter;
    List<Book> myBooks;
    ListView lv;
    Button buttonSearch;
    EditText editText;
    TextView textVie;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_find_books_by_author, container, false);

        buttonSearch = (Button) v.findViewById(R.id.buttonAutor);
        editText = (EditText) v.findViewById(R.id.editTextAutor);
        textVie = (TextView) v.findViewById(R.id.textViewAutorNotFound);

        myBookData = new BookData(getActivity().getApplicationContext());
        myBookData.open();
        lv = (ListView) v.findViewById(R.id.list_view_titles_by_author);
        ArrayList<String> titles = new ArrayList<>();
        myBooks = myBookData.getAllBooks();
        for (Book b : myBooks) {
            titles.add(b.getTitle());
        }

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filtrarPerAutor(editText.getText().toString());
            }
        });

        return v;
    }

    public void filtrarPerAutor(String autor) {
        ArrayList<String> filtered = new ArrayList<>();
        for (Book b : myBooks) {
            if (b.getAuthor().equals(autor)) filtered.add(b.getTitle());
        }

        if (filtered.size() > 0) {
            textVie.setVisibility(View.INVISIBLE);
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
        else {
            if (adapter != null) adapter.clear();
            textVie.setVisibility(View.VISIBLE);
        }
    }

}
