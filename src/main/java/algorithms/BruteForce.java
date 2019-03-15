package algorithms;

import models.Particle;

import java.util.Queue;

public class BruteForce {

	public static void run(Queue<Particle> particles,
	                       double L,
	                       double interactionRadius,
	                       boolean periodicBoundaryContour) {
		
		for (Particle p1 : particles) {
			for (Particle p2 : particles) {
				if (!p1.equals(p2) && !p1.getNeighbours().contains(p2)) {

					double distance;

					if (periodicBoundaryContour) {
						distance = p1.getPeriodicDistanceBetween(p2, L);
					} else {
						distance = p1.getDistanceBetween(p2);
					}

					if (distance < interactionRadius) {
						p1.addNeighbour(p2);
						p2.addNeighbour(p1);
					}
				}
			}
		}
	}
}
