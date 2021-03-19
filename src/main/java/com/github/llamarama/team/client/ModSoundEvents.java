package com.github.llamarama.team.client;

import com.github.llamarama.team.util.IdBuilder;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class ModSoundEvents {

    private static final Identifier LLAMARAMA_ID = IdBuilder.of("llamarama");
    public static final SoundEvent LLAMARAMA_DISC = new SoundEvent(LLAMARAMA_ID);
    private static final Identifier LLAMAJAMA_ID = IdBuilder.of("llamajama");
    public static final SoundEvent LLAMAJAMA_DISC = new SoundEvent(LLAMAJAMA_ID);
    private static final Identifier BUMBLLAMA_ID = IdBuilder.of("flight_of_the_bumble_llama");
    public static final SoundEvent BUMBLLAMA_DISC = new SoundEvent(BUMBLLAMA_ID);
    private static ModSoundEvents instance = null;

    private ModSoundEvents() {
        register(LLAMAJAMA_ID, LLAMAJAMA_DISC);
        register(LLAMARAMA_ID, LLAMARAMA_DISC);
        register(BUMBLLAMA_ID, BUMBLLAMA_DISC);
    }

    public static void init() {
        if (instance == null) {
            instance = new ModSoundEvents();
        }
    }

    private void register(Identifier id, SoundEvent event) {
        Registry.register(Registry.SOUND_EVENT, id, event);
    }

}
