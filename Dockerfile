# Used to generate a Docker image whose containers run CRAFTY Brazil Solo.
#
# To build the image run the following from the root of this repository
#
# $ docker build -t crafty-brazil-solo .
#
# To start an interactive session inside a container based on this image
# run
#
# $ docker run --rm -it crafty-brazil-solo

FROM debian:9

RUN apt-get update \
    && apt-get install \
    git \
    wget \
    libasound2 \
    libfreetype6 \
    libfontconfig1 \
    libx11-6 \
    libxdmcp6 \
    libxext6 \
    libxrender1 \
    libxtst6 \
    libxi6 \
    libxau6 \
    libxdmcp6 \
    libxcb1 \
    ant -y

RUN wget https://download.bell-sw.com/java/8u275+1/bellsoft-jdk8u275+1-linux-amd64-full.deb \
    && dpkg -i bellsoft-jdk8u275+1-linux-amd64-full.deb

WORKDIR /crafty

COPY ./Maestro ./Maestro

COPY ./scripts ./scripts

ENV PATH /crafty/scripts:$PATH

RUN cd Maestro && ant && cd ..

RUN git clone https://github.com/lanecodes/CRAFTY_Brazil.git \
    && cd CRAFTY_Brazil && git checkout al/main && ant && cd ..
