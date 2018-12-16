package msk.android.academy.javatemplate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import msk.android.academy.javatemplate.DTO.Contact;

public class ContactsFragment extends Fragment  {

    FragmentListener listener;
    TextView tvNoContacts;
    RecyclerView recyclerView;

    public static ContactsFragment newInstance() {
        ContactsFragment fragment = new ContactsFragment();
        return fragment;
    }

    public ContactsFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof FragmentListener) {
            listener = (FragmentListener) getActivity();
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.contact_list_fragment,
                container, false);
        FloatingActionButton btnAddContacts = view.findViewById(R.id.btn_add_contact);
        tvNoContacts = view.findViewById(R.id.tv_add_contact);
        recyclerView = view.findViewById(R.id.listView);

        btnAddContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onReplace(ChooseContactsFragment.newInstance(),null);
                }
        });
                // Inflate the fragment layout
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if ( !ContactRecyclerAdapter.checkedList.isEmpty()) {
            addContacts(ContactRecyclerAdapter.checkedList);
        }
    }

    public void addContacts(List<Contact> contacts) {
        if (!contacts.isEmpty()) {
            tvNoContacts.setVisibility(View.GONE);
           ContactRecyclerAdapter contactsAdapter = new ContactRecyclerAdapter(getContext(), contacts);
           recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
           recyclerView.setAdapter(contactsAdapter);
        }
    }
}
