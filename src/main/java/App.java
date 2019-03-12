import algorithms.BruteForce;
import algorithms.CellIndexMethod;
import com.google.devtools.common.options.OptionsParser;
import io.Parser;
import io.SimulationOptions;
import models.Particle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

public class App {

	public static void main(String[] args) {
		// Parse command line options
		OptionsParser parser = OptionsParser.newOptionsParser(SimulationOptions.class);
		parser.parseAndExitUponError(args);
		SimulationOptions options = parser.getOptions(SimulationOptions.class);
		assert options != null;
		if (options.rc < 0 || options.staticFile.isEmpty() || options.dynamicFile.isEmpty()) {
			printUsage(parser);
		}

		// Parse static and dynamic files
		Parser staticAndDynamicParser = new Parser(options.staticFile, options.dynamicFile);
		staticAndDynamicParser.parse();

		// Run algorithm
		runAlgorithm(staticAndDynamicParser.getParticles(), options.bf, options.rc, options.M, options.pbc,
				options.particle, staticAndDynamicParser.getBoxSide());
	}

	private static void runAlgorithm(Queue<Particle> particles, boolean bf, double rc, int m, boolean pbc, int particle, int L) {
		long startTime = System.currentTimeMillis();

		List<List<Particle>> cells = new ArrayList<>();

		if (bf) {
			BruteForce.run(particles, pbc, rc, L);
		} else {
			CellIndexMethod.run(cells);
		}

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;

		if (bf) {
			System.out.println("Brute Force execution time: " + elapsedTime + "ms");
		} else {
			System.out.println("Cell Index Method execution time: " + elapsedTime + "ms");
		}

		for (Particle p : particles) {
			System.out.print(p.getId());
			for (Particle neighbour : p.getNeighbours()) {
				System.out.print(" " + neighbour.getId());
			}
			System.out.print("\n");
		}
	}

	private static void printUsage(OptionsParser parser) {
		System.out.println("Usage: java -jar simulations.jar OPTIONS");
		System.out.println(parser.describeOptions(Collections.emptyMap(),
				OptionsParser.HelpVerbosity.LONG));
	}
}
