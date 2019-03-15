# Efficient-Search-of-Neighboring-Particles
Efficient Search of Neighboring Particles

## Compilation

```
mvn package
```

## Execution
### Generation of static and dynamic files
```
python StaticAndDynamicGenerator.py
```
### Running simulation

```
java -jar tp1-1.0-SNAPSHOT.jar
```

Options:

* **-h, --help**: Prints usage infp.
* **-M, --matrix &lt;size>**: Size of the squared matrix.
* **-r, --radius &lt;double>**: Interaction radius.
* **--pbc**: Enable periodic boundary conditions.
* **--bf**: Enable brute force algorithm.
* **--staticFile &lt;path>**: Path to static file.
* **--dynamicFile &lt;path>**: Path to dynamic file.

The simulation's results (execution time and list of neighbours for each particle)
are printed to stdout. If you wish, you can redirect the output to a txt file:

```
java -jar target/tp1-1.0-SNAPSHOT.jar --pbc=true --dynamicFile=random/Dynamic-5.txt --staticFile=random/Static-5.txt --radius=3.0 > output.txt
```

### Running Animation

The file `animation.m` provides of a function that animates the simulation's results:

* **static_file**: Path to static file.
* **dynamic_file**: Path to dynamic file.
* **output_file**: Path to output file.
* **M**: MxM matrix size.
* **particle_id**: a certain particle id to view with its neighbors.

```
animation('./random/Static-5.txt','./random/Dynamic-5.txt','./output.txt', 20, 43);
```