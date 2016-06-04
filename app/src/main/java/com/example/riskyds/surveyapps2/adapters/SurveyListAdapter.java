package com.example.riskyds.surveyapps2.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riskyds.surveyapps2.R;
import com.example.riskyds.surveyapps2.models.SurveyList;

import java.util.List;

/**
 * Created by sevima on 6/4/2016.
 */
public class SurveyListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<SurveyList> items;
    Context context;

    public SurveyListAdapter(List<SurveyList> items) {
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_survey, parent, false);
        context = parent.getContext();

        return new SurveyHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final SurveyList item = items.get(position);

        SurveyHolder h = ((SurveyHolder) holder);
        h.nama.setText(item.getNama());
        h.tglsurvey.setText(item.getTglsurvey());
        h.alamat.setText(item.getNamadesa() + " | " + item.getAlamat());

        h.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, item.getIdsurvey(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class SurveyHolder extends RecyclerView.ViewHolder {

        TextView nama;
        TextView tglsurvey;
        TextView alamat;
        ImageView status;

        public SurveyHolder(View itemView) {
            super(itemView);
            nama = ((TextView) itemView.findViewById(R.id.nama));
            tglsurvey = ((TextView) itemView.findViewById(R.id.tglsurvey));
            alamat = ((TextView) itemView.findViewById(R.id.alamat));
        }
    }
}
