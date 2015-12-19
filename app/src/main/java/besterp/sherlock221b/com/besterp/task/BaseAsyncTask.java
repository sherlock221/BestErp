package besterp.sherlock221b.com.besterp.task;

import android.os.AsyncTask;

import java.util.List;

/**
 * Created by sherlock on 15/12/19.
 */
public abstract class BaseAsyncTask<T> extends AsyncTask<Object, Integer, T> {

    protected DataFinishListener dataFinishListener;

    @Override
    protected abstract T doInBackground(Object... params);


    @Override
    protected void onProgressUpdate(Integer... values) {
        if(isCancelled()) return;
        super.onProgressUpdate(values);
    }


    @Override
    protected void onPostExecute(T t) {
        if(dataFinishListener != null)
            dataFinishListener.onSuccess(t);
        super.onPostExecute(t);
    }


    public interface DataFinishListener<T> {
        void onSuccess(T t);
        void onError();
    }

    public void setFinishListener(DataFinishListener dataFinishListener) {
        this.dataFinishListener = dataFinishListener;
    }
}
