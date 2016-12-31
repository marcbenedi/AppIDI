package com.example.pr_idi.mydatabaseexample;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class AddNewBookDialogFragment extends DialogFragment {

    private EditText authorEditText;
    private EditText titleEditText;
    private EditText yearEditText;
    private EditText publisherEditText;
    private EditText categoryEditText;
    private RatingBar ratingBar;
    private TextView ratingBarTextView;
    private String t = "molt dolent";
    private int r = 1;

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(Book b);
        //public void onDialogNegativeClick(Book b);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.v("dialog","onCreateView del dialog");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    // Use this instance of the interface to deliver action events
    private NoticeDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.new_book_dialog_layout,null);

        authorEditText = (EditText) view.findViewById(R.id.editTextAuthor);
        titleEditText = (EditText) view.findViewById(R.id.editTextTitle);
        yearEditText = (EditText) view.findViewById(R.id.editTextYear);
        publisherEditText = (EditText) view.findViewById(R.id.editTextPublisher);
        categoryEditText = (EditText) view.findViewById(R.id.editTextCategory);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        ratingBarTextView = (TextView) view.findViewById(R.id.textViewRating);

        ratingBar.setRating(1);
        ratingBarTextView.setText(t);


        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                if(fromUser){
                    if (rating < 1) rating = 1;
                    switch ((int) rating){
                        case 1:
                            t = "molt dolent";
                            break;
                        case 2:
                            t = "dolent";
                            break;
                        case 3:
                            t = "regular";
                            break;
                        case 4:
                            t = "bo";
                            break;
                        case 5:
                            t = "molt bo";
                            break;
                    }
                    r = (int) rating;
                    ratingBarTextView.setText(t);
                    ratingBar.setRating(rating);
                }
            }
        });

        alertDialogBuilder.setView(view);

        alertDialogBuilder.setTitle("Add new book");
        alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                String author = String.valueOf(authorEditText.getText()) ;
                String title = String.valueOf(titleEditText.getText()) ;
                int year = Integer.valueOf(String.valueOf(yearEditText.getText()));
                String publisher = String.valueOf(publisherEditText.getText()) ;
                String category = String.valueOf(categoryEditText.getText()) ;

                int ratingNum = (int) ratingBar.getRating();
                String tt = "molt dolent";

                switch (ratingNum){
                    case 1:
                        tt = "molt dolent";
                        break;
                    case 2:
                        tt = "dolent";
                        break;
                    case 3:
                        tt = "regular";
                        break;
                    case 4:
                        tt = "bo";
                        break;
                    case 5:
                        tt = "molt bo";
                        break;
                }

                mListener.onDialogPositiveClick(new Book(author,
                        title,
                        year,
                        publisher,
                        category,
                        tt));
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Dialog d = alertDialogBuilder.create();

        return d;
    }

    public void setNoticeDialogListener (NoticeDialogListener mListener) {
        this.mListener = mListener;
    }
}
