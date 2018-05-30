package hitaii.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Voyage entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "voyage", catalog = "usf3")
public class Voyage implements java.io.Serializable {

	// Fields

	private String id;
	private String voyage;
	private String vesselId;
	private String cutoffdate;
	private String eta;
	private String etd;
	private String terminal;
	private String active;
	private String note;

	// Constructors

	/** default constructor */
	public Voyage() {
	}

	/** minimal constructor */
	public Voyage(String id, String voyage) {
		this.id = id;
		this.voyage = voyage;
	}

	/** full constructor */
	public Voyage(String id, String voyage, String vesselId, String cutoffdate, String eta, String etd, String terminal, String active, String note) {
		this.id = id;
		this.voyage = voyage;
		this.vesselId = vesselId;
		this.cutoffdate = cutoffdate;
		this.eta = eta;
		this.etd = etd;
		this.terminal = terminal;
		this.active = active;
		this.note = note;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false, length = 36)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "voyage", nullable = false, length = 100)
	public String getVoyage() {
		return this.voyage;
	}

	public void setVoyage(String voyage) {
		this.voyage = voyage;
	}

	@Column(name = "vesselId", length = 36)
	public String getVesselId() {
		return this.vesselId;
	}

	public void setVesselId(String vesselId) {
		this.vesselId = vesselId;
	}

	@Column(name = "cutoffdate", length = 20)
	public String getCutoffdate() {
		return this.cutoffdate;
	}

	public void setCutoffdate(String cutoffdate) {
		this.cutoffdate = cutoffdate;
	}

	@Column(name = "eta", length = 20)
	public String getEta() {
		return this.eta;
	}

	public void setEta(String eta) {
		this.eta = eta;
	}

	@Column(name = "etd", length = 20)
	public String getEtd() {
		return this.etd;
	}

	public void setEtd(String etd) {
		this.etd = etd;
	}

	@Column(name = "terminal", length = 50)
	public String getTerminal() {
		return this.terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	@Column(name = "active", length = 20)
	public String getActive() {
		return this.active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	@Column(name = "note", length = 100)
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}