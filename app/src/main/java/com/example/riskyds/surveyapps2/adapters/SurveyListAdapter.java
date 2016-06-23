package com.example.riskyds.surveyapps2.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.riskyds.surveyapps2.R;
import com.example.riskyds.surveyapps2.Url;
import com.example.riskyds.surveyapps2.activities.SurveyActivity;
import com.example.riskyds.surveyapps2.activities.UpdateSurveyActivity;
import com.example.riskyds.surveyapps2.fragments.SurveyListFragment;
import com.example.riskyds.surveyapps2.helpers.RequestAsyncTask;
import com.example.riskyds.surveyapps2.helpers.ResponseManager;
import com.example.riskyds.surveyapps2.helpers.SessionManager;
import com.example.riskyds.surveyapps2.models.SurveyList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final SurveyList item = items.get(position);

        SurveyHolder h = ((SurveyHolder) holder);
        h.nama.setText(item.getNama());
        h.tglsurvey.setText(item.getTglsurvey());
        h.alamat.setText(item.getNamadesa() + " | " + item.getAlamat());
        if (item.getIsvalid().equals("1")) {
            h.status.setImageResource(R.drawable.ic_done_36dp);
            h.status.setColorFilter(ContextCompat.getColor(context, R.color.green_500));
        } else if (item.getIsvalid().equals("0")) {
            h.status.setImageResource(R.drawable.ic_close_36dp);
            h.status.setColorFilter(ContextCompat.getColor(context, R.color.red_500));
        }  else {
            h.status.setImageResource(R.drawable.ic_sync_36dp);
            h.status.setColorFilter(ContextCompat.getColor(context, R.color.blue_500));
        }

        h.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // todo goto update activity
                Intent intent = new Intent(context, UpdateSurveyActivity.class);
                SurveyList surveyList = new SurveyList();
                surveyList.setIdsurvey(item.getIdsurvey());
                v.getContext().startActivity(intent);
                Toast.makeText(context, "D Survey " + surveyList.getIdsurvey(), Toast.LENGTH_SHORT).show();

            }
        });
        if (!item.getIsvalid().equals("1")) {
            h.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
                    alertDialog.setTitle("Peringatan!");
                    alertDialog.setMessage("Apakah anda ingin menghapus data ini?");
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Ya",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Map<String, String> data = new HashMap<>();
                                    data.put("idsurvey", item.getIdsurvey());

                                    // request
                                    RequestAsyncTask login = new RequestAsyncTask(data, null, null) {
                                        @Override
                                        protected void setAfterThread(ResponseManager responseManager) {
                                            items.remove(position);
                                            notifyItemRemoved(position);
                                            Toast.makeText(context, responseManager.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    };
                                    login.execute(Url.DeleteSurvey);
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Tidak",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                    alertDialog.show();
                    return false;
                }
            });
        }
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
            status = ((ImageView) itemView.findViewById(R.id.imgStatus));
        }
    }
}
