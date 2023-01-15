package io.github.llamarama.team.common.register;

import io.github.llamarama.team.common.util.IdBuilder;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.registry.Registry;

public final class ModSoundEvents {

    private static final Identifier LLAMARAMA_ID = IdBuilder.of("llamarama");
    public static final SoundEvent LLAMARAMA_DISC = register(LLAMARAMA_ID);
    private static final Identifier LLAMAJAMA_ID = IdBuilder.of("llamajama");
    public static final SoundEvent LLAMAJAMA_DISC = register(LLAMAJAMA_ID);
    private static final Identifier BUMBLLAMA_ID = IdBuilder.of("flight_of_the_bumble_llama");
    public static final SoundEvent BUMBLLAMA_DISC = register(BUMBLLAMA_ID);
    private static final Identifier MOSSED_UP_ID = IdBuilder.of("mossed_up");
    public static final SoundEvent MOSSED_UP_DISC = register(MOSSED_UP_ID);

    private ModSoundEvents() {
    }

    @SuppressWarnings("EmptyMethod")
    public static void init() {
    }

    private static SoundEvent register(Identifier id) {
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

}
