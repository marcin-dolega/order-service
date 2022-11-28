package pl.dolega.orderservice.order.orderLine;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import pl.dolega.orderservice.BaseEntity;
import pl.dolega.orderservice.order.orderHeader.OrderHeader;

import java.util.Objects;

@Data
@Entity
public class OrderLine extends BaseEntity {

    private Integer quantityOrdered;
    @ManyToOne
    private OrderHeader orderHeader;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderLine orderLine = (OrderLine) o;
        return Objects.equals(quantityOrdered, orderLine.quantityOrdered) && Objects.equals(orderHeader, orderLine.orderHeader);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantityOrdered, orderHeader);
    }
}
