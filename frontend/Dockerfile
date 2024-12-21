FROM alpine:latest as build

ARG VITE_OAUTH_CLIENT_ID
ENV VITE_OAUTH_CLIENT_ID=$VITE_OAUTH_CLIENT_ID

RUN mkdir /app
WORKDIR /app
RUN apk add npm
COPY . /app
RUN npm ci
RUN npm run build

FROM alpine:latest 

COPY --from=build /app/dist /var/www/localhost/htdocs
COPY default.conf /etc/nginx/http.d/
RUN apk add nginx

CMD ["/usr/sbin/nginx", "-g", "daemon off;"]
