package com.techiv.leaderboard.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techiv.leaderboard.utils.NetworkUtils;
import com.techiv.leaderboard.R;
import com.techiv.leaderboard.adapters.StudentRecyclerViewAdapter;
import com.techiv.leaderboard.dto.Student;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.techiv.leaderboard.utils.Constants.LEARNING_LEADERS;


public class LearningFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.learning_fragment, container, false);
        AsyncTask<String, Void, List<Student>> task = new AsyncTask<String, Void, List<Student>>() {
            @Override
            protected List<Student> doInBackground(String... urls) {
                return NetworkUtils.fetchLearningHoursData(urls[0]);
            }

            @Override
            protected void onPostExecute(List<Student> students) {
                StudentRecyclerViewAdapter studentRecyclerViewAdapter = new StudentRecyclerViewAdapter(students);
                RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.learning_recycler_view);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(studentRecyclerViewAdapter);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
        };

        task.execute(LEARNING_LEADERS);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
