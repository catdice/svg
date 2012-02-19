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
		g.scale(zoom, zoom);
		g.translate(locx, locy);

		if (scaleX != 0 && scaleY != 0) {

			g.scale(scaleX, scaleY);
		}
		//
		// if (oldZoom != zoom) {
		// if (oldZoom < zoom) {
		// Log.info("Zooming in from " + oldZoom + " to " + zoom + "!");
		// Log.info("locx is " + locx + " and " + " locy is " + locy);
		//
		// float nlocx = locx - canvasWidth / 2;// (zoom - oldZoom) * 100;
		// float nlocy = locy - canvasHeight / 2;// (zoom - oldZoom) * 100;
		// // g.translate(nlocx, nlocy);
		// // g.translate(-canvasWidth / 2, -canvasHeight / 2);
		// g.scale(zoom, zoom);
		// // g.translate(canvasWidth / 2, canvasHeight / 2);
		//
		// Log.info("now, locx is " + locx + " and " + " locy is " + locy);
		// } else {
		// Log.info("Zooming out from " + oldZoom + " to " + zoom + "!");
		// Log.info("locx is " + locx + " and " + " locy is " + locy);
		//
		// // float nlocx = locx + canvasWidth / 2;// (zoom - oldZoom) *
		// // 100;
		// // float nlocy = locy + canvasHeight / 2;// (zoom - oldZoom) *
		// // 100;
		// // g.translate(nlocx, nlocy);
		// // g.translate(-canvasWidth / 2, -canvasHeight / 2);
		// g.scale(zoom, zoom);
		// // g.translate(canvasWidth / 2, canvasHeight / 2);
		//
		// Log.info("now, locx is " + locx + " and " + " locy is " + locy);
		// }
		// } else {
		// g.scale(zoom, zoom);
		// }


		g.translate(x, y);

		svg.render(g);

		g.resetTransform();

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
