package com.example.abdullah.assignment02;

import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdullah on 12/20/2017.
 */

public class BlankFragment extends Fragment {


    RecyclerView recyclerView;
    private List<ContactList> ContactListList = new ArrayList<>();
    RecyclerAdapter recyclerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycle);

        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new RecyclerAdapter(getActivity(), ContactListList);

        recyclerView.setAdapter(recyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        prepareContactListData();

        return view;
    }



    private void prepareContactListData() {


        for(int i = 1;i<=1000;i++){


            String id = String.valueOf(i);
            ContactList contactList = new ContactList(id, "Name","Email", "Phone");
            ContactListList.add(contactList);

        }

        recyclerAdapter.notifyDataSetChanged();
    }


}
