package org.vault.domain.embedded;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.vault.base.domain.Describeable;

@Embeddable
public class EmbeddedDescription implements Describeable {
	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "description", nullable = false)
	private String description;

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}
}