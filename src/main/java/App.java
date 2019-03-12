import algorithms.BruteForce;
import algorithms.CellIndexMethod;
import com.google.devtools.common.options.OptionsParser;
import io.Parser;
import io.SimulationOptions;
import models.Particle;

import java.util.Collections;
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
		runAlgorithm(staticAndDynamicParser.getParticles(),
				staticAndDynamicParser.getBoxSide(),
				options.M,
				options.rc,
				options.bf,
				options.pbc);
	}

	private static void runAlgorithm(Queue<Particle> particles,
	                                 int L,
	                                 int M,
	                                 double interactionRadius,
	                                 boolean bruteForce,
	                                 boolean periodicBoundaryContour) {

		long startTime = System.currentTimeMillis();

		if (bruteForce) {
			BruteForce.run(particles,
					L,
					interactionRadius,
					periodicBoundaryContour);
		} else {
			CellIndexMethod.run(particles,
					L,
					M,
					interactionRadius,
					periodicBoundaryContour);
		}

		long stopTime = System.currentTimeMillis();
		long elapsedTime = stopTime - startTime;

		if (bruteForce) {
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
