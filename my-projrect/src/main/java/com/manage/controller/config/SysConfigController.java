package com.manage.controller.config;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manage.controller.common.AbstractController;
import com.manage.controller.config.api.SearchConfigRequest;
import com.manage.controller.config.api.SysConfigVO;
import com.manage.entity.SysConfig;
import com.manage.filter.configFilter;
import com.manage.model.PagingData;
import com.manage.model.SearchResult;
import com.manage.service.SysConfigService;
import com.manage.utils.PageResponse;
import com.manage.utils.RRException;
import com.manage.utils.ServiceResponse;


@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {
	@Autowired
	private SysConfigService sysConfigService;
	
	/**
	 * 所有配置列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:config:list")
	public ServiceResponse searchConfig(SearchConfigRequest request){	
		configFilter filter = new configFilter();
		filter.setKey(request.getKey());
		filter.setPaged(request.isPaged());
		filter.setPagingData(new PagingData(request.getPage(), request.getLimit()));
		SearchResult<SysConfig>  result = sysConfigService.searchSysConfigByFilter(filter);
		PageResponse  response = new PageResponse(
				SysConfigVO.toVOs(result.getResult()),
				result.getPagingResult().getTotalPage(),
				result.getPagingResult().getPageSize(),
				result.getPagingResult().getTotalPage());
		return ServiceResponse.ok().put("page",response);
	}
	
	/**
	 * 配置信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:config:info")
	public ServiceResponse info(@PathVariable("id") Integer id){
		SysConfig config = sysConfigService.queryObject(id);
		
		return ServiceResponse.ok().put("config", config);
	}
	
	/**
	 * 保存配置
	 */
	@RequestMapping("/save")
	@RequiresPermissions("sys:config:save")
	public ServiceResponse save(@RequestBody SysConfig config){
		//参数校验
		verifyForm(config);

		sysConfigService.save(config);
		
		return ServiceResponse.ok();
	}
	
	/**
	 * 修改配置
	 */
	@RequestMapping("/update")
	@RequiresPermissions("sys:config:update")
	public ServiceResponse update(@RequestBody SysConfig config){
		//参数校验
		verifyForm(config);
		
		sysConfigService.update(config);
		
		return ServiceResponse.ok();
	}
	
	/**
	 * 删除配置
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("sys:config:delete")
	public ServiceResponse delete(@RequestBody Integer[] ids){
		sysConfigService.deleteBatch(ids);
		
		return ServiceResponse.ok();
	}
	
	/**
	 * 验证参数是否正确
	 */
	private void verifyForm(SysConfig config){
		if(StringUtils.isBlank(config.getKey())){
			throw new RRException("参数名不能为空");
		}
		
		if(StringUtils.isBlank(config.getValue())){
			throw new RRException("参数值不能为空");
		}
	}
}
