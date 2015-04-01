package com.doctusoft.smiling.equipment;

import com.doctusoft.smiling.BaseDAOImpl;
import com.doctusoft.smiling.equipment.Equipment;
import com.doctusoft.smiling.equipment.EquipmentDAO;

class EquipmentDAOImpl extends BaseDAOImpl<Equipment> implements EquipmentDAO {

	public EquipmentDAOImpl() {
		super(Equipment.class);
	}
}
