package com.ricardoorellana.welcometovoxfeed.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ricardoorellana.welcometovoxfeed.R;
import com.ricardoorellana.welcometovoxfeed.activities.PublicationDetail;
import com.ricardoorellana.welcometovoxfeed.adapters.PublicationsListAdapter;
import com.ricardoorellana.welcometovoxfeed.beans.Publication;
import com.ricardoorellana.welcometovoxfeed.loaders.PublicationLoader;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * create an instance of this fragment.
 */
public class Publications extends Fragment implements LoaderManager.LoaderCallbacks<List<Publication>> {

    private static final String LOG_TAG = Publications.class.getName();
    private TextView textView;

    private LoaderManager loaderManager;
    private PublicationsListAdapter mAdapter;
    private ListView mPublicationListView;
    private ProgressBar mProgressbar;

    /**
     * Constant value for the movie loader ID. We can choose any integer.
     * This really only comes into play if you're using multiple loaders.
     */
    private static final int PUBLICATION_LOADER_ID = 1;


    public Publications() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_publications, container, false);

        // Get a reference to the LoaderManager, in order to interact with loaders.
        loaderManager = getLoaderManager();
        loaderManager.initLoader(PUBLICATION_LOADER_ID, null, this);

        mProgressbar = (ProgressBar) rootView.findViewById(R.id.loading_spinner);
        mProgressbar.setVisibility(View.VISIBLE);

        mPublicationListView = (ListView) rootView.findViewById(R.id.publication_listview);
        mAdapter = new PublicationsListAdapter(this.getActivity(), new ArrayList<Publication>());
        mPublicationListView.setAdapter(mAdapter);

        mPublicationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Publication publication = mAdapter.getItem(i);

                Intent intent = new Intent(getActivity(), PublicationDetail.class);
                intent.putExtra(intent.EXTRA_TEXT, publication);
                startActivity(intent);
            }
        });

        return rootView;
    }

    @Override
    public Loader<List<Publication>> onCreateLoader(int id, Bundle args) {
        Log.i(LOG_TAG, "onCreateLoader");
        final String BASE_URL = "https://api.voxfeed.com/public/promoted_messages";
        // Create a new loader for the given URL
        return new PublicationLoader(getContext(), BASE_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Publication>> loader, List<Publication> publications) {
        Log.i(LOG_TAG, "onLoadFinished");

        mAdapter.clear();

        mProgressbar.setVisibility(View.GONE);

        if(publications != null && !publications.isEmpty()) {
            mAdapter.addAll(publications);
        } else {
            textView = (TextView) getActivity().findViewById(R.id.problems_textview);
            textView.setText(R.string.publications_not_found);
            textView.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<Publication>> loader) {
    }


}
