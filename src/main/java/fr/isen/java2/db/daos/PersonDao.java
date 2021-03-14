package fr.isen.java2.db.daos;

import fr.isen.java2.db.database.Database;
import fr.isen.java2.db.entities.Person;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PersonDao {

    private final Database database = new Database();
    private final String tableName = "person";
    private final Object[] columnNames = {"idperson", "lastname", "firstname", "nickname", "phone_number", "address",
            "email_address", "birth_date"};
    private List<Person> personList = new ArrayList<>();

    public List<Person> listPersons() {
        List<Person> personList = new ArrayList<>();
        try (
                ResultSet results = database.select(tableName, columnNames)
        ) {
            while (results.next()) {
                System.out.println(results.toString());
                personList.add(this.getPersonFromResultSet(results));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return personList;
    }

    public Person addPerson(Person person) {
        Object[] values = {
                person.getLastName(),
                person.getFirstName(),
                person.getNickname(),
                person.getPhoneNumber(),
                person.getAddress(),
                person.getEmailAddress(),
                Date.valueOf(person.getBirthDate())
        };
        Object[] localColumnNames = Arrays.copyOfRange(columnNames, 1, columnNames.length);
        try (
                ResultSet ids = database.insert(tableName, localColumnNames, values)
        ) {
            if (ids.next()) {
                return this.getPerson(ids.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<Person> addPersons(List<Person> Persons) {
        List<Person> resultList = new ArrayList<>();
        for (Person Person : Persons) {
            resultList.add(this.addPerson(Person));
        }
        return resultList;
    }

    public Person getPerson(Integer idperson) {
        Object[] columnNames = null;
        Object[] params = {idperson};
        try (
                ResultSet results = database.select(tableName, columnNames, "idPerson=?", params)
        ) {

            if (results.next()) {
                return this.getPersonFromResultSet(results);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public Person getPersonFromResultSet(ResultSet results) throws SQLException {
        return new Person(
                results.getInt(String.valueOf(columnNames[0])),
                results.getString(String.valueOf(columnNames[1])),
                results.getString(String.valueOf(columnNames[2])),
                results.getString(String.valueOf(columnNames[3])),
                results.getString(String.valueOf(columnNames[4])),
                results.getString(String.valueOf(columnNames[5])),
                results.getString(String.valueOf(columnNames[6])),
                results.getDate(String.valueOf(columnNames[7])).toLocalDate());
    }
}
