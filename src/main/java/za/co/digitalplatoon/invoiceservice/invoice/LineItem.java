package za.co.digitalplatoon.invoiceservice.invoice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Entity
public class LineItem
{
    //make mc not persistable or transferable over the wire
    transient MathContext mc = new MathContext(2, RoundingMode.HALF_UP);


    //using wrapper classes in-case of null DB values as always recommended by entity standards

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private Long quantity;

    private String description;

    private BigDecimal unitPrice;

    //-------------- getters and setters --------------------------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    //-----------methods to hold logic,I would not normally have this in an entity but keeping to the spec

    public BigDecimal getLineItemTotal()
    {
        if(quantity==null)//if quantity was null, set it to 0
            quantity=new Long(0);
        return unitPrice.multiply(new BigDecimal(quantity),mc);
    }

}
