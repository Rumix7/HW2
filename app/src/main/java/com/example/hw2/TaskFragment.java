package com.example.hw2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.hw2.tasks.TaskListContent;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TaskFragment extends Fragment {
    private MyTaskRecyclerViewAdapter myTaskRecyclerViewAdapter;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TaskFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static TaskFragment newInstance(int columnCount) {
        TaskFragment fragment = new TaskFragment ();
        Bundle args = new Bundle ();
        args.putInt ( ARG_COLUMN_COUNT, columnCount );
        fragment.setArguments ( args );
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );

        if (getArguments () != null) {
            mColumnCount = getArguments ().getInt ( ARG_COLUMN_COUNT );
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate ( R.layout.fragment_task_list, container, false );

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext ();
            RecyclerView recyclerView = ( RecyclerView ) view;
            recyclerView.setLayoutManager ( new LinearLayoutManager ( context ) );
            myTaskRecyclerViewAdapter = new MyTaskRecyclerViewAdapter ( TaskListContent.ITEMS, mListener );
            recyclerView.setAdapter ( myTaskRecyclerViewAdapter );
        }
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach ( context );

        if (context instanceof OnListFragmentInteractionListener) {
            mListener = ( OnListFragmentInteractionListener ) context;
        } else {
            throw new RuntimeException ( context.toString ()
                    + " must implement OnListFragmentInteractionListener" );
        }
    }

    @Override
    public void onDetach() {
        super.onDetach ();
        mListener = null;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == Activity.RESULT_OK)
        {
            if(data != null)
            {
                boolean changeDataSet = data.getBooleanExtra ( TaskInfoActivity.DATA_CHANGED_KEY, false );
                if(changeDataSet)
                    notifyDataChange ();
            }
        }
    }
    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name

        void OnDeleteClick(int position);
        void onListFragmentClickInteraction(TaskListContent.Task task, int position);

    }
    public void notifyDataChange()
    {
        myTaskRecyclerViewAdapter.notifyDataSetChanged ();

    }
}
