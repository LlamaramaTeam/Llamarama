package io.github.llamarama.team.block.blockentity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class LlamaWoolBedBlockEntity extends BlockEntity {

    public LlamaWoolBedBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.LLAMA_WOOL_BED, pos, state);
    }

    @Nullable
    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

}
