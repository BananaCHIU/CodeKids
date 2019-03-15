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
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_list, viewGroup, false);
        CommentViewHolder cvh = new CommentViewHolder(v);
        return cvh;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder VH, final int i)
    {
        PrettyTime prettyTime = new PrettyTime(Locale.getDefault());

        final CommentViewHolder commentViewHolder = (CommentViewHolder) VH;
        commentViewHolder.userName.setText(comments.get(i).getcUser().getuName());
        commentViewHolder.date.setText(prettyTime.format(comments.get(i).getcTime()));
        commentViewHolder.c_content.setText(comments.get(i).getcContent());
        commentViewHolder.numlike.setText(Integer.toString(comments.get(i).getcVote()));
        commentViewHolder.likeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                likeBtnPressed(commentViewHolder.likeButton, commentViewHolder.dislikeButton, i);
                commentViewHolder.numlike.setText(Integer.toString(comments.get(i).getcVote()));
            }
        });
        commentViewHolder.dislikeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dislikeBtnPressed(commentViewHolder.likeButton, commentViewHolder.dislikeButton, i);
                commentViewHolder.numlike.setText(Integer.toString(comments.get(i).getcVote()));
            }
        });
    }

    private void likeBtnPressed(ImageButton likebtn, ImageButton dislikebtn, int i) {
        float likealpha, dislikealpha;
        likealpha = likebtn.getAlpha(); dislikealpha = dislikebtn.getAlpha();
        if((likealpha == (float) 0.4) && (dislikealpha == (float) 1)){                //Disliked
            likebtn.setAlpha((float) 1); dislikebtn.setAlpha((float) 0.4);
            comments.get(i).setcVote(comments.get(i).getcVote() + 2);                 //double add
        } else if ((dislikealpha == (float) 0.4) && (likealpha == (float) 1)){        //Liked
            likebtn.setAlpha((float) 0.4);
            comments.get(i).setcVote(comments.get(i).getcVote() - 1);                 //reset
        } else if ((dislikealpha == (float) 0.4) && (likealpha == (float) 0.4)){      //No Select
            likebtn.setAlpha((float) 1);
            comments.get(i).setcVote(comments.get(i).getcVote() + 1);                 //add
        }
    }

    private void dislikeBtnPressed(ImageButton likebtn, ImageButton dislikebtn, int i) {
        float likealpha, dislikealpha;
        likealpha = likebtn.getAlpha(); dislikealpha = dislikebtn.getAlpha();
        if((likealpha == (float) 0.4) && (dislikealpha == (float) 1)){                //Disliked
            dislikebtn.setAlpha((float) 0.4);
            comments.get(i).setcVote(comments.get(i).getcVote() + 1);                 //reset
        } else if ((dislikealpha == (float) 0.4) && (likealpha == (float) 1)){        //Liked
            likebtn.setAlpha((float) 0.4); dislikebtn.setAlpha((float) 1);
            comments.get(i).setcVote(comments.get(i).getcVote() - 2);                 //double minus
        } else if ((dislikealpha == (float) 0.4) && (likealpha == (float) 0.4)){      //No Select
            dislikebtn.setAlpha((float) 1);
            comments.get(i).setcVote(comments.get(i).getcVote() - 1);                 //minus
        }
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
