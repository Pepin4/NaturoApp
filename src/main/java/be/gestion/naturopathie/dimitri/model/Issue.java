package be.gestion.naturopathie.dimitri.model;

import jakarta.persistence.*;


/**

        ██╗███████╗███████╗██╗   ██╗███████╗
        ██║██╔════╝██╔════╝██║   ██║██╔════╝
        ██║███████╗███████╗██║   ██║█████╗  
        ██║╚════██║╚════██║██║   ██║██╔══╝  
        ██║███████║███████║╚██████╔╝███████╗
        ╚═╝╚══════╝╚══════╝ ╚═════╝ ╚══════╝
                                                                                                                                                                                    
**/



/**
 * Représente un problème.
 * 
 * Cette entité est liée à la table `issue` en base de données.
 * Un problème possède un nom, une description, une origine et des contraintes éventuelles.
 */
@Entity
@Table(name = "issue")
public class Issue {

    /**
     * Identifiant unique du problème (clé primaire auto-incrémentée).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "i_id")
    private int issueId;

    /**
     * Nom du problème.
     * Ce champ est obligatoire et doit avoir une longueur maximale de 256 caractères.
     */
    @Column(name = "i_name", nullable = false, length = 256)
    private String name;

    /**
     * Origine du problème.
     * Ce champ est optionnel et peut contenir jusqu'à 512 caractères.
     * Il décrit la cause ou la source du problème.
     */
    @Column(name = "i_origin", length = 512)
    private String origin;

    /**
     * Contraintes associées au problème.
     * Ce champ est optionnel et peut contenir jusqu'à 1024 caractères.
     * Il peut s'agir de limitations techniques, de dépendances, etc.
     */
    @Column(name = "i_constraint", length = 1024)
    private String constraint;

    /**
     * Description détaillée du problème.
     * Ce champ est obligatoire et peut contenir jusqu'à 1024 caractères.
     */
    @Column(name = "i_description", nullable = false, length = 1024)
    private String description;

    
/**

         ██████╗ ██████╗ ███╗   ██╗███████╗████████╗██████╗ ██╗   ██╗ ██████╗████████╗███████╗██╗   ██╗██████╗ ███████╗
        ██╔════╝██╔═══██╗████╗  ██║██╔════╝╚══██╔══╝██╔══██╗██║   ██║██╔════╝╚══██╔══╝██╔════╝██║   ██║██╔══██╗██╔════╝
        ██║     ██║   ██║██╔██╗ ██║███████╗   ██║   ██████╔╝██║   ██║██║        ██║   █████╗  ██║   ██║██████╔╝███████╗
        ██║     ██║   ██║██║╚██╗██║╚════██║   ██║   ██╔══██╗██║   ██║██║        ██║   ██╔══╝  ██║   ██║██╔══██╗╚════██║
        ╚██████╗╚██████╔╝██║ ╚████║███████║   ██║   ██║  ██║╚██████╔╝╚██████╗   ██║   ███████╗╚██████╔╝██║  ██║███████║
         ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝  ╚═════╝   ╚═╝   ╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝
                                                                                                                 
**/

    /** Constructeur par défaut */
    public Issue() {
    }

    /**
     * Constructeur pour créer un problème avec un nom et une description.
     *
     * @param name Le nom du problème.
     * @param description La description du problème.
     */
    public Issue(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Constructeur pour créer un problème complet avec nom, origine, contrainte et description.
     *
     * @param name Le nom du problème.
     * @param origin L'origine du problème.
     * @param constraint Les contraintes associées au problème.
     * @param description La description du problème.
     */
    public Issue(String name, String origin, String constraint, String description) {
        this.name = name;
        this.origin = origin;
        this.constraint = constraint;
        this.description = description;
    }


/**

         ██████╗ ███████╗████████╗████████╗███████╗██████╗ ███████╗       ██╗       ███████╗███████╗████████╗████████╗███████╗██████╗ ███████╗
        ██╔════╝ ██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝       ██║       ██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
        ██║  ███╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗    ████████╗    ███████╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗
        ██║   ██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║    ██╔═██╔═╝    ╚════██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║
        ╚██████╔╝███████╗   ██║      ██║   ███████╗██║  ██║███████║    ██████║      ███████║███████╗   ██║      ██║   ███████╗██║  ██║███████║
         ╚═════╝ ╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝    ╚═════╝      ╚══════╝╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                       
**/
    
     

	public int getIssueId() {
		return issueId;
	}

	public void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
