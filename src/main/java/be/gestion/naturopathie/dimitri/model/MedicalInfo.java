package be.gestion.naturopathie.dimitri.model;

import jakarta.persistence.*;
import java.util.Date;


/**

        ███╗   ███╗███████╗██████╗ ██╗ ██████╗ █████╗ ██╗     ██╗███╗   ██╗███████╗ ██████╗ 
        ████╗ ████║██╔════╝██╔══██╗██║██╔════╝██╔══██╗██║     ██║████╗  ██║██╔════╝██╔═══██╗
        ██╔████╔██║█████╗  ██║  ██║██║██║     ███████║██║     ██║██╔██╗ ██║█████╗  ██║   ██║
        ██║╚██╔╝██║██╔══╝  ██║  ██║██║██║     ██╔══██║██║     ██║██║╚██╗██║██╔══╝  ██║   ██║
        ██║ ╚═╝ ██║███████╗██████╔╝██║╚██████╗██║  ██║███████╗██║██║ ╚████║██║     ╚██████╔╝
        ╚═╝     ╚═╝╚══════╝╚═════╝ ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝╚═╝      ╚═════╝ 
                                                                                                                                                                                                                                  
**/



/**
 * Représente les informations médicales d’un patient.
 * 
 * Cette entité est liée à la table `medical_info` en base de données.
 * Elle contient des données telles que le type, la description, 
 * le traitement associé, la date d’ajout et le patient concerné.
 */
@Entity
@Table(name = "medical_info")
public class MedicalInfo {

    /**
     * Identifiant unique des informations médicales (clé primaire auto-incrémentée).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mi_id")
    private int medicalInfoId;

    /**
     * Type de l’information médicale (ex. : pathologie, allergie...).
     * Ce champ est obligatoire et limité à 64 caractères.
     */
    @Column(name = "mi_type", nullable = false, length = 64)
    private String type;

    /**
     * Description détaillée de l’information médicale.
     * Champ obligatoire, limité à 256 caractères.
     */
    @Column(name = "mi_description", nullable = false, length = 256)
    private String description;

    /**
     * Traitement associé à l’information médicale (optionnel).
     * Limité à 128 caractères.
     */
    @Column(name = "mi_treatment", length = 128)
    private String treatment;

    /**
     * Date d’ajout de l’information médicale.
     * Champ obligatoire, stocké en TIMESTAMP.
     */
    @Column(name = "mi_date_added", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAdded;

    /**
     * Patient concerné par cette information médicale.
     * Lien obligatoire vers l’entité Patient.
     */
    @ManyToOne
    @JoinColumn(name = "fk_patient", nullable = false)
    private Patient patient;


    /**

         ██████╗ ██████╗ ███╗   ██╗███████╗████████╗██████╗ ██╗   ██╗ ██████╗████████╗███████╗██╗   ██╗██████╗ ███████╗
        ██╔════╝██╔═══██╗████╗  ██║██╔════╝╚══██╔══╝██╔══██╗██║   ██║██╔════╝╚══██╔══╝██╔════╝██║   ██║██╔══██╗██╔════╝
        ██║     ██║   ██║██╔██╗ ██║███████╗   ██║   ██████╔╝██║   ██║██║        ██║   █████╗  ██║   ██║██████╔╝███████╗
        ██║     ██║   ██║██║╚██╗██║╚════██║   ██║   ██╔══██╗██║   ██║██║        ██║   ██╔══╝  ██║   ██║██╔══██╗╚════██║
        ╚██████╗╚██████╔╝██║ ╚████║███████║   ██║   ██║  ██║╚██████╔╝╚██████╗   ██║   ███████╗╚██████╔╝██║  ██║███████║
         ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝  ╚═════╝   ╚═╝   ╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝

    **/


    /** Constructeur par défaut */
    public MedicalInfo() {
    }

    /**
     * Constructeur sans traitement médical.
     *
     * @param type        Le type de l’information.
     * @param description La description de l’information.
     * @param dateAdded   La date d’ajout.
     * @param patient     Le patient concerné.
     */
    public MedicalInfo(String type, String description, Date dateAdded, Patient patient) {
        this.type = type;
        this.description = description;
        this.dateAdded = dateAdded;
        this.patient = patient;
    }

    /**
     * Constructeur avec traitement médical.
     *
     * @param type        Le type de l’information.
     * @param description La description.
     * @param treatment   Le traitement associé.
     * @param dateAdded   La date d’ajout.
     * @param patient     Le patient concerné.
     */
    public MedicalInfo(String type, String description, String treatment, Date dateAdded, Patient patient) {
        this.type = type;
        this.description = description;
        this.treatment = treatment;
        this.dateAdded = dateAdded;
        this.patient = patient;
    }


/**

         ██████╗ ███████╗████████╗████████╗███████╗██████╗ ███████╗       ██╗       ███████╗███████╗████████╗████████╗███████╗██████╗ ███████╗
        ██╔════╝ ██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝       ██║       ██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
        ██║  ███╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗    ████████╗    ███████╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗
        ██║   ██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║    ██╔═██╔═╝    ╚════██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║
        ╚██████╔╝███████╗   ██║      ██║   ███████╗██║  ██║███████║    ██████║      ███████║███████╗   ██║      ██║   ███████╗██║  ██║███████║
         ╚═════╝ ╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝    ╚═════╝      ╚══════╝╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                       
**/
    
    

	public int getMedicalInfoId() {
		return medicalInfoId;
	}

	public void setMedicalInfoId(int medicalInfoId) {
		this.medicalInfoId = medicalInfoId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTreatment() {
		return treatment;
	}

	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}

	public Date getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(Date dateAdded) {
		this.dateAdded = dateAdded;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}   
}
