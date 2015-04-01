package com.doctusoft.smiling.equipment;

import com.doctusoft.smiling.BaseDAO;
import com.doctusoft.smiling.equipment.Equipment;
import com.doctusoft.smiling.equipment.EquipmentDAOImpl;
import com.google.inject.ImplementedBy;

@ImplementedBy(EquipmentDAOImpl.class)
public interface EquipmentDAO extends BaseDAO<Equipment> {
}
