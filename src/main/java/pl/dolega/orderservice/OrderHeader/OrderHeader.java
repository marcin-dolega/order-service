package pl.dolega.orderservice.OrderHeader;

import jakarta.persistence.Entity;
import lombok.Data;
import pl.dolega.orderservice.BaseEntity;

import java.util.Objects;

@Entity
@Data
public class OrderHeader extends BaseEntity {

    private String customerName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderHeader that = (OrderHeader) o;
        return Objects.equals(customerName, that.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customerName);
    }
}
