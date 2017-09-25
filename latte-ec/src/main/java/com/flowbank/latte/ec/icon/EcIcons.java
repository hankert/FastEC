package com.flowbank.latte.ec.icon;

import com.joanzapata.iconify.Icon;

/**
 * @auther jh
 * @des ${TODO}
 * Created by J.H on 2017/9/25.
 */

public enum  EcIcons implements Icon{
    icon_scan('\ue628');
  //icon_ali_apy();

    private char character;

    EcIcons(char character) {
        this.character = character;
    }

    @Override
    public String key() {
        return name().replace('_','-');
    }

    @Override
    public char character() {
        return character;
    }
}
