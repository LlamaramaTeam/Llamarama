package com.github.llamarama.team.client;

import com.github.llamarama.team.util.IDBuilder;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class ModSoundEvents {

    private static final Identifier LLAMARAMA_ID = IDBuilder.of("llamarama");
    private static final Identifier LLAMAJAMA_ID = IDBuilder.of("llamajama");

    public static final SoundEvent LLAMARAMA_DISC = new SoundEvent(LLAMARAMA_ID);
    public static final SoundEvent LLAMAJAMA_DISC = new SoundEvent(LLAMAJAMA_ID);

    static {
        Registry.register(Registry.SOUND_EVENT, LLAMARAMA_ID, LLAMARAMA_DISC);
        Registry.register(Registry.SOUND_EVENT, LLAMAJAMA_ID, LLAMAJAMA_DISC);
    }

    private ModSoundEvents() {

    }

}
