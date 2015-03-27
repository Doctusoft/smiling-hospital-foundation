package com.doctusoft.smiling.equipment;

import java.util.List;
import com.doctusoft.smiling.Constants;
import com.doctusoft.smiling.equipment.ApiEquipment;
import com.doctusoft.smiling.equipment.Equipment;
import com.doctusoft.smiling.equipment.EquipmentDAO;
import com.doctusoft.smiling.security.PermissionLevel;
import com.doctusoft.smiling.security.Restricted;
import com.google.api.server.spi.ServiceException;
import com.google.api.server.spi.auth.common.User;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.AuthLevel;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.config.Nullable;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.google.inject.Inject;

@Api(
		name = "equipment",
		resource = "equipment",
		clientIds = {
				com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID,
				Constants.WEB_CLIENT_ID,
		},
		description = "Lets you manage the equipments",
		authLevel = AuthLevel.NONE)
public class EquipmentApi {

	private final EquipmentDAO equipmentDAO;

	@Inject
	public EquipmentApi(EquipmentDAO equipmentDAO) {
		this.equipmentDAO = equipmentDAO;
	}

	@Restricted(PermissionLevel.COORDINATOR)
	@ApiMethod(httpMethod = HttpMethod.POST, path = "/equipment")
	public void createEquipment(
			ApiEquipment apiEquipment,
			User user,
			@Nullable @Named("sessionId") String sessionId)
			throws ServiceException {

		Equipment equipment = Equipment.builder()
				.name(apiEquipment.getName())
				.build();

		equipmentDAO.save(equipment);
	}

	@Restricted(PermissionLevel.VOLUNTEER)
	@ApiMethod(httpMethod = HttpMethod.GET, path = "/equipment/{equipmentName}")
	public ApiEquipment getEquipment(
			@Named("equipmentName") String equipmentName,
			User user,
			@Nullable @Named("sessionId") String sessionId)
			throws ServiceException {

		Equipment equipment = equipmentDAO.get(equipmentName);
		return convert(equipment);
	}

	@Restricted(PermissionLevel.COORDINATOR)
	@ApiMethod(httpMethod = HttpMethod.GET, path = "/equipment/delete/{equipmentName}")
	public void deleteEquipment(
			@Named("equipmentName") String equipmentName,
			User user,
			@Nullable @Named("sessionId") String sessionId)
			throws ServiceException {

		Equipment equipment = equipmentDAO.get(equipmentName);
		equipmentDAO.delete(equipment);
	}

	@Restricted(PermissionLevel.VOLUNTEER)
	@ApiMethod(httpMethod = HttpMethod.GET, path = "/equipment")
	public List<ApiEquipment> getEquipments(
			User user,
			@Nullable @Named("sessionId") String sessionId)
			throws ServiceException {

		List<Equipment> equipments = equipmentDAO.getAll();
		return convertList(equipments);
	}

	private List<ApiEquipment> convertList(List<Equipment> equipments) {
		List<ApiEquipment> apiEquipments = Lists.newArrayList();
		for (Equipment equipment : equipments) {
			apiEquipments.add(convert(equipment));
		}
		return apiEquipments;
	}

	private ApiEquipment convert(Equipment equipment) {
		return ApiEquipment.builder().name(equipment.getName()).build();
	}

}
