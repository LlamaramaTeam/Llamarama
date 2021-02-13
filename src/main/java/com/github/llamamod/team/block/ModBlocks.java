package com.github.llamamod.team.block;

import com.github.llamamod.team.LlamaMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PeterGamesGR
 * This class is to blocks, what {@link com.github.llamamod.team.item.ModItems} is to items.
 * All blocks should be instantiated in this class, after the registry list.
 * This class is final, so it cannot be extended.
 * The constructor of this class is private, so it cannot be instantiated.
 * There is no need to manually create block items.
 * All block items are created automatically and unless you don't need a block item or want a different block item
 * that the default one you needn't worry.
 * In the event that you need to create a class that extends {@link Block} make it extend {@link CustomBlock} instead.
 */
public final class ModBlocks {

    private static final List<CustomBlock> REGISTRY = new ArrayList<>();

    //Instantiate Blocks Here!!!
    public static final CustomBlock LLAMA_WOOL = new CustomBlock("llama_wool", AbstractBlock.Settings.copy(Blocks.WHITE_WOOL));


    private ModBlocks() {

    }

    public static <BLOCK extends CustomBlock> void addToRegistry(BLOCK block) {
        REGISTRY.add(block);
    }

    public static void init() {
        for (CustomBlock block : REGISTRY) {
            Registry.register(Registry.BLOCK, new Identifier(LlamaMod.MOD_ID, block.getId()), block);
            Registry.register(Registry.ITEM, new Identifier(LlamaMod.MOD_ID, block.getId()), block.getItem());
        }
    }

}
