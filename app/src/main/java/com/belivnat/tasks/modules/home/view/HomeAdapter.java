package com.belivnat.tasks.modules.home.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.belivnat.tasks.R;
import com.belivnat.tasks.model.HomeMenuOptions;
import com.bumptech.glide.Glide;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    Context context;
    List<HomeMenuOptions> menuList;
    HomeMenuOnClickListener homeMenuOnClickListener;
    private long mLastClickTime = System.currentTimeMillis();
    private static final long CLICK_TIME_INTERVAL = 1000;

    public HomeAdapter(Context context, List<HomeMenuOptions> menuList, HomeMenuOnClickListener homeMenuOnClickListener) {
        this.context = context;
        this.menuList = menuList;
        this.homeMenuOnClickListener = homeMenuOnClickListener;
    }

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_menu, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, final int position) {
        ViewGroup.LayoutParams layoutParams = holder.homeRootLayout.getLayoutParams();
        holder.homeRootLayout.setLayoutParams(layoutParams);
        holder.homeMenuTextView.setText(menuList.get(position).getName());
        Glide.with(context)
                .load(menuList.get(position).getImgUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.homeMenuImageView);
        holder.homeRootLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long now = System.currentTimeMillis();
                if (now - mLastClickTime < CLICK_TIME_INTERVAL) {
                    return;
                }
                homeMenuOnClickListener.onMenuClicked(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return menuList.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout homeRootLayout;
        ImageView homeMenuImageView;
        TextView homeMenuTextView;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            homeRootLayout = itemView.findViewById(R.id.rl_home_menu_root_layout);
            homeMenuImageView = itemView.findViewById(R.id.img_home_menu);
            homeMenuTextView = itemView.findViewById(R.id.txt_menu_item_name);
        }
    }

    interface HomeMenuOnClickListener {
        void onMenuClicked(int item);
    }
}
