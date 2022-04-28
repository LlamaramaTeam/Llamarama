package io.github.llamarama.team.common.block;

import io.github.llamarama.team.common.item.ModItems;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import net.minecraft.block.AbstractCauldronBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.cauldron.CauldronBehavior;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.Map;
import java.util.Random;

public class LlamaMilkCauldronBlock extends AbstractCauldronBlock {

    public static final BooleanProperty IS_CHEESE = BooleanProperty.of("is_cheese");

    public static final Map<Item, CauldronBehavior> BEHAVIOR_MAP = Util.make(new Object2ObjectOpenHashMap<>(), it -> {
        CauldronBehavior.EMPTY_CAULDRON_BEHAVIOR.put(ModItems.LLAMA_MILK, (state, world, pos, player, hand, stack) ->
            CauldronBehavior.fillCauldron(world, pos, player, hand, stack,
                ModBlocks.LLAMA_MILK_CAULDRON.getDefaultState(), SoundEvents.ITEM_BUCKET_EMPTY)
        );

        it.put(Items.BUCKET, (state, world, pos, player, hand, stack) ->
            CauldronBehavior.emptyCauldron(
                state, world, pos, player, hand, stack,
                ModItems.LLAMA_MILK.getDefaultStack(),
                blockState -> !blockState.get(IS_CHEESE),
                SoundEvents.ITEM_BUCKET_EMPTY
            )
        );

        it.defaultReturnValue((state, world, pos, player, hand, stack) -> {
            if (!world.isClient && state.get(IS_CHEESE)) {
                player.giveItemStack(new ItemStack(ModItems.LLAMA_CHEESE, 3));
                player.incrementStat(Stats.USE_CAULDRON);
                world.setBlockState(pos, Blocks.CAULDRON.getDefaultState());
                world.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0F, 1.0F);
                world.emitGameEvent(player, GameEvent.MOB_INTERACT, pos);
                return ActionResult.success(false);
            }

            return ActionResult.PASS;
        });
    });

    public LlamaMilkCauldronBlock(Settings settings) {
        super(settings, BEHAVIOR_MAP);
        this.setDefaultState(this.getStateManager().getDefaultState().with(IS_CHEESE, false));
    }

    @Override
    public boolean isFull(BlockState state) {
        return true;
    }

    @Override
    protected double getFluidHeight(BlockState state) {
        return 0.93d;
    }

    @SuppressWarnings("deprecation") // It's an override only method, there is no deprecation issue.
    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return 5;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(IS_CHEESE);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return !state.get(IS_CHEESE);
    }

    @SuppressWarnings("deprecation")// It's an override only method, there is no deprecation issue.
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (random.nextInt(5) == 0) {
            world.setBlockState(pos, state.with(IS_CHEESE, random.nextBoolean()));
        }
    }

}
