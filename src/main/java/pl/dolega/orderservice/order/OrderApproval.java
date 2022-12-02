package pl.dolega.orderservice.order;

import jakarta.persistence.Entity;
import pl.dolega.orderservice.BaseEntity;

@Entity
public class OrderApproval extends BaseEntity {

    private String approvedBy;

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
