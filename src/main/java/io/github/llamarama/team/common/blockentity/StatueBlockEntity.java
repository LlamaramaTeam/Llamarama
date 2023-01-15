package io.github.llamarama.team.common.blockentity;

import io.github.llamarama.team.common.block.StatueBlock;
import io.github.llamarama.team.common.register.ModBlockEntityTypes;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.registry.Registry;
import org.jetbrains.annotations.Nullable;

public class StatueBlockEntity extends BlockEntity {

    private static final String IMITATED_TYPE = "ImitatedType";
    private EntityType<? extends LivingEntity> imitatedType;

    public StatueBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntityTypes.STATUE, pos, state);
        this.imitatedType = EntityType.PIG;
    }

    public EntityType<? extends LivingEntity> getImitatedType() {
        return this.imitatedType;
    }

    public void setImitatedType(EntityType<? extends LivingEntity> imitatedType) {
        this.imitatedType = imitatedType;
        PlayerLookup.tracking(this)
            .forEach(serverPlayer -> serverPlayer.networkHandler.sendPacket(this.toUpdatePacket()));
    }

    public boolean isHardened() {
        return this.getCachedState().get(StatueBlock.HARDENED);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        String storedString = nbt.getString(IMITATED_TYPE);
        Identifier id = Identifier.tryParse(storedString);
        id = id != null ? id : Registries.ENTITY_TYPE.getId(EntityType.PIG);
        //noinspection unchecked
        this.imitatedType = Registries.ENTITY_TYPE.getOrEmpty(id)
            .map(it -> (EntityType<? extends LivingEntity>) it)
            .orElseThrow();
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return this.createNbt();
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.putString(IMITATED_TYPE, Registries.ENTITY_TYPE.getId(this.imitatedType).toString());
    }

}
