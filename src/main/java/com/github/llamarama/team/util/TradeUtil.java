package com.github.llamarama.team.util;

import com.github.llamarama.team.block.ModBlocks;
import com.github.llamarama.team.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.PlantBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.entity.Entity;
import net.minecraft.item.*;
import net.minecraft.item.map.MapIcon;
import net.minecraft.item.map.MapState;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionUtil;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.registry.Registry;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.StructureFeature;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.stream.Collectors;

public final class TradeUtil {

    public static final TradeOffers.Factory[] FACTORY = new TradeOffers.Factory[]{
            new SellOneForBuyOne(ModItems.FLIGHT_OF_THE_BUMBLE_LLAMA, ModItems.LLAMAJAMA),
            new SellOneForBuyOne(ModItems.LLAMAJAMA, ModItems.LLAMARAMA),
            new SellOneForBuyOne(ModItems.FLIGHT_OF_THE_BUMBLE_LLAMA, ModItems.LLAMARAMA),
            new DefaultTrade(ModItems.LLAMA_MILK, 1, ModItems.LLAMA_CHEESE, 4),
            new DefaultTrade(Items.EMERALD, 2, ModItems.LLAMA_MILK, 1),
            new SellRandomForBuyRandom(Items.EMERALD, ModItems.RAW_LLAMA_MEAT, 6, 6, 1, 1),
            new VillageMapTrade(),
            new RandomMaxEnchantTrade(),
            new CombinedEnchantmentTrade(),
            new RandomPlantOrNameTagTrade(),
            new RandomPotionTrade(),
            new BuyItemsForEmerald(ModBlocks.LLAMA_WOOL.asItem(), 24, 16),
            new BuyItemsForEmerald(Items.COMPASS, 6, 3),
            new SellOneForBuyOne(Items.DIAMOND, Items.EMERALD),
            new SellOneForBuyOne(Items.IRON_INGOT, Items.GOLD_INGOT),
            new SellOneForBuyOne(Items.EMERALD, Items.DIAMOND),
            new SellOneForBuyOne(Items.GOLD_INGOT, Items.IRON_INGOT),
            new BuyItemsForEmerald(Items.COAL, 18, 8),
            new BuyItemsForEmerald(Items.PAPER, 24, 16),
            new BuyItemsForEmerald(Items.GLASS_PANE, 12, 20),
            new SellForOneEmerald(Items.REDSTONE, 4),
            new SellForOneEmerald(Items.LAPIS_LAZULI, 4),
            new SellForOneEmerald(Items.SLIME_BALL, 4),
            new DefaultTrade(Items.EMERALD, 2, Items.IRON_INGOT, 4),
            new DefaultTrade(Items.EMERALD, 2, Items.GOLD_INGOT, 4)};

    public record SellItemForEmeralds(ItemStack sell, int maxEmeralds,
                                      int mixEmeralds) implements TradeOffers.Factory {

        @Override
        public TradeOffer create(Entity entity, Random random) {
            return new TradeOffer(new ItemStack(Items.EMERALD, MathHelper.nextInt(random, this.mixEmeralds, this.maxEmeralds)), this.sell, 12, 2, 1.0f);
        }

    }

    public static class RandomMaxEnchantTrade implements TradeOffers.Factory {

        public RandomMaxEnchantTrade() {

        }

