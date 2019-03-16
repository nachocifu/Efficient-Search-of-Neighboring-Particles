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
		averages = [x]
		std = ['std']
		for y in range(1, 15):
			values = []
			brute_force = 'true' if y == 14 else 'false'
			for z in range(1, 5):
				command = 'java -jar ./target/tp1-1.0-SNAPSHOT.jar --staticFile=random/Static-{index}.txt --dynamicFile=random/Dynamic-{index}.txt --radius=1.0 --matrix={matrix} '.format(
					index=x,
					matrix=y
					)
				if brute_force == 'true':
					print("Running BF ... ")
					command = 'java -jar ./target/tp1-1.0-SNAPSHOT.jar --staticFile=random/Static-{index}.txt --dynamicFile=random/Dynamic-{index}.txt --radius=1.0 --bf=true '.format(index=x)
				print(command)
				p = subprocess.Popen(command, shell=True, stdout=subprocess.PIPE, stderr=subprocess.STDOUT)
				number = None;
				for line in p.stdout.readlines():
					print(line)
					number = line.decode().split(' ')
					number = number[-1]
					number = number.replace('ms\n', '')
					break;
				values.append(int(number))
			retval = p.wait()
			averages.append(numpy.mean(values))
			std.append(round(numpy.std(values),2))
		csv_writer.writerow(averages)
		csv_writer.writerow(std)