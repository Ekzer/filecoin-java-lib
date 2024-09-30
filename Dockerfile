FROM openjdk:17

## BUILD SWIG
RUN apt-get update && \
apt-get -y install gcc g++ libpcre2-dev make
RUN wget http://prdownloads.sourceforge.net/swig/swig-4.2.1.tar.gz
RUN mkdir swig && tar -zxf swig-4.2.1.tar.gz --directory swig/
RUN echo 'Configuration' && cd swig/swig-4.2.1 && ./configure && make && make install

## BUILD BLST LIB
RUN wget https://github.com/supranational/blst/archive/refs/tags/v0.3.13.tar.gz
RUN mkdir blst && tar -zxf v0.3.13.tar.gz --directory blst/
RUN cd blst/blst-0.3.13/bindings/java && \
    ./build.sh && \
    mkdir -p /app/libs && \
    cp supranational.blst.jar /app/libs