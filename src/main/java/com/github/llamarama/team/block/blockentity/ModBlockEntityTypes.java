package com.github.llamarama.team.block.blockentity;

import com.github.llamarama.team.block.ModBlocks;
import com.github.llamarama.team.util.IDBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;


@SuppressWarnings("InstantiationOfUtilityClass")
public final class ModBlockEntityTypes {

    public static final BlockEntityType<LlamaWoolBedBlockEntity> LLAMA_WOOL_BED_BLOCK_ENTITY_BLOCK_ENTITY_TYPE = BlockEntityType.Builder.create(LlamaWoolBedBlockEntity::new, ModBlocks.LLAMA_WOOL_BED).build(null);
    private static ModBlockEntityTypes instance;

    private ModBlockEntityTypes() {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, IDBuilder.of("llama_wool_bed"), LLAMA_WOOL_BED_BLOCK_ENTITY_BLOCK_ENTITY_TYPE);
    }


    public static void init() {
        if (instance == null) {
            instance = new ModBlockEntityTypes();
        }
    }

}
