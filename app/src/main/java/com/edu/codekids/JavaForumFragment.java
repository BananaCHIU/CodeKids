package com.edu.codekids;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnJavaFragmentInteractionListener}
 * interface.
 */
public class JavaForumFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private static final String TAG = "Message: ";
    private int mColumnCount = 1;
    private SwipeRefreshLayout mySwipeRefreshLayout;
    private OnJavaFragmentInteractionListener mListener;
    private List<Post> posts = new ArrayList<Post>();
    private View view;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public JavaForumFragment() {
    }

    private void refresh(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference colRef = db.collection("javaPost");
        colRef.orderBy("pTime", Query.Direction.DESCENDING).get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            posts.clear();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                HashMap map  = (HashMap) document.get("user");
                                User temp = new User (map.get("uId").toString(), map.get("uName").toString(), map.get("uType").toString());
                                Post post = document.toObject(Post.class);
                                post.setpUser(temp);
                                posts.add(post);

                            }
                            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.forum_RecyclerView);
                            recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
                            recyclerView.setAdapter(new MyJavaForumRecyclerViewAdapter(posts));
                            if (posts.size() == 0){
                                Toast.makeText(getActivity(), "No post in the Java forum",
                                        Toast.LENGTH_LONG).show();
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    @SuppressWarnings("unused")
    public static JavaForumFragment newInstance(int columnCount) {
        JavaForumFragment fragment = new JavaForumFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_javaforum_list, container, false);

        refresh();
        mySwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_container);
        mySwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);
        mySwipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        Log.i(TAG, "onRefresh called from SwipeRefreshLayout");

                        // This method performs the actual data-refresh operation.
                        // The method calls setRefreshing(false) when it's finished.
                        refresh();
                        mySwipeRefreshLayout.setRefreshing(false);
                    }
                }
        );

        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        refresh();
        if (context instanceof OnJavaFragmentInteractionListener) {
            mListener = (OnJavaFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnJavaFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnJavaFragmentInteractionListener {
        // TODO: Update argument type and name

    }
}
