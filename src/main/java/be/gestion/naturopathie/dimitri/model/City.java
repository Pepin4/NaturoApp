package be.gestion.naturopathie.dimitri.model;

import jakarta.persistence.*;


/**

         ██████╗██╗████████╗██╗   ██╗
        ██╔════╝██║╚══██╔══╝╚██╗ ██╔╝
        ██║     ██║   ██║    ╚████╔╝ 
        ██║     ██║   ██║     ╚██╔╝  
        ╚██████╗██║   ██║      ██║   
         ╚═════╝╚═╝   ╚═╝      ╚═╝   
                                                                                                                                   
**/



/**
 * Représente une ville.
 * 
 * Cette entité est liée à la table `city` en base de données.
 * Une ville est identifiée par un code, un nom, et est associée à un pays.
 */
@Entity
@Table(name = "city")
public class City {

    /**
     * Identifiant unique de la ville (clé primaire auto-incrémentée).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_id")
    private int cityId;

    /**
     * Code de la ville (ex : code INSEE ou code administratif).
     * Ce champ est obligatoire et limité à 10 caractères.
     */
    @Column(name = "c_code", nullable = false, length = 10)
    private String cityCode;

    /**
     * Nom de la ville.
     * Ce champ est obligatoire et limité à 64 caractères.
     */
    @Column(name = "c_name", nullable = false, length = 64)
    private String cityName;

    /**
     * Pays auquel appartient cette ville.
     * Relation ManyToOne : une ville appartient à un pays,
     * mais un pays peut contenir plusieurs villes.
     */
    @ManyToOne
    @JoinColumn(name = "fk_country", nullable = false)
    private Country country;

    
/**

         ██████╗ ██████╗ ███╗   ██╗███████╗████████╗██████╗ ██╗   ██╗ ██████╗████████╗███████╗██╗   ██╗██████╗ ███████╗
        ██╔════╝██╔═══██╗████╗  ██║██╔════╝╚══██╔══╝██╔══██╗██║   ██║██╔════╝╚══██╔══╝██╔════╝██║   ██║██╔══██╗██╔════╝
        ██║     ██║   ██║██╔██╗ ██║███████╗   ██║   ██████╔╝██║   ██║██║        ██║   █████╗  ██║   ██║██████╔╝███████╗
        ██║     ██║   ██║██║╚██╗██║╚════██║   ██║   ██╔══██╗██║   ██║██║        ██║   ██╔══╝  ██║   ██║██╔══██╗╚════██║
        ╚██████╗╚██████╔╝██║ ╚████║███████║   ██║   ██║  ██║╚██████╔╝╚██████╗   ██║   ███████╗╚██████╔╝██║  ██║███████║
         ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝  ╚═════╝   ╚═╝   ╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝
                                                                                                                     
**/

    /** Constructeur par défaut */
    public City() {
    }

    /**
     * Constructeur avec paramètres pour créer une ville.
     * 
     * @param cityCode Code unique de la ville (obligatoire)
     * @param cityName Nom de la ville (obligatoire)
     * @param country Pays auquel appartient la ville (obligatoire)
     */
    public City(String cityCode, String cityName, Country country) {
        this.cityCode = cityCode;
        this.cityName = cityName;
        this.country = country;
    }

	
/**

         ██████╗ ███████╗████████╗████████╗███████╗██████╗ ███████╗       ██╗       ███████╗███████╗████████╗████████╗███████╗██████╗ ███████╗
        ██╔════╝ ██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝       ██║       ██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
        ██║  ███╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗    ████████╗    ███████╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗
        ██║   ██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║    ██╔═██╔═╝    ╚════██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║
        ╚██████╔╝███████╗   ██║      ██║   ███████╗██║  ██║███████║    ██████║      ███████║███████╗   ██║      ██║   ███████╗██║  ██║███████║
         ╚═════╝ ╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝    ╚═════╝      ╚══════╝╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                       
**/
    
    
    
    public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
