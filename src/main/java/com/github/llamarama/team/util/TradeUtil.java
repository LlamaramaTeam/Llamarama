package com.github.llamarama.team.util;

import com.github.llamarama.team.item.ModItems;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.StructureFeature;

public final class TradeUtil {

    public static final TradeOffers.Factory[] FACTORY = new TradeOffers.Factory[]{(entity, random) -> new TradeOffer(new ItemStack(ModItems.FLIGHT_OF_THE_BUMBLE_LLAMA), ModItems.LLAMAJAMA.getDefaultStack(), 4, 2, 2.0f), (entity, random) -> new TradeOffer(new ItemStack(ModItems.LLAMA_MILK), new ItemStack(ModItems.LLAMA_CHEESE, 4), 8, 2, 1.0f), (entity, random) -> new TradeOffer(new ItemStack(Items.EMERALD, random.nextInt(4) + 1), new ItemStack(ModItems.RAW_LLAMA_MEAT, random.nextInt(5) + 1), 8, 1, 2.0f), (entity, random) -> {
        World world = entity.getEntityWorld();
        if (!world.isClient() && world instanceof ServerWorld) {
            BlockPos structurePos = ((ServerWorld) world).locateStructure(StructureFeature.VILLAGE, entity.getBlockPos(), 100, true);

            if (structurePos != null) {
                ItemStack out = FilledMapItem.createMap(world, structurePos.getX(), structurePos.getZ(), (byte) 4, true, true);

                FilledMapItem.fillExplorationMap((ServerWorld) world, out);

                MapState.addDecorationsTag(out, structurePos, "+", MapIcon.Type.RED_X);

                out.setCustomName(new TranslatableText("llamarama.text.village_map"));

                return new TradeOffer(new ItemStack(Items.EMERALD, 4 + random.nextInt(10)), out, 1, 3, 4.0f);
            }
        }

        return new TradeOffer(new ItemStack(Items.EMERALD, 6 + random.nextInt(20)), new ItemStack(Items.EMERALD_BLOCK), 12, 2, 1.0f);
    }};

}
