package org.openrap.tdd.arquillian.jbossejb.domain;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor

public class ItemBid {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @NotNull
    private AuctionItem item;
    @Min(1)
    private long amount;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    
    private Date dateOfBid;

    public ItemBid(AuctionItem item, long amount) {
        this.item = item;
        this.amount = amount;
    }
    
    @PrePersist
    public void setDateOfBid() {
        dateOfBid = null;
    }
    
}
