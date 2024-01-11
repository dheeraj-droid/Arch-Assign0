import subprocess
import itertools
import matplotlib.pyplot as plt
from mpl_toolkits.mplot3d import Axes3D  # Importing 3D plotting tools
import random

# Function to run Java application with given parameters
def run_java_simulation(width, probability, output_file):
    command = [
        "java",
        "Project",
        str(width),
        str(probability),
        output_file
    ]
    subprocess.run(command, capture_output=True, text=True)

# Function to read the output file and return a dictionary of width, probability, and crossing time
def read_output_file(output_file):
    data = []
    with open(output_file, 'r') as file:
        for line in file:
            values = line.strip().split()
            width, probability, crossing_time = map(float, values)
            data.append({'width': width, 'probability': probability, 'crossing_time': crossing_time})
    return data

# Function to plot the 3D data
def plot_3d_data(data, title):
    fig = plt.figure()
    ax = fig.add_subplot(111, projection='3d')
    ax.scatter([entry['width'] for entry in data], 
               [entry['probability'] for entry in data], 
               [entry['crossing_time'] for entry in data])
    ax.set_xlabel('Width')
    ax.set_ylabel('Probability')
    ax.set_zlabel('Crossing Time (secs)')
    ax.set_title(title)
    plt.show()

output_file = "output.txt"

# Generating 100 random values for width, probability, and running Java simulations
width_values = [5,10,15,20,25,30,35,40]
probability_values = [0.1,0.2,0.3,0.4,0.5,0.6,0.7,0.8,0.9]
with open(output_file, 'a') as file:
    for width, probability in zip(width_values, probability_values):
        run_java_simulation(width, probability, output_file)
        file.write(f"{width} {probability} {read_output_file(output_file)[-1]['crossing_time']}\n")

# Reading the output file and plotting the 3D data
output_data = read_output_file(output_file)
plot_3d_data(output_data, 'Width vs Probability vs Crossing Time')
