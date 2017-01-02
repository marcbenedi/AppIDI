package com.example.pr_idi.mydatabaseexample;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class JuniorAboutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.profile_about_layout,container,false);
        ImageView img = (ImageView) v.findViewById(R.id.imagePerfil);
        Drawable d = getActivity().getDrawable(R.drawable.junior);
        img.setImageDrawable(d);
        String nom = "Hermes";
        String cognom = "Valenciano Farré";
        String correu = "hermes.valenciano@est.fib.upc.edu";
        TextView nom_ = (TextView) v.findViewById(R.id.nom);
        TextView cognom_ = (TextView) v.findViewById(R.id.cognoms);
        nom_.setText(nom);
        cognom_.setText(cognom);
        TextView correu_ = (TextView) v.findViewById(R.id.correu);
        correu_.setText(correu);
        return v;
    }

}
