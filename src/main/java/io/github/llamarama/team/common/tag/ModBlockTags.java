package io.github.llamarama.team.common.tag;

import io.github.llamarama.team.common.util.IdBuilder;
import net.minecraft.block.Block;
import net.minecraft.tag.TagKey;
import net.minecraft.util.registry.Registry;

public final class ModBlockTags {

    public static final TagKey<Block> AZALEA_BLOCKS = TagKey.of(Registry.BLOCK_KEY, IdBuilder.of("azalea_blocks"));
    public static final TagKey<Block> LUSH_GROWTH = TagKey.of(Registry.BLOCK_KEY, IdBuilder.of("lush_growth"));

}
