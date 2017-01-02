package com.example.pr_idi.mydatabaseexample;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;


public class ChangeValorationDialogFragment extends DialogFragment {


    private TextView autor;
    private TextView titol;
    private TextView editorial;
    private TextView any;
    private TextView categoria;
    private RatingBar valoracio;
    private Book b;

    private RatingBar ratingBar;
    private TextView ratingBarTextView;
    private String t = "molt dolent";
    private int r = 1;

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(Book b, String newVal);
    }
    // Use this instance of the interface to deliver action events
    private ChangeValorationDialogFragment.NoticeDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_change_valoration_dialog,null);

        if(savedInstanceState != null) {
            b = (Book) savedInstanceState.getSerializable("llibre_guardat");
        }

        autor = (TextView) view.findViewById(R.id.textView6);
        titol = (TextView) view.findViewById(R.id.textView2);
        editorial = (TextView) view.findViewById(R.id.textView4);
        any = (TextView) view.findViewById(R.id.textView3);
        valoracio = (RatingBar) view.findViewById(R.id.ratingBar);
        categoria = (TextView) view.findViewById(R.id.textView5);


        autor.setText(b.getAuthor());
        titol.setText(b.getTitle());
        editorial.setText(b.getPublisher());
        any.setText(String.valueOf(b.getYear()));
        categoria.setText(b.getCategory());

        switch (b.getPersonal_evaluation()){
            case "molt dolent":
                valoracio.setRating(1);
                break;
            case "dolent":
                valoracio.setRating(2);
                break;
            case "regular":
                valoracio.setRating(3);
                break;
            case "bo":
                valoracio.setRating(4);
                break;
            case "molt bo":
                valoracio.setRating(5);
                break;
        }
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBarDialog);
        ratingBarTextView = (TextView) view.findViewById(R.id.stateRatingBar);

        if (savedInstanceState == null) {
            ratingBarTextView.setText("molt dolent");
            ratingBar.setRating(1);
        }
        else {
            ratingBarTextView.setText(savedInstanceState.getCharSequence("state_rating"));
        }


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

        alertDialogBuilder.setTitle("Quina valoració vols posar?");
        alertDialogBuilder.setPositiveButton("Actualitzar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
                mListener.onDialogPositiveClick(b,tt);
            }
        });

        alertDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        Dialog d = alertDialogBuilder.create();

        return d;
    }

    public void setParams(NoticeDialogListener mListener, Book b) {
        this.mListener = mListener;
        this.b = b;
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("llibre_guardat",b);
        outState.putCharSequence("state_rating",ratingBarTextView.getText().toString());
    }

    public void setListener (NoticeDialogListener mListener) {
        this.mListener = mListener;
    }

}
