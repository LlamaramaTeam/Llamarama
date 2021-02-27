package com.github.llamarama.team.block;

import com.github.llamarama.team.Llamarama;
import com.github.llamarama.team.util.IDBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * @author PeterGamesGR
 * This class is to blocks, what {@link com.github.llamarama.team.item.ModItems} is to items.
 * All blocks should be instantiated in this class, after the registry list.
 * This class is final, so it cannot be extended.
 * The constructor of this class is private, so it cannot be instantiated.
 * There is no need to manually create block items.
 * All block items are created automatically and unless you don't need a block item or want a different block item
 * that the default one you needn't worry.
 * To get the respective block item of a block, you need to use the asItem() method, from the {@link Block} class.
 */
@SuppressWarnings("SameParameterValue")
public final class ModBlocks {

    public static final Block LLAMA_WOOL = new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL));
    public static final Block RUG = new RugBlock(AbstractBlock.Settings.copy(Blocks.WHITE_CARPET));
    public static final Block LLAMA_WOOL_BED = new LlamaWoolBedBlock(AbstractBlock.Settings.copy(Blocks.WHITE_BED));
    private static ModBlocks instance = null;


    private ModBlocks() {
        register(LLAMA_WOOL, "llama_wool");
        register(RUG, "rug");
        registerNoItem(LLAMA_WOOL_BED, "llama_wool_bed");
    }


    public static void init() {
        if (instance == null) { instance = new ModBlocks(); }
    }

    private void register(Block block, String id) {
        Identifier identifier = IDBuilder.of(id);
        Registry.register(Registry.BLOCK, identifier, block);
        Registry.register(Registry.ITEM, identifier, new BlockItem(block, new Item.Settings().group(Llamarama.LLAMA_ITEM_GROUP)));
    }

    private void registerNoItem(Block block, String id) {
        Registry.register(Registry.BLOCK, IDBuilder.of(id), block);
    }

}
