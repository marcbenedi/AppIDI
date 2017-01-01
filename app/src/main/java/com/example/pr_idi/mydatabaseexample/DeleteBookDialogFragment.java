package com.example.pr_idi.mydatabaseexample;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class DeleteBookDialogFragment extends DialogFragment {


    private TextView autor;
    private TextView titol;
    private TextView editorial;
    private TextView any;
    private RatingBar valoracio;
    private Book b;
    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface NoticeDialogListener {
        public void onDialogPositiveClick(Book b);
    }

    // Use this instance of the interface to deliver action events
    private NoticeDialogListener mListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.delete_book_dialog_layout,null);

        if(savedInstanceState != null){
            b = (Book) savedInstanceState.getSerializable("llibre_guardat");
        }

        autor = (TextView) view.findViewById(R.id.textView6);
        titol = (TextView) view.findViewById(R.id.textView2);
        editorial = (TextView) view.findViewById(R.id.textView4);
        any = (TextView) view.findViewById(R.id.textView3);
        valoracio = (RatingBar) view.findViewById(R.id.ratingBar);

        autor.setText(b.getAuthor());
        titol.setText(b.getTitle());
        editorial.setText(b.getPublisher());
        any.setText(String.valueOf(b.getYear()));

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

        alertDialogBuilder.setView(view);

        alertDialogBuilder.setTitle("Segur que vol eliminar el llibre?");
        alertDialogBuilder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mListener.onDialogPositiveClick(b);
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
    }
}
