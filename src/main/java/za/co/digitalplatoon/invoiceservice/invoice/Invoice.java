package za.co.digitalplatoon.invoiceservice.invoice;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.*;

@Entity
public class Invoice
{

    //make mc not persistable or transferable over the wire
    transient MathContext mc = new MathContext(2, RoundingMode.HALF_UP);

    //using wrapper classes in-case of null DB values as always recommended by entity standards

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    private String client;

    private Long vateRate;

    private Date invoiceDate;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<LineItem> items = new ArrayList<LineItem>();

    //-------------getters and setters--------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Long getVateRate() {
        return vateRate;
    }

    public void setVateRate(Long vateRate) {
        this.vateRate = vateRate;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    //-----------methods to hold logic,I would not normally have this in an entity but keeping to the spec
//calculate subtotal without vat
    public BigDecimal getSubTotal()
    {
        BigDecimal total=new BigDecimal(0);
        for (LineItem lineItem:items)
        {
            total=total.add(new BigDecimal(lineItem.getLineItemTotal().toString()));
        }
        return total;
    }
//culculates vat based on vat rate
   public BigDecimal getVat()
   {

       BigDecimal vat = new BigDecimal(getSubTotal().toString());

       if(vateRate==null)//if vatRate was null, set it to 0
            vateRate=new Long(0);
        vat=vat.multiply(new BigDecimal(vateRate),mc);
        vat = vat.divide(new BigDecimal(100),mc);
        return vat;
    }
    //gets all sum of subtotal and vat
   public BigDecimal getTotal()
    {
        BigDecimal total=new BigDecimal(getSubTotal().toString());
        total=total.add(new BigDecimal(getVat().toString()),mc);
        return total;
    }

}
