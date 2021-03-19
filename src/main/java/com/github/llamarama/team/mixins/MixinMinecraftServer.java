package com.github.llamarama.team.mixins;

import com.github.llamarama.team.entity.spawn.CaravanTraderSpawnFactory;
import com.google.common.collect.Lists;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerTask;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.util.snooper.SnooperListener;
import net.minecraft.util.thread.ReentrantThreadExecutor;
import net.minecraft.village.ZombieSiegeManager;
import net.minecraft.world.SaveProperties;
import net.minecraft.world.WanderingTraderManager;
import net.minecraft.world.gen.CatSpawner;
import net.minecraft.world.gen.PhantomSpawner;
import net.minecraft.world.gen.PillagerSpawner;
import net.minecraft.world.gen.Spawner;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.List;

@Mixin(MinecraftServer.class)
public abstract class MixinMinecraftServer extends ReentrantThreadExecutor<ServerTask> implements SnooperListener, CommandOutput, AutoCloseable {

    @Shadow
    @Final
    protected SaveProperties saveProperties;

    public MixinMinecraftServer(String string) {
        super(string);
    }

    @ModifyVariable(method = "createWorlds", at = @At("STORE"), name = "list")
    private List<Spawner> modifySpawnsList(List<Spawner> list) {
        return Lists.newArrayList(new CatSpawner(), new PillagerSpawner(), new WanderingTraderManager(this.saveProperties.getMainWorldProperties()), new ZombieSiegeManager(), new CaravanTraderSpawnFactory(), new PhantomSpawner());
    }

}
