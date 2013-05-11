package com.flyingPuckGames.projectFinale.utils;

import java.nio.ByteBuffer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
 
public class Screenshots{
 
    public Pixmap saveScreenshot() {

        Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), 
                                               Gdx.graphics.getHeight(), true);

        return pixmap;
    }
 
    private Pixmap getScreenshot(int x, int y, int w, int h, boolean flipY) {
    	Gdx.gl.glPixelStorei(GL10.GL_PACK_ALIGNMENT, 1);
    	 
    	Pixmap pixmap = new Pixmap(w, h, Pixmap.Format.RGBA8888);
    	ByteBuffer pixels = pixmap.getPixels();
    	Gdx.gl.glReadPixels(x, y, w, h, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE, pixels);
     
    	final int numBytes = w * h * 4;
    	byte[] lines = new byte[numBytes];
    	if (flipY) {
    		final int numBytesPerLine = w * 4;
    		for (int i = 0; i < h; i++) {
    			pixels.position((h - i - 1) * numBytesPerLine);
    			pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
    		}
    		pixels.clear();
    		pixels.put(lines);
    	} else {
    		pixels.clear();
    		pixels.get(lines);
    	}
        return pixmap;
    }
}