package com.edu.codekids;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import org.ocpsoft.prettytime.PrettyTime;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class CommentRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private static final String TAG = "Message: ";

    public static class CommentViewHolder extends RecyclerView.ViewHolder
    {
        public final CardView cv;
        public final TextView userName;
        public final TextView userType;
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
            userType = (TextView)itemView.findViewById(R.id.comment_Type);
            date = (TextView)itemView.findViewById(R.id.comment_Date);
            c_content = (TextView)itemView.findViewById(R.id.comment_Content);
            numlike = (TextView)itemView.findViewById(R.id.comment_LikeNum);
            likeButton = (ImageButton)itemView.findViewById(R.id.comment_LikeButton);
            dislikeButton = (ImageButton)itemView.findViewById(R.id.comment_DislikeButton);

        }
    }

    private List<Comment> comments;
    private String type, id;

    public CommentRVAdapter(List<Comment> comments, String type, String id)
    {
        this.comments = comments;
        this.type = type;
        this.id = id;
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
        String tempType = comments.get(i).getcUser().getuType();
        String output = tempType.substring(0, 1).toUpperCase() + tempType.substring(1);
        commentViewHolder.userType.setText(output);
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
            updateComment(comments);
        } else if ((dislikealpha == (float) 0.4) && (likealpha == (float) 1)){        //Liked
            likebtn.setAlpha((float) 0.4);
            comments.get(i).setcVote(comments.get(i).getcVote() - 1);                 //reset
            updateComment(comments);
        } else if ((dislikealpha == (float) 0.4) && (likealpha == (float) 0.4)){      //No Select
            likebtn.setAlpha((float) 1);
            comments.get(i).setcVote(comments.get(i).getcVote() + 1);                 //add
            updateComment(comments);
        }
    }

    private void dislikeBtnPressed(ImageButton likebtn, ImageButton dislikebtn, int i) {
        float likealpha, dislikealpha;
        likealpha = likebtn.getAlpha(); dislikealpha = dislikebtn.getAlpha();
        if((likealpha == (float) 0.4) && (dislikealpha == (float) 1)){                //Disliked
            dislikebtn.setAlpha((float) 0.4);
            comments.get(i).setcVote(comments.get(i).getcVote() + 1);                 //reset
            updateComment(comments);
        } else if ((dislikealpha == (float) 0.4) && (likealpha == (float) 1)){        //Liked
            likebtn.setAlpha((float) 0.4); dislikebtn.setAlpha((float) 1);
            comments.get(i).setcVote(comments.get(i).getcVote() - 2);                 //double minus
            updateComment(comments);
        } else if ((dislikealpha == (float) 0.4) && (likealpha == (float) 0.4)){      //No Select
            dislikebtn.setAlpha((float) 1);
            comments.get(i).setcVote(comments.get(i).getcVote() - 1);                 //minus
            updateComment(comments);
        }
    }

    public List<Comment> getComments(){
        return comments;
    }

    private void updateComment(List<Comment> comments){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection(type).document(id)
                .update("pComments", comments).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.d(TAG, "DocumentSnapshot successfully updated!");
            }
        })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error updating document", e);
                    }
                });
}

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
