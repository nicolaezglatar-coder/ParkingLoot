package com.parking.parkinglot;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.EJBException;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/** Initializes the persistence unit and ensures the owner foreign key exists. */
@Singleton
@Startup
public class DatabaseBootstrap {
    @PersistenceContext(unitName = "default")
    private EntityManager entityManager;

    @Resource(lookup = "jdbc/ParkingLotResource")
    private DataSource dataSource;

    @PostConstruct
    public void initialize() {
        entityManager.createQuery("SELECT COUNT(u) FROM User u", Long.class).getSingleResult();
        ensureOwnerForeignKey();
    }

    private void ensureOwnerForeignKey() {
        try (Connection connection = dataSource.getConnection()) {
            try (ResultSet keys = connection.getMetaData()
                    .getImportedKeys(connection.getCatalog(), null, "cars")) {
                while (keys.next()) {
                    if ("owner".equalsIgnoreCase(keys.getString("FKCOLUMN_NAME"))
                            && "users".equalsIgnoreCase(keys.getString("PKTABLE_NAME"))) {
                        return;
                    }
                }
            }
            try (Statement statement = connection.createStatement()) {
                statement.executeUpdate("ALTER TABLE cars ADD CONSTRAINT FK_cars_owner "
                        + "FOREIGN KEY (owner) REFERENCES users (ID)");
            }
        } catch (SQLException ex) {
            throw new EJBException("Could not initialize the cars owner foreign key", ex);
        }
    }
}
