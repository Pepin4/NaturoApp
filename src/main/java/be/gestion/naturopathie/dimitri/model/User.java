package be.gestion.naturopathie.dimitri.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.*;
import java.util.Collection;
import java.util.Collections;


/**

        ██╗   ██╗███████╗███████╗██████╗ 
        ██║   ██║██╔════╝██╔════╝██╔══██╗
        ██║   ██║███████╗█████╗  ██████╔╝
        ██║   ██║╚════██║██╔══╝  ██╔══██╗
        ╚██████╔╝███████║███████╗██║  ██║
         ╚═════╝ ╚══════╝╚══════╝╚═╝  ╚═╝
                                                                                                                                                                                
**/



/**
 * Représente un utilisateur.
 * 
 * Cette entité est liée à la table `user` en base de données.
 * Chaque utilisateur possède un identifiant, un nom, un mot de passe, une adresse et un contact.
 * Implémente l'interface `UserDetails` pour l'intégration avec Spring Security.
 */
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "u_name"))
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;

    /**
     * Identifiant unique de l'utilisateur (clé primaire auto-incrémentée).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private int userId;

    /**
     * Nom d'utilisateur (identifiant de connexion).
     * Ce champ est unique, obligatoire et limité à 64 caractères.
     */
    @Column(name = "u_name", nullable = false, length = 64)
    private String userName;

    /**
     * Mot de passe de l'utilisateur.
     * Ce champ est obligatoire et limité à 64 caractères.
     */
    @Column(name = "u_password", nullable = false, length = 64)
    private String userPassword;

    /**
     * Adresse associée à l'utilisateur.
     * Lien obligatoire vers l'entité Address.
     */
    @ManyToOne
    @JoinColumn(name = "fk_address", nullable = false)
    private Address address;

    /**
     * Contact associé à l'utilisateur.
     * Lien obligatoire vers l'entité Contact.
     */
    @ManyToOne
    @JoinColumn(name = "fk_contact", nullable = false)
    private Contact contact;

    
/**

         ██████╗ ██████╗ ███╗   ██╗███████╗████████╗██████╗ ██╗   ██╗ ██████╗████████╗███████╗██╗   ██╗██████╗ ███████╗
        ██╔════╝██╔═══██╗████╗  ██║██╔════╝╚══██╔══╝██╔══██╗██║   ██║██╔════╝╚══██╔══╝██╔════╝██║   ██║██╔══██╗██╔════╝
        ██║     ██║   ██║██╔██╗ ██║███████╗   ██║   ██████╔╝██║   ██║██║        ██║   █████╗  ██║   ██║██████╔╝███████╗
        ██║     ██║   ██║██║╚██╗██║╚════██║   ██║   ██╔══██╗██║   ██║██║        ██║   ██╔══╝  ██║   ██║██╔══██╗╚════██║
        ╚██████╗╚██████╔╝██║ ╚████║███████║   ██║   ██║  ██║╚██████╔╝╚██████╗   ██║   ███████╗╚██████╔╝██║  ██║███████║
         ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝  ╚═════╝   ╚═╝   ╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝
                                                                                                                        
**/

    /** Constructeur par défaut */
    public User() {}

    /**
     * Constructeur pour créer un utilisateur avec un nom, un mot de passe, une adresse et un contact.
     *
     * @param userName     Nom d'utilisateur
     * @param userPassword Mot de passe
     * @param address      Adresse associée
     * @param contact      Contact associé
     */
    public User(String userName, String userPassword, Address address, Contact contact) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.address = address;
        this.contact = contact;
    }

    
/**

         ██████╗ ███████╗████████╗████████╗███████╗██████╗ ███████╗       ██╗       ███████╗███████╗████████╗████████╗███████╗██████╗ ███████╗
        ██╔════╝ ██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝       ██║       ██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
        ██║  ███╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗    ████████╗    ███████╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗
        ██║   ██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║    ██╔═██╔═╝    ╚════██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║
        ╚██████╔╝███████╗   ██║      ██║   ███████╗██║  ██║███████║    ██████║      ███████║███████╗   ██║      ██║   ███████╗██║  ██║███████║
         ╚═════╝ ╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝    ╚═════╝      ╚══════╝╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                       
**/
    
    
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword +
               ", address=" + address + ", contact=" + contact + "]";
    }

    // Héritage springframework.security

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
