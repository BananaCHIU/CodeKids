package com.edu.codekids;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.edu.codekids.JavaForumFragment.OnJavaFragmentInteractionListener;

import org.ocpsoft.prettytime.PrettyTime;

import java.io.Serializable;
import java.security.CryptoPrimitive;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MyJavaForumRecyclerViewAdapter extends RecyclerView.Adapter<MyJavaForumRecyclerViewAdapter.PostViewHolder>
{

    static List<Post> posts;


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
    public void onBindViewHolder(final PostViewHolder holder, int i)
    {
        final Post post = posts.get(i);
        PrettyTime prettyTime = new PrettyTime(Locale.getDefault());

        holder.postUser.setText(posts.get(i).getUser().getuName());
        String tempType = posts.get(i).getUser().getuType();
        String output = tempType.substring(0, 1).toUpperCase() + tempType.substring(1);
        holder.postuType.setText(output);
        holder.postDate.setText(prettyTime.format(posts.get(i).getpTime()));
        holder.postTitle.setText(posts.get(i).getpTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(v.getContext(), ForumPostActivity.class);
                intent.putExtra("post", post);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder
    {
        public final CardView cardView;
        public final TextView postUser;
        public final TextView postuType;
        public final TextView postDate;
        public final TextView postTitle;

        public PostViewHolder(View view)
        {
            super(view);
            cardView = (CardView)itemView.findViewById(R.id.forum_CardView);
            postUser = (TextView)itemView.findViewById(R.id.forum_PostUser);
            postuType = (TextView)itemView.findViewById(R.id.forum_PostType);
            postDate = (TextView)itemView.findViewById(R.id.forum_PostDate);
            postTitle = (TextView)itemView.findViewById(R.id.forum_PostTitle);
        }
    }
}
