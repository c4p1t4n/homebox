FROM node:16

WORKDIR /src/react/

COPY public ./public/
COPY src ./src/
COPY package.json ./
RUN curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64-2.0.30.zip" -o "awscliv2.zip"
RUN unzip awscliv2.zip
RUN ./aws/install
RUN npm install -g npm

RUN npm i --force

CMD ["npm", "start"]