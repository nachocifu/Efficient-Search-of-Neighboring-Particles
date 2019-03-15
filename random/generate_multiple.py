from functions import generate_multiple_files

numbers = [100,200,300,400,500,600,700,800,900,1000]

i = 0
for x in numbers:
    i += 1
    generate_multiple_files(i, numbers[i-1], 20.0, 0.25)