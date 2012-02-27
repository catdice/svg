package catdice.game.kor.util;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.opengl.renderer.SGL;
import org.newdawn.slick.svg.InkscapeLoader;
import org.newdawn.slick.svg.SimpleDiagramRenderer;
import org.newdawn.slick.util.Log;

public class VectorUtil {

	/** The renderer to use for all GL operations */
	protected static SGL GL = Renderer.get();

	public static SimpleDiagramRenderer loadSvg(String fileName) {
		SimpleDiagramRenderer rv = null;
		try {
			rv = new SimpleDiagramRenderer(InkscapeLoader.load(fileName));
		} catch (SlickException e) {
			Log.error("SVG " + fileName + " was not loaded! " + e);
		}
		return rv;
	}

	public static float findScale(float originalWidth, float newWidth) {
		// the scale is the ratio of the old to the new
		// if it's originally 100 pixels and it needs to be 50
		// then the scale is .5
		float scale = newWidth / originalWidth;
		Log.info(newWidth + "/" + originalWidth + " is " + scale);
		return scale;
	}

	public static void drawCentered(Graphics g, SimpleDiagramRenderer svg,
			float x, float y, float scale) {
		float width = svg.diagram.getWidth() * scale;
		float height = svg.diagram.getHeight() * scale;
		GL.glTranslatef(x - width / 2, y - height / 2, 0);
		g.scale(scale, scale);
		svg.render(g);
		GL.glEnd();
		g.resetTransform();

	}

	private static void scaleCenter(Graphics g, float zoom, float locx,
			float locy, float canvasWidth, float canvasHeight) {

		float screenCenterX = (canvasWidth / 2f - locx);
		float screenCenterY = (canvasHeight / 2f - locy);

		Log.info("width is " + canvasWidth + " and height is " + canvasHeight);
		Log.info("locx is " + locx + " and locy is " + locy);
		Log.info("screenCenterX is " + screenCenterX + " and screenCenterY is "
				+ screenCenterY);

		g.translate(screenCenterX, screenCenterY);
		g.scale(zoom, zoom);
		g.translate(-screenCenterX, -screenCenterY);
		g.translate(locx, locy);
	}

}
