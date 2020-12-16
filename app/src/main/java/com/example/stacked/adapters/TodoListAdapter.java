package com.example.stacked.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stacked.R;
import com.example.stacked.models.Todo;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    private List<Todo> mTodoList = new ArrayList<>();
    private Context context;

    public TodoListAdapter(Context context, List<Todo> todoList){
        this.context = context;
        mTodoList = todoList;
    }
    @NonNull
    @Override
    public TodoListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_list_item,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = simpleDateFormat.format(mTodoList.get(position).getTimestamp());
        holder.title.setText(mTodoList.get(position).getName());
        holder.date.setText(date);
        holder.cardView.setBackgroundResource(R.drawable.curved_todo_bg);
        holder.cardViewBottom.setBackgroundResource(R.drawable.curved_todo_bg_bottom);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.cardViewBottom.getVisibility() == View.VISIBLE) holder.cardViewBottom.setVisibility(View.GONE);
                else holder.cardViewBottom.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTodoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, date;
        CardView cardView, cardViewBottom;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            cardView = itemView.findViewById(R.id.cardview);
            cardViewBottom = itemView.findViewById(R.id.cardview_bottom);
        }
    }
}
