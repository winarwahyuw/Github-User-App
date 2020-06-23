package winarwahyuw.winw.githubuser.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import winarwahyuw.winw.githubuser.R;
import winarwahyuw.winw.githubuser.model.User;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.CustomViewHolder> {
    private List<User> userList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(User user);
    }

    public FavoriteAdapter(Context context, List<User> list) {
        this.context = context;
        this.userList = list;
    }

    public void OnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setUserData(List<User> userData) {
        userList.clear();
        userList.addAll(userData);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        User user = userList.get(position);
        holder.bind(user, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_username;
        CircleImageView iv_avatar;

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_username = itemView.findViewById(R.id.tv_username);
            iv_avatar = itemView.findViewById(R.id.iv_avatar);
        }

        void bind(final User user, final OnItemClickListener listener) {
            String avatar_url = "https://avatars1.githubusercontent.com/u/" + user.getItem_id() + "?v=4";

            if (user.getUsername() != null) {
                tv_username.setText(user.getUsername());
            }
            if (user.getAvatar_url() != null) {
                Glide.with(context)
                        .load(avatar_url)
                        .into(iv_avatar);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(user);
                }
            });

        }
    }
}
