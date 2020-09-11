package com.techiv.leaderboard.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.techiv.leaderboard.R;
import com.techiv.leaderboard.dto.StudentIQ;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentIQRecyclerViewAdapter extends RecyclerView.Adapter<StudentIQRecyclerViewAdapter.ViewHolder> {

    private List<StudentIQ> skillIQList;

    public StudentIQRecyclerViewAdapter(List<StudentIQ> skillIQList) {
        this.skillIQList = skillIQList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.skill_iq_list_item, parent, false);
        return new StudentIQRecyclerViewAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentIQ skillIQ = skillIQList.get(position);
        holder.studentName.setText(skillIQ.getName());
        holder.studentDescription.setText(String.format("%s skill IQ Score, %s", skillIQ.getScore(), skillIQ.getCountry()));
        holder.imageView.setImageBitmap(skillIQ.getBadge());
    }

    @Override
    public int getItemCount() {
        return skillIQList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView studentName;
        private final TextView studentDescription;
        private final ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.skill_iq_logo);
            studentName = itemView.findViewById(R.id.skill_iq_student_name);
            studentDescription = itemView.findViewById(R.id.skill_iq_description);

        }
    }
}
