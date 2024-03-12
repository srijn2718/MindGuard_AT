package com.example.mindguard2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;

public class dass21adapter extends RecyclerView.Adapter<dass21adapter.viewHolder>{
    Context context;
    ArrayList<dass21model> arrayList;
    private static final int RADIO_BUTTON_2 = R.id.radioButton2;
    private static final int RADIO_BUTTON_3 = R.id.radioButton3;
    private static final int RADIO_BUTTON_4 = R.id.radioButton4;



    public dass21adapter(Context context, ArrayList<dass21model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override

    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.dass21_layout,parent,false);
        viewHolder viewHolder;
        viewHolder = new viewHolder(v);
        return viewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        dass21model model = arrayList.get(position);

        holder.question.setText(model.getQuestion());

        // Set the selected state of radio buttons based on the stored state in the model
        switch (model.getSelectedOption()) {
            case 0:
                holder.option1.setChecked(true);
                break;
            case 1:
                holder.option2.setChecked(true);
                break;
            case 2:
                holder.option3.setChecked(true);
                break;
            case 3:
                holder.option4.setChecked(true);
                break;
            default:
                holder.options.clearCheck();
                break;
        }

        holder.options.setOnCheckedChangeListener((group, checkedId) -> {
            int score = -1;
            if (checkedId == R.id.radioButton) {
                score = 0;
            } else if (checkedId == R.id.radioButton2) {
                score = 1;
            } else if (checkedId == R.id.radioButton3) {
                score = 2;
            } else if (checkedId == R.id.radioButton4) {
                score = 3;
            }

            // Update the selected state in the model
            model.setSelectedOption(score);

            // Calculate scores based on the position
            if (position == 2 || position == 4 || position == 9 || position == 12 || position == 15 || position == 16 || position == 20) {
                Dass21.depression += score;
            } else if (position == 2 || position == 4 || position == 7 || position == 9 || position == 15 || position == 19 || position == 20) {
                Dass21.anxiety += score;
            } else {
                Dass21.stress += score;
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends ViewHolder{
        TextView question;
        RadioGroup options;
        RadioButton option1,option2,option3,option4;
        int f=0;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            question=itemView.findViewById(R.id.textView4);
            options=itemView.findViewById(R.id.options);
            option1=itemView.findViewById(R.id.radioButton);
            option2=itemView.findViewById(R.id.radioButton2);
            option3=itemView.findViewById(R.id.radioButton3);
            option4=itemView.findViewById(R.id.radioButton4);
        }

    }

    @Override
    public void onViewDetachedFromWindow(@NonNull viewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.options.setOnCheckedChangeListener(null);

    }
}
