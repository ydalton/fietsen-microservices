FROM alpine:latest as build

RUN mkdir /app
WORKDIR /app
RUN apk add npm
COPY . /app
RUN npm install
RUN npm run build

FROM alpine:latest 

COPY --from=build /app/dist /var/www/localhost/htdocs
COPY default.conf /etc/nginx/http.d/
RUN apk add nginx

CMD ["/usr/sbin/nginx", "-g", "daemon off;"]
