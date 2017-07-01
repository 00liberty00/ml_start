package ml.modelLicense;
// Generated 01.07.2017 12:42:23 by Hibernate Tools 4.3.1


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name="user"
    ,catalog="crimeali_license"
)
public class User  implements java.io.Serializable {


     private Integer idUser;
     private License license;
     private String name;
     private String phone;
     private String email;

    public User() {
    }

    public User(License license, String name, String phone, String email) {
       this.license = license;
       this.name = name;
       this.phone = phone;
       this.email = email;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_user", unique=true, nullable=false)
    public Integer getIdUser() {
        return this.idUser;
    }
    
    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_license")
    public License getLicense() {
        return this.license;
    }
    
    public void setLicense(License license) {
        this.license = license;
    }

    
    @Column(name="name", length=100)
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    
    @Column(name="phone", length=100)
    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
    @Column(name="email", length=100)
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }




}


