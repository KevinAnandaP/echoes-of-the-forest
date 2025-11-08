package com.kevin.echoes;

import com.badlogic.gdx.Game;
import com.kevin.echoes.screens.MenuScreen;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class EchoesGame extends Game {
    
    @Override
    public void create() {
        // Start with the menu screen
        setScreen(new MenuScreen(this));
    }

    @Override
    public void dispose() {
        super.dispose();
        // Screen disposal is handled by each screen's dispose method
    }
}
