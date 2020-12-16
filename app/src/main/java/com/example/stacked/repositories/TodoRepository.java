package com.example.stacked.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.stacked.models.Todo;

import java.util.ArrayList;
import java.util.List;

public class TodoRepository {
    private static TodoRepository instance;
    private ArrayList<Todo> datasSet = new ArrayList<>();

    public static TodoRepository getInstance(){
        if(instance == null){
            instance = new TodoRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Todo>> getTodo(){
        setTodo();
        MutableLiveData<List<Todo>> data = new MutableLiveData<>();
        data.setValue(datasSet);
        return data;
    }

    private void setTodo(){
        datasSet.add(new Todo("Complete NodeJs",System.currentTimeMillis()-5000, 0));
        datasSet.add(new Todo("Complete DSA",System.currentTimeMillis()-10000, 0));
        datasSet.add(new Todo("Complete Algo++",System.currentTimeMillis()-15000, 0));
        datasSet.add(new Todo("Complete Placement",System.currentTimeMillis()-25000, 0));
        datasSet.add(new Todo("Complete Android",System.currentTimeMillis()-50000, 0));

    }
}
