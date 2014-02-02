package edu.illinois.coursera.android.json;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import java.util.List;

import edu.illinois.coursera.android.json.models.Item;

public class ItemListFragment extends ListFragment
        implements LoaderManager.LoaderCallbacks<List<Item>> {
    private ItemListAdapter mListAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mListAdapter = new ItemListAdapter(getActivity());
        setListAdapter(mListAdapter);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setEmptyText("Error");
        setListShown(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mListAdapter.isEmpty()) {
            getLoaderManager().initLoader(0, null, this).forceLoad();
        } else {
            setListShown(true);
        }
    }

    @Override
    public Loader<List<Item>> onCreateLoader(int i, Bundle bundle) {
        // call our loader to load json objects over the internet
        return new ItemListLoader(getActivity());
    }

    @Override
    public void onLoadFinished(Loader<List<Item>> listLoader, List<Item> items) {
        // our loader got results back so lets set the data
        mListAdapter.setData(items);

        if (isResumed()) {
            setListShown(true);
        } else {
            setListShownNoAnimation(true);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Item>> listLoader) {
    }}
