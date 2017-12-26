package com.example.abdullah.assignment02;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

    private ClickListener mListener;

    private GestureDetector gestureDetector;

    public RecyclerTouchListener(Context context, RecyclerView recyclerView, ClickListener mListener) {
        this.mListener = mListener;
        gestureDetector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {


        View view = rv.findChildViewUnder(e.getX(),e.getY());
        if(view!=null && mListener!=null && gestureDetector.onTouchEvent(e)){

            mListener.onClick(view,rv.getChildAdapterPosition(view));
            return true;

        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }


    public interface ClickListener{

        void onClick(View view ,int position);

    }
}


