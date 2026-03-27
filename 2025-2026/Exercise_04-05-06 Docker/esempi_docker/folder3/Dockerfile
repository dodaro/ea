FROM ubuntu
RUN apt-get update
RUN apt-get install -y curl
RUN touch ip.txt
RUN curl ifconfig.me/ip -o "ip.txt"
CMD ["cat", "ip.txt"]