package fr.pfe.mapper;

import fr.pfe.dto.WebClientAccountDto;
import fr.pfe.entities.WebClientAccount;

public class WebClientAccountMapper {
	
	private WebClientAccountMapper() {
		
	}
	
	public static WebClientAccountDto toWebClientAccountDto(WebClientAccount webClientAccount) {
		WebClientAccountDto webClientAccountDto = new WebClientAccountDto();
		webClientAccountDto.setIdWebClientAccount(webClientAccount.getId());
		webClientAccountDto.setTelephone(webClientAccount.getTelephone());
		webClientAccountDto.setSimCardFeePerMonth(webClientAccount.getSimCardFeePerMonth());
		webClientAccountDto.setRawPassword(webClientAccount.getRawPassword());
		webClientAccountDto.setNotificationSubquery(webClientAccount.getNotificationSubquery());
		webClientAccountDto.setPool(webClientAccount.getPool());
		webClientAccountDto.setAccountFeeByMonth(webClientAccount.getAccountFeeByMonth());
		webClientAccountDto.setArea(webClientAccount.getArea());
		webClientAccountDto.setCode_pays(webClientAccount.getCode_pays());
		webClientAccountDto.setDate_creation(webClientAccount.getDate_creation());
		webClientAccountDto.setDate_expiration(webClientAccount.getDate_expiration());
		webClientAccountDto.setDeviceFeeByDay(webClientAccount.getDeviceFeeByDay());
		webClientAccountDto.setDeviceFeePerMonth(webClientAccount.getDeviceFeePerMonth());
		webClientAccountDto.setEmail(webClientAccount.getEmail());
		webClientAccountDto.setPassword(webClientAccount.getPassword());
		webClientAccountDto.setUsername(webClientAccount.getUsername());
		webClientAccountDto.setRole(RoleMapper.toRoleDto(webClientAccount.getRole()));
		return webClientAccountDto;
	}
	
	public static WebClientAccount toWebClientAccount(WebClientAccountDto webClientAccountDto) {
		WebClientAccount webClientAccount = new WebClientAccount();
		webClientAccount.setTelephone(webClientAccountDto.getTelephone());
		webClientAccount.setSimCardFeePerMonth(webClientAccountDto.getSimCardFeePerMonth());
		webClientAccount.setRawPassword(webClientAccountDto.getRawPassword());
		webClientAccount.setPool(webClientAccountDto.getPool());
		webClientAccount.setNotificationSubquery(webClientAccountDto.getNotificationSubquery());
		webClientAccount.setAccountFeeByMonth(webClientAccountDto.getAccountFeeByMonth());
		webClientAccount.setArea(webClientAccountDto.getArea());
		webClientAccount.setDate_creation(webClientAccountDto.getDate_creation());
		webClientAccount.setDate_expiration(webClientAccountDto.getDate_expiration());
		webClientAccount.setDeviceFeeByDay(webClientAccountDto.getDeviceFeeByDay());
		webClientAccount.setDeviceFeePerMonth(webClientAccountDto.getDeviceFeePerMonth());
		webClientAccount.setEmail(webClientAccountDto.getEmail());
		webClientAccount.setUsername(webClientAccountDto.getUsername());
		webClientAccount.setPassword(webClientAccountDto.getPassword());
		webClientAccount.setCode_pays(webClientAccountDto.getCode_pays());
		return webClientAccount;
	}
}
