package be.gestion.naturopathie.dimitri.model;

import jakarta.persistence.*;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;


/**

         █████╗ ██████╗ ██████╗  ██████╗ ██╗███╗   ██╗████████╗███╗   ███╗███████╗███╗   ██╗████████╗
        ██╔══██╗██╔══██╗██╔══██╗██╔═══██╗██║████╗  ██║╚══██╔══╝████╗ ████║██╔════╝████╗  ██║╚══██╔══╝
        ███████║██████╔╝██████╔╝██║   ██║██║██╔██╗ ██║   ██║   ██╔████╔██║█████╗  ██╔██╗ ██║   ██║   
        ██╔══██║██╔═══╝ ██╔═══╝ ██║   ██║██║██║╚██╗██║   ██║   ██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║   
        ██║  ██║██║     ██║     ╚██████╔╝██║██║ ╚████║   ██║   ██║ ╚═╝ ██║███████╗██║ ╚████║   ██║   
        ╚═╝  ╚═╝╚═╝     ╚═╝      ╚═════╝ ╚═╝╚═╝  ╚═══╝   ╚═╝   ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝   
                                                                                                                                          
**/



/**
 * Représente un rendez-vous entre un patient et un utilisateur (praticien).
 * 
 * Cette entité est liée à la table `appointment` en base de données.
 * Elle stocke des informations liées au rendez-vous ainsi que des observations
 * sur les habitudes de vie et la condition du patient.
 */
@Entity
@Table(name = "appointment")
public class Appointment {

