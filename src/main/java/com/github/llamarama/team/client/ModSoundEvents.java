package com.github.llamarama.team.client;

import com.github.llamarama.team.util.IdBuilder;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public final class ModSoundEvents {

    private static final Identifier LLAMARAMA_ID = IdBuilder.of("llamarama");
    public static final SoundEvent LLAMARAMA_DISC = register(LLAMARAMA_ID);
    private static final Identifier LLAMAJAMA_ID = IdBuilder.of("llamajama");
    public static final SoundEvent LLAMAJAMA_DISC = register(LLAMAJAMA_ID);
    private static final Identifier BUMBLLAMA_ID = IdBuilder.of("flight_of_the_bumble_llama");
    public static final SoundEvent BUMBLLAMA_DISC = register(BUMBLLAMA_ID);

    private ModSoundEvents() {
    }

    @SuppressWarnings("EmptyMethod")
    public static void init() {
    }

    private static SoundEvent register(Identifier id) {
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }

}
