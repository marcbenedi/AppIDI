package com.example.pr_idi.mydatabaseexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class AddNewBookFragment extends Fragment implements AddNewBookDialogFragment.NoticeDialogListener {

    private static final String TAG = "AddNewBookFragment";
    private FragmentManager myFragmentManager;
    private FragmentTransaction myFragmentTransaction;
    private FragmentWithInterface currentFragment;
    private ArrayList<Book> myBooks = new ArrayList<>();
    private BookData myBookData;
    private AddNewBookFragment me;
    private AddNewBookDialogFragment addNewBookDialogFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.v(TAG, "OnCreateView de del Fragment");

        me = this;
        View v = inflater.inflate(R.layout.add_new_book,container,false);
        FloatingActionButton floatingActionButton = (FloatingActionButton) v.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (addNewBookDialogFragment == null){
                    //Encara no l'haviem fet servir mai
                    addNewBookDialogFragment = new AddNewBookDialogFragment();
                }

                addNewBookDialogFragment.setNoticeDialogListener(me);
                addNewBookDialogFragment.show(myFragmentManager, "fragment_tag_add_new_book_dialog");

            }
        });

        if (savedInstanceState != null) {
            addNewBookDialogFragment = (AddNewBookDialogFragment) getActivity().getSupportFragmentManager().findFragmentByTag("fragment_tag_add_new_book_dialog");
            if (addNewBookDialogFragment != null){
                addNewBookDialogFragment.setNoticeDialogListener(me);
            }
        }

        myFragmentManager = getActivity().getSupportFragmentManager();
        myFragmentTransaction = myFragmentManager.beginTransaction();
        currentFragment = new BooksSortedByTitleWithCardsFragment();
        myFragmentTransaction.replace(R.id.content_frame_2, currentFragment);
        myFragmentTransaction.commit();


        return v;
    }

    @Override
    public void onDialogPositiveClick(Book b) {
        myBookData = new BookData(getActivity().getApplicationContext());
        myBookData.open();
        myBookData.insertBook(b);
        myBooks.clear();
        myBooks.addAll(myBookData.getAllBooks());
        myBookData.close();

        currentFragment.updateList(myBooks);

        Snackbar snackbar = Snackbar
                .make(getView(), "El llibre "+ b.getTitle() +" s'ha afegit!", Snackbar.LENGTH_LONG);
        snackbar.show();


    }
}
