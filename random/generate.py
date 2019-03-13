from functions import is_int_string, generate_files

number_of_particles = input("Enter number of particles N: ")
area_length = input("Enter area length L: ")

if not is_int_string(number_of_particles) or not is_int_string(area_length):
    sys.exit("All arguments must be integers. Exit.")

generate_files(int(number_of_particles), int(area_length))