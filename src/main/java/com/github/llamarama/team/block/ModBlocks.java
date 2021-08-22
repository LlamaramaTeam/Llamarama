package com.github.llamarama.team.block;

import com.github.llamarama.team.Llamarama;
import com.github.llamarama.team.util.IdBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("SameParameterValue")
public final class ModBlocks {

    public static final Block LLAMA_WOOL =
            register(new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)), "llama_wool");
    public static final Block RUG =
            register(new RugBlock(AbstractBlock.Settings.copy(Blocks.WHITE_CARPET)), "rug");
    public static final Block LLAMA_WOOL_BED =
            register(new LlamaWoolBedBlock(AbstractBlock.Settings.copy(Blocks.WHITE_BED)), "llama_wool_bed");


    private ModBlocks() {
    }


    public static void init() {
    }

    private static Block register(Block block, String id) {
        Identifier identifier = IdBuilder.of(id);
        Registry.register(Registry.ITEM, identifier, new BlockItem(block, new Item.Settings().group(Llamarama.LLAMA_ITEM_GROUP)));
        return registerNoItem(block, id);
    }

    private static Block registerNoItem(Block block, String id) {
        return Registry.register(Registry.BLOCK, IdBuilder.of(id), block);
    }

}
