# Parking Lot

Jakarta EE 10 / Java 21 web application based on the supplied Parking Lot lessons through section 4.

## What is implemented

- Responsive JSP template with Bootstrap, shared menu and footer, home and About pages.
- `/Cars` and `/Users` servlets and views that display records from MySQL through stateless EJBs and DTOs.
- The requested count of 10 free spots on the Cars page.
- JPA `User` and `Car` entities with required fields, unique values, and a required owner relationship.
- JTA persistence unit bound to `jdbc/ParkingLotResource`, with EclipseLink schema creation/extension triggered at application startup.

The Login link is a placeholder for a later lesson; authentication is not implemented by the supplied lessons.

## Build

Use JDK 21 and run:

```powershell
.\gradlew.bat clean build
```

The deployable file is `build/libs/ParkingLot-1.0-SNAPSHOT.war`.

## MySQL and GlassFish setup

1. In MySQL Workbench, connect with an administrator account and run `database/setup.sql`. This creates the `parkinglot` database and the tutorial's `ParkingLotUser` account. If this account already exists with another password, adjust the connection-pool password accordingly.
2. Copy the MySQL Connector/J JAR into `glassfish7/glassfish/domains/domain1/lib`, then restart GlassFish. The provided `mysql-connector-j-8.0.31.jar.zip` is a JAR-format archive with a `.zip` suffix: copy it under the name `mysql-connector-j-8.0.31.jar`. Do not extract its class files.
3. In the GlassFish admin console (`http://localhost:4848/`), create a JDBC connection pool with the following values:

   | Setting | Value |
   | --- | --- |
   | Pool name | `ParkingLotConnectionPool` |
   | Resource type | `javax.sql.XADataSource` |
   | Data source class | `com.mysql.cj.jdbc.MysqlXADataSource` |
   | `databaseName` | `parkinglot` |
   | `user` | `ParkingLotUser` |
   | `password` | `ParkingLotPassword` (or your chosen password) |
   | `serverName` | `localhost` |
   | `portNumber` | `3306` |
   | `useSSL` | `false` |

   Ping the pool. The tutorial pairs `javax.sql.XADataSource` with `MysqlDataSource`; `MysqlXADataSource` is the class that implements the selected XA interface.
4. Create a JDBC resource named `jdbc/ParkingLotResource` using `ParkingLotConnectionPool`.
5. Deploy the WAR from IntelliJ or the GlassFish admin console. Open `/ParkingLot-1.0-SNAPSHOT/`, `/ParkingLot-1.0-SNAPSHOT/about.jsp`, and `/ParkingLot-1.0-SNAPSHOT/Cars`.
6. Run `database/lab4-data.sql` in MySQL Workbench to insert the lesson 4 sample user and two cars. Open `/ParkingLot-1.0-SNAPSHOT/Cars` and `/ParkingLot-1.0-SNAPSHOT/Users` to see the database records.

The lesson 4 SQL example uses column names that differ from this project's entity mappings. `database/lab4-data.sql` uses the actual `license_plate`, `parking_spot`, and `owner` columns and can be run more than once.

The `create-or-extend-tables` value is an EclipseLink extension, so it is configured with `eclipselink.ddl-generation` and `eclipselink.ddl-generation.output-mode=database`. It creates missing tables and columns; it is not a general migration system for existing constraints or changed column types.
