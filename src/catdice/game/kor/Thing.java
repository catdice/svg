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

	/** The current frame of animation */
	private int frame;
	/** The timer controlling frame change */
	private int frameTimer = 0;
	/** The interval between animation frame change in milliseconds */
	private int frameInterval = 100;

	/** how scaled is the thing? */
	private float scale;

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
		svg = loadSvg(fileLoc);

	}

	public Thing(String fileLoc, int xvel, int yvel, float x, float y,
			float scale, float mass, float size) {
		super(x, y, mass, size);
		svg = loadSvg(fileLoc);
		this.xvel = xvel;
		this.yvel = yvel;
		this.scale = scale;
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
		g.scale(scale, scale);
		// move the thing to its correct spot
		g.translate(getX(), getY());
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

	public float getScale() {
		return scale;
	}

	public void setScale(float scaleX) {
		this.scale = scaleX;
	}

}
