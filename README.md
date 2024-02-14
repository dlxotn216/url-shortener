```shell
docker run -p 16379:6379 -d redis:7.2.4 redis-server --requirepass "password"
docker run -p 16379:6379 -d 3ceffe93e5ee --requirepass "password"


docker run --name mysql-server -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d mysql:8.0.36
```

```sql
create database url_shortener;

drop table if exists shorten_url;
create table shorten_url
(
  url_key    bigint auto_increment,
  original_url varchar(4096) not null,
  hash       varchar(255) not null,
  primary key (url_key)
);
```
