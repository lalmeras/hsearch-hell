package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

@Indexed
@Entity
@Access(AccessType.FIELD)
public class Stagiaire {

	@DocumentId
	@Id
	public Long id;

	@Field
	@Basic
	public String label;

	@ManyToOne
	@ContainedIn
	@IndexedEmbedded(
			prefix = "ressortissant.",
			includePaths = {"label"})
	public Ressortissant ressortissant;

	@ManyToOne
	@ContainedIn
	@IndexedEmbedded(
			prefix = "entreprise.",
			includePaths = {"label"})
	public Entreprise entreprise;

	@OneToMany(mappedBy = "stagiaire")
	@ContainedIn
	public Set<Participation> participations = new HashSet<>();

}
