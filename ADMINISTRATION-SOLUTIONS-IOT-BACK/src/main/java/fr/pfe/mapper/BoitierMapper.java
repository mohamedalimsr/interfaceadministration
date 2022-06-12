package fr.pfe.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import fr.pfe.dto.BoitierDto;
import fr.pfe.entities.Boitier;

public class BoitierMapper {
	
	
	private BoitierMapper() {
		
	}
	
	public static BoitierDto toBoitierDto(Boitier boitier) {
		BoitierDto boitierDto = new BoitierDto();
		boitierDto.setIdBoitier(boitier.getIdBoitier());
		boitierDto.setDateGps(boitier.getDateGps());
		boitierDto.setDateOption(boitier.getDateOption());
		boitierDto.setDateServeur(boitier.getDateServeur());
		boitierDto.setEtat(boitier.getEtat());
		boitierDto.setEtatBoitier(boitier.getEtatBoitier());
		boitierDto.setGps(boitier.getGps());
		boitierDto.setGsm(boitier.getGsm());
		boitierDto.setHlr(boitier.getHlr());
		boitierDto.setIdTrajet(boitier.getIdTrajet());
		boitierDto.setIgnition(boitier.getIgnition());
		boitierDto.setImei(boitier.getImei());
		boitierDto.setLabel(boitier.getLabel());
		boitierDto.setLatitude(boitier.getLatitude());
		boitierDto.setLongitude(boitier.getLongitude());
		boitierDto.setMatricule(boitier.getMatricule());
		boitierDto.setNumBoitier(boitier.getNumBoitier());
		boitierDto.setRawstream_id(boitier.getRawstream_id());
		boitierDto.setStreamId(boitier.getStreamId());
		boitierDto.setValidite(boitier.getValidite());
		boitierDto.setVersion(boitier.getVersion());
		boitierDto.setVitesse(boitier.getVitesse());
		return boitierDto;
	}
	
	public static Boitier toBoitier(BoitierDto boitierDto) {
		Boitier boitier = new Boitier();
		boitier.setIdBoitier(boitierDto.getIdBoitier());
		boitier.setDateGps(boitierDto.getDateGps());
		boitier.setDateOption(boitierDto.getDateOption());
		boitier.setDateServeur(boitierDto.getDateServeur());
		boitier.setEtat(boitierDto.getEtat());
		boitier.setEtatBoitier(boitierDto.getEtatBoitier());
		boitier.setGps(boitierDto.getGps());
		boitier.setGsm(boitierDto.getGsm());
		boitier.setHlr(boitierDto.getHlr());
		boitier.setIdTrajet(boitierDto.getIdTrajet());
		boitier.setIgnition(boitierDto.getIgnition());
		boitier.setImei(boitierDto.getImei());
		boitier.setLabel(boitierDto.getLabel());
		boitier.setLatitude(boitierDto.getLatitude());
		boitier.setLongitude(boitierDto.getLongitude());
		boitier.setMatricule(boitierDto.getMatricule());
		boitier.setNumBoitier(boitierDto.getNumBoitier());
		boitier.setRawstream_id(boitierDto.getRawstream_id());
		boitier.setStreamId(boitierDto.getStreamId());
		boitier.setValidite(boitierDto.getValidite());
		boitier.setVersion(boitierDto.getVersion());
		boitier.setVitesse(boitierDto.getVitesse());
		return boitier;
	}
}
