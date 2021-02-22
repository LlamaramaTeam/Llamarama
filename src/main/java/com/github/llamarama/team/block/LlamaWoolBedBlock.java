package com.github.llamarama.team.block;

import com.github.llamarama.team.block.blockentity.LlamaWoolBedBlockEntity;
import net.minecraft.block.BedBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.world.BlockView;

public class LlamaWoolBedBlock extends BedBlock {

    public LlamaWoolBedBlock(Settings settings) {
        super(DyeColor.WHITE, settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new LlamaWoolBedBlockEntity();
    }

}
