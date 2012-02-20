package catdice.game.kor;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.svg.InkscapeLoader;
import org.newdawn.slick.svg.SimpleDiagramRenderer;
import org.newdawn.slick.util.Log;

public class Thing {
	/** the svg file that is the image */
	private SimpleDiagramRenderer svg;

	/** how fast the thing is moving */
	private int velocity = 0;

	/** where is the thing? */
	private int x = 0;
	private int y = 0;

	/** how scaled is the thing? */
	private float scaleX = 1.0f;
	private float scaleY = 1.0f;

	/* the fileLoc looks like "data/svg/liftarn_Orc.svg" */
	public Thing(String fileLoc) {
		super();
		svg = loadSvg(fileLoc);
	}

	public Thing(String fileLoc, int velocity, int x, int y, float xScale,
			float yScale) {
		super();
		svg = loadSvg(fileLoc);
		this.velocity = velocity;
		this.x = x;
		this.y = y;
		this.scaleX = xScale;
		this.scaleY = yScale;
	}

	private SimpleDiagramRenderer loadSvg(String fileName) {
		SimpleDiagramRenderer rv = null;
		try {
			rv = new SimpleDiagramRenderer(InkscapeLoader.load(fileName));
		} catch (SlickException e) {
			Log.error("SVG " + fileName + " was not loaded! " + e);
		}

		return rv;
	}

	public void renderThing(GameContainer container, Graphics g, float zoom,
			float locx, float locy, int canvasWidth, int canvasHeight,
			float oldZoom) throws SlickException {

		// reset where everything is being drawn
		// g.scale(zoom, zoom);
		scaleCenter(g, zoom, locx, locy, canvasWidth, canvasHeight);
		g.translate(locx, locy);

		// make the thing to its correct scale
		if (scaleX != 0 && scaleY != 0) {

			g.scale(scaleX, scaleY);

		}

		g.translate(x, y);

		svg.render(g);

		g.resetTransform();

	}

	private void scaleCenter(Graphics g, float zoom, float locx, float locy,
			int canvasWidth, int canvasHeight) {
		int screenCenterX = (int) (canvasWidth / 2f - locx);
		int screenCenterY = (int) (canvasHeight / 2f - locy);
		// Log.info("screenCenterX is " + screenCenterX);
		// Log.info("screenCenterY is " + screenCenterY);
		// Log.info("locx is " + locx + " and " + " locy is " + locy);

		g.translate(screenCenterX, screenCenterY);
		g.scale(zoom, zoom);
		g.translate(locx, locy);

		// Log.info("now, locx is " + locx + " and " + " locy is " + locy);

	}

	public SimpleDiagramRenderer getSvg() {
		return svg;
	}

	public void setSvg(SimpleDiagramRenderer svg) {
		this.svg = svg;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public float getScaleX() {
		return scaleX;
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}

}
