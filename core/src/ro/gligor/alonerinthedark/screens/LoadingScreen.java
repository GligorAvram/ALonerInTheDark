package ro.gligor.alonerinthedark.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import ro.gligor.alonerinthedark.tween.SpriteAccessor;

public class LoadingScreen implements Screen {

    private SpriteBatch batch;
    private Sprite logo;
    private TweenManager tweenManager;

    @Override
    public void show() {
        batch = new SpriteBatch();
        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        Texture texture = new Texture("loading.png");
        logo  = new Sprite(texture);
        logo.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Tween.set(logo, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(logo,SpriteAccessor.ALPHA, 2).target(1).repeatYoyo(1, 0.1f).setCallback(new TweenCallback() {
            @Override
            public void onEvent(int i, BaseTween<?> baseTween) {
                //todo: remember for others games: set main menu
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
            }
        }).start(tweenManager);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);

        batch.begin();
        logo.draw(batch);

        batch.end();
    }

    @Override
    public void resize(int width, int height) {

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
        batch.dispose();
        //prolly not needed
        logo.getTexture().dispose();
    }
}
