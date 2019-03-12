import com.google.devtools.common.options.OptionsParser;
import io.SimulationOptions;
import io.StaticParser;

import java.util.Collections;

public class App {

	public static void main(String[] args) {
		OptionsParser parser = OptionsParser.newOptionsParser(SimulationOptions.class);
		parser.parseAndExitUponError(args);
		SimulationOptions options = parser.getOptions(SimulationOptions.class);
		assert options != null;
		if (options.rc < 0 || options.staticFile.isEmpty() || options.dynamicFile.isEmpty()) {
			printUsage(parser);
		}
		StaticParser staticParser = new StaticParser(options.staticFile);
		staticParser.parse();
	}

	private static void printUsage(OptionsParser parser) {
		System.out.println("Usage: java -jar simulations.jar OPTIONS");
		System.out.println(parser.describeOptions(Collections.<String, String>emptyMap(),
				OptionsParser.HelpVerbosity.LONG));
	}
}
