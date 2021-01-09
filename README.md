# Maestro_Solo
Dummy application used with [CRAFTY Brazil][crafty-brazil] (so simulate presence of BioLUC model)

## Building

To build the application, use `ant` to compile the sources

```bash
cd Maestro
ant clean && ant
```

## Running

To run the application, first set the environment variables

```bash
MAESTRO_SOLO_HOME=/path/to/Maestro_Solo
CRAFTY_BRAZIL_HOME=/path/to/CRAFTY_TemplateCoBRA
```

Suppose we want to use Meastro Solo to continuously check that the file `data/updated.txt` within the CRAFTY Brazil directory exists, and recreate it if necessary. This can be achieved by running

```bash
java -cp $MAESTRO_SOLO_HOME/Maestro/bin/ \
    Maestro.Maestro $CRAFTY_BRAZIL_HOME/data/updated.txt
```

[crafty-brazil]: https://github.com/jamesdamillington/CRAFTY_Brazil
