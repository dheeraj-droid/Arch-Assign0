import subprocess
import itertools
import matplotlib.pyplot as plt

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

# Function to plot the data
def plot_data(data):
    # Example: Plotting width vs crossing time
    plt.scatter([entry['width'] for entry in data], [entry['crossing_time'] for entry in data])
    plt.xlabel('Width')
    plt.ylabel('Crossing Time (secs)')
    plt.title('Width vs Crossing Time')
    plt.show()

# Generating combinations of width and probability
width_values = [5, 10, 15]
probability_values = [0.1, 0.5, 0.9]
output_file = "output.txt"

# Running Java simulations and writing results to the output file
with open(output_file, 'a') as file:
    for width, probability in itertools.product(width_values, probability_values):
        run_java_simulation(width, probability, output_file)
        file.write(f"{width} {probability} {read_output_file(output_file)[-1]['crossing_time']}\n")

# Reading the output file and plotting the data
output_data = read_output_file(output_file)
plot_data(output_data)
