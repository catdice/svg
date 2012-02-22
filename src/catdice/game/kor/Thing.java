package catdice.game.kor;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.svg.SimpleDiagramRenderer;

import catdice.game.kor.util.VectorUtil;

public class Thing extends Actor {
	/** The svg image to display for the thing */
	private SimpleDiagramRenderer svg;
	private float scale;

	/** The current frame of animation */
	private int frame;
	/** The timer controlling frame change */
	private int frameTimer = 0;
	/** The interval between animation frame change in milliseconds */
	private int frameInterval = 100;

	/* the fileLoc looks like "data/svg/liftarn_Orc.svg" */
	// public Thing(String fileLoc) {
	// super(height, height, height, height);
	// svg = loadSvg(fileLoc);
	// }

	/**
	 * Create a new Thing actor to add to the world
	 * 
	 * @param x
	 *            The x position of the thing
	 * @param y
	 *            The y position of the thing
	 * @param mass
	 *            The mass of the thing
	 * @param size
	 *            The size of the thing (collision size)
	 * @throws SlickException
	 *             Indicates a failure to load resources for this thing
	 */
	public Thing(String fileLoc, float x, float y, float scale, float mass,
			float size) throws SlickException {
		super(x, y, mass, size);
		this.scale = scale;
		svg = VectorUtil.loadSvg(fileLoc);

	}

	@Override
	public void render(Graphics g) {
		// make the thing to its correct scale
		g.scale(scale, scale);
		// move the thing to its correct spot
		g.translate(getX(), getY());
		// draw the thing
		svg.render(g);
		g.resetTransform();
	}

	public void renderThings(GameContainer container, Graphics g, float zoom,
			float locx, float locy) throws SlickException {

		// reset where everything is being drawn
		// g.scale(zoom, zoom);
		scaleCenter(g, zoom, locx, locy, container.getWidth(), container
			.getHeight());
		g.translate(locx, locy);

		// make the thing to its correct scale
		g.scale(scale, scale);

		g.translate(getX(), getY());

		svg.render(g);

		g.resetTransform();

	}

	private void scaleCenter(Graphics g, float zoom, float locx, float locy,
			int canvasWidth, int canvasHeight) {
		int screenCenterX = (int) (canvasWidth / 2f - locx);
		int screenCenterY = (int) (canvasHeight / 2f - locy);

		g.translate(screenCenterX, screenCenterY);
		g.scale(zoom, zoom);
		g.translate(-screenCenterX, -screenCenterY);
		g.translate(locx, locy);

		// Log.info("now, locx is " + locx + " and " + " locy is " + locy);

	}

	@Override
	public void preUpdate(int delta) {
		super.preUpdate(delta);

		frameTimer -= delta;
		while (frameTimer < 0) {
			frame++;
			frameTimer += frameInterval;
		}

	}

	public SimpleDiagramRenderer getSvg() {
		return svg;
	}

	public void setSvg(SimpleDiagramRenderer svg) {
		this.svg = svg;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scaleX) {
		this.scale = scaleX;
	}

}
