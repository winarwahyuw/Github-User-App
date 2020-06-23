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

public class FollowingAdapter extends RecyclerView.Adapter<FollowingAdapter.CustomViewHolder> {
    private List<User> itemList;
    private Context context;

    public FollowingAdapter(List<User> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    public void setFollowingData(List<User> items) {
        itemList.clear();
        itemList.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new FollowingAdapter.CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        User item = itemList.get(position);
        String avatar_url = "https://avatars1.githubusercontent.com/u/" + item.getItem_id() + "?v=4";

        if (item.getUsername() != null) {
            holder.tv_username.setText(item.getUsername());
        }
        if (item.getAvatar_url() != null) {
            Glide.with(context)
                    .load(avatar_url)
                    .into(holder.iv_avatar);
        }
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tv_username;
        CircleImageView iv_avatar;

        CustomViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_username = itemView.findViewById(R.id.tv_username);
            iv_avatar = itemView.findViewById(R.id.iv_avatar);
        }
    }
}
