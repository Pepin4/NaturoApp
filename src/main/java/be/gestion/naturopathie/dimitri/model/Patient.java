package be.gestion.naturopathie.dimitri.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**

        ██████╗  █████╗ ████████╗██╗███████╗███╗   ██╗████████╗
        ██╔══██╗██╔══██╗╚══██╔══╝██║██╔════╝████╗  ██║╚══██╔══╝
        ██████╔╝███████║   ██║   ██║█████╗  ██╔██╗ ██║   ██║   
        ██╔═══╝ ██╔══██║   ██║   ██║██╔══╝  ██║╚██╗██║   ██║   
        ██║     ██║  ██║   ██║   ██║███████╗██║ ╚████║   ██║   
        ╚═╝     ╚═╝  ╚═╝   ╚═╝   ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝   
                                                                                                                                                                                                    
**/



/**
 * Représente un patient.
 * 
 * Cette entité est liée à la table `patient` en base de données.
 * Un patient possède des informations personnelles, une adresse et un contact associés.
 */
@Entity
@Table(name = "patient")
public class Patient {

    /**
     * Identifiant unique du patient (clé primaire auto-incrémentée).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private int patientId;

    /**
     * Date de naissance du patient.
     * Champ obligatoire au format date (yyyy-MM-dd).
     */
    @Column(name = "p_date_of_birth", nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    /**
     * Sexe du patient.
     * Champ obligatoire avec une longueur maximale de 2 caractères (ex: M/F).
     */
    @Column(name = "p_gender", nullable = false, length = 2)
    private String gender;

    /**
     * Prénom du patient.
     * Champ obligatoire limité à 64 caractères.
     */
    @Column(name = "p_firstname", nullable = false, length = 64)
    private String firstName;

    /**
     * Nom de famille du patient.
     * Champ obligatoire limité à 64 caractères.
     */
    @Column(name = "p_name", nullable = false, length = 64)
    private String lastName;

    /**
     * Situation familiale du patient (ex: marié, célibataire, etc.).
     * Champ optionnel limité à 256 caractères.
     */
    @Column(name = "p_family_situation", length = 256)
    private String familySituation;

    /**
     * Situation professionnelle du patient (ex: employé, indépendant, etc.).
     * Champ optionnel limité à 128 caractères.
     */
    @Column(name = "p_professional_situation", length = 128)
    private String professionalSituation;

    /**
     * Adresse associée au patient.
     * Lien obligatoire vers l'entité Address.
     */
    @ManyToOne
    @JoinColumn(name = "fk_address", nullable = false)
    private Address address;

    /**
     * Coordonnées de contact du patient.
     * Lien obligatoire vers l'entité Contact.
     */
    @ManyToOne
    @JoinColumn(name = "fk_contact", nullable = false)
    private Contact contact;
    
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<MedicalInfo> medicalInfos = new ArrayList<>();

/**

         ██████╗ ██████╗ ███╗   ██╗███████╗████████╗██████╗ ██╗   ██╗ ██████╗████████╗███████╗██╗   ██╗██████╗ ███████╗
        ██╔════╝██╔═══██╗████╗  ██║██╔════╝╚══██╔══╝██╔══██╗██║   ██║██╔════╝╚══██╔══╝██╔════╝██║   ██║██╔══██╗██╔════╝
        ██║     ██║   ██║██╔██╗ ██║███████╗   ██║   ██████╔╝██║   ██║██║        ██║   █████╗  ██║   ██║██████╔╝███████╗
        ██║     ██║   ██║██║╚██╗██║╚════██║   ██║   ██╔══██╗██║   ██║██║        ██║   ██╔══╝  ██║   ██║██╔══██╗╚════██║
        ╚██████╗╚██████╔╝██║ ╚████║███████║   ██║   ██║  ██║╚██████╔╝╚██████╗   ██║   ███████╗╚██████╔╝██║  ██║███████║
         ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝  ╚═════╝   ╚═╝   ╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝

**/

    /** Constructeur par défaut */
    public Patient() {
    }

    /**
     * Constructeur minimal pour instancier un patient avec les champs essentiels.
     *
     * @param dateOfBirth Date de naissance
     * @param gender Sexe
     * @param firstName Prénom
     * @param lastName Nom
     * @param address Adresse
     * @param contact Coordonnées de contact
     */
    public Patient(Date dateOfBirth, String gender, String firstName, String lastName, Address address, Contact contact) {
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contact = contact;
    }

    /**
     * Constructeur complet pour instancier un patient avec toutes les informations disponibles.
     *
     * @param dateOfBirth Date de naissance
     * @param gender Sexe
     * @param firstName Prénom
     * @param lastName Nom
     * @param familySituation Situation familiale
     * @param professionalSituation Situation professionnelle
     * @param address Adresse
     * @param contact Coordonnées de contact
     */
    public Patient(Date dateOfBirth, String gender, String firstName, String lastName, String familySituation,
                   String professionalSituation, Address address, Contact contact) {
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.familySituation = familySituation;
        this.professionalSituation = professionalSituation;
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
    
    
    
	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFamilySituation() {
		return familySituation;
	}

	public void setFamilySituation(String familySituation) {
		this.familySituation = familySituation;
	}

	public String getProfessionalSituation() {
		return professionalSituation;
	}

	public void setProfessionalSituation(String professionalSituation) {
		this.professionalSituation = professionalSituation;
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
	
	public List<MedicalInfo> getMedicalInfos() {
		return medicalInfos;
	}

	public void setMedicalInfos(List<MedicalInfo> medicalInfos) {
		this.medicalInfos = medicalInfos;
	}

	public void addMedicalInfo(MedicalInfo medicalInfo) {
		medicalInfos.add(medicalInfo);
		medicalInfo.setPatient(this);
	}

	public void removeMedicalInfo(MedicalInfo medicalInfo) {
		medicalInfos.remove(medicalInfo);
		medicalInfo.setPatient(null);
	}
	
	@Override
	public String toString() {
		return "Patient [patientId=" + patientId + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender
				+ ", firstName=" + firstName + ", lastName=" + lastName + ", familySituation=" + familySituation
				+ ", professionalSituation=" + professionalSituation + ", address=" + address + ", contact=" + contact
				+ "]";
	}
}

