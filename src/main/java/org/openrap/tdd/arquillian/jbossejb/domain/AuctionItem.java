package org.openrap.tdd.arquillian.jbossejb.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class AuctionItem {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @NotNull
    private String name;
    @Min(value = 1)
    private long minBidCents;
    
    public AuctionItem(String name,long minBidCents) {
        this.name = name;
        this.minBidCents = minBidCents;
    }
    
    
}
