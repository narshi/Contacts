package com.reciproci.contacts.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.reciproci.contacts.R;
import com.reciproci.contacts.models.ModelCalls;

import java.util.List;

public class CallsViewAdapter extends RecyclerView.Adapter<CallsViewAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context mContext;

    private List<ModelCalls> mlistCalls;

    public CallsViewAdapter(Context context, List<ModelCalls> listCalls) {
        mContext = context;
        mlistCalls = listCalls;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_calls,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView name,duration,date;

        name = holder.name;
        duration = holder.duration;
        date = holder.date;

        name.setText(mlistCalls.get(position).getName());
        duration.setText(mlistCalls.get(position).getDuration());
        date.setText(mlistCalls.get(position).getDate());





    }

    @Override
    public int getItemCount() {
        return mlistCalls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name,duration,date;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.contact_name);
        duration = itemView.findViewById(R.id.call_duration);
        date = itemView.findViewById(R.id.call_date);
    }
}

}
