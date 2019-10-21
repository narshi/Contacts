package com.reciproci.contacts.fragments;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reciproci.contacts.R;
import com.reciproci.contacts.adapter.CallsViewAdapter;
import com.reciproci.contacts.models.ModelCalls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class FragmentCalls extends Fragment {

    private RecyclerView recyclerView;
    private View v;


    public FragmentCalls() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.frag_calls,container,false);
        recyclerView = v.findViewById(R.id.rv_calls);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        RecyclerView.LayoutManager layoutManager = linearLayoutManager;
        recyclerView.setLayoutManager(layoutManager);

        CallsViewAdapter adapter = new CallsViewAdapter(getContext(),getCallLogs());
        recyclerView.setAdapter(adapter);
        return v;
    }
    private List<ModelCalls> getCallLogs(){
        List<ModelCalls> list = new ArrayList<>();

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_CALL_LOG},1);
        }

        Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI,null
        ,null,null,CallLog.Calls.DATE +"");


        int number = 0;
        int duration = 0;
        int date = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            number = Objects.requireNonNull(cursor).getColumnIndex(CallLog.Calls.NUMBER);
            duration = Objects.requireNonNull(cursor).getColumnIndex(CallLog.Calls.DURATION);
            date = Objects.requireNonNull(cursor).getColumnIndex(CallLog.Calls.DATE);
        }

        if (cursor != null) {
            cursor.moveToFirst();
        }
        while (cursor.moveToNext()){
            Date date1 = new Date(Long.valueOf(cursor.getString(date)));
        list.add(new ModelCalls(cursor.getString(number),cursor.getString(duration),date1.toString()));
      }
      return list;
    }

}
