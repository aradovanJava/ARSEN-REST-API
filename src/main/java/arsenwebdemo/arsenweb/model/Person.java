package arsenwebdemo.arsenweb.model;

import java.time.LocalDate;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class Person extends Entity {
    private String oib;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

    public Person(Integer id, String oib, String firstName, String lastName, LocalDate dateOfBirth) {
        super(id);
        this.oib = oib;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