        @Nullable
        @Override
        public TradeOffer create(Entity entity, Random random) {
            if (random.nextBoolean()) {
                List<Enchantment> enchantments =
                        Registry.ENCHANTMENT.getEntries().stream().map(Map.Entry::getValue).toList();

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

    public record SellRandomForBuyRandom(Item buy, Item sell, int maxBuy, int maxSell,
                                         int minBuy, int minSell) implements TradeOffers.Factory {

        @Override
        public TradeOffer create(Entity entity, Random random) {
            int finalBuyCount = MathHelper.nextInt(random, this.minBuy, this.maxBuy);
            int finalSellCount = MathHelper.nextInt(random, this.minSell, this.maxSell);
            return new TradeOffer(new ItemStack(this.buy, finalBuyCount), new ItemStack(this.sell, finalSellCount), 8, 3, 1.0f);
        }

    }

    public static class VillageMapTrade implements TradeOffers.Factory {

        @Nullable
        @Override
        public TradeOffer create(Entity entity, Random random) {
            World world = entity.getEntityWorld();
            if (!world.isClient() && world instanceof ServerWorld && random.nextInt(4) == 0) {
                BlockPos structurePos = ((ServerWorld) world).locateStructure(StructureFeature.VILLAGE, entity.getBlockPos(), 100, false);

                if (structurePos != null) {
                    ItemStack out = FilledMapItem.createMap(world, structurePos.getX(), structurePos.getZ(), (byte) 4, true, true);

                    FilledMapItem.fillExplorationMap((ServerWorld) world, out);
                    MapState.addDecorationsNbt(out, structurePos, "+", MapIcon.Type.RED_X);
                    out.setCustomName(new TranslatableText("llamarama.text.village_map"));

                    return new TradeOffer(new ItemStack(Items.EMERALD, 4 + random.nextInt(10)), out, 1, 3, 4.0f);
                }
            }

            return new SellItemForEmeralds(Items.EMERALD_BLOCK.getDefaultStack(), 20, 6).create(entity, random);
        }

    }

    public record SellOneForBuyOne(Item buy,
                                   Item sell) implements TradeOffers.Factory {

        @Override
        public TradeOffer create(Entity entity, Random random) {
            return new TradeOffer(this.buy.getDefaultStack(), this.sell.getDefaultStack(), 4, 3, 1.0f);
        }

    }

    public record DefaultTrade(Item buy, int buyCount, Item sell,
                               int sellCount) implements TradeOffers.Factory {

        @Override
        public TradeOffer create(Entity entity, Random random) {
            ItemStack buyStack = new ItemStack(this.buy, this.buyCount);
            ItemStack sellStack = new ItemStack(this.sell, this.sellCount);
            return new TradeOffer(buyStack, sellStack, 12, 3, 1.0f);
        }

    }

    public static class CombinedEnchantmentTrade implements TradeOffers.Factory {

        public CombinedEnchantmentTrade() {

        }

        @Nullable
        @Override
        public TradeOffer create(Entity entity, Random random) {
            List<Enchantment> enchantments = Registry.ENCHANTMENT.getEntries().stream().map(Map.Entry::getValue).toList();
            List<Enchantment> appliedEnchantments = new ArrayList<>();

            int amountOfEnchants = random.nextInt(6) + 1;

            for (int i = amountOfEnchants; i > 0; i--) {
                Enchantment current = enchantments.get(random.nextInt(enchantments.size()));

                appliedEnchantments.add(current);
            }

            List<EnchantmentLevelEntry> finalEnchants =
                    appliedEnchantments.stream().map(enchantment -> new EnchantmentLevelEntry(enchantment,
                            random.nextInt(enchantment.getMaxLevel()) + 1)).toList();

            ItemStack bookOut = Items.ENCHANTED_BOOK.getDefaultStack();
            int totalLevelCount = 0;

            for (EnchantmentLevelEntry entry : finalEnchants) {
                EnchantedBookItem.addEnchantment(bookOut, entry);
                totalLevelCount += entry.level;
                if (entry.enchantment.isTreasure()) {
                    totalLevelCount += 1;
                }
            }

            int totalLevels = totalLevelCount * finalEnchants.size();

            int finalPrice = MathHelper.nextInt(random, totalLevelCount, totalLevels) + MathHelper.nextInt(random, 5, 15);

            finalPrice = Math.min(finalPrice, 64);

            ItemStack buy = new ItemStack(Items.EMERALD, finalPrice);
            ItemStack bookshelves = new ItemStack(Items.BOOKSHELF, finalEnchants.size());

            return new TradeOffer(buy, bookshelves, bookOut, 1, 5, 1.0f);
        }

    }

    public static class RandomPlantOrNameTagTrade implements TradeOffers.Factory {

        public RandomPlantOrNameTagTrade() {

        }


        @Nullable
        @Override
        public TradeOffer create(Entity entity, Random random) {
            if (random.nextBoolean()) {
                Set<Block> plants = Registry.BLOCK.getEntries().stream().map(Map.Entry::getValue).filter((block) -> block instanceof PlantBlock).collect(Collectors.toSet());
                Block out = new ArrayList<>(plants).get(random.nextInt(plants.size()));

                return new TradeOffer(new ItemStack(Items.EMERALD, MathHelper.nextInt(random, 1, 5)), new ItemStack(out, random.nextInt(3) + 1), 8, 2, 1.0f);
            } else {
                return new TradeOffer(new ItemStack(Items.EMERALD, MathHelper.nextInt(random, 5, 10)), Items.NAME_TAG.getDefaultStack(), 8, 3, 1.0f);
            }
        }

    }

    public static class RandomPotionTrade implements TradeOffers.Factory {

        @Nullable
        @Override
        public TradeOffer create(Entity entity, Random random) {
            List<Potion> potions = Registry.POTION.getEntries().stream().map(Map.Entry::getValue).toList();

            ItemStack out = PotionUtil.setPotion(new ItemStack(Items.POTION), potions.get(random.nextInt(potions.size())));
            ItemStack price = new ItemStack(Items.EMERALD, MathHelper.nextInt(random, 1, 5));
            ItemStack catalyst = new ItemStack(Items.GLASS_BOTTLE);

            return new TradeOffer(price, catalyst, out, 4, 4, 1.0f);
        }

    }

    public record BuyItemsForEmerald(Item buy, int max, int min) implements TradeOffers.Factory {

        @Override
        public TradeOffer create(Entity entity, Random random) {
            return new TradeOffer(new ItemStack(this.buy, MathHelper.nextInt(random, this.min, this.max)), Items.EMERALD.getDefaultStack(), 12, 2, 1.0f);
        }

    }

    public record SellForOneEmerald(Item sell, int amount) implements TradeOffers.Factory {

        @Override
        public TradeOffer create(Entity entity, Random random) {
            return new DefaultTrade(Items.EMERALD, 1, this.sell, this.amount).create(entity, random);
        }

    }

}
