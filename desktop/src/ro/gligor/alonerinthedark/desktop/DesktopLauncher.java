package ro.gligor.alonerinthedark.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import ro.gligor.alonerinthedark.ALonerInTheDark;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = ALonerInTheDark.TITLE + " v" + ALonerInTheDark.VERSION;
		config.vSyncEnabled = true;
		new LwjglApplication(new ALonerInTheDark(), config);
	}
}
