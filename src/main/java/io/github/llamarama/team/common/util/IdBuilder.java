package io.github.llamarama.team.common.util;

import io.github.llamarama.team.Llamarama;
import net.minecraft.util.Identifier;

public interface IdBuilder {

    static Identifier of(String path) {
        return new Identifier(Llamarama.MOD_ID, path);
    }

    static Identifier vanillaOf(String id) {
        return new Identifier("minecraft", id);
    }

}
