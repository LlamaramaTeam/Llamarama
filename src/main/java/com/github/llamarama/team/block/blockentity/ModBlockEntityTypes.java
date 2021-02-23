package com.github.llamarama.team.block.blockentity;

import com.github.llamarama.team.block.ModBlocks;
import com.github.llamarama.team.util.IDBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;


public final class ModBlockEntityTypes {

    public static final BlockEntityType<LlamaWoolBedBlockEntity> LLAMA_WOOL_BED = BlockEntityType.Builder.create(LlamaWoolBedBlockEntity::new, ModBlocks.LLAMA_WOOL_BED).build(null);
    private static ModBlockEntityTypes instance;

    private ModBlockEntityTypes() {
        register("llama_wool_bed", LLAMA_WOOL_BED);
    }


    public static void init() {
        if (instance == null) {
            instance = new ModBlockEntityTypes();
        }
    }

    private void register(String id, BlockEntityType<?> blockEntityType) {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, IDBuilder.of(id), blockEntityType);
    }

}
