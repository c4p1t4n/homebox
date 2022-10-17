FROM node:16

WORKDIR /src/react/

COPY public ./public/
COPY src ./src/
COPY package.json ./
RUN apt install -y awscli
RUN npm install -g npm

RUN npm i --force

CMD ["npm", "start"]