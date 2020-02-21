package com.icon.moksh.sqliteexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends BaseAdapter {

    Context context;
    ArrayList<Integer> sid;
    ArrayList<String> sname;
    StudentAdapter(Context context, ArrayList<Integer> sid, ArrayList<String> sname)
    {
        this.context = context;
        this.sid = sid;
        this.sname = sname;
    }
    @Override
    public int getCount() {
        return sid.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Handler handler;

        if(convertView == null)
        {
            convertView = inflater.inflate(R.layout.listcell,null);
            handler = new Handler();
            handler.tvid = convertView.findViewById(R.id.textView_id);
            handler.tvname = convertView.findViewById(R.id.textView_name);
            convertView.setTag(handler);
        }
        else
        {
            handler = (Handler) convertView.getTag();
        }

        handler.tvid.setText(String.valueOf(sid.get(position)));
        handler.tvname.setText(sname.get(position));

        return convertView;
    }

    public class Handler
    {
        TextView tvid,tvname;
    }
}
