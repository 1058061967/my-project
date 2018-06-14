package com.manage.controller.config.api;

import java.util.ArrayList;
import java.util.List;

import com.manage.entity.SysConfig;

public class SysConfigVO {
	
	private Integer id; 
	private String key; 
	private String value; 
	private String remark;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public static SysConfigVO toVO(SysConfig config) {
		
		SysConfigVO vo = new SysConfigVO();
		vo.setId(config.getId());
		vo.setKey(config.getKey());
		vo.setRemark(config.getRemark());
		vo.setValue(config.getValue());
		return vo;
	}
	
	public static List<SysConfigVO> toVOs(List<SysConfig> configs){
		
		List<SysConfigVO> vos = new ArrayList<>();
		for(SysConfig config : configs) {
			vos.add(SysConfigVO.toVO(config));
		}
		return vos;
	}

}
