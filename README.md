## Getting Started
 Goto docker directory and run 
 
 ```docker-compose up -d```
 
 Give it few minutes if you are running for the first time
 
 `docker ps -a`
 
 Cassandra is ready when you see 
 
 `Up 35 seconds (healthy)`
 
 The cassandra should be up now with keyspace **userdb** and table **user** created.
## Cassandra status check and cqlsh
```
>docker exec cassandra nodetool status
- Get the IP Address
>docker exec -it cassandra cqlsh [IPAddress]
- you should be in cqlsh, now you can execute cql
cqlsh> select * from userdb.user;
```
 
### Reference Documentation
