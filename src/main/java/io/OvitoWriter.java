package io;

import models.Particle;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class OvitoWriter<T extends Particle> {
	private final File file;
	private final FileWriter fileWriter;

	public OvitoWriter(File file) throws IOException {
		// Create directories if needed, see https://stackoverflow.com/a/4040667
		Optional<File> containingDir = Optional.ofNullable(file.getParentFile());
		if (containingDir.map(dir -> !dir.exists() && !dir.mkdirs()).orElse(false)) {
			throw new IllegalStateException("Couldn't create dir: " + containingDir);
		}
		this.file = file;
		this.fileWriter = new FileWriter(this.file);
	}

	public OvitoWriter(Path path) throws IOException {
		this(path.toFile());
	}

	public void close() throws IOException {
		this.fileWriter.close();
	}

	public void exportPositions(List<T> particles, double time) throws IOException {
		Objects.requireNonNull(particles);
		final int dataSize = particles.size();
		fileWriter.write(String.format("%d\n%g\n", dataSize, time));
		for (T element : particles) {
			// Write basic element data
			fileWriter.write(String.format("%d\t%g\t%g",
					element.getId(),
					element.getPosition().x,
					element.getPosition().y))
			;
			// Write color
			Color color = Color.BLUE;
			fileWriter.write(String.format("\t%d\t%d\t%d", color.getRed(), color.getGreen(), color.getBlue()));
			// Write draw radius
			fileWriter.write(String.format("\t%g", element.getRadius()));
			// Always write newline
			fileWriter.write('\n');
		}
		fileWriter.flush();
	}

}
