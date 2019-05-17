CQL="DROP keyspace userdb;
CREATE KEYSPACE userdb WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1'} AND durable_writes = true;
CREATE TABLE userdb.user ( 
	id uuid, 
	firstName varchar, 
	lastName varchar,
	email varchar,
	PRIMARY KEY (id) );"

until echo $CQL | cqlsh; do
  echo "cqlsh: Cassandra is unavailable to initialize - will retry later"
  sleep 2
done &

exec /docker-entrypoint.sh "$@"