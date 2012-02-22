package catdice.game.kor;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.Body;
import net.phys2d.raw.BodyList;
import net.phys2d.raw.CollisionEvent;
import net.phys2d.raw.CollisionListener;
import net.phys2d.raw.shapes.Box;
import net.phys2d.raw.shapes.Polygon;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import catdice.game.kor.struct.AbstractEnvironment;

public class VectorEnvironment extends AbstractEnvironment {
	/** The bounds of the entire environment */
	private Rectangle bounds;

	@Override
	public Rectangle getBounds() {
		return bounds;
	}

	public VectorEnvironment(int width, int height) {
		super();
		this.setBounds(bounds = new Rectangle(0, 0, width, height));

		world.addListener(new CollisionListener() {
			public void collisionOccured(CollisionEvent event) {
			}
		});
	}

	@Override
	public void render(Graphics g) {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(g);
		}
	}

	@Override
	public void renderBounds(Graphics g) {
		g.setColor(Color.yellow);
		BodyList list = world.getBodies();
		for (int i = 0; i < list.size(); i++) {
			Body body = list.get(i);
			net.phys2d.raw.shapes.Shape shape = body.getShape();

			if (shape instanceof Polygon) {
				Polygon poly = (Polygon) shape;
				org.newdawn.slick.geom.Polygon p = new org.newdawn.slick.geom.Polygon();
				ROVector2f[] verts = poly.getVertices();
				for (int k = 0; k < verts.length; k++) {
					p.addPoint(verts[k].getX(), verts[k].getY());
				}

				g.draw(p);
			}

			if (shape instanceof Box) {
				Box b = (Box) shape;
				Vector2f[] verts = b.getPoints(body.getPosition(), body
					.getRotation());

				org.newdawn.slick.geom.Polygon p = new org.newdawn.slick.geom.Polygon();
				for (int k = 0; k < verts.length; k++) {
					p.addPoint(verts[k].getX(), verts[k].getY());
				}

				g.draw(p);
			}
		}

		g.setLineWidth(1);

	}

	public Rectangle setBounds(Rectangle bounds) {
		this.bounds = bounds;
		return bounds;
	}

	public void init() {
		// TODO Auto-generated method stub

	}

}
