package com.example.pr_idi.mydatabaseexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChangeValorationFragment extends Fragment implements BookRecyclerViewAdapterWithOnClickListener.Interaction,
                                                                    ChangeValorationDialogFragment.NoticeDialogListener {


    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private BookData myBookData;
    private ChangeValorationFragment me;
    private ArrayList<Book> myBooks = new ArrayList<>();
    private ChangeValorationDialogFragment changeValorationDialogFragment;
    private FragmentManager myFragmentManager;
    private Book llibreAModificar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        me = this;
        View v = inflater.inflate(R.layout.fragment_change_valoration, container, false);

        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE){
            myLayoutManager = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        }
        else{
            myLayoutManager = new LinearLayoutManager(inflater.getContext());
        }
        myRecyclerView = (RecyclerView) v.findViewById(R.id.book_recycler_view_valoration);
        myRecyclerView.setLayoutManager(myLayoutManager);
        myAdapter = new BookRecyclerViewAdapterWithOnClickListener(myBooks, me);
        myRecyclerView.setAdapter(myAdapter);
        myBookData = new BookData(getActivity().getApplicationContext());
        myBookData.open();
        myBooks.clear();
        myBooks.addAll(myBookData.getAllBooks());
        myBookData.close();
        myAdapter.notifyDataSetChanged();

        myFragmentManager = getActivity().getSupportFragmentManager();


        if (savedInstanceState != null) {
            changeValorationDialogFragment = (ChangeValorationDialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag("fragment_change_valoration_dialog");
            if (changeValorationDialogFragment != null){
                changeValorationDialogFragment.setListener(me);
            }
        }

        return v;
    }

    @Override
    public void onItemClick(Book b) {
        if (changeValorationDialogFragment == null) changeValorationDialogFragment = new ChangeValorationDialogFragment();
        changeValorationDialogFragment.setParams(me,b);
        changeValorationDialogFragment.show(myFragmentManager, "fragment_change_valoration_dialog");
    }

    private void updateData() {
        myBooks.clear();
        myBookData.open();
        myBooks.addAll(myBookData.getAllBooks());
        myBookData.close();
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDialogPositiveClick(Book b, String newVal) {
        myBookData.open();
        myBookData.updateValoracion(b.getId(),newVal);
        myBookData.close();
        updateData();
    }
}
