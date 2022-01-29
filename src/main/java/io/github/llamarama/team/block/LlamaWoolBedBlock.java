package io.github.llamarama.team.block;

import io.github.llamarama.team.block.blockentity.ModBlockEntityTypes;
import net.minecraft.block.BedBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;

public class LlamaWoolBedBlock extends BedBlock {

    public LlamaWoolBedBlock(Settings settings) {
        super(DyeColor.WHITE, settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.LLAMA_WOOL_BED.instantiate(pos, state);
    }

}
