package io.github.llamarama.team.common.register;

import io.github.llamarama.team.common.block.blockentity.LlamaWoolBedBlockEntity;
import io.github.llamarama.team.common.util.IdBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;

@SuppressWarnings("SameParameterValue")
public final class ModBlockEntityTypes {

    private ModBlockEntityTypes() {
    }

    @SuppressWarnings("EmptyMethod")
    public static void init() {
    }

    private static <T extends BlockEntity> BlockEntityType<T> create(String id,
                                                                     FabricBlockEntityTypeBuilder.Factory<T> factory,
                                                                     Block... blocks) {
        return register(id, FabricBlockEntityTypeBuilder.create(factory, blocks).build());
    }    public static final BlockEntityType<LlamaWoolBedBlockEntity> LLAMA_WOOL_BED =
        create("llama_wool_bed", LlamaWoolBedBlockEntity::new, ModBlocks.LLAMA_WOOL_BED);

    private static <T extends BlockEntity> BlockEntityType<T> register(String id, BlockEntityType<T> blockEntityType) {
        return Registry.register(Registry.BLOCK_ENTITY_TYPE, IdBuilder.of(id), blockEntityType);
    }




}
