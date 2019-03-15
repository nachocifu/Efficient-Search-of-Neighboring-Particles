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
			defaultValue = "false"
	)
	public boolean help;

	@Option(
			name = "matrix",
			abbrev = 'M',
			help = "Box division.",
			category = "startup",
			defaultValue = "10"
	)
	public int M;

	@Option(
			name = "radius",
			abbrev = 'r',
			help = "interaction radius",
			category = "startup",
			defaultValue = "1.0"
	)
	public double rc;

	@Option(
			name = "pbc",
			abbrev = 'b',
			help = "Enable periodic boundary conditions.",
			category = "startup",
			defaultValue = "false"
	)
	public boolean pbc;

	@Option(
			name = "bf",
			abbrev = 'f',
			help = "Enable brute force algorithm.",
			category = "startup",
			defaultValue = "false"
	)
	public boolean bf;

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
