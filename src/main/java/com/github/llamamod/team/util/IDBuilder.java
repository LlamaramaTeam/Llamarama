package com.github.llamamod.team.util;

import com.github.llamamod.team.LlamaMod;
import net.minecraft.util.Identifier;

public interface IDBuilder {

    static Identifier of(String path){
        return new Identifier(LlamaMod.MOD_ID, path);
    }

    static Identifier fullPath(String path){
        return new Identifier(path);
    }
}
