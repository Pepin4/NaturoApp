package be.gestion.naturopathie.dimitri.model;

import jakarta.persistence.*;


/**

         █████╗ ██████╗ ██████╗ ██████╗ ███████╗███████╗███████╗
        ██╔══██╗██╔══██╗██╔══██╗██╔══██╗██╔════╝██╔════╝██╔════╝
        ███████║██║  ██║██║  ██║██████╔╝█████╗  ███████╗███████╗
        ██╔══██║██║  ██║██║  ██║██╔══██╗██╔══╝  ╚════██║╚════██║
        ██║  ██║██████╔╝██████╔╝██║  ██║███████╗███████║███████║
        ╚═╝  ╚═╝╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝
                                                                                                                                     
**/



/**
 * Représente une adresse postale.
 * 
 * Cette entité est liée à la table `address` en base de données.
 * Chaque adresse possède un identifiant unique, un numéro, une rue et une référence à une ville.
 */
@Entity
@Table(name = "address")
public class Address {

    /**
     * Identifiant unique de l'adresse (clé primaire auto-incrémentée).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ad_id")
    private int id;

    /**
     * Numéro de l'adresse (ex: "12", "45B", etc.).
     * Ce champ est obligatoire et limité à 5 caractères.
     * Par exemple, il pourrait contenir un numéro de maison ou un numéro d'appartement.
     */
    @Column(name = "ad_number", nullable = false, length = 5)
    private String number;

    /**
     * Nom de la rue de l'adresse.
     * Ce champ est obligatoire et limité à 128 caractères.
     * Par exemple : "Avenue des Champs Elysées", "Rue de la Paix", etc.
     */
    @Column(name = "ad_street", nullable = false, length = 128)
    private String street;

    /**
     * Ville associée à cette adresse.
     * Cette relation ManyToOne indique qu'une adresse appartient à une seule ville,
     * mais qu'une ville peut être liée à plusieurs adresses.
     */
    @ManyToOne
    @JoinColumn(name = "fk_city", nullable = false)
    private City city;

    
/**

         ██████╗ ██████╗ ███╗   ██╗███████╗████████╗██████╗ ██╗   ██╗ ██████╗████████╗███████╗██╗   ██╗██████╗ ███████╗
        ██╔════╝██╔═══██╗████╗  ██║██╔════╝╚══██╔══╝██╔══██╗██║   ██║██╔════╝╚══██╔══╝██╔════╝██║   ██║██╔══██╗██╔════╝
        ██║     ██║   ██║██╔██╗ ██║███████╗   ██║   ██████╔╝██║   ██║██║        ██║   █████╗  ██║   ██║██████╔╝███████╗
        ██║     ██║   ██║██║╚██╗██║╚════██║   ██║   ██╔══██╗██║   ██║██║        ██║   ██╔══╝  ██║   ██║██╔══██╗╚════██║
        ╚██████╗╚██████╔╝██║ ╚████║███████║   ██║   ██║  ██║╚██████╔╝╚██████╗   ██║   ███████╗╚██████╔╝██║  ██║███████║
         ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝  ╚═════╝   ╚═╝   ╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝
                                                                                                                     
**/


    
    /** Constructeur par défaut */
    public Address() {
    }

    /**
     * Constructeur avec paramètres pour créer une adresse complète.
     * 
     * @param number Numéro de l'adresse (obligatoire)
     * @param street Nom de la rue (obligatoire)
     * @param city Ville associée (obligatoire)
     */
    public Address(String number, String street, City city) {
        this.number = number;
        this.street = street;
        this.city = city;
    }

    
/**

         ██████╗ ███████╗████████╗████████╗███████╗██████╗ ███████╗       ██╗       ███████╗███████╗████████╗████████╗███████╗██████╗ ███████╗
        ██╔════╝ ██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝       ██║       ██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
        ██║  ███╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗    ████████╗    ███████╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗
        ██║   ██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║    ██╔═██╔═╝    ╚════██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║
        ╚██████╔╝███████╗   ██║      ██║   ███████╗██║  ██║███████║    ██████║      ███████║███████╗   ██║      ██║   ███████╗██║  ██║███████║
         ╚═════╝ ╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝    ╚═════╝      ╚══════╝╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                       
**/
    
    
    
    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}
}
