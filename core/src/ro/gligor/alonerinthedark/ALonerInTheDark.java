package ro.gligor.alonerinthedark;

import com.badlogic.gdx.Game;


import ro.gligor.alonerinthedark.screens.LoadingScreen;

public class ALonerInTheDark extends Game {

	public static final String TITLE = "A Loner in the Dark", VERSION = "0.0.0.0";
	
	@Override
	public void create () {
		setScreen(new LoadingScreen());
	}

	@Override
	public void render () {
		super.render();

	}
	
	@Override
	public void dispose () {
		super.dispose();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
