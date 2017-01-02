package com.example.pr_idi.mydatabaseexample;


import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class DeleteBookFragment extends Fragment implements DeleteBookDialogFragment.NoticeDialogListener, BookRecyclerViewAdapterWithOnClickListener.Interaction {

    private EditText myEditText;
    private Button delButton;
    private BookData myBookData;
    private DeleteBookFragment me;
    private FragmentManager myFragmentManager;
    private TextView error_message;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;
    private ArrayList<Book> books = new ArrayList<>();
    private Book llibreAEliminar;
    private int pos_previa;
    private DeleteBookDialogFragment deleteBookDialogFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        me = this;
        View v = inflater.inflate(R.layout.delete_book_layout, container, false);
        myEditText = (EditText) v.findViewById(R.id.editTextTitle);
        delButton = (Button) v.findViewById(R.id.button);
        error_message = (TextView) v.findViewById(R.id.textView16);
        myBookData = new BookData(getActivity().getApplicationContext());

        if(getResources().getConfiguration().orientation == getResources().getConfiguration().ORIENTATION_LANDSCAPE){
            myLayoutManager = new GridLayoutManager(getActivity().getApplicationContext(),2);
        }
        else{
            myLayoutManager = new LinearLayoutManager(inflater.getContext());
        }

        recyclerView = (RecyclerView) v.findViewById(R.id.recview);
        recyclerView.setLayoutManager(myLayoutManager);
        myAdapter = new BookRecyclerViewAdapterWithOnClickListener(books,me);
        recyclerView.setAdapter(myAdapter);

        if(savedInstanceState != null){
            System.out.println("oncreateview DIFERENT DE NULL");
            myEditText.setText(savedInstanceState.getString("titol_buscat"));
            myBookData.open();
            books.clear();
            books.addAll(myBookData.findBookByTitle(String.valueOf(myEditText.getText())));
            myBookData.close();
            myAdapter.notifyDataSetChanged();

            if (books.isEmpty()){
                error_message.setVisibility(View.VISIBLE);
            }
            deleteBookDialogFragment = (DeleteBookDialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag("fragment_delete_book_dialog");
            if (deleteBookDialogFragment != null){
                deleteBookDialogFragment.setParams(me, llibreAEliminar);
            }


        }

        myFragmentManager = getActivity().getSupportFragmentManager();

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Per amagar el teclat
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);

                boolean trobat = false;
                myBookData.open();
                books.clear();
                books.addAll(myBookData.findBookByTitle(String.valueOf(myEditText.getText())));
                myBookData.close();
                myAdapter.notifyDataSetChanged();

                if(books.size() == 0) error_message.setVisibility(View.VISIBLE);
                else error_message.setVisibility(View.INVISIBLE);
            }
        });

        return v;
    }

    @Override
    public void onDialogPositiveClick(Book b) {
        myBookData.open();
        myBookData.deleteBook(b);
        myBookData.close();

        int temp = 0;
        boolean trobat = false;
        while (!trobat){
            Book bb = books.get(temp);
            //System.out.println("Bucle: "+bb.getTitle() + " Id: " + bb.getId());
            //System.out.println("Lid de l'original es "+ b.getId());
            if (bb.getId() == b.getId())trobat = true;
            else ++temp;
        }
        //System.out.println("Posicio: " + temp + " Longitud array: "+books.size());
        pos_previa = temp;
        books.remove(pos_previa);
        myAdapter.notifyItemRemoved(pos_previa);

        llibreAEliminar = b;

        Snackbar snackbar = Snackbar
                .make(getView(), "Llibre eliminat", Snackbar.LENGTH_LONG);
        snackbar.setAction("Desfés",new MyUndoListener());
        snackbar.show();
    }

    public class MyUndoListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            myBookData.open();
            myBookData.insertBook(llibreAEliminar);
            myBookData.close();

            books.add(pos_previa,llibreAEliminar);
            myAdapter.notifyItemInserted(pos_previa);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("titol_buscat", String.valueOf(myEditText.getText()));
    }

    @Override
    public void onItemClick(Book b) {
        if (deleteBookDialogFragment == null){
            deleteBookDialogFragment = new DeleteBookDialogFragment();
        }
        deleteBookDialogFragment.setParams(me, b);
        deleteBookDialogFragment.show(myFragmentManager, "fragment_delete_book_dialog");
        error_message.setVisibility(View.INVISIBLE);
        llibreAEliminar = b;
    }
}
