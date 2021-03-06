package cn.action.modules.equip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.action.common.service.CrudService;
import cn.action.common.utils.UserUtils;
import cn.action.modules.equip.dao.EquipFaultReportDao;
import cn.action.modules.equip.entity.EquipFaultReport;
import cn.action.modules.equip.entity.Equipment;

@Service
@Transactional(readOnly=true)
public class EquipFaultReportService extends CrudService<EquipFaultReportDao, EquipFaultReport>{
	@Autowired
	private EquipmentService equipmentService;
	/**
	 * 保存设备故障上报记录
	 * @param equipFaultReport
	 * @return
	 */
	public boolean saveFaultReport(EquipFaultReport equipFaultReport) {
		//根据设备编号查询是否有这个设备，如果没有，不能保存，否则设置相应属性信息并保存
		Equipment equipment=new Equipment();
		equipment.setQrCode(equipFaultReport.getEquipNo());
		Equipment equip=equipmentService.getByQRCode(equipment);
		if(equip==null) {
			return false;
		}
		//设置相关属性
		equipFaultReport.setEquipId(equip.getId());//设置设备Id
		equipFaultReport.setReportPerson(UserUtils.getUser().getName());//设置上报人
		equipFaultReport.setStatus("0001");//设置状态为“上报”
		//保存
		this.save(equipFaultReport);
		return true;
	}
}
