package com.edu.codekids;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import org.ocpsoft.prettytime.PrettyTime;

import java.util.List;
import java.util.Locale;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CommentRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    public static class CommentViewHolder extends RecyclerView.ViewHolder
    {
        public final CardView cv;
        public final TextView userName;
        public final TextView date;
        public final TextView c_content;
        public final TextView numlike;
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
            likeButton = (ImageButton)itemView.findViewById(R.id.comment_LikeButton);
            dislikeButton = (ImageButton)itemView.findViewById(R.id.comment_DislikeButton);
        }
    }

    public static class PostViewHolder extends RecyclerView.ViewHolder
    {
        public final CardView pCV;
        public final TextView pUserName;
        public final TextView pDate;
        public final TextView pContent;

        public final CardView fcCV;
        public final TextView fcUserName;
        public final TextView fcDate;
        public final TextView fcContent;
        public final TextView fcNumLike;
        public final ImageButton fcLikeButton;
        public final ImageButton fcDislikeButton;

        public PostViewHolder(View view)
        {
            super(view);
            pCV = (CardView)itemView.findViewById(R.id.post_CardView);
            pUserName = (TextView)itemView.findViewById(R.id.post_UserName);
            pDate = (TextView)itemView.findViewById(R.id.post_Date);
            pContent = (TextView)itemView.findViewById(R.id.post_Content);

            fcCV = (CardView)itemView.findViewById(R.id.firstComment_CardView);
            fcUserName = (TextView)itemView.findViewById(R.id.first_comment_UserName);
            fcDate = (TextView)itemView.findViewById(R.id.first_comment_Date);
            fcContent = (TextView)itemView.findViewById(R.id.first_comment_Content);
            fcNumLike = (TextView)itemView.findViewById(R.id.first_comment_LikeNum);
            fcLikeButton = (ImageButton)itemView.findViewById(R.id.first_comment_LikeButton);
            fcDislikeButton = (ImageButton)itemView.findViewById(R.id.first_comment_DislikeButton);
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        if(viewType == 1)
        {
            View pv = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_comment, viewGroup, false);
            PostViewHolder postViewHolder = new PostViewHolder(pv);
            return postViewHolder;
        }
        else
        {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_list, viewGroup, false);
            CommentViewHolder cvh = new CommentViewHolder(v);
            return cvh;
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder VH, final int i)
    {
        comments = Comment.sampledata();
        PrettyTime prettyTime = new PrettyTime(Locale.getDefault());

        if(VH.getItemViewType() == 1)
        {
            PostViewHolder postViewHolder = (PostViewHolder) VH;
            postViewHolder.pUserName.setText(comments.get(i).getParentPost().getUserName());
            postViewHolder.pDate.setText(prettyTime.format(comments.get(i).getParentPost().getpTime()));
            postViewHolder.pContent.setText(comments.get(i).getParentPost().getpContent());

            postViewHolder.fcUserName.setText(comments.get(i).getCuName());
            postViewHolder.fcDate.setText(prettyTime.format(comments.get(i).getcTime()));
            postViewHolder.fcContent.setText(comments.get(i).getcContent());
            postViewHolder.fcNumLike.setText(Integer.toString(comments.get(i).getcVote() - comments.get(i).getcDislike()));
        }
        else
        {
            CommentViewHolder commentViewHolder = (CommentViewHolder) VH;
            commentViewHolder.userName.setText(comments.get(i).getCuName());
            commentViewHolder.date.setText(prettyTime.format(comments.get(i).getcTime()));
            commentViewHolder.c_content.setText(comments.get(i).getcContent());
            commentViewHolder.numlike.setText(Integer.toString(comments.get(i).getcVote() - comments.get(i).getcDislike()));
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public int getItemViewType(int position)
    {
        if(position == 0) return 1;
        else return 2;
    }
}
