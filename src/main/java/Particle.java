import java.awt.*;
import java.util.Objects;

public class Particle {

	private final int id;
	private Point position;
	private int radius;

	public Particle(int id, Point position, int radius) {
		this.id = id;
		this.position = position;
		this.radius = radius;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Particle particle = (Particle) o;
		return id == particle.id &&
				radius == particle.radius &&
				Objects.equals(position, particle.position);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, position, radius);
	}

	@Override
	public String toString() {
		return "Particle{" +
				"id=" + id +
				", position=" + position +
				", radius=" + radius +
				'}';
	}

	public int getId() {
		return id;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}
}
