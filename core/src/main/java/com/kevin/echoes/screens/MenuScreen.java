package com.kevin.echoes.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.kevin.echoes.EchoesGame;

/**
 * Menu screen with logo and start/exit buttons
 */
public class MenuScreen implements Screen {
    private final EchoesGame game;
    private SpriteBatch batch;

    // Textures
    private Texture backgroundTexture;
    private Texture logoTexture;
    private Texture startButtonTexture;
    private Texture exitButtonTexture;

    // Button positions and sizes
    private Rectangle startButtonBounds;
    private Rectangle exitButtonBounds;

    // Layout constants
    private static final float LOGO_Y_OFFSET = 20; // Distance from top
    private static final float LOGO_SCALE = 1.0f; // Logo scale (100% of original size)
    private static final float BUTTON_SCALE = 0.2f; // Button scale (20% of original size)
    private static final float BUTTON_SPACING = 30; // Space between buttons (horizontal)
    private static final float BUTTON_Y_OFFSET = 40; // Distance from bottom

    public MenuScreen(EchoesGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();

        // Load textures
        backgroundTexture = new Texture("menu/echoes-menu-screen.png");
        logoTexture = new Texture("menu/echoes-logo.png");
        startButtonTexture = new Texture("menu/start.png");
        exitButtonTexture = new Texture("menu/exit.png");

        // Setup button bounds for click detection
        setupButtons();
    }

    private void setupButtons() {
        float screenWidth = Gdx.graphics.getWidth();
        float screenHeight = Gdx.graphics.getHeight();

        // Button dimensions with scaling
        float buttonWidth = startButtonTexture.getWidth() * BUTTON_SCALE;
        float buttonHeight = startButtonTexture.getHeight() * BUTTON_SCALE;

        // Calculate total width of both buttons with spacing
        float totalWidth = (buttonWidth * 2) + BUTTON_SPACING;

        // Center the button group horizontally
        float startX = (screenWidth - totalWidth) / 2;

        // Position buttons horizontally at the same Y position
        float buttonY = BUTTON_Y_OFFSET;
        float startButtonX = startX;
        float exitButtonX = startX + buttonWidth + BUTTON_SPACING;

        startButtonBounds = new Rectangle(startButtonX, buttonY, buttonWidth, buttonHeight);
        exitButtonBounds = new Rectangle(exitButtonX, buttonY, buttonWidth, buttonHeight);
    }

    @Override
    public void render(float delta) {
        // Clear screen
        Gdx.gl.glClearColor(0.15f, 0.15f, 0.2f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Handle input
        handleInput();

        // Draw everything
        batch.begin();

        // Draw background
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Draw logo at top center with scaling
        float logoWidth = logoTexture.getWidth() * LOGO_SCALE;
        float logoHeight = logoTexture.getHeight() * LOGO_SCALE;
        float logoX = (Gdx.graphics.getWidth() - logoWidth) / 2;
        float logoY = Gdx.graphics.getHeight() - logoHeight - LOGO_Y_OFFSET;
        batch.draw(logoTexture, logoX, logoY, logoWidth, logoHeight);

        // Draw buttons with scaling
        batch.draw(startButtonTexture, startButtonBounds.x, startButtonBounds.y,
                   startButtonBounds.width, startButtonBounds.height);
        batch.draw(exitButtonTexture, exitButtonBounds.x, exitButtonBounds.y,
                   exitButtonBounds.width, exitButtonBounds.height);

        batch.end();
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

            // Check if Start button was clicked
            if (startButtonBounds.contains(touchPos)) {
                // Switch to game screen
                game.setScreen(new GameScreen(game));
                dispose();
            }

            // Check if Exit button was clicked
            if (exitButtonBounds.contains(touchPos)) {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
        // Recalculate button positions when window is resized
        setupButtons();
    }

    @Override
    public void pause() {
        // Called when the application is paused
    }

    @Override
    public void resume() {
        // Called when the application is resumed
    }

    @Override
    public void hide() {
        // Called when this screen is no longer the current screen
    }

    @Override
    public void dispose() {
        // Dispose of resources
        if (batch != null) batch.dispose();
        if (backgroundTexture != null) backgroundTexture.dispose();
        if (logoTexture != null) logoTexture.dispose();
        if (startButtonTexture != null) startButtonTexture.dispose();
        if (exitButtonTexture != null) exitButtonTexture.dispose();
    }
}
