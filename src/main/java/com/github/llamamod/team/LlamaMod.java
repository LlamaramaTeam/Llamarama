package com.github.llamamod.team;

import net.fabricmc.api.ModInitializer;

public class LlamaMod implements ModInitializer {

    public static final String MOD_ID = "llamamod";

    @Override
    public void onInitialize() {
        System.out.println(MOD_ID);
    }

}
