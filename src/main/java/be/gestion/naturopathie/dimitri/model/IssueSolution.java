package be.gestion.naturopathie.dimitri.model;

import jakarta.persistence.*;


/**

        ██╗███████╗███████╗██╗   ██╗███████╗███████╗ ██████╗ ██╗     ██╗   ██╗████████╗██╗ ██████╗ ███╗   ██╗
        ██║██╔════╝██╔════╝██║   ██║██╔════╝██╔════╝██╔═══██╗██║     ██║   ██║╚══██╔══╝██║██╔═══██╗████╗  ██║
        ██║███████╗███████╗██║   ██║█████╗  ███████╗██║   ██║██║     ██║   ██║   ██║   ██║██║   ██║██╔██╗ ██║
        ██║╚════██║╚════██║██║   ██║██╔══╝  ╚════██║██║   ██║██║     ██║   ██║   ██║   ██║██║   ██║██║╚██╗██║
        ██║███████║███████║╚██████╔╝███████╗███████║╚██████╔╝███████╗╚██████╔╝   ██║   ██║╚██████╔╝██║ ╚████║
        ╚═╝╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚══════╝ ╚═════╝ ╚══════╝ ╚═════╝    ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝
                                                                                                                                                                                                                                                   
**/



/**
 * Représente une association entre un problème et une solution.
 * 
 * Cette entité est liée à la table `issue_solution` en base de données.
 * Elle permet d'établir un lien (possiblement multiple) entre les problèmes rencontrés et les solutions proposées.
 */
@Entity
@Table(name = "issue_solution")
public class IssueSolution {

    /**
     * Identifiant unique de l'association problème-solution (clé primaire auto-incrémentée).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "is_id")
    private int issueSolutionId;

    /**
     * Le problème associé.
     * Ce champ est obligatoire et représente le lien vers l'entité Issue.
     */
    @ManyToOne
    @JoinColumn(name = "fk_issue", nullable = false)
    private Issue issue;

    /**
     * La solution proposée pour le problème.
     * Ce champ est optionnel. Représente le lien vers l'entité Solution.
     */
    @ManyToOne
    @JoinColumn(name = "fk_solution")
    private Solution solution;

    
/**

         ██████╗ ██████╗ ███╗   ██╗███████╗████████╗██████╗ ██╗   ██╗ ██████╗████████╗███████╗██╗   ██╗██████╗ ███████╗
        ██╔════╝██╔═══██╗████╗  ██║██╔════╝╚══██╔══╝██╔══██╗██║   ██║██╔════╝╚══██╔══╝██╔════╝██║   ██║██╔══██╗██╔════╝
        ██║     ██║   ██║██╔██╗ ██║███████╗   ██║   ██████╔╝██║   ██║██║        ██║   █████╗  ██║   ██║██████╔╝███████╗
        ██║     ██║   ██║██║╚██╗██║╚════██║   ██║   ██╔══██╗██║   ██║██║        ██║   ██╔══╝  ██║   ██║██╔══██╗╚════██║
        ╚██████╗╚██████╔╝██║ ╚████║███████║   ██║   ██║  ██║╚██████╔╝╚██████╗   ██║   ███████╗╚██████╔╝██║  ██║███████║
         ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝  ╚═════╝   ╚═╝   ╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝
                                                                                                                     
**/

    /** Constructeur par défaut */
    public IssueSolution() {
    }

    /**
     * Constructeur pour créer une instance avec uniquement un problème.
     *
     * @param issue Le problème à associer.
     */
    public IssueSolution(Issue issue) {
        this.issue = issue;
    }

    /**
     * Constructeur pour créer une instance avec un problème et une solution.
     *
     * @param issue Le problème à associer.
     * @param solution La solution liée au problème.
     */
    public IssueSolution(Issue issue, Solution solution) {
        this.issue = issue;
        this.solution = solution;
    }

    
/**

         ██████╗ ███████╗████████╗████████╗███████╗██████╗ ███████╗       ██╗       ███████╗███████╗████████╗████████╗███████╗██████╗ ███████╗
        ██╔════╝ ██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝       ██║       ██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
        ██║  ███╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗    ████████╗    ███████╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗
        ██║   ██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║    ██╔═██╔═╝    ╚════██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║
        ╚██████╔╝███████╗   ██║      ██║   ███████╗██║  ██║███████║    ██████║      ███████║███████╗   ██║      ██║   ███████╗██║  ██║███████║
         ╚═════╝ ╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝    ╚═════╝      ╚══════╝╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                       
**/
    
    

	public int getIssueSolutionId() {
		return issueSolutionId;
	}

	public void setIssueSolutionId(int issueSolutionId) {
		this.issueSolutionId = issueSolutionId;
	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

}
