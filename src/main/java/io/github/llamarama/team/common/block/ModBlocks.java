package io.github.llamarama.team.common.block;

import io.github.llamarama.team.Llamarama;
import io.github.llamarama.team.common.util.IdBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public final class ModBlocks {

    public static final Block LLAMA_WOOL =
        register(new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)), "llama_wool");
    public static final Block RUG =
        register(new RugBlock(AbstractBlock.Settings.copy(Blocks.WHITE_CARPET)), "rug");
    public static final Block LLAMA_WOOL_BED =
        registerNoItem(new LlamaWoolBedBlock(AbstractBlock.Settings.copy(Blocks.WHITE_BED)), "llama_wool_bed");


    private ModBlocks() {
    }

    @SuppressWarnings("EmptyMethod")
    public static void init() {
    }

    @NotNull
    private static Block register(Block block, String id) {
        Identifier identifier = IdBuilder.of(id);
        Registry.register(Registry.ITEM, identifier, new BlockItem(block, new Item.Settings().group(Llamarama.LLAMA_ITEM_GROUP)));
        return registerNoItem(block, id);
    }

    @NotNull
    private static Block registerNoItem(Block block, String id) {
        return Registry.register(Registry.BLOCK, IdBuilder.of(id), block);
    }

}
