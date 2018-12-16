package msk.android.academy.javatemplate;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import msk.android.academy.javatemplate.adapters.ListEventAdapter;
import msk.android.academy.javatemplate.data.model.MarkersDTO;
import msk.android.academy.javatemplate.data.network.RestApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEventFragment extends Fragment {
    private static final String TAG = ListEventFragment.class.getSimpleName();
    private Context context;
    private RecyclerView recyclerView;
    private ListEventAdapter listEventAdapter;
    private ProgressBar eventFSpb;
    private TextView eventError;
    private TextView serverError;
    private MyAsyncTask myAsyncTask;
    private ImageView starAdd;
    private Call<MarkersDTO> searchRequest;
    private Disposable dispose;

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
        loadEvents();
        return view;
    }

    private void loadEvents() {
        showState(State.Loading);
        dispose = RestApi.getInstance()
                .getEndPoint()
                .search_all()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::showData, e->Log.e(TAG,Log.getStackTraceString(e)));
    }

    private void showData(List<MarkersDTO> markersDTOS) {
        listEventAdapter = new ListEventAdapter(markersDTOS, getActivity().getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(listEventAdapter);
        showState(State.HasData);
        listEventAdapter.notifyDataSetChanged();
    }

    private class MyAsyncTask extends AsyncTask<List<MarkersDTO>, Void, List<MarkersDTO>[]> {

        @Override
        protected List<MarkersDTO>[] doInBackground(List<MarkersDTO>... lists) {//
            loadEvents();
            return lists;
        }
    }

        private void showState(State state) {
            switch (state) {
                case HasData:
                    eventFSpb.setVisibility(ProgressBar.GONE);
                    recyclerView.setVisibility(RecyclerView.VISIBLE);
                    eventError.setVisibility(TextView.GONE);
                    break;
                case HasNoData:
                    eventFSpb.setVisibility(ProgressBar.GONE);
                    eventError.setVisibility(TextView.VISIBLE);
                    break;
                case ServerError:
                    recyclerView.setVisibility(RecyclerView.GONE);
                    eventError.setVisibility(TextView.GONE);
                    break;
                case Loading:
                    eventFSpb.setVisibility(ProgressBar.VISIBLE);
                    recyclerView.setVisibility(RecyclerView.GONE);
                    eventError.setVisibility(TextView.GONE);
                    break;
            }
        }
}