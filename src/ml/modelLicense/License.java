package ml.modelLicense;
// Generated 01.07.2017 12:42:23 by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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

/**
 * License generated by hbm2java
 */
@Entity
@Table(name="license"
    ,catalog="crimeali_license"
)
public class License  implements java.io.Serializable {


     private Integer idLicense;
     private long license;
     private Date datecreated;
     private Date dateuse;
     private boolean created;
     private int countPc;
     private int includeUser;
     private Set comps = new HashSet(0);
     private Set users = new HashSet(0);

    public License() {
    }

	
    public License(long license, Date datecreated, Date dateuse, boolean created, int countPc, int includeUser) {
        this.license = license;
        this.datecreated = datecreated;
        this.dateuse = dateuse;
        this.created = created;
        this.countPc = countPc;
        this.includeUser = includeUser;
    }
    public License(long license, Date datecreated, Date dateuse, boolean created, int countPc, int includeUser, Set comps, Set users) {
       this.license = license;
       this.datecreated = datecreated;
       this.dateuse = dateuse;
       this.created = created;
       this.countPc = countPc;
       this.includeUser = includeUser;
       this.comps = comps;
       this.users = users;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id_license", unique=true, nullable=false)
    public Integer getIdLicense() {
        return this.idLicense;
    }
    
    public void setIdLicense(Integer idLicense) {
        this.idLicense = idLicense;
    }

    
    @Column(name="license", nullable=false)
    public long getLicense() {
        return this.license;
    }
    
    public void setLicense(long license) {
        this.license = license;
    }

    @Temporal(TemporalType.DATE)
    @Column(name="datecreated", nullable=false, length=10)
    public Date getDatecreated() {
        return this.datecreated;
    }
    
    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="dateuse", nullable=false, length=19)
    public Date getDateuse() {
        return this.dateuse;
    }
    
    public void setDateuse(Date dateuse) {
        this.dateuse = dateuse;
    }

    
    @Column(name="created", nullable=false)
    public boolean isCreated() {
        return this.created;
    }
    
    public void setCreated(boolean created) {
        this.created = created;
    }

    
    @Column(name="countPC", nullable=false)
    public int getCountPc() {
        return this.countPc;
    }
    
    public void setCountPc(int countPc) {
        this.countPc = countPc;
    }

    
    @Column(name="includeUser", nullable=false)
    public int getIncludeUser() {
        return this.includeUser;
    }
    
    public void setIncludeUser(int includeUser) {
        this.includeUser = includeUser;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="license")
    public Set getComps() {
        return this.comps;
    }
    
    public void setComps(Set comps) {
        this.comps = comps;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="license")
    public Set getUsers() {
        return this.users;
    }
    
    public void setUsers(Set users) {
        this.users = users;
    }




}


