package com.adam.voiture.entities;
import org.springframework.data.rest.core.config.Projection;
@Projection(name = "nomVoiture", types = { Voiture.class })
public interface VoitureProjection {
	public String getNomVoiture();
}
