package msk.android.academy.javatemplate;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import msk.android.academy.javatemplate.adapters.ListEventAdapter;

public class ListEventFragment extends Fragment {
    private Context context;
    private RecyclerView recyclerView;
    private ListEventAdapter listEventAdapter;
    private ProgressBar eventFSpb;
    private TextView eventError;
    private TextView serverError;
    private MyAsyncTask myAsyncTask;
    private ImageView starAdd;

    public static ListEventFragment newInstance() {
        return new ListEventFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_events_fragment, container, false);
        recyclerView = view.findViewById(R.id.list_event_recycler);
        eventFSpb = view.findViewById(R.id.list_event_progressbar);
        eventError = view.findViewById(R.id.error_not_found);
        starAdd = view.findViewById(R.id.added_btn);


       return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    private class MyAsyncTask {
    }
}
