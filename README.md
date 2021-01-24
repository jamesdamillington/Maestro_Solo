# Maestro_Solo
Dummy application used with [CRAFTY Brazil][crafty-brazil] (so simulate presence of BioLUC model)

## Building

To build the application, use `ant` to compile the sources

```bash
cd Maestro
ant clean && ant
```

## Running Maestro Solo

To run Maestro Solo on its own, first set the environment variables

## Running

```bash
MAESTRO_SOLO_HOME=/path/to/Maestro_Solo
CRAFTY_BRAZIL_HOME=/path/to/CRAFTY_TemplateCoBRA
```

Suppose we want to use Maestro Solo to continuously check that the file `data/updated.txt` within the CRAFTY Brazil directory exists, and recreate it if necessary. This can be achieved by running

```bash
java -cp $MAESTRO_SOLO_HOME/Maestro/bin/ \
    Maestro.Maestro $CRAFTY_BRAZIL_HOME/data/updated.txt
```

## Running CRAFTY Brazil Solo

The purpose of Maestro Solo is to enable modellers to run CRAFTY Brazil without coupling it to an external global commodity trade model. This repository contains the scripts necessary to run such a model within a Docker container. To follow these instructions you will need to have [Docker installed on your system](https://docs.docker.com/get-docker/).

### Building the Docker image

To build the image, set the root of this repository (where this `README` is stored) as your working directory and run

```bash
docker build -t crafty-brazil-solo .
```

This will create an image called `crafty-brazil-solo`, whose successful creation can be confirmed with `docker image ls`.

### Running a model in Docker

To run a model you need to have two directories set up on your local machine:

1. A CRAFTY Brazil `data` directory containing model run specification data (boundary conditions, scenario file etc.). See the [CRAFTY Brazil][crafty-brazil] repository for an example run configuration.
2. An empty directory where model outputs will be generated.

You can now run the model with, e.g.

```bash
scripts/crafty-brazil-docker /path/to/CRAFTY_Brazil/data /path/to/output \
    -f xml/Scenario.xml -n 1 -o 0 -r 1
```

The first two arguments are mandatory, and specify the paths to the data and output directories on your system. These must be absolute rather than relative paths. All remaining arguments will be passed directly to the CRAFTY Brazil `ModelRunner` class. These are the same arguments that users will be familiar with specifying in Eclipse, and which are documented in the [CRAFTY Model Run instructions][model-run-instructions].

### Running a different version of CRAFTY-Brazil

By default the Docker image will clone the latest release of [CRAFTY Brazil][crafty-brazil]. To run a specific release, edit the Dockerfile. For example, to run v1.0.2 change

```bash
RUN git clone https://github.com/jamesdamillington/CRAFTY_Brazil.git \
    && cd CRAFTY_Brazil && git checkout master && ant clean && ant && cd ..
```

to

```bash
RUN git clone https://github.com/jamesdamillington/CRAFTY_Brazil.git \
    && cd CRAFTY_Brazil && git checkout v1.0.2 && ant clean && ant && cd ..
```

### Limitations when running models with Docker

Docker does not interact particularly well with graphical interfaces. Consequently attempts to run models that specify graphical displays in their scenario files are expected to fail. This is consistent with the [documented preconditions][model-run-instructions] for running CRAFTY models on computing clusters. Model displays can be disabled by providing the following display configuration in your scenario `.xml` file

```xml
<displays class="org.volante.abm.visualisation.NoModelDisplays">
  </displays>
```

[crafty-brazil]: https://github.com/jamesdamillington/CRAFTY_Brazil
[model-run-instructions]: https://www.wiki.ed.ac.uk/display/CRAFTY/Model+Run+Instructions.
=======
