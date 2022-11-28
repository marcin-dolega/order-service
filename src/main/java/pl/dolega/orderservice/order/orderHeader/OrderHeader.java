package pl.dolega.orderservice.order.orderHeader;

import jakarta.persistence.*;
import lombok.Data;
import pl.dolega.orderservice.BaseEntity;
import pl.dolega.orderservice.order.address.Address;
import pl.dolega.orderservice.order.orderLine.OrderLine;
import pl.dolega.orderservice.order.orderStatus.OrderStatus;

import java.util.Objects;
import java.util.Set;


@Data
@AttributeOverride(name = "shippingAddress.address", column = @Column(name = "shipping_address"))
@AttributeOverride(name = "shippingAddress.city", column = @Column(name = "shipping_city"))
@AttributeOverride(name = "shippingAddress.state", column = @Column(name = "shipping_state"))
@AttributeOverride(name = "shippingAddress.zipCode", column = @Column(name = "shipping_zipCode"))
@AttributeOverride(name = "billToAddress.address", column = @Column(name = "bill_to_address"))
@AttributeOverride(name = "billToAddress.city", column = @Column(name = "bill_to_city"))
@AttributeOverride(name = "billToAddress.state", column = @Column(name = "bill_to_state"))
@AttributeOverride(name = "billToAddress.zipCode", column = @Column(name = "bill_to_zipCode"))
@Entity
public class OrderHeader extends BaseEntity {

    private String customer;
    private Address shippingAddress;
    private Address billToAddress;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orderHeader")
    private Set<OrderLine> orderLines;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderHeader that = (OrderHeader) o;
        return Objects.equals(customer, that.customer) && Objects.equals(shippingAddress, that.shippingAddress) && Objects.equals(billToAddress, that.billToAddress) && orderStatus == that.orderStatus && Objects.equals(orderLines, that.orderLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), customer, shippingAddress, billToAddress, orderStatus, orderLines);
    }
}
