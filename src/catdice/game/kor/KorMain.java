package catdice.game.kor;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.opengl.renderer.Renderer;
import org.newdawn.slick.svg.InkscapeLoader;
import org.newdawn.slick.util.Log;

public class KorMain extends BasicGame {
	public KorMain(String title) {
		super(title);
	}

	/** width of the canvas */
	private static int WIDTH = 600;// 600;
	/** height of the canvas */
	private static int HEIGHT = 250;// 250;

	/** The renderer doing the work */
	private List<Thing> things = new ArrayList<Thing>();

	/** The zoom */
	private float zoom = 1;
	/* used for calculating previous zoom */
	private float oldZoom = zoom;
	/** The x location */
	private float locx;
	/** The y location */
	private float locy;

	/**
	 * @see org.newdawn.slick.BasicGame#init(org.newdawn.slick.GameContainer)
	 */
	public void init(GameContainer container) throws SlickException {
		container.getGraphics().setBackground(Color.white);

		InkscapeLoader.RADIAL_TRIANGULATION_LEVEL = 2;

		Thing one = new Thing("data/svg/griffin.svg", 0, -400, -400, .002f,
				.002f);
		Thing two = new Thing("data/svg/orc.svg", 0, 0, 400, 1.0f, 1.0f);
		Thing three = new Thing("data/svg/orc.svg", 0, 400, 0, 1f, 1f);

		things.add(one);
		things.add(two);
		things.add(three);

		container.getGraphics().setBackground(new Color(0.5f, 0.7f, 1.0f));
	}

	/**
	 * @see org.newdawn.slick.BasicGame#update(org.newdawn.slick.GameContainer,
	 *      int)
	 * 
	 *      delta - amount of time in seconds since last update
	 */
	public void update(GameContainer container, int delta)
			throws SlickException {
		float maxZoom = 4f;
		float minZoom = 1f;
		// int maxy = 0;
		// int maxx = 0;
		// int minx = -(int) (((zoom / minZoom) * (WIDTH) - WIDTH) / zoom);
		// int miny = -(int) (((zoom / minZoom) * (HEIGHT) - HEIGHT) / zoom);

		int maxy = (int) (((zoom / minZoom) * (HEIGHT) - HEIGHT) / (zoom * 2));
		int maxx = (int) (((zoom / minZoom) * (WIDTH) - WIDTH) / (zoom * 2));
		int minx = -(int) (((zoom / minZoom) * (WIDTH) - WIDTH) / (zoom * 2));
		int miny = -(int) (((zoom / minZoom) * (HEIGHT) - HEIGHT) / (zoom * 2));

		Log.info("zoom is " + zoom);
		Log.info("minx is " + minx + " miny is " + miny);
		Log.info("maxx is " + maxx + " maxy is " + maxy);
		Log.info("locx is " + locx);
		Log.info("locy is " + locy);

		oldZoom = zoom;

		if (container.getInput().isKeyDown(Input.KEY_Q)) {
			zoom += (delta * 0.01f);
			if (zoom > maxZoom) {
				zoom = maxZoom;
			}
		}
		if (container.getInput().isKeyDown(Input.KEY_A)) {
			zoom -= (delta * 0.01f);
			if (zoom < minZoom) {
				zoom = minZoom;
			}
		}

		// if zoomed out more, scroll faster
		float shiftAmount = (delta * 0.1f) * maxZoom / zoom;

		if (container.getInput().isKeyDown(Input.KEY_RIGHT)) {
			locx -= shiftAmount;
		}
		if (container.getInput().isKeyDown(Input.KEY_LEFT)) {
			locx += shiftAmount;
		}
		if (container.getInput().isKeyDown(Input.KEY_DOWN)) {
			locy -= shiftAmount;
		}
		if (container.getInput().isKeyDown(Input.KEY_UP)) {
			locy += shiftAmount;
		}

		if (container.getInput().isKeyDown(Input.KEY_ESCAPE)) {
			container.exit();
		}

		if (locx <= minx)
			locx = minx;
		if (locx >= maxx)
			locx = maxx;
		if (locy <= miny)
			locy = miny;
		if (locy >= maxy)
			locy = maxy;
	}

	/**
	 * @see org.newdawn.slick.Game#render(org.newdawn.slick.GameContainer,
	 *      org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, Graphics g)
			throws SlickException {

		for (Thing t : things) {
			t.renderThing(container, g, zoom, locx, locy, WIDTH, HEIGHT,
					oldZoom);
		}
	}

	/**
	 * Entry point to our simple test
	 * 
	 * @param argv
	 *            The arguments passed in
	 */
	public static void main(String argv[]) {
		try {
			Renderer.setRenderer(Renderer.VERTEX_ARRAY_RENDERER);
			Renderer
					.setLineStripRenderer(Renderer.QUAD_BASED_LINE_STRIP_RENDERER);

			AppGameContainer container = new AppGameContainer(
					new KorMain("Kor"));
			container.setDisplayMode(WIDTH, HEIGHT, false);
			container.start();
		} catch (SlickException e) {
			Log.error("the game died");
			e.printStackTrace();
		}
	}
}
