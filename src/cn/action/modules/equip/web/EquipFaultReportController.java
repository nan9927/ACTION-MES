package cn.action.modules.equip.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.action.common.web.BaseController;
import cn.action.modules.bas.entity.Line;
import cn.action.modules.bas.service.LineService;
import cn.action.modules.equip.entity.EquipFaultReport;
import cn.action.modules.equip.service.EquipFaultReportService;

@Controller
@RequestMapping(value="${adminPath}/equip/report")
public class EquipFaultReportController extends BaseController{
	@Autowired
	private EquipFaultReportService equipFaultReportService;
	@Autowired
	private LineService lineService;
	//设备故障上报保存
	@RequestMapping(value="save")
	public String save(EquipFaultReport equipFaultReport,Model model,RedirectAttributes redirectAttributes) {
		boolean flag=equipFaultReportService.saveFaultReport(equipFaultReport);
		String messages="设备编号有误，无此设备！";
		if(flag) {
			messages="故障上报成功！";
		}
		this.addMessage(redirectAttributes, messages);
		return "redirect:"+adminPath+"/equip/report/form";
	}
	
	//跳转到故障上报页面
	@RequestMapping(value="form")
	public String form(EquipFaultReport equipFaultReport,Model model) {
		//获得所有产线信息
		List<Line> lines=lineService.findAllList(new Line());
		model.addAttribute("lineList",lines);
		return "modules/equip/equipFaultReportForm";
	}
	
}
