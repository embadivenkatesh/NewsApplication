FROM node:8.10.0
RUN mkdir -p /usr/src/app

# Create app directory
WORKDIR /usr/src/app

COPY server.js /usr/src/app

COPY dist /usr/src/app/dist

# Install app dependencies
# A wildcard is used to ensure both package.json AND package-lock.json are copied
# where available (npm@5+)
COPY package-production.json /usr/src/app
RUN mv package-production.json package.json

RUN npm install
# If you are building your code for production
# RUN npm install express --save



EXPOSE 4200
CMD [ "node", "server.js" ]

