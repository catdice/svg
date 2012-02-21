package catdice.game.kor;

import net.phys2d.raw.Body;
import net.phys2d.raw.World;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.svg.InkscapeLoader;
import org.newdawn.slick.svg.SimpleDiagramRenderer;
import org.newdawn.slick.util.Log;

public class Thing extends Actor {
	/** The svg image to display for the crate */
	private SimpleDiagramRenderer svg;
	/** The width of the crate */
	private float width;
	/** The height of the crate */
	private float height;
	/** The world to which the crate has been added */
	private World world;

	/** how fast the thing is moving */
	private int xvel = 0;
	private int yvel = 0;

	/** where is the thing? */
	private int x = 0;
	private int y = 0;

	/** how scaled is the thing? */
	private float scaleX = 1.0f;
	private float scaleY = 1.0f;

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
	public Thing(String fileLoc, float x, float y, float mass, float size)
			throws SlickException {
		super(x, y, mass, size);
		svg = loadSvg(fileLoc);

	}

	public Thing(String fileLoc, int xvel, int yvel, int x, int y,
			float xScale, float yScale) {
		super(yScale, yScale, yScale, yScale);
		svg = loadSvg(fileLoc);
		this.xvel = xvel;
		this.yvel = yvel;
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

	@Override
	public void render(Graphics g) {
		// make the thing to its correct scale
		g.scale(scaleX, scaleY);
		// move the thing to its correct spot
		g.translate(x, y);
		// draw the thing
		svg.render(g);
		g.resetTransform();
	}

	public void renderThing(GameContainer container, Graphics g, float zoom,
			float locx, float locy) throws SlickException {

		// reset where everything is being drawn
		// g.scale(zoom, zoom);
		scaleCenter(g, zoom, locx, locy, container.getWidth(), container
				.getHeight());
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

		g.translate(screenCenterX, screenCenterY);
		g.scale(zoom, zoom);
		g.translate(-screenCenterX, -screenCenterY);
		g.translate(locx, locy);

		// Log.info("now, locx is " + locx + " and " + " locy is " + locy);

	}

	@Override
	public Body getBody() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void preUpdate(int delta) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setWorld(World world) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(int delta) {
		// TODO Auto-generated method stub

	}

	public SimpleDiagramRenderer getSvg() {
		return svg;
	}

	public void setSvg(SimpleDiagramRenderer svg) {
		this.svg = svg;
	}

	public int getVelocity() {
		return xvel;
	}

	public int getXvel() {
		return xvel;
	}

	public void setXvel(int xvel) {
		this.xvel = xvel;
	}

	public int getYvel() {
		return yvel;
	}

	public void setYvel(int yvel) {
		this.yvel = yvel;
	}

	public void setVelocity(int velocity) {
		this.xvel = velocity;
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
