FROM ubuntu
RUN apt-get update
RUN apt-get install -y iputils-ping
ENTRYPOINT ["ping", "www.google.it", "-c","5"]
