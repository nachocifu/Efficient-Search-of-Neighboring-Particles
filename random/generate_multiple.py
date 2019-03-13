from functions import generate_multiple_files

numbers = [10,20,30,40,50,60,70,80,90,100,200,300,400,500,600,700,800,900,1000,2000]

i = 0
for x in numbers:
    i += 1
    generate_multiple_files(i, numbers[i-1], 20)