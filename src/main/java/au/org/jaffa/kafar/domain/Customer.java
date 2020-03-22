package au.org.jaffa.kafar.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Exclude
    private Long id;

    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Contact> contacts;

    @ManyToMany(mappedBy = "customers", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Role> roles;

    public void addContact(Contact contact) {
	if (contacts == null)
	    contacts = new HashSet<>();
	contact.setCustomer(this);
	contacts.add(contact);
    }
    
    public void removeContact(Contact contact) {
	if (contacts == null)
	    contacts = new HashSet<>();
	contact.setCustomer(null);
	contacts.remove(contact);
    }

    public void addRole(Role role) {
	if (roles == null)
	    roles = new HashSet<>();
	role.addCustomer(this);
	roles.add(role);
    }

}
