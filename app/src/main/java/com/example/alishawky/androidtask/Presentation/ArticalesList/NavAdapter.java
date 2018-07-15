package com.example.alishawky.androidtask.Presentation.ArticalesList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alishawky.androidtask.Entitties.NavItem;
import com.example.alishawky.androidtask.R;

import java.util.ArrayList;


public class NavAdapter extends RecyclerView.Adapter<NavAdapter.ItemViewHolder> {

    private ArrayList<NavItem> navItems;
    private NavItem selectedItem;
    private NavItemSelectedListener navItemSelectedListener;

    public NavAdapter(ArrayList<NavItem> navItems) {
        this.navItems = navItems;
        selectFirstItem();
    }

    private void selectFirstItem() {
        NavItem navItem = navItems.get(0);
        navItem.setSelected(true);
        selectedItem = navItem;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nav_item, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bindData(navItems.get(position));
    }

    @Override
    public int getItemCount() {
        return navItems.size();
    }

    public void setNavItemSelectedListener(NavItemSelectedListener navItemSelectedListener) {
        this.navItemSelectedListener = navItemSelectedListener;
    }

    interface NavItemSelectedListener {
        void onItemSelected(NavItem navItem);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private View selector;
        private ImageView imageIv;
        private TextView titleTv;

        public ItemViewHolder(View itemView) {
            super(itemView);
            selector = itemView.findViewById(R.id.nav_selector);
            imageIv = itemView.findViewById(R.id.nav_image);
            titleTv = itemView.findViewById(R.id.nav_title);
        }

        public void bindData(final NavItem navItem) {
            if (navItem.isSelected())
                selector.setVisibility(View.VISIBLE);
            else
                selector.setVisibility(View.INVISIBLE);

            imageIv.setImageResource(navItem.getImageRes());

            titleTv.setText(navItem.getTitle());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navItemSelectedListener.onItemSelected(navItem);
                    selectItem(navItem);
                }
            });
        }

        public void selectItem(NavItem navItem) {

            selectedItem.setSelected(false);

            navItem.setSelected(true);
            selectedItem = navItem;

            notifyDataSetChanged();
        }

    }
}
