package msk.android.academy.javatemplate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import msk.android.academy.javatemplate.DTO.Contact;

public class ContactsAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Contact> contactModelArrayList;

    private static ArrayList<Contact> addContactList= new ArrayList<>();
    private  List<Integer> checkedItems = new ArrayList<>();

    public ContactsAdapter(Context context, ArrayList<Contact> contactModelArrayList) {

        this.context = context;
        this.contactModelArrayList = contactModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return contactModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View itemView;
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.lv_item, null, true);

            holder.tvname = (TextView) itemView.findViewById(R.id.name);
            holder.tvnumber = (TextView) itemView.findViewById(R.id.number);
            holder.checkBox = itemView.findViewById(R.id.check_add_contact);


        holder.tvname.setText(contactModelArrayList.get(position).getName());
        holder.tvnumber.setText(contactModelArrayList.get(position).getNumber());
        if (holder.checkBox.isChecked()){
            addContactList.add(contactModelArrayList.get(position));
        }
        return itemView;
    }

    private class ViewHolder {
        protected TextView tvname, tvnumber;
        protected CheckBox checkBox;
    }
}


