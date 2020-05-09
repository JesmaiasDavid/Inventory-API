package inventoryservice.domain.admin;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int supplierId;

    private String supplierName;
    private String supplierCompany;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDateTime;

    private String createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDateTime;

    private String lastModifiedUser;

    //bi-directional many-to-one association to Stock
    @OneToMany(mappedBy="supplier")
    private List<Stock> stocks;

    Supplier() {
    }

    Supplier(Builder builder) {
        this.supplierId = builder.supplierId;
        this.supplierName = builder.supplierName;
        this.supplierCompany = builder.supplierCompany;
        this.createdDateTime = builder.createdDateTime;
        this.createdUser = builder.createdUser;
        this.lastModifiedDateTime = builder.lastModifiedDateTime;
        this.lastModifiedUser = builder.lastModifiedUser;
    }


    public int getSupplierId() {
        return supplierId;
    }

    public String getSupplierName() {
        return this.supplierName;
    }

    public String getSupplierCompany() {
        return this.supplierCompany;
    }


    public Date getCreatedDateTime() {
        return createdDateTime;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public Date getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    @PreUpdate
    public void preUpdate() {
        lastModifiedDateTime = new Date();
    }


    @PrePersist
    public void prePersist() {
        Date now = new Date();
        lastModifiedDateTime = now;
        createdDateTime=now;
    }

    public static class Builder {

        private int supplierId;
        private String supplierName;
        private String supplierCompany;

        private Date createdDateTime;

        private String createdUser;

        private Date lastModifiedDateTime;

        private String lastModifiedUser;

        public Builder supplierId(int supplierId) {
            this.supplierId = supplierId;
            return this;
        }

        public Builder supplierName(String supplierName) {
            this.supplierName = supplierName;
            return this;
        }

        public Builder supplierCompany(String supplierCompany) {
            this.supplierCompany = supplierCompany;
            return this;
        }

        public Builder createdDateTime(Date createdDateTime) {
            this.createdDateTime = createdDateTime;
            return this;
        }

        public Builder createdUser(String createdUser) {
            this.createdUser = createdUser;
            return this;
        }

        public Builder lastModifiedDateTime(Date lastModifiedDateTime) {
            this.lastModifiedDateTime = lastModifiedDateTime;
            return this;
        }

        public Builder lastModifiedUser(String lastModifiedUser) {
            this.lastModifiedUser = lastModifiedUser;
            return this;
        }

        public Supplier build() {
            return new Supplier(this);
        }
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", supplierCompany='" + supplierCompany + '\'' +
                ", createdDateTime=" + createdDateTime +
                ", createdUser='" + createdUser + '\'' +
                ", lastModifiedDateTime=" + lastModifiedDateTime +
                ", lastModifiedUser='" + lastModifiedUser + '\'' +
                '}';
    }
}
