package au.org.jaffa.kafar.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;
    
    private String number;

    @Enumerated(EnumType.STRING)
    private ContactType contactType;
    
    @ManyToOne
    @JoinColumn(name = "cust_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Customer customer;

}
