package com.edu.codekids;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.codekids.JavaForumFragment.OnJavaFragmentInteractionListener;
import com.edu.codekids.dummy.DummyContent.DummyItem;

import org.ocpsoft.prettytime.PrettyTime;

import java.security.CryptoPrimitive;
import java.util.List;
import java.util.Locale;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnJavaFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyJavaForumRecyclerViewAdapter extends RecyclerView.Adapter<MyJavaForumRecyclerViewAdapter.PostViewHolder>
{

    List<Post> posts;


    public MyJavaForumRecyclerViewAdapter(List<Post> items)
    {
        posts = items;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_javaforum, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, final int i)
    {
        PrettyTime prettyTime = new PrettyTime(Locale.getDefault());

        holder.postUser.setText(posts.get(i).getUser().getuName());
        holder.postDate.setText(prettyTime.format(posts.get(i).getpTime()));
        holder.postTitle.setText(posts.get(i).getpTitle());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder
    {
        public final CardView cardView;
        public final TextView postUser;
        public final TextView postDate;
        public final TextView postTitle;

        public PostViewHolder(View view)
        {
            super(view);
            cardView = (CardView)itemView.findViewById(R.id.forum_CardView);
            postUser = (TextView)itemView.findViewById(R.id.forum_PostUser);
            postDate = (TextView)itemView.findViewById(R.id.forum_PostDate);
            postTitle = (TextView)itemView.findViewById(R.id.forum_PostTitle);
            view.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent(v.getContext(), ForumPostActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
