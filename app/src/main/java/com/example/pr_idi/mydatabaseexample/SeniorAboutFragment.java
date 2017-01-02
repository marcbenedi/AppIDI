package com.example.pr_idi.mydatabaseexample;


import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SeniorAboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_about_layout,container,false);

        ImageView img = (ImageView) v.findViewById(R.id.imagePerfil);
        Drawable d = getActivity().getDrawable(R.drawable.senior);
        img.setImageDrawable(d);
        String nom = "Marc";
        String cognoms = "Benedí San Millán";
        String correu = "marc.benedi@est.fib.upc.edu";
        TextView nom_ = (TextView) v.findViewById(R.id.nom);
        TextView cognom_ = (TextView) v.findViewById(R.id.cognoms);
        nom_.setText(nom);
        cognom_.setText(cognoms);
        TextView correu_ = (TextView) v.findViewById(R.id.correu);
        correu_.setText(correu);
        TextView part = (TextView) v.findViewById(R.id.part);
        part.setText("A");

        return v;
    }

}
