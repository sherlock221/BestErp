package besterp.sherlock221b.com.besterp.task;

import android.content.Context;

import besterp.sherlock221b.com.besterp.view.LoadingDialog;

/**
 * Created by sherlock on 15/12/20.
 */
public abstract class LoadingTask<T> extends  BaseAsyncTask<T> {

    protected LoadingDialog dialog;

    public LoadingTask(Context context) {
        dialog = new LoadingDialog(context);
    }

    @Override
    protected abstract T doInBackground(Object... params);


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog.show();
    }

    @Override
    protected void onPostExecute(T t) {
        super.onPostExecute(t);
        dialog.dismiss();
    }
}