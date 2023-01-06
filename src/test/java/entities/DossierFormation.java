package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Indexed
@Entity
@Access(AccessType.FIELD)
public class DossierFormation {

	@DocumentId
	@Id
	public Long id;

	@Field
	@Basic
	public String label;

	@OneToMany(mappedBy = "dossierFormation")
	@ContainedIn
	@IndexedEmbedded(
			prefix = "participation.",
			includePaths = {
					"stagiaire.ressortissant.label",
					"stagiaire.entreprise.label"
			})
	public Set<Participation> participations = new HashSet<>();

}
