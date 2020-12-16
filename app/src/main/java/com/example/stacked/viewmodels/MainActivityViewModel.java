package com.example.stacked.viewmodels;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.stacked.models.Todo;
import com.example.stacked.repositories.TodoRepository;

import java.util.ArrayList;
import java.util.List;

public class MainActivityViewModel extends ViewModel {
    private MutableLiveData<List<Todo>> mTodo;
    private TodoRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if(mTodo!=null){
            return;
        }
        mRepo = TodoRepository.getInstance();
        mTodo = mRepo.getTodo();
    }
    public void addNewValue(final Todo todo){
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>(){

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<Todo> currentTodos = mTodo.getValue();
                currentTodos.add(todo);
                mTodo.postValue(currentTodos);
                mIsUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }
    public LiveData<List<Todo>> getTodo(){
        return  mTodo;
    }
    public void markImportant(final int pos){
        List<Todo> list = mTodo.getValue();
        list.get(pos).setPriority(1);
        mTodo.postValue(list);
    }

    public LiveData<Boolean> getIsUpdating(){
    return mIsUpdating;
    }
}
