package com.example.alishawky.androidtask.Domain.StaticData;

import com.example.alishawky.androidtask.Entitties.NavItem;
import com.example.alishawky.androidtask.R;

import java.util.ArrayList;

public class NavItemsGenerator {
    public static ArrayList<NavItem> provideNavItems() {
        ArrayList<NavItem> navItems = new ArrayList<>();
        navItems.add(new NavItem(NavItem.itemsIds.explore, "Explore", R.drawable.explore));
        navItems.add(new NavItem(NavItem.itemsIds.live_chat, "Live Chat", R.drawable.live));
        navItems.add(new NavItem(NavItem.itemsIds.gallery, "Gallery", R.drawable.gallery));
        navItems.add(new NavItem(NavItem.itemsIds.wish_list, "Wish List", R.drawable.wishlist));
        navItems.add(new NavItem(NavItem.itemsIds.e_magazine, "E-Magazine", R.drawable.e_magazine));
        return navItems;
    }
}
