package com.example.abdullah.assignment02;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class DetailsFragment extends Fragment {


    private android.widget.ImageView image;
    private android.widget.TextView id;
    private android.widget.TextView name;
    private android.widget.TextView phone;
    private android.widget.TextView email;
    private android.widget.Button button3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_details, container, false);
        this.button3 = (Button) view.findViewById(R.id.button3);
        this.image = (ImageView) view.findViewById(R.id.image);
        this.email = (TextView) view.findViewById(R.id.email);
        this.phone = (TextView) view.findViewById(R.id.phone);
        this.name = (TextView) view.findViewById(R.id.name);
        this.id = (TextView) view.findViewById(R.id.id);

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BlankFragment fragment = new BlankFragment();
                FragmentManager manager = getFragmentManager();
                FragmentTransaction trans = manager.beginTransaction();
                trans.replace(R.id.main_layout,fragment,"");
                trans.commit();
            }
        });


        return view;
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEvent(ContactList event){
        id.setText(event.getId().toString());
        name.setText(event.getName().toString());
        phone.setText(event.getPhone().toString());
        email.setText(event.getEmail().toString());
        image.setImageResource(R.drawable.original);

        EventBus.getDefault().removeStickyEvent(event);

    }
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }
    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


}
