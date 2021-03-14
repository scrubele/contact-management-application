package fr.isen.java2.db.daos;

import fr.isen.java2.db.database.DatabaseConnector;
import fr.isen.java2.db.entities.Person;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;


public class PersonDaoTestCase {

    private PersonDao personDao = new PersonDao();

    @Before
    public void initDb() throws Exception {
        Connection connection = DatabaseConnector.getConnectionFromProps();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(
                "CREATE TABLE IF NOT EXISTS person (\n" +
                        "idperson INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                        "lastname VARCHAR(45) NOT NULL,\n" +
                        "firstname VARCHAR(45) NOT NULL,\n" +
                        "nickname VARCHAR(45) NOT NULL,\n" +
                        "phone_number VARCHAR(15) NULL,\n" +
                        "address VARCHAR(200) NULL,\n" +
                        "email_address VARCHAR(150) NULL,\n" +
                        "birth_date DATE NULL);");
        stmt.executeUpdate("DELETE FROM person");
        stmt.executeUpdate("INSERT INTO person(idperson, lastName, firstName, nickname, phone_number, address, email_address, birth_date) "
                + "VALUES (1, 'Bond', 'James', 'bond007', '+380967498094', 'USA', 'james.bond@gmail.com'," +
                        "'1964-11-14 12:00:00.000)')");
        stmt.executeUpdate("INSERT INTO person(idperson, lastName, firstName, nickname, phone_number, address, email_address, birth_date) "
                + "VALUES (2, 'Jolie', 'Angelina', 'angelina111', '+380967498094', 'USA', 'angelina.jolie@gmail.com'," +
                "'1964-11-14 12:00:00.000)')");
        stmt.executeUpdate("INSERT INTO person(idperson, lastName, firstName, nickname, phone_number, address, email_address, birth_date) "
                + "VALUES (3, 'Potter', 'Harry', 'harry', '+380967498094', 'USA', 'harry.potter@gmail.com'," +
                "'1964-11-14 12:00:00.000)')");
        stmt.close();
        connection.close();
    }

    @Test
    public void shouldListPersons() throws SQLException {
        List<Person> Persons = personDao.listPersons();
        assertThat(Persons).hasSize(3);
        assertThat(Persons).extracting("idperson", "lastName", "firstName").containsOnly(
                tuple(1, "Bond", "James"),
                tuple(2, "Jolie", "Angelina"),
                tuple(3, "Potter", "Harry")
        );
    }


    @Test
    public void shouldAddPerson() throws Exception {
        // WHEN
        Person person = new Person(4, "Granger", "Hermione", "Hermione",
                "+380967498094", "USA", "Hermione.Granger@gmail.com", LocalDate.now());
        personDao.addPerson(person);
        // THEN
        Connection connection = DataSourceFactory.getDataSource().getConnection();
        Statement statement = connection.createStatement();
        String sqlQuery = String.format("SELECT * FROM Person WHERE lastname='%s'", person.getLastName());
        ResultSet resultSet = statement.executeQuery(sqlQuery);
        assertThat(resultSet.next()).isTrue();
        assertThat(resultSet.getInt("idPerson")).isNotNull();
        assertThat(resultSet.getString("lastName")).isEqualTo(person.getLastName());
        assertThat(resultSet.getString("firstName")).isEqualTo(person.getFirstName());
        assertThat(resultSet.next()).isFalse();
        resultSet.close();
        statement.close();
        connection.close();
    }

    @Test
    public void shouldGetById(){
        Person person = personDao.getPerson(2);
        assertThat(person.getIdperson()).isEqualTo(2);
        assertThat(person.getLastName()).isEqualTo("Jolie");
    }
}
