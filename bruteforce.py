import os
import subprocess
import csv
import numpy

with open('brute_force.csv', 'w') as f:
	csv_writer = csv.writer(f, delimiter=';', quotechar='|', quoting=csv.QUOTE_MINIMAL)
	header = ['Matrix {n}x{n}'.format(n=i) for i in range(1, 20)]
	header.insert(0, 'file')
	header.append('Brute Force')
	csv_writer.writerow(header)
	for x in range(1, 20):
		averages = [x]
		std = ['std']
		for y in range(1, 21):
			values = []
			brute_force = '--bf' if y == 20 else ''
			if brute_force:
				for z in range(0,20):
					command = 'java -jar ./target/tp1-1.0-SNAPSHOT.jar --staticFile ./random/Static-{index}.txt --dynamicFile ./random/Dynamic-{index}.txt -r 1 -M {matrix} {brute_force}'.format(
						index=x,
						matrix=y,
						brute_force=brute_force
						)
					p = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
					number = None;
					for line in p.stdout.readlines():
						number = line.decode().split(' ')
						number = number[-1]
						number = number.replace('ms\n', '')
						break;
					values.append(int(float(number)))
				retval = p.wait()
				averages.append(numpy.mean(values))
				std.append(numpy.std(values))
		csv_writer.writerow(averages)
		csv_writer.writerow(std)