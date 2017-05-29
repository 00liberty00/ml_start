package ml.model;
// Generated 07.01.2017 22:47:20 by Hibernate Tools 4.3.1

import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javafx.beans.property.BooleanProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * UserSwing generated by hbm2java
 */
@Entity
@Table(name = "userSwing",
         catalog = "marleo"
)
public class UserSwing1 implements java.io.Serializable {

    private LongProperty idUser;
    private StringProperty login;
    private StringProperty name;
    private StringProperty password;
    private ObjectProperty<LocalDate> lastLogin;
    private BooleanProperty active;
    private StringProperty pass;
    private StringProperty phone;
    private StringProperty email;
    private IntegerProperty admin;
    private Set checks = new HashSet(0);

    public UserSwing1() {
    }

    public UserSwing1(ObjectProperty<LocalDate> lastLogin, StringProperty pass, IntegerProperty admin) {
        this.lastLogin = lastLogin;
        this.pass = pass;
        this.admin = admin;
    }

    public UserSwing1(StringProperty login, StringProperty name, StringProperty password,
            ObjectProperty<LocalDate> lastLogin, BooleanProperty active, StringProperty pass,
            StringProperty phone, StringProperty email, IntegerProperty admin, Set checks) {
        this.login = login;
        this.name = name;
        this.password = password;
        this.lastLogin = lastLogin;
        this.active = active;
        this.pass = pass;
        this.phone = phone;
        this.email = email;
        this.admin = admin;
        this.checks = checks;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id_user", unique = true, nullable = false)
    public LongProperty getIdUser() {
        return this.idUser;
    }

    public void setIdUser(LongProperty idUser) {
        this.idUser = idUser;
    }

    @Column(name = "login", length = 50)
    public StringProperty getLogin() {
        return this.login;
    }

    public void setLogin(StringProperty login) {
        this.login = login;
    }

    @Column(name = "name", length = 50)
    public StringProperty getName() {
        return this.name;
    }

    public void setName(StringProperty name) {
        this.name = name;
    }

    @Column(name = "password", length = 50)
    public StringProperty getPassword() {
        return this.password;
    }

    public void setPassword(StringProperty password) {
        this.password = password;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "last_login", nullable = false, length = 19)
    public ObjectProperty<LocalDate> getLastLogin() {
        return this.lastLogin;
    }

    public void setLastLogin(ObjectProperty<LocalDate> lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Column(name = "active")
    public BooleanProperty getActive() {
        return this.active;
    }

    public void setActive(BooleanProperty active) {
        this.active = active;
    }

    @Column(name = "pass", nullable = false, length = 50)
    public StringProperty getPass() {
        return this.pass;
    }

    public void setPass(StringProperty pass) {
        this.pass = pass;
    }

    @Column(name = "phone", length = 50)
    public StringProperty getPhone() {
        return this.phone;
    }

    public void setPhone(StringProperty phone) {
        this.phone = phone;
    }

    @Column(name = "email", length = 50)
    public StringProperty getEmail() {
        return this.email;
    }

    public void setEmail(StringProperty email) {
        this.email = email;
    }

    @Column(name = "admin", nullable = false)
    public IntegerProperty getAdmin() {
        return this.admin;
    }

    public void setAdmin(IntegerProperty admin) {
        this.admin = admin;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userSwing")
    public Set getChecks() {
        return this.checks;
    }

    public void setChecks(Set checks) {
        this.checks = checks;
    }

}
