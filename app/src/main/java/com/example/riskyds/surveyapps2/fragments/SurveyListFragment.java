package com.example.riskyds.surveyapps2.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.riskyds.surveyapps2.R;
import com.example.riskyds.surveyapps2.Url;
import com.example.riskyds.surveyapps2.activities.SurveyActivity;
import com.example.riskyds.surveyapps2.adapters.SurveyListAdapter;
import com.example.riskyds.surveyapps2.helpers.RequestAsyncTask;
import com.example.riskyds.surveyapps2.helpers.ResponseManager;
import com.example.riskyds.surveyapps2.helpers.SessionManager;
import com.example.riskyds.surveyapps2.models.SurveyList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sevima on 6/4/2016.
 */
public class SurveyListFragment extends Fragment {
    protected View v;

    ProgressBar progres_bar;
    RecyclerView rvMain;
    FloatingActionButton addSurvey;

    List<SurveyList> surveyLists = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_survey_list, container, false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        progres_bar = ((ProgressBar) v.findViewById(R.id.progress_bar));
        rvMain = ((RecyclerView) v.findViewById(R.id.rvMain));
        rvMain.setLayoutManager(layoutManager);

        addSurvey = ((FloatingActionButton) v.findViewById(R.id.addSurvey));
        addSurvey.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent(getActivity(), SurveyActivity.class);
                    startActivity(intent);
                }
                return true; // consume the event
            }
        });

        // todo adapter
        final SurveyListAdapter adapter = new SurveyListAdapter(surveyLists);
        rvMain.setAdapter(adapter);

        SessionManager sessionManager = SessionManager.getInstance(getContext());
        Map<String, String> data = new HashMap<>();
        data.put("idakun", sessionManager.getThisUser().getIdakun());

        // request
        RequestAsyncTask request = new RequestAsyncTask(data, null, progres_bar) {
            @Override
            protected void setAfterThread(ResponseManager responseManager) {
                surveyLists.clear();
                surveyLists.addAll((List<SurveyList>) responseManager.getMany(SurveyList.class));
                adapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), responseManager.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        request.execute(Url.SurveyAll);
        return v;
    }
}
