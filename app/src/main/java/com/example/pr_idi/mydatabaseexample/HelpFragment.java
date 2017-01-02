package com.example.pr_idi.mydatabaseexample;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HelpFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.help_fragment_layout, container, false);

        CardView cv = (CardView) v.findViewById(R.id.card1);
        TextView tv = (TextView) cv.findViewById(R.id.title_card_help);
        tv.setText(R.string.llibres_per_titol_help_title);
        tv = (TextView) cv.findViewById(R.id.content_card_help);
        tv.setText(R.string.llibres_per_titol_help_body);

        cv = (CardView) v.findViewById(R.id.card2);
        tv = (TextView) cv.findViewById(R.id.title_card_help);
        tv.setText(R.string.llibres_per_categoria_help_title);
        tv = (TextView) cv.findViewById(R.id.content_card_help);
        tv.setText(R.string.llibres_per_categoria_help_body);

        cv = (CardView) v.findViewById(R.id.card3);
        tv = (TextView) cv.findViewById(R.id.title_card_help);
        tv.setText(R.string.llibres_per_autor_help_title);
        tv = (TextView) cv.findViewById(R.id.content_card_help);
        tv.setText(R.string.llibres_per_autor_help_body);

        cv = (CardView) v.findViewById(R.id.card4);
        tv = (TextView) cv.findViewById(R.id.title_card_help);
        tv.setText(R.string.afegir_llibre_help_title);
        tv = (TextView) cv.findViewById(R.id.content_card_help);
        tv.setText(R.string.afegir_llibre_help_body);

        cv = (CardView) v.findViewById(R.id.card5);
        tv = (TextView) cv.findViewById(R.id.title_card_help);
        tv.setText(R.string.eliminar_llibre_help_title);
        tv = (TextView) cv.findViewById(R.id.content_card_help);
        tv.setText(R.string.eliminar_llibre_help_body);

        cv = (CardView) v.findViewById(R.id.card6);
        tv = (TextView) cv.findViewById(R.id.title_card_help);
        tv.setText(R.string.canviar_valoracio_help_title);
        tv = (TextView) cv.findViewById(R.id.content_card_help);
        tv.setText(R.string.canviar_valoracio_help_body);

        return v;
        }

}
