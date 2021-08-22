package com.github.llamarama.team.block.blockentity;

import com.github.llamarama.team.block.ModBlocks;
import com.github.llamarama.team.util.IdBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("SameParameterValue")
public final class ModBlockEntityTypes {

    public static final BlockEntityType<LlamaWoolBedBlockEntity> LLAMA_WOOL_BED =
            create(LlamaWoolBedBlockEntity::new, ModBlocks.LLAMA_WOOL_BED);
    private static ModBlockEntityTypes instance;

    private ModBlockEntityTypes() {
        register("llama_wool_bed", LLAMA_WOOL_BED);
    }


    public static void init() {
        if (instance == null) {
            instance = new ModBlockEntityTypes();
        }
    }

    private static <T extends BlockEntity> BlockEntityType<T> create(FabricBlockEntityTypeBuilder.Factory<T> factory,
                                                                     Block... blocks) {
        return FabricBlockEntityTypeBuilder.create(factory, blocks).build();
    }

    private void register(String id, BlockEntityType<?> blockEntityType) {
        Registry.register(Registry.BLOCK_ENTITY_TYPE, IdBuilder.of(id), blockEntityType);
    }

}
