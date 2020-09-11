package com.techiv.leaderboard.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiv.leaderboard.utils.NetworkUtils;
import com.techiv.leaderboard.R;
import com.techiv.leaderboard.adapters.StudentIQRecyclerViewAdapter;
import com.techiv.leaderboard.dto.StudentIQ;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.techiv.leaderboard.utils.Constants.SKILL_IQ_LEADERS;


public class SkillIQFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.skill_iq_fragment, container, false);
        AsyncTask<String, Void, List<StudentIQ>> task = new AsyncTask<String, Void, List<StudentIQ>>() {
            @Override
            protected List<StudentIQ> doInBackground(String... urls) {
                return NetworkUtils.fetchSkillIQData(urls[0]);
            }

            @Override
            protected void onPostExecute(List<StudentIQ> students) {
                StudentIQRecyclerViewAdapter studentIQRecyclerViewAdapter = new StudentIQRecyclerViewAdapter(students);
                RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.skill_iq_recycler_view);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(studentIQRecyclerViewAdapter);
            }
        };

        task.execute(SKILL_IQ_LEADERS);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
