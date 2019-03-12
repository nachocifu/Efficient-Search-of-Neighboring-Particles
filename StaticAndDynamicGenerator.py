from numpy import random
import sys

def generate_static_file(number_of_particles, area_length, name, radius=0.25):
    with open(name, 'w') as f:
        one_value = '{}\n'
        f.write(one_value.format(number_of_particles))
        f.write(one_value.format(area_length))
        particle_property = 1
        for x in range(0,number_of_particles):
            f.write('{} {}\n'.format(radius, particle_property))

def generate_dynamic_file(number_of_particles, area_length, name):
    with open(name, 'w') as f:
        f.write('0\n')
        for x in range(0, number_of_particles):
            f.write('{} {}\n'.format(random.uniform(0, area_length), random.uniform(0,area_length)))

def generate_files(number_of_particles, area_length):
    generate_static_file(number_of_particles, area_length,'Static-' + str(number_of_particles) + '.txt')
    generate_dynamic_file(number_of_particles, area_length,'Dynamic-' + str(number_of_particles) + '.txt')

def is_int_string(s):
    try:
        int(s)
        return True
    except ValueError:
        return False

number_of_particles = input("Enter number of particles N: ")
area_length = input("Enter area length L: ")

if not is_int_string(number_of_particles) or not is_int_string(area_length):
    sys.exit("All arguments must be integers. Exit.")

generate_files(int(number_of_particles), int(area_length))