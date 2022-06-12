package fr.pfe.mapper;

import fr.pfe.dto.RoleDto;
import fr.pfe.entities.Role;

public class RoleMapper {
	
	private RoleMapper() {
		
	}
	
	public static RoleDto toRoleDto(Role role) {
		RoleDto roleDto = new RoleDto();
		roleDto.setId(role.getId());
		roleDto.setName(role.getName());
		return roleDto;
	}
}
