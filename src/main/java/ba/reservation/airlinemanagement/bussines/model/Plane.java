package ba.reservation.airlinemanagement.bussines.model;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "plane")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Plane.findAll", query = "SELECT p FROM Plane p"),
        @NamedQuery(name = "Plane.findById", query = "SELECT p FROM Plane p WHERE p.id = :id"),
        @NamedQuery(name = "Plane.findByCode", query = "SELECT p FROM Plane p WHERE p.code = :code"),
        @NamedQuery(name = "Plane.findByNumberSeat", query = "SELECT p FROM Plane p WHERE p.numberSeat = :numberSeat"),
        @NamedQuery(name = "Plane.findByNamePlane", query = "SELECT p FROM Plane p WHERE p.namePlane = :namePlane")})

public class Plane implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @Column(name = "numberSeat")
    private int numberSeat;

    @Basic(optional = false)
    @Column(name = "namePlane")
    private String namePlane;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlane")
    private List<Reservation> reservationList;


    public Plane() {
    }

    public Plane(Integer id) {
        this.id = id;
    }

    public Plane(Integer id, String code, int numberSeat, String namePlane) {
        this.id = id;
        this.code = code;
        this.numberSeat = numberSeat;
        this.namePlane = namePlane;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getNumberSeat() {
        return numberSeat;
    }

    public void setNumberSeat(int numberSeat) {
        this.numberSeat = numberSeat;
    }


    public String getNamePlane() {
        return namePlane;
    }

    public void setNamePlane(String namePlane) {
        this.namePlane = namePlane;
    }

    @XmlTransient
    public List<Reservation> getReservationList() {
        return reservationList;
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Plane)) {
            return false;
        }
        Plane other = (Plane) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return namePlane + " " + code;
    }
}

