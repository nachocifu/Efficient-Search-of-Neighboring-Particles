import com.google.devtools.common.options.OptionsParser;
import io.SimulationOptions;

import java.util.Collections;

public class App {

	public static void main(String[] args) {
		OptionsParser parser = OptionsParser.newOptionsParser(SimulationOptions.class);
		parser.parseAndExitUponError(args);
		SimulationOptions options = parser.getOptions(SimulationOptions.class);
//		if (options.host.isEmpty() || options.port < 0 || options.dirs.isEmpty()) {
		printUsage(parser);
//			return;
//		}

	}

	private static void printUsage(OptionsParser parser) {
		System.out.println("Usage: java -jar simulations.jar OPTIONS");
		System.out.println(parser.describeOptions(Collections.<String, String>emptyMap(),
				OptionsParser.HelpVerbosity.LONG));
	}
}
