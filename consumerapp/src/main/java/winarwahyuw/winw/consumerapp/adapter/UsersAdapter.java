package winarwahyuw.winw.consumerapp.adapter;

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
import winarwahyuw.winw.consumerapp.R;
import winarwahyuw.winw.consumerapp.model.Item;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CustomViewHolder> {
    private List<Item> itemList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }

    public UsersAdapter(Context context, List<Item> list) {
        this.context = context;
        this.itemList = list;
    }

    public void OnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setUserData(List<Item> userData) {
        itemList.clear();
        itemList.addAll(userData);
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
        Item item = itemList.get(position);
        holder.bind(item, onItemClickListener);

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

        void bind(final Item item, final OnItemClickListener listener) {
            String avatar_url = "https://avatars1.githubusercontent.com/u/" + item.getItem_id() + "?v=4";

            if (item.getUsername() != null) {
                tv_username.setText(item.getUsername());
            }
            if (item.getAvatar_url() != null) {
                Glide.with(context)
                        .load(avatar_url)
                        .into(iv_avatar);
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });

        }
    }
}
