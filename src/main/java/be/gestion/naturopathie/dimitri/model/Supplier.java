package be.gestion.naturopathie.dimitri.model;

import jakarta.persistence.*;


/**

        ███████╗██╗   ██╗██████╗ ██████╗ ██╗     ██╗███████╗██████╗ 
        ██╔════╝██║   ██║██╔══██╗██╔══██╗██║     ██║██╔════╝██╔══██╗
        ███████╗██║   ██║██████╔╝██████╔╝██║     ██║█████╗  ██████╔╝
        ╚════██║██║   ██║██╔═══╝ ██╔═══╝ ██║     ██║██╔══╝  ██╔══██╗
        ███████║╚██████╔╝██║     ██║     ███████╗██║███████╗██║  ██║
        ╚══════╝ ╚═════╝ ╚═╝     ╚═╝     ╚══════╝╚═╝╚══════╝╚═╝  ╚═╝
                                                                                                                                                                                                         
**/



/**
 * Représente un fournisseur.
 * 
 * Cette entité est liée à la table `supplier` en base de données.
 * Un fournisseur a un nom, une adresse et un contact associés.
 */
@Entity
@Table(name = "supplier")
public class Supplier {

	/**
     * Identifiant unique du fournisseur (clé primaire auto-incrémentée).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "su_id")
    private int supplierId;

    /**
     * Nom du fournisseur.
     * Ce champ est obligatoire et doit avoir une longueur maximale de 64 caractères.
     */
    @Column(name = "su_name", nullable = false, length = 64)
    private String supplierName;

    /**
     * L'adresse associée au fournisseur.
     * Lien obligatoire vers l'entité Address.
     */
    @ManyToOne
    @JoinColumn(name = "fk_address", nullable = false)
    private Address address;

    /**
     * Le contact associé au fournisseur.
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
    public Supplier() {
    }

    /**
     * Constructeur pour créer un fournisseur avec un nom, une adresse et un contact.
     *
     * @param supplierName Le nom du fournisseur
     * @param address L'adresse du fournisseur
     * @param contact Le contact du fournisseur
     */
    public Supplier(String supplierName, Address address, Contact contact) {
        this.supplierName = supplierName;
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
    
    
    
    public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
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

}
