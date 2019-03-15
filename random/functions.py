from numpy import random
import sys

def generate_static_file(number_of_particles, area_length, name, particle_radius=0.25):
    with open(name, 'w') as f:
        one_value = '{}\n'
        f.write(one_value.format(number_of_particles))
        f.write(one_value.format(area_length))
        particle_property = 1
        for x in range(0,number_of_particles):
            f.write('{} {}\n'.format(particle_radius, particle_property))

def generate_dynamic_file(number_of_particles, area_length, name):
    with open(name, 'w') as f:
        f.write('0\n')
        for x in range(0, number_of_particles):
            f.write('{} {}\n'.format(random.uniform(0, area_length), random.uniform(0,area_length)))

def generate_files(number_of_particles, area_length, particle_radius):
    generate_static_file(number_of_particles, area_length, 'Static-N=' + str(number_of_particles) + '.txt', particle_radius)
    generate_dynamic_file(number_of_particles, area_length, 'Dynamic-N=' + str(number_of_particles) + '.txt')

def generate_multiple_files(index, number_of_particles, area_length, particle_radius):
    generate_static_file(number_of_particles, area_length, 'Static-' + str(index) + '.txt', particle_radius)
    generate_dynamic_file(number_of_particles, area_length, 'Dynamic-' + str(index) + '.txt')

def is_int_string(s):
    try:
        int(s)
        return True
    except ValueError:
        return False

def get_radius():
    while True:
        try:
            return float(input("Enter particle radius r: "))
        except ValueError:
            print("Number not a float.")

def get_area_length():
    while True:
        try:
            return float(input("Enter area length L: "))
        except ValueError:
            print("Number not a float.")