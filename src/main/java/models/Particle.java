package models;

import java.awt.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Particle {

	private final int id;
	private Point position;
	private double radius;
	private double property;
	private Set<Particle> neighbours;

	public Particle(int id, Point position, double radius) {
		this.id = id;
		this.position = position;
		this.radius = radius;
		this.neighbours = new HashSet<>();
	}

	public Particle(int id, double radius, double property) {
		this.id = id;
		this.radius = radius;
		this.property = property;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Particle particle = (Particle) o;
		return id == particle.id &&
				Double.compare(particle.radius, radius) == 0 &&
				Double.compare(particle.property, property) == 0 &&
				Objects.equals(position, particle.position) &&
				Objects.equals(neighbours, particle.neighbours);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id, position, radius, property, neighbours);
	}

	@Override
	public String toString() {
		return "models.Particle{" +
				"id=" + id +
				", position=" + position +
				", radius=" + radius +
				", property=" + property +
				", neighbours=" + neighbours +
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

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getProperty() {
		return property;
	}

	public void setProperty(double property) {
		this.property = property;
	}

	public Set<Particle> getNeighbours() {
		return neighbours;
	}

	public void setNeighbours(Set<Particle> neighbours) {
		this.neighbours = neighbours;
	}
}
