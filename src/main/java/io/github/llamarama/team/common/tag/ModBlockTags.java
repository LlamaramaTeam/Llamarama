package io.github.llamarama.team.common.tag;

import io.github.llamarama.team.common.util.IdBuilder;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.registry.Registry;

public final class ModBlockTags {

    public static final TagKey<Block> AZALEA_BLOCKS = TagKey.of(RegistryKeys.BLOCK, IdBuilder.of("azalea_blocks"));
    public static final TagKey<Block> LUSH_GROWTH = TagKey.of(RegistryKeys.BLOCK, IdBuilder.of("lush_growth"));

}
