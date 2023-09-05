package com.example.estudia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.estudia.R;
import com.example.estudia.entities.StudyProgram;

import java.util.ArrayList;

public class ProgramsHorizontalAdapter extends RecyclerView.Adapter<ProgramsHorizontalAdapter.ViewHolderPrograms> {

    Context mContext;
    ArrayList<StudyProgram> programsList;

    public ProgramsHorizontalAdapter(ArrayList<StudyProgram> programsList, Context context) {
        this.programsList = programsList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public ViewHolderPrograms onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_list_horizontal_programs, null, false);
        return new ViewHolderPrograms(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPrograms holder, int position) {
        holder.programName.setText(programsList.get(position).getName());
        holder.descriptionProgram.setText(programsList.get(position).getInfo());
        holder.image.setImageResource(programsList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return programsList.size();
    }

    public class ViewHolderPrograms extends RecyclerView.ViewHolder {

        TextView programName, descriptionProgram;
        ImageView image;

        public ViewHolderPrograms(@NonNull View itemView) {
            super(itemView);
            programName = (TextView) itemView.findViewById(R.id.nameIdHorizontalProgram);
            descriptionProgram = (TextView) itemView.findViewById(R.id.infoIdHorizontalProgram);
            image = (ImageView) itemView.findViewById(R.id.imageIdHorizontalProgram);
        }
    }
}
