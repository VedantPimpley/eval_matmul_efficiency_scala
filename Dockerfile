FROM openjdk:11-jdk-slim

# Install basic dependencies
RUN apt-get update && \
    apt-get install -y curl bash procps wget git && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Set environment variables
ENV SCALA_VERSION=2.12.15 \
    SCALA_HOME=/usr/share/scala \
    SBT_VERSION=1.6.2 \
    SPARK_VERSION=3.3.2 \
    SPARK_HOME=/opt/spark

# Install Scala
RUN mkdir -p $SCALA_HOME && \
    curl -L https://downloads.lightbend.com/scala/$SCALA_VERSION/scala-$SCALA_VERSION.tgz | \
    tar -xz -C $SCALA_HOME --strip-components=1 && \
    ln -s $SCALA_HOME/bin/* /usr/bin/

# Install SBT (Scala Build Tool)
RUN curl -L "https://github.com/sbt/sbt/releases/download/v$SBT_VERSION/sbt-$SBT_VERSION.tgz" | \
    tar -xz -C /usr/local && \
    ln -s /usr/local/sbt/bin/sbt /usr/bin/

# Install Apache Spark
RUN mkdir -p $SPARK_HOME && \
    curl -L https://archive.apache.org/dist/spark/spark-$SPARK_VERSION/spark-$SPARK_VERSION-bin-hadoop3.tgz | \
    tar -xz -C /opt && \
    mv /opt/spark-$SPARK_VERSION-bin-hadoop3 $SPARK_HOME && \
    ln -s $SPARK_HOME/bin/* /usr/bin/

# Set PATH
ENV PATH=$PATH:$SCALA_HOME/bin:$SPARK_HOME/bin

# Create a working directory
WORKDIR /app

# Create a sample build.sbt file with Breeze dependency
RUN mkdir -p /app/project
COPY build.sbt /app/
COPY project/build.properties /app/project/

# Copy the main scala file
RUN mkdir -p /app/src/main/scala
COPY HelloWorld.scala /app/src/main/scala

RUN sbt update

CMD ["bash"]