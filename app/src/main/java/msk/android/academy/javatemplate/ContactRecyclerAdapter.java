package msk.android.academy.javatemplate;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import msk.android.academy.javatemplate.DTO.Contact;

public class ContactRecyclerAdapter extends RecyclerView.Adapter<ContactRecyclerAdapter.ViewHolder> {

   public static List<Contact> checkedList = new ArrayList<>();
    private List<Contact> contactList;
   private LayoutInflater inflater;


    public ContactRecyclerAdapter(Context context, List<Contact> contactList) {
        this.contactList = contactList;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ContactRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new  ViewHolder(inflater.inflate(R.layout.lv_item,viewGroup ,false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ContactRecyclerAdapter.ViewHolder viewHolder, int i)  {
        final Contact contact = contactList.get(i);
        viewHolder.tvPhone.setText(contact.getName());
        viewHolder.tvName.setText(contact.getNumber());
        viewHolder.checkBox.setChecked(contact.isChecked());

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    contact.setChecked(b);
                    checkedList.add(contact);
                } else {
                    checkedList.remove(contact);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkBox;
        TextView tvName, tvPhone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.name);
            tvPhone = itemView.findViewById(R.id.number);
            checkBox = itemView.findViewById(R.id.check_add_contact);
        }
    }
}