    /**
     * Identifiant unique du rendez-vous (clé primaire auto-incrémentée).
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "a_id")
    private int appointmentId;

    /**
     * Date et heure du rendez-vous.
     * Ce champ est renseigné lors de la prise de rendez-vous.
     * Ce champ est obligatoire.
     */
    @Column(name = "a_date_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date dateTime;

    /**
     * Sujet principal abordé durant le rendez-vous.
     * Ce champ est renseigné lors de la prise de rendez-vous.
     * Ce champ est obligatoire et limité à 1024 caractères.
     */
    @Column(name = "a_subject", nullable = false, length = 1024)
    private String subject;

    /**
     * Informations sur les excès (alcool, sucre, etc.).
     */
    @Column(name = "a_the_excesses", length = 128)
    private String theExcesses;

    /**
     * Habitudes alimentaires du patient.
     */
    @Column(name = "a_eating_habits", length = 256)
    private String eatingHabits;

    /**
     * Qualité du sommeil du patient.
     */
    @Column(name = "a_sleep_quality", length = 128)
    private String sleepQuality;

    /**
     * Activité physique pratiquée par le patient.
     */
    @Column(name = "a_physical_activity", length = 128)
    private String physicalActivity;

    /**
     * Circulation sanguine du patient.
     */
    @Column(name = "a_blood_circulation", length = 128)
    private String bloodCirculation;

    /**
     * Capacité respiratoire du patient.
     */
    @Column(name = "a_respiratory_capacity", length = 128)
    private String respiratoryCapacity;

    /**
     * Transit intestinal du patient.
     */
    @Column(name = "a_transit", length = 128)
    private String transit;

    /**
     * Réaction du patient au stress.
     */
    @Column(name = "a_stress_reaction", length = 256)
    private String stressReaction;

    /**
     * Note ou résumé libre saisi par le praticien.
     */
    @Column(name = "a_note", length = 1024)
    private String note;

    /**
     * Patient concerné par ce rendez-vous.
     * Lien obligatoire vers l'entité Patient.
     */
    @ManyToOne
    @JoinColumn(name = "fk_patient", nullable = false)
    private Patient patient;

    /**
     * Utilisateur ayant effectué le rendez-vous (praticien).
     * Lien obligatoire vers l'entité User.
     */
    @ManyToOne
    @JoinColumn(name = "fk_user", nullable = false)
    private User user;

    /**

         ██████╗ ██████╗ ███╗   ██╗███████╗████████╗██████╗ ██╗   ██╗ ██████╗████████╗███████╗██╗   ██╗██████╗ ███████╗
        ██╔════╝██╔═══██╗████╗  ██║██╔════╝╚══██╔══╝██╔══██╗██║   ██║██╔════╝╚══██╔══╝██╔════╝██║   ██║██╔══██╗██╔════╝
        ██║     ██║   ██║██╔██╗ ██║███████╗   ██║   ██████╔╝██║   ██║██║        ██║   █████╗  ██║   ██║██████╔╝███████╗
        ██║     ██║   ██║██║╚██╗██║╚════██║   ██║   ██╔══██╗██║   ██║██║        ██║   ██╔══╝  ██║   ██║██╔══██╗╚════██║
        ╚██████╗╚██████╔╝██║ ╚████║███████║   ██║   ██║  ██║╚██████╔╝╚██████╗   ██║   ███████╗╚██████╔╝██║  ██║███████║
         ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝  ╚═════╝   ╚═╝   ╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝
                                                                                                                    
    **/

    /** Constructeur par défaut */
    public Appointment() {
    }

    /**
     * Constructeur minimal pour créer un rendez-vous avec les champs essentiels.
     *
     * @param dateTime Date et heure du rendez-vous
     * @param subject Sujet du rendez-vous
     * @param patient Patient concerné
     * @param user Utilisateur (praticien)
     */
    public Appointment(Date dateTime, String subject, Patient patient, User user) {
        super();
        this.dateTime = dateTime;
        this.subject = subject;
        this.patient = patient;
        this.user = user;
    }

    /**
     * Constructeur utilisé pour mettre à jour les informations d'un rendez-vous
     * sans modifier les liens vers le patient et l'utilisateur associés.
     *
     * Ce constructeur initialise uniquement les champs liés aux détails
     * du rendez-vous (date, sujet, habitudes, observations...).
     * 
     * Il est utile lors d'une mise à jour partielle de l'entité,
     * où les relations patient et utilisateur doivent rester inchangées.
     *
     * @param dateTime            Date et heure du rendez-vous
     * @param subject             Sujet principal abordé lors du rendez-vous
     * @param theExcesses         Informations sur les excès (alcool, sucre, etc.)
     * @param eatingHabits        Habitudes alimentaires du patient
     * @param sleepQuality        Qualité du sommeil du patient
     * @param physicalActivity    Activité physique pratiquée par le patient
     * @param bloodCirculation    Circulation sanguine du patient
     * @param respiratoryCapacity Capacité respiratoire du patient
     * @param transit             Transit intestinal du patient
     * @param stressReaction      Réaction du patient face au stress
     * @param note                Note ou remarques libres ajoutées par le praticien
     */
    public Appointment(Date dateTime, String subject, String theExcesses, String eatingHabits, String sleepQuality,
                       String physicalActivity, String bloodCirculation, String respiratoryCapacity, String transit,
                       String stressReaction, String note) {
        super();
        this.dateTime = dateTime;
        this.subject = subject;
        this.theExcesses = theExcesses;
        this.eatingHabits = eatingHabits;
        this.sleepQuality = sleepQuality;
        this.physicalActivity = physicalActivity;
        this.bloodCirculation = bloodCirculation;
        this.respiratoryCapacity = respiratoryCapacity;
        this.transit = transit;
        this.stressReaction = stressReaction;
        this.note = note;
    }

    /**
     * Constructeur complet pour créer un rendez-vous avec toutes les informations disponibles.
     *
     * @param dateTime Date et heure du rendez-vous
     * @param subject Sujet du rendez-vous
     * @param theExcesses Informations sur les excès
     * @param eatingHabits Habitudes alimentaires
     * @param sleepQuality Qualité du sommeil
     * @param physicalActivity Activité physique
     * @param bloodCirculation Circulation sanguine
     * @param respiratoryCapacity Capacité respiratoire
     * @param transit Transit intestinal
     * @param stressReaction Réaction au stress
     * @param note Remarques libres
     * @param patient Patient concerné
     * @param user Utilisateur (praticien)
     */
    public Appointment(Date dateTime, String subject, String theExcesses, String eatingHabits, String sleepQuality,
                       String physicalActivity, String bloodCirculation, String respiratoryCapacity, String transit,
                       String stressReaction, String note, Patient patient, User user) {
        super();
        this.dateTime = dateTime;
        this.subject = subject;
        this.theExcesses = theExcesses;
        this.eatingHabits = eatingHabits;
        this.sleepQuality = sleepQuality;
        this.physicalActivity = physicalActivity;
        this.bloodCirculation = bloodCirculation;
        this.respiratoryCapacity = respiratoryCapacity;
        this.transit = transit;
        this.stressReaction = stressReaction;
        this.note = note;
        this.patient = patient;
        this.user = user;
    }

    
/**

         ██████╗ ███████╗████████╗████████╗███████╗██████╗ ███████╗       ██╗       ███████╗███████╗████████╗████████╗███████╗██████╗ ███████╗
        ██╔════╝ ██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝       ██║       ██╔════╝██╔════╝╚══██╔══╝╚══██╔══╝██╔════╝██╔══██╗██╔════╝
        ██║  ███╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗    ████████╗    ███████╗█████╗     ██║      ██║   █████╗  ██████╔╝███████╗
        ██║   ██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║    ██╔═██╔═╝    ╚════██║██╔══╝     ██║      ██║   ██╔══╝  ██╔══██╗╚════██║
        ╚██████╔╝███████╗   ██║      ██║   ███████╗██║  ██║███████║    ██████║      ███████║███████╗   ██║      ██║   ███████╗██║  ██║███████║
         ╚═════╝ ╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝    ╚═════╝      ╚══════╝╚══════╝   ╚═╝      ╚═╝   ╚══════╝╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                       
**/
    
    
    
	public int getAppointmentId() {
		return appointmentId;
	}
	
    public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getTheExcesses() {
		return theExcesses;
	}


	public void setTheExcesses(String theExcesses) {
		this.theExcesses = theExcesses;
	}


	public String getEatingHabits() {
		return eatingHabits;
	}


	public void setEatingHabits(String eatingHabits) {
		this.eatingHabits = eatingHabits;
	}


	public String getSleepQuality() {
		return sleepQuality;
	}


	public void setSleepQuality(String sleepQuality) {
		this.sleepQuality = sleepQuality;
	}


	public String getPhysicalActivity() {
		return physicalActivity;
	}


	public void setPhysicalActivity(String physicalActivity) {
		this.physicalActivity = physicalActivity;
	}


	public String getBloodCirculation() {
		return bloodCirculation;
	}


	public void setBloodCirculation(String bloodCirculation) {
		this.bloodCirculation = bloodCirculation;
	}


	public String getRespiratoryCapacity() {
		return respiratoryCapacity;
	}


	public void setRespiratoryCapacity(String respiratoryCapacity) {
		this.respiratoryCapacity = respiratoryCapacity;
	}


	public String getTransit() {
		return transit;
	}


	public void setTransit(String transit) {
		this.transit = transit;
	}


	public String getStressReaction() {
		return stressReaction;
	}


	public void setStressReaction(String stressReaction) {
		this.stressReaction = stressReaction;
	}


	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}


	public Patient getPatient() {
		return patient;
	}


	public void setPatient(Patient patient) {
		this.patient = patient;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	
	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", dateTime=" + dateTime + ", subject=" + subject
				+ ", theExcesses=" + theExcesses + ", eatingHabits=" + eatingHabits + ", sleepQuality=" + sleepQuality
				+ ", physicalActivity=" + physicalActivity + ", bloodCirculation=" + bloodCirculation
				+ ", respiratoryCapacity=" + respiratoryCapacity + ", transit=" + transit + ", stressReaction="
				+ stressReaction + ", note=" + note + ", patient=" + patient + ", user=" + user + "]";
	}


}
