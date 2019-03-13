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
