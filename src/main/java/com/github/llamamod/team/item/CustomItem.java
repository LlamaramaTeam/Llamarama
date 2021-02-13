package com.github.llamamod.team.item;

import net.minecraft.item.Item;


/**
 * @author PeterGamesGR
 * This is also a utility class that extends item.
 * The methods added allow for automatic registration.
 * This class also takes in the id of your item when you instansiate it.
 * Make sure all your items are {@link CustomItem} because later we can add more things in this class
 * and use them with all our items.
 */
public class CustomItem extends Item {

    private final String id;

    public CustomItem(String id, Settings settings) {
        super(settings);
        this.id = id;
        this.register();
    }

    public void register() {
        ModItems.addToRegistry(this);
    }

    public String getId() {
        return this.id;
    }

}
