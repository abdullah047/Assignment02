package com.example.abdullah.assignment02;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    Context context;
    private LayoutInflater inflater;
    String [] ids;
    String [] name;
    String [] email;
    String [] phone;

    private List<ContactList> contact1 ;

    public RecyclerAdapter(Activity context, List<ContactList> contact) {


        this.contact1 = contact;
        this.context = context;

        inflater = LayoutInflater.from(context);


    }

    public RecyclerAdapter(Activity activity, List<ContactList> contactListList) {

    }

    public RecyclerAdapter(Activity activity, List<ContactList> contactListList) {
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.custom_view,parent,false);
        // View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_view,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {


        final ContactList contact = contact1.get(position);
        holder.image.setImageResource(R.drawable.original);
        holder.id.setText(contact.getId());
        holder.name.setText(contact.getName());
        holder.phone.setText(contact.getPhone());
        holder.email.setText(contact.getEmail());
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("Click", "onClick: Hi "+ holder.id.getText().toString());

                String pervious =holder.name.getText().toString();
                contact1.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position,contact1.size());

                Toast.makeText(context, "Removed: "+pervious, Toast.LENGTH_SHORT).show();

            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TextView id = view.findViewById(R.id.id);
                TextView name = view.findViewById(R.id.name);
                TextView phone = view.findViewById(R.id.phone);
                TextView email = view.findViewById(R.id.email);

                String idS,nameS,phoneS,emailS;

                idS = id.getText().toString();
                nameS = name.getText().toString();
                phoneS = phone.getText().toString();
                emailS = email.getText().toString();
                //Toast.makeText(getActivity(), "Hi: " +idS+nameS+phoneS+emailS, Toast.LENGTH_SHORT).show();
                ContactList contact = new ContactList(idS,nameS,emailS,phoneS);
                EventBus.getDefault().postSticky(contact);

                MainActivity mainActivity = (MainActivity) context;
                DetailsFragment fragment = new DetailsFragment();
                FragmentManager manager = mainActivity.getFragmentManager();
                FragmentTransaction trans = manager.beginTransaction();
                trans.replace(R.id.main_layout,fragment,"");
                trans.commit();
            }
        });


    }


    @Override
    public int getItemCount() {
        return contact1.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView id,name,email,phone;
        Button button;


        public MyViewHolder(View itemView) {
            super(itemView);

            this.id = (TextView) itemView.findViewById(R.id.id);
            this.name = (TextView)itemView.findViewById(R.id.name);
            this.email = (TextView)itemView.findViewById(R.id.phone);
            this.phone = (TextView)itemView.findViewById(R.id.email);
            this.image = (ImageView) itemView.findViewById(R.id.imageView);
            this.button = (Button) itemView.findViewById(R.id.button);

        }
    }


}


