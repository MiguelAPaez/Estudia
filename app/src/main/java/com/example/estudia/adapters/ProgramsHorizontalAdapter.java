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
import com.example.estudia.interfaces.RecyclerViewEstudiaInterface;

import java.util.ArrayList;

public class ProgramsHorizontalAdapter extends RecyclerView.Adapter<ProgramsHorizontalAdapter.ViewHolderPrograms> {
    private final RecyclerViewEstudiaInterface recyclerViewEstudiaInterface;

    Context mContext;
    ArrayList<StudyProgram> programsList;

    int arrayNumber;

    public ProgramsHorizontalAdapter(ArrayList<StudyProgram> programsList, Context context, RecyclerViewEstudiaInterface recyclerViewEstudiaInterface, int arrayNumber) {
        this.programsList = programsList;
        this.mContext = context;
        this.recyclerViewEstudiaInterface = recyclerViewEstudiaInterface;
        this.arrayNumber = arrayNumber;
    }

    @NonNull
    @Override
    public ViewHolderPrograms onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_list_horizontal_programs, null, false);
        return new ViewHolderPrograms(view, this.recyclerViewEstudiaInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPrograms holder, int position) {
        holder.programName.setText(programsList.get(position).getName());
        holder.descriptionProgram.setText(programsList.get(position).getSchedule());
        holder.image.setImageResource(programsList.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return programsList.size();
    }

    public class ViewHolderPrograms extends RecyclerView.ViewHolder {

        TextView programName, descriptionProgram;
        ImageView image;

        public ViewHolderPrograms(@NonNull View itemView, RecyclerViewEstudiaInterface recyclerViewEstudiaInterface) {
            super(itemView);
            programName = (TextView) itemView.findViewById(R.id.nameIdHorizontalProgram);
            descriptionProgram = (TextView) itemView.findViewById(R.id.infoIdHorizontalProgram);
            image = (ImageView) itemView.findViewById(R.id.imageIdHorizontalProgram);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (recyclerViewEstudiaInterface != null) {
                        int pos = getAbsoluteAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            recyclerViewEstudiaInterface.onItemClick(pos, arrayNumber);
                        }
                    }
                }
            });
        }
    }
}
