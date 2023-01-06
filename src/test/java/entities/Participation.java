package entities;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Indexed
@Entity
@Access(AccessType.FIELD)
public class Participation {

	@DocumentId
	@Id
	public Long id;

	@Field
	@Basic
	public String label;

	@ManyToOne
	@ContainedIn
	@IndexedEmbedded(
			prefix = "stagiaire.",
			includePaths = {
					"ressortissant.label",
					"entreprise.label"
			})
	public Stagiaire stagiaire;

	@ManyToOne
	public DossierFormation dossierFormation;

	@ContainedIn
	public Entreprise getEntreprise() {
		return stagiaire.entreprise;
	}

	@ContainedIn
	public Ressortissant getRessortissant() {
		return stagiaire.ressortissant;
	}

}
