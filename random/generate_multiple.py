from functions import generate_multiple_files

numbers = [10,50,100,500,1000,1100]

i = 0
for x in numbers:
    i += 1
    generate_multiple_files(i, numbers[i-1], 20.0, 0.25)