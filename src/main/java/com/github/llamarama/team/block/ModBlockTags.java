package com.github.llamarama.team.block;

import com.github.llamarama.team.util.IdBuilder;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;

public final class ModBlockTags {

    public static final Tag<Block> AZALEA_BLOCKS = register("azalea_blocks");
    public static final Tag<Block> LUSH_GROWTH = register("lush_growth");

    @SuppressWarnings("SameParameterValue")
    private static Tag<Block> register(String id) {
        return TagFactory.BLOCK.create(IdBuilder.of(id));
    }

    public static void init() {

    }

}
