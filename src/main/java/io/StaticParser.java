package io;


import models.Particle;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StaticParser {

	private int numberOfParticles;
	private int boxSide;
	private Set<Particle> particles;

	private String staticFilePath;

	public StaticParser(String staticFilePath) {
		this.staticFilePath = staticFilePath;
		this.particles = new HashSet<>();
	}

	public void parse() {
		File staticFile = new File(staticFilePath);
		Scanner sc = null;
		try {
			sc = new Scanner(staticFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found exception: " + staticFilePath);
		}
		numberOfParticles = sc.nextInt();
		boxSide = sc.nextInt();
		for (int i = 0; i < numberOfParticles; i++) {
			double radius = sc.nextDouble();
			double property = sc.nextDouble();
			particles.add(new Particle(i + 1, radius, property));
		}
		sc.close();
	}

	public int getNumberOfParticles() {
		return numberOfParticles;
	}

	public int getBoxSide() {
		return boxSide;
	}

	public Set<Particle> getParticles() {
		return particles;
	}

	public String getStaticFilePath() {
		return staticFilePath;
	}
}
