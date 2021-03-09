# MariaDB Quick Guide

## Starting MariaDB

### Linux

Starting
```bash
sudo systemctl start mariadb
```

Stopping
```bash
sudo systemctl stop mysql
```

Restarting
```bash
sudo kill `/var/run/mariadb/mariadb.pid`
```

Checking Status
```bash
sudo systemctl status mariadb
```

Resources
1. https://www.digitalocean.com/community/tutorials/how-to-install-mariadb-on-debian-10

## Configuring the Database Info for the Service


## Useful Commands

1. Creating a New Database

```sql
CREATE DATABASE db_name ...;
```

Resources:
https://mariadb.com/kb/en/create-database/

2. Show all Databases

```sql
SHOW DATABASES;
```

Resources:
https://mariadb.com/kb/en/show-databases/


3. Creating a New Table in the Database

Resources:
https://www.techonthenet.com/mariadb/tables/create_table.php

4. Change Password

Resources:
https://www.digitalocean.com/community/tutorials/how-to-reset-your-mysql-or-mariadb-root-password
http://www.sesan.gob.gt/dashboard/docs/reset-mysql-password.html
https://www.tecmint.com/change-mysql-mariadb-root-password/


## Creating Data to Send to the Database


## Deleting Data from the Database


## Updating Data in the Database
