package com.github.llamamod.team.block;

import net.minecraft.item.BlockItem;


/**
 * @author PeterGamesGR
 * Just a class that extends {@link BlockItem}.
 *
 * In the event that you need to create a class that extends {@link net.minecraft.item.BlockItem} make it extend
 * {@link CustomBlockItem} instead.
 */
public class CustomBlockItem extends BlockItem {

    public CustomBlockItem(CustomBlock block, Settings settings) {
        super(block, settings);
    }

}
