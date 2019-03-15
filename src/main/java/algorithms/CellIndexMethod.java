package algorithms;

import models.Particle;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class CellIndexMethod {

	/**
	 * Cells from 0 to MxM - 1.
	 * Each one has a list of CellParticles from that cell number.
	 * A CellParticle contains a Particle and the cell's position.
	 */
	private static List<List<CellParticle>> cells = new ArrayList<>();
	private static boolean pbc;
	private static double rc;
	private static int M;
	private static int L;

	public static void run(Queue<Particle> particles,
	                       int boxSide,
	                       int matrixSize,
	                       double interactionRadius,
	                       boolean periodicBoundaryContour) {

		cells = new ArrayList<>();
		pbc = periodicBoundaryContour;
		rc = interactionRadius;
		M = matrixSize;
		L = boxSide;

		for (int i = 0; i < M * M; i++)
			cells.add(new ArrayList<>());

		for (Particle p : particles) {
			// Calculate particle's cell indexes
			double cellX = Math.floor(p.getPosition().x / (L / M));
			double cellY = Math.floor(p.getPosition().y / (L / M));

			// Calculate particle's cell number
			int cellNumber = (int) (cellY * M + cellX);

			// Add particle to that cell with cell position
			cells.get(cellNumber).add(new CellParticle(p, new Point2D.Double(cellX, cellY)));
		}

		for (List<CellParticle> cellParticles : cells) {
			for (CellParticle cp : cellParticles) {
				double cellX = cp.cellPosition.x;
				double cellY = cp.cellPosition.y;

				// Check neighbouring cells from inverted up-side down L shape
				visitNeighbour(cp.particle, cellX, cellY);
				visitNeighbour(cp.particle, cellX, cellY + 1);
				visitNeighbour(cp.particle, cellX + 1, cellY + 1);
				visitNeighbour(cp.particle, cellX + 1, cellY);
				visitNeighbour(cp.particle, cellX + 1, cellY - 1);
			}
		}
	}

	private static void visitNeighbour(Particle particle, double cellX, double cellY) {

		if (pbc) {
			// Reset neighbour cell indexes to comply with contour
			if (cellX >= M) {
				cellX = 0;
			}

			if (cellX < 0) {
				cellX = M - 1;
			}

			if (cellY >= M) {
				cellY = 0;
			}

			if (cellY < 0) {
				cellY = M - 1;
			}

		} else {
			if (cellX >= M || cellX < 0 || cellY >= M || cellY < 0) {
				return;
			}
		}

		int neighbourCellNumber = (int) (cellY * M + cellX);

		if (neighbourCellNumber == -1) {
			System.out.println("cellY: " + cellY);
			System.out.println("M: " + M);
			System.out.println("cellX: " + cellX);
		}
		List<CellParticle> neighbourCellParticles = cells.get(neighbourCellNumber);

		for (CellParticle neighbourCellParticle : neighbourCellParticles) {
			Particle neighbourParticle = neighbourCellParticle.particle;
			if (neighbourParticle.getId() != particle.getId()) {

				double distance;

				if (pbc) {
					distance = particle.getPeriodicDistanceBetween(neighbourParticle, L);
				} else {
					distance = particle.getDistanceBetween(neighbourParticle);
				}

				if (distance < rc) {
					// Mutually add both particles as neighbours
					particle.addNeighbour(neighbourParticle);
					neighbourParticle.addNeighbour(particle);
				}

			}
		}
	}

	private static class CellParticle {
		Particle particle;
		Point2D.Double cellPosition;

		CellParticle(Particle particle, Point2D.Double cellPosition) {
			this.particle = particle;
			this.cellPosition = cellPosition;
		}
	}
}
