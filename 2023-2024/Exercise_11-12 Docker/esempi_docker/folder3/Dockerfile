FROM ubuntu
RUN apt-get update
Run apt-get install -y curl
RUN touch ip.txt
RUN curl ifconfig.me/ip -o "ip.txt"
CMD ["cat", "ip.txt"]