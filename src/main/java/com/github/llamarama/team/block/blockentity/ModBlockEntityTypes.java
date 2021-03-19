package com.github.llamarama.team.block.blockentity;

import com.github.llamarama.team.block.ModBlocks;
import com.github.llamarama.team.util.IdBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

@SuppressWarnings("SameParameterValue")
public final class ModBlockEntityTypes {

    public static final BlockEntityType<LlamaWoolBedBlockEntity> LLAMA_WOOL_BED = create(LlamaWoolBedBlockEntity::new, ModBlocks.LLAMA_WOOL_BED);
    private static ModBlockEntityTypes instance;

    private ModBlockEntityTypes() {
        register("llama_wool_bed", LLAMA_WOOL_BED);
    }


    public static void init() {
        if (instance == null) {
            instance = new ModBlockEntityTypes();
        }
    }

    private static <T extends BlockEntity> BlockEntityType<T> create(Supplier<T> supplier, Block... blocks) {
        return BlockEntityType.Builder.create(supplier, blocks).build(null);
    }

    private void register(String id, BlockEntityType<?> blockEntityType) {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, IdBuilder.of(id), blockEntityType);
    }

}
