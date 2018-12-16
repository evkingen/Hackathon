package msk.android.academy.javatemplate;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.JsonObject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static msk.android.academy.javatemplate.data.network.RestApi.getInstance;

public class CreateEventFragment extends Fragment {
    private static final String TAG = CreateEventFragment.class.getSimpleName();
    public static CreateEventFragment newInstance() {
        return new CreateEventFragment();
    }
    private Disposable disposable;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        final View fragmentView = inflater.inflate(R.layout.create_event_fragment, container, false);
        final Button addFotoButton = (Button) fragmentView.findViewById(R.id.addFotoButton);
        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        addFotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        final Button saveMarkerButton = (Button) fragmentView.findViewById(R.id.saveMarker);

        saveMarkerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText titleEditText = (EditText) fragmentView.findViewById(R.id.editText);
                final EditText fullEditText= (EditText) fragmentView.findViewById(R.id.editText2);
                final EditText imageURLEditText   = (EditText) fragmentView.findViewById(R.id.editText3);
                String title=titleEditText.getText().toString();
                String fullText=fullEditText.getText().toString();
                String imageURL=imageURLEditText.getText().toString();

                disposable = getInstance()
                .getEndPoint()
                .set(1,55.7786102,37.6053241,title,fullText,imageURL)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::saveResult, e->Log.e(TAG,Log.getStackTraceString(e)));

            }

            private void saveResult(JsonObject jsonObject) {
                Log.d(TAG,"ok");
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.frame_layout, MapFragment.newInstance());
                transaction.commit();
            }
        });
        return fragmentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }




}
