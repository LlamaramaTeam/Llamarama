package io.github.llamarama.team.common.entity.sandyllama;

import com.mojang.serialization.Dynamic;
import io.github.llamarama.team.common.entity.woolyllama.WoollyLlamaEntity;
import io.github.llamarama.team.common.item.ModSpawnEggItem;
import io.github.llamarama.team.common.register.ModEntityTypes;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

public class SandyLlamaEntity extends WoollyLlamaEntity {

    public SandyLlamaEntity(EntityType<? extends WoollyLlamaEntity> type, World world) {
        super(type, world);
    }

    public static ModSpawnEggItem.SpawnEggData createSpawnEggData() {
        return new ModSpawnEggItem.SpawnEggData(ModEntityTypes.SANDY_LLAMA, 0xffffff, 0x0);
    }

    @Override
    public SandyLlamaEntity createChild(ServerWorld serverWorld, PassiveEntity mate) {
        return ModEntityTypes.SANDY_LLAMA.create(world);
    }

    @Override
    public Brain<SandyLlamaEntity> getBrain() {
        return (Brain<SandyLlamaEntity>) super.getBrain();
    }

    @Override
    protected @NotNull ItemStack getShearedItem() {
        return ItemStack.EMPTY;
    }

    @Override
    protected Brain.Profile<SandyLlamaEntity> createBrainProfile() {
        return SandyLlamaBrain.createProfile();
    }

    @Override
    protected Brain<SandyLlamaEntity> deserializeBrain(Dynamic<?> dynamic) {
        return SandyLlamaBrain.create(this.createBrainProfile().deserialize(dynamic));
    }

    @Override
    protected void mobTick() {
        super.mobTick();
        if (!this.world.isClient) {
            this.getBrain().resetPossibleActivities();
            this.getBrain().tick((ServerWorld) this.world, this);
        }
    }

}
