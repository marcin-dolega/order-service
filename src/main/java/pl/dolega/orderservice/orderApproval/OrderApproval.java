package pl.dolega.orderservice.orderApproval;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import pl.dolega.orderservice.BaseEntity;
import pl.dolega.orderservice.orderHeader.OrderHeader;

@Entity
public class OrderApproval extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "order_header_id")
    private OrderHeader orderHeader;

    private String approvedBy;

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }
}
