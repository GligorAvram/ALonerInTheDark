package ro.gligor.alonerinthedark.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import ro.gligor.alonerinthedark.ALonerInTheDark;
import ro.gligor.alonerinthedark.tween.ActorAccessor;

public class MainMenu implements Screen {

    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonExit, buttonPlay;
    private Label heading;
    private BitmapFont white, black;
    private TweenManager tweenManager;

    @Override
    public void show() {
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("ui/button.atlas");
        skin = new Skin(atlas);

        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //creating fonts
        white = new BitmapFont(Gdx.files.internal("font/white.fnt"), false);
        black = new BitmapFont(Gdx.files.internal("font/black.fnt"), false);

        //creating buttons
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button up");
        textButtonStyle.down = skin.getDrawable("button down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;

        buttonExit = new TextButton("EXIT", textButtonStyle);
        buttonExit.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        buttonExit.pad(10);


        buttonPlay = new TextButton("PLAY", textButtonStyle);
        buttonPlay.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Levels());
            }
        });
        buttonPlay.pad(10);

        //creating heading
        heading = new Label(ALonerInTheDark.TITLE, new Label.LabelStyle(white, Color.WHITE));
        heading.setFontScale(0.6f);


        //set buttons on the screen
        table.add(heading);
        table.getCell(heading).spaceBottom(100);
        table.row();
        table.add(buttonPlay);
        table.getCell(buttonPlay).spaceBottom(10);
        table.row();
        table.padBottom(50);
        table.add(buttonExit);

        stage.addActor(table);


        //create animations
        tweenManager = new TweenManager();
        Tween.registerAccessor(Actor.class, new ActorAccessor());

        //animation to create the color change of the heading
        Timeline.createSequence().beginSequence()
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(0, 0, 0))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1, 1, 0))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(0, 1, 0))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1, 0, 0))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(0, 0, 1))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(0, 1, 0))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(0, 1, 1))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1, 0, 1))
                .push(Tween.to(heading, ActorAccessor.RGB, 0.5f).target(1, 1, 1))
                .end().repeat(Tween.INFINITY, 0).start(tweenManager);


        //timeline for the animation to show the heading/buttons
        Timeline.createSequence().beginSequence()
                .push(Tween.set(buttonPlay, ActorAccessor.ALPHA).target(0))
                .push(Tween.set(buttonExit, ActorAccessor.ALPHA).target(0))
                .push(Tween.from(heading, ActorAccessor.ALPHA, 0.5f).target(0))
                .push(Tween.to(buttonPlay, ActorAccessor.ALPHA, 0.5f).target(1))
                .push(Tween.to(buttonExit, ActorAccessor.ALPHA, 0.5f).target(1))
                .end().start(tweenManager);

        //table fade-in
        Tween.from(table, ActorAccessor.ALPHA, 0.5f).target(0).start(tweenManager);
        Tween.from(table, ActorAccessor.Y, 0.5f).target(Gdx.graphics.getHeight() / 8).start(tweenManager);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);

        tweenManager.update(delta);

        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        table.setClip(true);
        table.setSize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
        atlas.dispose();
        skin.dispose();
        white.dispose();
        black.dispose();
    }
}
