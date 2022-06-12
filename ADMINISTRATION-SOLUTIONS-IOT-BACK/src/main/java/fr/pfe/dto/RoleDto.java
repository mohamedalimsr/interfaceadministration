package fr.pfe.dto;

import fr.pfe.enumeration.ERole;
import lombok.Data;

@Data
public class RoleDto {
	
	private Long id;
	private ERole name;
	
	public RoleDto() {
		super();
	}
	
	public RoleDto(Long id, ERole name) {
		super();
		this.id = id;
		this.name = name;
	}
}
