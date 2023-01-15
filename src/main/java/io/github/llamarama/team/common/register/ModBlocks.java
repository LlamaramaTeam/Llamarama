package io.github.llamarama.team.common.register;

import io.github.llamarama.team.Llamarama;
import io.github.llamarama.team.common.block.LlamaMilkCauldronBlock;
import io.github.llamarama.team.common.block.LlamaWoolBedBlock;
import io.github.llamarama.team.common.block.RugBlock;
import io.github.llamarama.team.common.block.StatueBlock;
import io.github.llamarama.team.common.util.IdBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public final class ModBlocks {

    public static final Block LLAMA_WOOL =
        register(new Block(AbstractBlock.Settings.copy(Blocks.WHITE_WOOL)), "llama_wool");
    public static final Block RUG =
        register(new RugBlock(AbstractBlock.Settings.copy(Blocks.WHITE_CARPET)), "rug");
    public static final Block LLAMA_WOOL_BED =
        registerNoItem(new LlamaWoolBedBlock(AbstractBlock.Settings.copy(Blocks.WHITE_BED)), "llama_wool_bed");
    public static final Block LLAMA_MILK_CAULDRON =
        registerNoItem(new LlamaMilkCauldronBlock(AbstractBlock.Settings.copy(Blocks.CAULDRON).ticksRandomly()), "llama_milk_cauldron");
    public static final Block STATUE =
        register(new StatueBlock(AbstractBlock.Settings.copy(Blocks.SAND)), "statue");


    private ModBlocks() {
    }

    @SuppressWarnings("EmptyMethod")
    public static void init() {
    }

    @NotNull
    private static Block register(Block block, String id) {
        Identifier identifier = IdBuilder.of(id);
        Registry.register(Registries.ITEM, identifier, new BlockItem(block,
            // See https://fabricmc.net/2022/11/24/1193.html for ItemGroups
            new Item.Settings().group(Llamarama.LLAMA_ITEM_GROUP)));
        return registerNoItem(block, id);
    }

    @NotNull
    private static Block registerNoItem(Block block, String id) {
        return Registry.register(Registries.BLOCK, IdBuilder.of(id), block);
    }

}
