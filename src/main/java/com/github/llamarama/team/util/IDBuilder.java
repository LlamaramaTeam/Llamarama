package com.github.llamarama.team.util;

import com.github.llamarama.team.Llamarama;
import net.minecraft.util.Identifier;

public interface IDBuilder {

    static Identifier of(String path) {
        return new Identifier(Llamarama.MOD_ID, path);
    }

    static Identifier fullPath(String path) {
        return new Identifier(path);
    }

    static Identifier vanillaOf(String id){
        return new Identifier("minecraft", id);
    }

}
