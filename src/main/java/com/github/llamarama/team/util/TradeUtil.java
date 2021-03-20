package com.github.llamarama.team.util;

import com.github.llamarama.team.item.ModItems;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.StructureFeature;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public final class TradeUtil {

    public static final TradeOffers.Factory[] FACTORY = new TradeOffers.Factory[]{new SellOneForBuyOne(ModItems.FLIGHT_OF_THE_BUMBLE_LLAMA, ModItems.LLAMAJAMA), new SellOneForBuyOne(ModItems.LLAMAJAMA, ModItems.LLAMARAMA), new GetDefaultTrade(ModItems.LLAMA_MILK, 1, ModItems.LLAMA_CHEESE, 4), new SellRandomForBuyRandom(Items.EMERALD, ModItems.RAW_LLAMA_MEAT, 6, 6, 1, 1), new GetMapTrade(), new GetRandomEnchantTrade()};

    public static class SellItemsForEmeralds implements TradeOffers.Factory {

        private final ItemStack sell;
        private final int experience;
        private final int maxEmeralds;
        private final int mixEmeralds;

        public SellItemsForEmeralds(ItemStack sell, int maxEmeralds, int mixEmeralds, int experience) {
            this.maxEmeralds = maxEmeralds;
            this.mixEmeralds = mixEmeralds;
            this.sell = sell;
            this.experience = experience;
        }

        @Nullable
        @Override
        public TradeOffer create(Entity entity, Random random) {
            return new TradeOffer(new ItemStack(Items.EMERALD, MathHelper.nextInt(random, this.mixEmeralds, this.maxEmeralds)), this.sell, 12, this.experience, 1.0f);
        }

    }

    public static class GetRandomEnchantTrade implements TradeOffers.Factory {

        public GetRandomEnchantTrade() {

        }

        @Nullable
        @Override
        public TradeOffer create(Entity entity, Random random) {
            if (random.nextBoolean()) {
                Set<Map.Entry<RegistryKey<Enchantment>, Enchantment>> enchantmentEntries = Registry.ENCHANTMENT.getEntries();
                List<Enchantment> enchantments = enchantmentEntries.stream().map(Map.Entry::getValue).collect(Collectors.toList());

                Enchantment selectedEnchantment = null;

                if (enchantments.size() > 0) {
                    selectedEnchantment = enchantments.get(random.nextInt(enchantments.size()));
                }

                if (selectedEnchantment != null) {
                    ItemStack out = new ItemStack(Items.ENCHANTED_BOOK);
                    EnchantedBookItem.addEnchantment(out, new EnchantmentLevelEntry(selectedEnchantment, selectedEnchantment.getMaxLevel()));

                    ItemStack buy = new ItemStack(Items.EMERALD, MathHelper.nextInt(random, 5, 64));

                    return new TradeOffer(buy, Items.BOOKSHELF.getDefaultStack(), out, 1, 5, 1.0f);
                }
            }

            return new TradeOffer(new ItemStack(Items.EMERALD, MathHelper.nextInt(random, 5, 10)), new ItemStack(Items.BOOKSHELF, MathHelper.nextInt(random, 1, 5)), 8, 3, 1.0f);
        }

    }

    public static class SellRandomForBuyRandom implements TradeOffers.Factory {

        private final Item buy;
        private final Item sell;
        private final int maxBuy;
        private final int maxSell;
        private final int minBuy;
        private final int minSell;

        public SellRandomForBuyRandom(Item buy, Item sell, int maxBuy, int maxSell, int minBuy, int minSell) {
            this.buy = buy;
            this.sell = sell;
            this.maxBuy = maxBuy;
            this.maxSell = maxSell;
            this.minBuy = minBuy;
            this.minSell = minSell;
        }

        @Nullable
        @Override
        public TradeOffer create(Entity entity, Random random) {
            int finalBuyCount = MathHelper.nextInt(random, this.minBuy, this.maxBuy);
            int finalSellCount = MathHelper.nextInt(random, this.minSell, this.maxSell);
            return new TradeOffer(new ItemStack(this.buy, finalBuyCount), new ItemStack(this.sell, finalSellCount), 8, 3, 1.0f);
        }

    }

    public static class GetMapTrade implements TradeOffers.Factory {

        @Nullable
        @Override
        public TradeOffer create(Entity entity, Random random) {
            World world = entity.getEntityWorld();
            if (!world.isClient() && world instanceof ServerWorld && random.nextInt(4) == 0) {
                BlockPos structurePos = ((ServerWorld) world).locateStructure(StructureFeature.VILLAGE, entity.getBlockPos(), 100, false);

                if (structurePos != null) {
                    ItemStack out = FilledMapItem.createMap(world, structurePos.getX(), structurePos.getZ(), (byte) 4, true, true);

                    FilledMapItem.fillExplorationMap((ServerWorld) world, out);

                    MapState.addDecorationsTag(out, structurePos, "+", MapIcon.Type.RED_X);

                    out.setCustomName(new TranslatableText("llamarama.text.village_map"));

                    return new TradeOffer(new ItemStack(Items.EMERALD, 4 + random.nextInt(10)), out, 1, 3, 4.0f);
                }
            }

            return new SellItemsForEmeralds(Items.EMERALD_BLOCK.getDefaultStack(), 20, 6, 3).create(entity, random);
        }

    }

    public static class SellOneForBuyOne implements TradeOffers.Factory {

        private final Item buy;
        private final Item sell;

        public SellOneForBuyOne(Item buy, Item sell) {
            this.buy = buy;
            this.sell = sell;
        }

        @Nullable
        @Override
        public TradeOffer create(Entity entity, Random random) {
            return new TradeOffer(this.buy.getDefaultStack(), this.sell.getDefaultStack(), 4, 3, 1.0f);
        }

    }

    public static class GetDefaultTrade implements TradeOffers.Factory {

        private final Item buy;
        private final int buyCount;
        private final Item sell;
        private final int sellCount;

        public GetDefaultTrade(Item buy, int buyCount, Item sell, int sellCount) {
            this.buy = buy;
            this.buyCount = buyCount;
            this.sell = sell;
            this.sellCount = sellCount;
        }

        @Nullable
        @Override
        public TradeOffer create(Entity entity, Random random) {
            ItemStack buyStack = new ItemStack(this.buy, this.buyCount);
            ItemStack sellStack = new ItemStack(this.sell, this.sellCount);
            return new TradeOffer(buyStack, sellStack, 8, 3, 1.0f);
        }

    }

}
