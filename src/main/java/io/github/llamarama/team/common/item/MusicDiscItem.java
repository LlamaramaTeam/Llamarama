package io.github.llamarama.team.common.item;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Rarity;

public class MusicDiscItem extends net.minecraft.item.MusicDiscItem {

    public MusicDiscItem(int comparatorOutput, SoundEvent sound, Settings settings, int lengthInSeconds) {
        super(comparatorOutput, sound, settings.rarity(Rarity.RARE), lengthInSeconds);
    }

    @Override
    public boolean isFood() {
        return false;
    }

}
