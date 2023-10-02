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

public class ProgramsVerticalAdapter extends RecyclerView.Adapter<ProgramsVerticalAdapter.ViewVerticalHolderPrograms> {

    Context mContext;
    ArrayList<StudyProgram> programsList;

    public ProgramsVerticalAdapter(Context mContext, ArrayList<StudyProgram> programsList) {
        this.mContext = mContext;
        this.programsList = programsList;
    }

    @NonNull
    @Override
    public ViewVerticalHolderPrograms onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_list_vertical_programs, null, false);
        return new ViewVerticalHolderPrograms(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewVerticalHolderPrograms holder, int position) {
        holder.programName.setText(programsList.get(position).getName());
        holder.programModality.setText(programsList.get(position).getProgramType());
        holder.programCode.setText(programsList.get(position).getId());
        holder.image.setImageResource(programsList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return programsList.size();
    }

    public class ViewVerticalHolderPrograms extends RecyclerView.ViewHolder {
        TextView programName, programModality, programCode;
        ImageView image;

        public ViewVerticalHolderPrograms(@NonNull View itemView) {
            super(itemView);
            programName = (TextView) itemView.findViewById(R.id.nameIdVerticalProgram);
            programModality = (TextView) itemView.findViewById(R.id.modalityIdVerticalProgram);
            programCode = (TextView) itemView.findViewById(R.id.codeIdVerticalProgram);
            image = (ImageView) itemView.findViewById(R.id.imageIdVerticalProgram);
        }
    }
}
