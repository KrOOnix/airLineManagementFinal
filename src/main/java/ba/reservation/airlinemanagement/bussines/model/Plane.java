package ba.reservation.airlinemanagement.bussines.model;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
        @NamedQuery(name = "Plane.findByDestination", query = "SELECT p FROM Plane p WHERE p.destination = :destination"),
        @NamedQuery(name = "Plane.findByPrice", query = "SELECT p FROM Plane p WHERE p.price = :price")})
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
    @Column(name = "numberOfBeds")
    private int numberSeat;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "price")
    private BigDecimal price;
    @Basic(optional = false)
    @Column(name = "destination")
    private String destination;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPlane")
    private List<Reservation> reservationList;

    public Plane() {
    }

    public Plane(Integer id) {
        this.id = id;
    }

    public Plane(Integer id, String code, int numberSeat, BigDecimal price, String destination, List<Reservation> reservationList) {
        this.id = id;
        this.code = code;
        this.numberSeat = numberSeat;
        this.price = price;
        this.destination = destination;
        this.reservationList = reservationList;
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

    public BigDecimal getPrice() {
        return price;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @XmlTransient
    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
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
        return "Plane{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", numberSeat=" + numberSeat +
                ", price=" + price +
                ", destination='" + destination + '\'' +
                ", reservationList=" + reservationList +
                '}';
    }
}
