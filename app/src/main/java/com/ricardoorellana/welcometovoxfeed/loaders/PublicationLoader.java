package com.ricardoorellana.welcometovoxfeed.loaders;



import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.ricardoorellana.welcometovoxfeed.beans.Publication;
import com.ricardoorellana.welcometovoxfeed.http.HttpConnectionUtil;

import java.util.List;

/**
 * Created by Rorellanam on 11/2/16.
 */

public class PublicationLoader extends AsyncTaskLoader<List<Publication>> {

    /** Tag for log messages */
    private static final String LOG_TAG = PublicationLoader.class.getName();

    /** Query URL */
    private String mUrl;
    private List<Publication> mPublications;


    public PublicationLoader(Context context, String url) {
        super(context);

        Log.i(LOG_TAG, "MovieLoader()");
        mUrl = url;
    }


    @Override
    public List<Publication> loadInBackground() {
        Log.i(LOG_TAG, "loadInBackground");

        if (mUrl == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        mPublications = HttpConnectionUtil.fetchPublicationData(mUrl);
        return mPublications;
    }

    /**
     * To call forceLoad() which is a required step to actually trigger the loadInBackground() method to execute.
     */
    @Override
    protected void onStartLoading() {
        Log.i(LOG_TAG, "onStartLoading");
        if (mPublications !=null) {
            deliverResult(mPublications);
        } else {
            forceLoad();
        }

    }

    @Override
    public void deliverResult(List<Publication> publications) {
        mPublications = publications;
        super.deliverResult(publications);
    }

}
