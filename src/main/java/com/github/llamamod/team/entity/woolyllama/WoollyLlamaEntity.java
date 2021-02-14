package com.github.llamamod.team.entity.woolyllama;

import com.github.llamamod.team.entity.ModEntityTypes;
import com.github.llamamod.team.mixins.AccessorLlamaEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.world.World;

@SuppressWarnings("all")
public class WoollyLlamaEntity extends LlamaEntity implements Shearable {

    private static final TrackedData<Boolean> SHEARED;

    public WoollyLlamaEntity(EntityType<? extends WoollyLlamaEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SHEARED, false);
    }

    @Override
    public void sheared(SoundCategory shearedSoundCategory) {

    }

    @Override
    public boolean isShearable() {
        return true;
    }

    protected WoollyLlamaEntity getChild() {
        return ModEntityTypes.WOOLLY_LLAMA.create(this.world);
    }

    @Override
    public WoollyLlamaEntity createChild(ServerWorld serverWorld, PassiveEntity mate) {
        WoollyLlamaEntity child = this.getChild();

        this.setChildAttributes(mate, child);

        WoollyLlamaEntity actualMate = (WoollyLlamaEntity) mate;

        int i = this.random.nextInt(Math.max(this.getStrength(), actualMate.getStrength())) + 1;

        if (this.random.nextFloat() < 0.03F) {
            ++i;
        }

        child.setVariant(this.random.nextBoolean() ? this.getVariant() : actualMate.getVariant());
        child.setStrength(i);


        return child;
    }

    public void setStrength(int strength){
        this.dataTracker.set(((AccessorLlamaEntity)this).getStrength(), strength);
    }

    @Override
    public boolean eatsGrass() {
        return true;
    }

    static {
        SHEARED = DataTracker.registerData(WoollyLlamaEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }



}
