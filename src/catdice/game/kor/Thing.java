package catdice.game.kor;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.svg.SimpleDiagramRenderer;
import org.newdawn.slick.util.Log;

import catdice.game.kor.util.VectorUtil;

public class Thing extends Actor {
	/** The svg image to display for the thing */
	//	private SimpleDiagramRenderer svg;
	private float scale;

	/** The current frame of animation */
	private int frame;
	/** The timer controlling frame change */
	private int frameTimer = 0;
	/** The interval between animation frame change in milliseconds */
	private int frameInterval = 100;

	//width of the svg image
	private float width;
	//height of the svg image
	private float height;

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
	public Thing(String fileLoc, float x, float y, float width, float mass)

	throws SlickException {
		super(x, y, mass, width);

		svg = VectorUtil.loadSvg(fileLoc);
		this.scale = VectorUtil.findScale(svg.diagram.getWidth(), width);
		this.width = width;
		this.height = svg.diagram.getHeight() * scale;
		this.setSize(Math.max(this.width, this.height));
		Log.info("1. width is " + width + " and height is " + height);
		Log.info("2. width is " + svg.diagram.getWidth() + " and height is "
				+ svg.diagram.getHeight());
	}

	@Override
	public void render(Graphics g) {

		VectorUtil.drawCentered(g, svg, getX(), getY(), scale);

		// make the thing to its correct scale

		//		Log.info(String.format("x %f", getX()));
		//		Log.info(String.format("y %f", getY()));
		//		Log.info(String.format("maxx %f", g.getClip().getMaxX()));
		//		Log.info(String.format("maxy %f", g.getClip().getMaxY()));
		//		scaleCenter(g, 1, getX(), getY(), g.getClip().getMaxX(), g.getClip()
		//			.getMaxY());
		//		g.scale(scale, scale);
		//
		//		// move the thing to its correct spot
		//		g.translate(getX() + this.width / 2, getY() + this.height / 2);
		//
		//		// draw the thing
		//		svg.render(g);
		//		g.resetTransform();
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
