https://medium.com/@taesulee93/url-%EB%8B%A8%EC%B6%95%EA%B8%B0-%EC%84%A4%EA%B3%84-d4c73dd283a3

```shell
docker run -p 16379:6379 -d redis:7.2.4 redis-server --requirepass "password"
docker run -p 16379:6379 -d 3ceffe93e5ee --requirepass "password"


docker run -p 3306:3306 -e MYSQL_ROOT_PASSWORD=password -d 738c35640937
```

```sql
drop table if exists shorten_url;
create table shorten_url
(
  url_key    bigint auto_increment,
  original_url varchar(4096) not null,
  hash       varchar(255) not null,
  primary key (url_key)
);
create index shorten_url_uk_01 on shorten_url (hash);
```
