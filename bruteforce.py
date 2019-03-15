import os
import subprocess
import csv
import numpy

with open('brute_force.csv', 'w') as f:
	csv_writer = csv.writer(f, delimiter=';', quotechar='|', quoting=csv.QUOTE_MINIMAL)
	header = ['Matrix {n}x{n}'.format(n=i) for i in range(1, 14)]
	header.insert(0, 'file')
	header.append('Brute Force')
	csv_writer.writerow(header)
	for x in range(1, 11):
		print("x ")
		print(x)
		averages = [x]
		print("averages ")
		print(averages)
		std = ['std']
		for y in range(1, 15):
			print("y ")
			print(y)
			values = []
			brute_force = 'true' if y == 14 else 'false'
			for z in range(1, 5):
				command = 'java -jar ./target/tp1-1.0-SNAPSHOT.jar --staticFile=random/Static-{index}.txt --dynamicFile=random/Dynamic-{index}.txt --radius=1.0 --matrix={matrix} '.format(
					index=x,
					matrix=y,
					brute_force=brute_force
					)
				if brute_force == 'true':
					print("Running BF ... ")
					command = 'java -jar ./target/tp1-1.0-SNAPSHOT.jar --staticFile=random/Static-{index}.txt --dynamicFile=random/Dynamic-{index}.txt --radius=1.0 --bf=true '.format(
						index=x,
						matrix=y,
						brute_force=brute_force
						)
				print(command)
				p = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
				number = None;
				for line in p.stdout.readlines():
					print(line)
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