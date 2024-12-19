FROM node:18 AS build

WORKDIR /app

COPY package*.json ./

RUN npm install

COPY . .

RUN npx ng version

RUN npx ng build --configuration production --aot --output-path=dist

FROM nginx:1.25

COPY --from=build /dist/front-end /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]


