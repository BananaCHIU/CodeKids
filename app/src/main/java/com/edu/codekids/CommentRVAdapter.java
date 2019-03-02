package com.edu.codekids;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CommentRVAdapter extends RecyclerView.Adapter<CommentRVAdapter.CommentViewHolder>
{
    public static class CommentViewHolder extends RecyclerView.ViewHolder
    {
        public final CardView cv;
        public final TextView userName;
        public final TextView date;
        public final TextView c_content;
        public final TextView numlike;
        public final TextView numdislike;
        public final ImageButton likeButton;
        public final ImageButton dislikeButton;


        public CommentViewHolder(View view)
        {
            super(view);
            cv = (CardView)itemView.findViewById(R.id.comment_CardView);
            userName = (TextView)itemView.findViewById(R.id.comment_UserName);
            date = (TextView)itemView.findViewById(R.id.comment_Date);
            c_content = (TextView)itemView.findViewById(R.id.comment_Content);
            numlike = (TextView)itemView.findViewById(R.id.comment_LikeNum);
            numdislike = (TextView)itemView.findViewById(R.id.comment_DislikeNum);
            likeButton = (ImageButton)itemView.findViewById(R.id.comment_LikeButton);
            dislikeButton = (ImageButton)itemView.findViewById(R.id.comment_DislikeButton);
        }
    }

    List<Comment> comments;

    public CommentRVAdapter(List<Comment> comments)
    {
        this.comments = comments;
    }

    @Override
    public int getItemCount() {return comments.size();}

    @Override
    public CommentViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_list, viewGroup, false);
        CommentViewHolder cvh = new CommentViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(final CommentViewHolder commentViewHolder, final int i)
    {
        comments = Comment.sampledata();

        if (i == 0)
        {
            commentViewHolder.userName.setText(comments.get(i).getParentPost().getUserName());
            commentViewHolder.date.setText(comments.get(i).getParentPost().getpTime().toString());
            commentViewHolder.c_content.setText(comments.get(i).getParentPost().getpContent());
        }
        else
        {
            commentViewHolder.userName.setText(comments.get(i).getCuName());
            commentViewHolder.date.setText(comments.get(i).getcTime().toString());
            commentViewHolder.c_content.setText(comments.get(i).getcContent());
            commentViewHolder.numlike.setText(comments.get(i).getcVote());
            commentViewHolder.numdislike.setText(comments.get(i).getcDislike());
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
