package com.github.llamamod.team.block;

import com.github.llamamod.team.LlamaMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;


/**
 * @author PeterGamesGR
 * This class should be extended instead of {@link Block}.
 * In order to add a custom block item, you need to override the getItem() method.
 * This class also takes in the id of your item when you instansiate it.
 * Make sure all your blocks are {@link CustomBlock} because later if we want to add things to all our blocks, we can
 * just add them to this class instead.
 * If you want to use the block item for something you get access to it by doing,
 * ModBlocks.BLOCK_NAME.getItem();
 */
public class CustomBlock extends Block {

    private final String id;
    private CustomBlockItem blockItem;

    public CustomBlock(String id, Settings settings) {
        super(settings);
        this.id = id;
        ModBlocks.addToRegistry(this);
    }

    public CustomBlockItem getItem() {
        if (this.blockItem == null) {
            this.blockItem = new CustomBlockItem(this, new Item.Settings().group(LlamaMod.LLAMA_ITEM_GROUP));
        }
        return this.blockItem;
    }

    public String getId() {
        return id;
    }

}
