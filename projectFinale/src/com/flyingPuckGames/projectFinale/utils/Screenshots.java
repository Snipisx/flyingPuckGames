package com.flyingPuckGames.projectFinale.utils;

import java.awt.Point;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferByte;
import java.awt.image.PixelInterleavedSampleModel;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.nio.ByteBuffer;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.utils.ScreenUtils;

public class Screenshots {

	/*public Pixmap saveScreenshot() {
		Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(),
				Gdx.graphics.getHeight(), true);
		return pixmap;
	}
*/
	public Pixmap getScreenshot(int x, int y, int w, int h, boolean flipY) {
		Gdx.gl.glPixelStorei(GL10.GL_PACK_ALIGNMENT, 1);

		final Pixmap pixmap = new Pixmap(w, h, Format.RGBA8888);
		ByteBuffer pixels = pixmap.getPixels();
		Gdx.gl.glReadPixels(x, y, w, h, GL10.GL_RGBA, GL10.GL_UNSIGNED_BYTE,
				pixels);

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
	
	private static final int[] RGBA_OFFSETS = { 0, 1, 2, 3 };
	private static final int[] RGB_OFFSETS = { 0, 1, 2 };

	public static BufferedImage saveScreenshot(boolean hasAlpha){

		byte[] screenshotPixels = ScreenUtils.getFrameBufferPixels(true);

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();

		
		return saveScreenshot( screenshotPixels, width, height, hasAlpha);
	}

	
	public static BufferedImage saveScreenshot(byte[] pixels, int width, int height, boolean hasAlpha){
		DataBufferByte dataBuffer = new DataBufferByte(pixels, pixels.length);

		PixelInterleavedSampleModel sampleModel = new PixelInterleavedSampleModel(DataBuffer.TYPE_BYTE, width, height, 4, 4 * width, getOffsets(hasAlpha));

		WritableRaster raster = Raster.createWritableRaster(sampleModel, dataBuffer, new Point(0, 0));

		BufferedImage img = new BufferedImage(getColorModel(hasAlpha), raster, false, null);
		return img;
	}
	
	private static ColorModel getColorModel(boolean alpha) {
		if (alpha)
			return new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] { 8, 8, 8, 8 }, true, false, ComponentColorModel.TRANSLUCENT, DataBuffer.TYPE_BYTE);
		return new ComponentColorModel(ColorSpace.getInstance(ColorSpace.CS_sRGB), new int[] { 8, 8, 8 }, false, false, ComponentColorModel.OPAQUE, DataBuffer.TYPE_BYTE);
	}
	
	private static int[] getOffsets(boolean alpha) {
		if (alpha)
			return RGBA_OFFSETS;
		return RGB_OFFSETS;
	}

}