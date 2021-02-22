package com.github.llamarama.team.block.blockentity;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import org.jetbrains.annotations.Nullable;

public class LlamaWoolBedBlockEntity extends BlockEntity {

    public LlamaWoolBedBlockEntity() {
        super(ModBlockEntityTypes.LLAMA_WOOL_BED_BLOCK_ENTITY_BLOCK_ENTITY_TYPE);
    }

    @Nullable
    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return new BlockEntityUpdateS2CPacket(this.getPos(), 11, this.toInitialChunkDataTag());
    }

}
