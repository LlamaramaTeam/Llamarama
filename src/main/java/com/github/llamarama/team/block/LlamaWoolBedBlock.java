package com.github.llamarama.team.block;

import com.github.llamarama.team.tile.LlamaWoolBedBlockEntity;
import net.minecraft.block.BedBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.DyeColor;
import net.minecraft.world.BlockView;

public class LlamaWoolBedBlock extends BedBlock {

    public LlamaWoolBedBlock(DyeColor color, Settings settings) {
        super(color, settings);
    }


    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new LlamaWoolBedBlockEntity();
    }

}
