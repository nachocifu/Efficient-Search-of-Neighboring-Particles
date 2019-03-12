package io;

import models.Particle;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Parser for static and dynamic files.
 */
public class Parser {

	private String staticFilePath;
	private String dynamicFilePath;

	private int numberOfParticles;
	private int boxSide;
	private Queue<Particle> particles;

	public Parser(String staticFilePath, String dynamicFilePath) {
		this.staticFilePath = staticFilePath;
		this.dynamicFilePath = dynamicFilePath;
		this.particles = new LinkedList<>();
	}

	public boolean parse() {
		return parseStaticFile() && parseDynamicFile();
	}

	private boolean parseStaticFile() {
		File staticFile = new File(staticFilePath);
		Scanner sc;
		try {
			sc = new Scanner(staticFile);
		} catch (FileNotFoundException e) {
			System.out.println("Static file not found exception: " + staticFilePath);
			return false;
		}
		numberOfParticles = sc.nextInt();
		boxSide = sc.nextInt();
		for (int i = 0; i < numberOfParticles; i++) {
			double radius = sc.nextDouble();
			double property = sc.nextDouble();
			particles.add(new Particle(i + 1, radius, property));
		}
		sc.close();
		return true;
	}

	private boolean parseDynamicFile() {
		File dynamicFile = new File(dynamicFilePath);
		Scanner sc;
		try {
			sc = new Scanner(dynamicFile);
		} catch (FileNotFoundException e) {
			System.out.println("Dynamic file not found exception: " + dynamicFilePath);
			return false;
		}
		sc.nextInt();   // t0 not used
		for (int i = 0; i < numberOfParticles; i++) {
			double x = sc.nextDouble();
			double y = sc.nextDouble();
			Particle particle = particles.poll();
			particle.setPosition(new Point2D.Double(x, y));
			particles.add(particle);
		}
		sc.close();
		return true;
	}

	public String getStaticFilePath() {
		return staticFilePath;
	}

	public void setStaticFilePath(String staticFilePath) {
		this.staticFilePath = staticFilePath;
	}

	public String getDynamicFilePath() {
		return dynamicFilePath;
	}

	public void setDynamicFilePath(String dynamicFilePath) {
		this.dynamicFilePath = dynamicFilePath;
	}

	public int getNumberOfParticles() {
		return numberOfParticles;
	}

	public void setNumberOfParticles(int numberOfParticles) {
		this.numberOfParticles = numberOfParticles;
	}

	public int getBoxSide() {
		return boxSide;
	}

	public void setBoxSide(int boxSide) {
		this.boxSide = boxSide;
	}

	public Queue<Particle> getParticles() {
		return particles;
	}

	public void setParticles(Queue<Particle> particles) {
		this.particles = particles;
	}
}
