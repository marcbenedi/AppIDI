package com.example.pr_idi.mydatabaseexample;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


import static android.graphics.Color.LTGRAY;
import static android.graphics.Color.WHITE;

public class AboutFragment extends Fragment {

    private AppBarLayout appBarLayout;
    private TabLayout pestanas;
    private ViewPager viewPager;
    private AdaptadorSecciones adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.about_fragment_layout, container, false);
        adapter = new AdaptadorSecciones(getChildFragmentManager());
        viewPager = (ViewPager) v.findViewById(R.id.pager);

        View padre = (View) container.getParent();
        appBarLayout = (AppBarLayout) padre.findViewById(R.id.appbar);
        pestanas = new TabLayout(getActivity());
        pestanas.setTabTextColors(LTGRAY,WHITE);
        adapter.addFragment(new SeniorAboutFragment(), "Marc Benedí");
        adapter.addFragment(new JuniorAboutFragment(), "Hermes Valenciano");
        viewPager.setAdapter(adapter);
        appBarLayout.addView(pestanas);
        pestanas.post(new Runnable() {
            @Override
            public void run() {
                pestanas.setupWithViewPager(viewPager);
            }
        });

        return v;
    }

    public class AdaptadorSecciones extends FragmentStatePagerAdapter {

        private final ArrayList<Fragment> fragmentos = new ArrayList<>();
        private final ArrayList<String> titulos = new ArrayList<>();

        public AdaptadorSecciones(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentos.get(position);
        }

        @Override
        public int getCount() {
            return fragmentos.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentos.add(fragment);
            titulos.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titulos.get(position);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //if(appBar!=null){
        appBarLayout.removeView(pestanas);
        //}
    }

}
