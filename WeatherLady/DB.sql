--CREATE DATABASE IF NOT EXISTS weatherlady;
--DROP TABLE IF EXISTS locations;
--CREATE TABLE locations(
--id UUID PRIMARY KEY,
--longitude DOUBLE NOT NULL,
--latitude DOUBLE NOT NULL,
--region VARCHAR(255),
--country VARCHAR(255) NOT NULL,
--city VARCHAR(255) NOT NULL,
----Temperature VARCHAR (255),
----AirPressure VARCHAR (255),
----WindSpeed VARCHAR (255),
----WindAngle VARCHAR (30)
CREATE TABLE locations(
id binary(16) NOT NULL DEFAULT (UUID_TO_BIN(UUID(), TRUE)),
region VARCHAR(255),
country VARCHAR(255) NOT NULL,
city VARCHAR(255) NOT NULL,
longitude DOUBLE NOT NULL,
latitude DOUBLE NOT NULL,
PRIMARY KEY ( id )
)
