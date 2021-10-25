package com.example.notassqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.notassqlite.db.Datos;

import java.util.ArrayList;


public class Adapter extends BaseAdapter {

    private Context context;
    private ArrayList<Datos> datos;


    public Adapter(Context context, ArrayList<Datos> datos) {
        this.context = context;
        this.datos = datos;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int i) {
        return datos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Datos data = (Datos) getItem(i);
       view = LayoutInflater.from(context).inflate(R.layout.notas, null);
        TextView txtTitle = (TextView) view.findViewById(R.id.txtTitle);
        TextView txtDes = (TextView) view.findViewById(R.id.txtDes);

        txtTitle.setText(data.getTitle());
        txtDes.setText(data.getDescription());

        return view;
    }
}
