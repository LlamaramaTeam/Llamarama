package com.github.llamarama.team.item;

import net.minecraft.sound.SoundEvent;

public class MusicDiscItem extends net.minecraft.item.MusicDiscItem {

    public MusicDiscItem(int comparatorOutput, SoundEvent sound, Settings settings) {
        super(comparatorOutput, sound, settings);
    }

    @Override
    public boolean isFood() {
        return false;
    }

}
