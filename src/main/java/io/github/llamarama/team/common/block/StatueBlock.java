package io.github.llamarama.team.common.block;

import io.github.llamarama.team.common.register.ModBlockEntityTypes;
import io.github.llamarama.team.common.register.ModBlocks;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.context.LootContextParameters;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

// TODO: Avoid Goal Mixins (FleeEntityGoal and GoToNearestPositionTask)
// FIXME: Feature rendering!
public class StatueBlock extends BlockWithEntity {

    public static final BooleanProperty HARDENED = BooleanProperty.of("hardened");
    public static final DirectionProperty HORIZONTAL_FACING = Properties.HORIZONTAL_FACING;
    public static final String BLOCK_ENTITY_DATA = "BlockEntityNbt";
    private static final VoxelShape SHAPE =
        Block.createCuboidShape(0, 0, 0, 16, 2, 16);

    public StatueBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState()
            .with(HARDENED, true)
            .with(HORIZONTAL_FACING, Direction.NORTH)
        );
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return ModBlockEntityTypes.STATUE.instantiate(pos, state);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        NbtCompound blockEntityNbt = itemStack.getSubNbt(BLOCK_ENTITY_DATA);
        if (blockEntityNbt != null) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity == null) {
                return;
            }

            blockEntity.readNbt(blockEntityNbt);
        }
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(HORIZONTAL_FACING, ctx.getPlayerFacing());
    }

    @SuppressWarnings("deprecation")
    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
        List<ItemStack> droppedStacks = super.getDroppedStacks(state, builder);
        droppedStacks.forEach(itemStack -> {
            if (itemStack.isOf(ModBlocks.STATUE.asItem())) {
                BlockEntity blockEntity = builder.get(LootContextParameters.BLOCK_ENTITY);
                itemStack.setSubNbt(BLOCK_ENTITY_DATA, blockEntity.createNbt());
            }
        });
        return droppedStacks;
    }

    @Override
    public BlockSoundGroup getSoundGroup(BlockState state) {
        return state.get(HARDENED) ? BlockSoundGroup.STONE : BlockSoundGroup.SAND;
    }

    @SuppressWarnings("deprecation")
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @SuppressWarnings("deprecation")
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        var stackInHand = player.getStackInHand(hand);
        if (!state.get(HARDENED) && stackInHand.isOf(Items.WATER_BUCKET)) {
            var resultStack = ItemUsage.exchangeStack(stackInHand, player, new ItemStack(Items.BUCKET));

            player.setStackInHand(hand, resultStack);

            world.setBlockState(pos, state.with(HARDENED, true));
            world.emitGameEvent(player, GameEvent.BLOCK_CHANGE, pos);
            world.playSound(null, pos, SoundEvents.BLOCK_STONE_PLACE, SoundCategory.BLOCKS, 1f, 1f);

            return ActionResult.success(world.isClient);
        }

        return ActionResult.PASS;
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HARDENED, HORIZONTAL_FACING);
    }

}
