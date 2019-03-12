package io;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

/**
 * Command-line options definition for example server.
 */
public class SimulationOptions extends OptionsBase {

	@Option(
			name = "help",
			abbrev = 'h',
			help = "Prints usage info.",
			defaultValue = "true"
	)
	public boolean help;

//	@Option(
//			name = "side",
//			abbrev = 'L',
//			help = "Side of box.",
//			category = "startup",
//			defaultValue = "10"
//	)
//	public int L;
//
//	@Option(
//			name = "particles",
//			abbrev = 'N',
//			help = "Number of particles.",
//			category = "startup",
//			defaultValue = "10"
//	)
//	public int N;

//	@Option(
//			name = "division",
//			abbrev = 'M',
//			help = "Box division.",
//			category = "startup",
//			defaultValue = "10"
//	)
//	public int M;

	@Option(
			name = "radiusC",
			abbrev = 'r',
			help = "interaction radius",
			category = "startup",
			defaultValue = "1"
	)
	public int rc;

	@Option(
			name = "particle",
			abbrev = 'p',
			help = "A specified particle to view its neighbours.",
			category = "startup",
			defaultValue = "1"
	)
	public int particle;

	@Option(
			name = "pbc",
			abbrev = 'b',
			help = "Periodic boundary conditions.",
			category = "startup",
			defaultValue = "true"
	)
	public boolean pbc;

	@Option(
			name = "staticFile",
			abbrev = 's',
			help = "Path to static file.",
			category = "startup",
			defaultValue = "/"
	)
	public String staticFile;

	@Option(
			name = "dynamicFile",
			abbrev = 'd',
			help = "Path to dynamic file.",
			category = "startup",
			defaultValue = "/"
	)
	public String dynamicFile;

}
