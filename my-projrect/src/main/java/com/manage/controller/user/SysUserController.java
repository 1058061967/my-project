package com.manage.controller.user;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manage.controller.common.AbstractController;
import com.manage.controller.user.api.CreatetUserRequest;
import com.manage.controller.user.api.ModifySysUserRequest;
import com.manage.controller.user.api.SearchUserRequest;
import com.manage.controller.user.api.SysUserVO;
import com.manage.entity.SysUser;
import com.manage.filter.UserFilter;
import com.manage.model.PagingData;
import com.manage.model.SearchResult;
import com.manage.service.SysUserRoleService;
import com.manage.service.SysUserService;
import com.manage.utils.PageResponse;
import com.manage.utils.ServiceResponse;
import com.manage.utils.ShiroUtils;

@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public ServiceResponse searchSysUserlist(SearchUserRequest request){
		UserFilter filter = new UserFilter();
		filter.setPagingData(new PagingData(request.getPage(), request.getLimit()));
		filter.setUserName(request.getUserName());
		filter.setPaged(request.isPaged());
		SearchResult<SysUser> result = sysUserService.searchUserByFilter(filter);
		PageResponse  response = new PageResponse(SysUserVO.toVOs(result.getResult()), 
				result.getPagingResult().getRecordNumber(), 
				result.getPagingResult().getPageNumber(),
				result.getPagingResult().getPageSize());
		return ServiceResponse.ok().put("page", response);
}		
	

	@RequestMapping("/info")
	public ServiceResponse searchSysUserinfo(){
		return ServiceResponse.ok().put("user", getUser());
	}
	

	@RequestMapping("/password")
	public ServiceResponse updateSysUserPassword(String password, String newPassword){
		if(StringUtils.isBlank(newPassword)){
			return ServiceResponse.error("新密码不为能空");
		}
		password = new Sha256Hash(password).toHex();
		newPassword = new Sha256Hash(newPassword).toHex();		
		int count = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(count == 0){
			return ServiceResponse.error("原密码不正确");
		}
		ShiroUtils.logout();
		return ServiceResponse.ok();
	}
	
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public ServiceResponse searchSysUserinfoById(@PathVariable("userId") Integer userId){
		SysUser user = sysUserService.searchUserById(userId);
		List<Integer> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		return ServiceResponse.ok().put("user", user);
	}
	
	
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public ServiceResponse createSysUser(@RequestBody CreatetUserRequest request){
		if(StringUtils.isBlank(request.getUserName())){
			return ServiceResponse.error("用户名不能为空");
		}
		if(StringUtils.isBlank(request.getPassword())){
			return ServiceResponse.error("密码不能为空");
		}
		SysUser user = new SysUser();
		user.setCreateTime(new Date());
		user.setEmail(request.getEmail());
		user.setMobile(request.getMobile());
		user.setPassword(request.getMobile());
		user.setRoleIdList(request.getRoleIdList());
		user.setStatus(request.getStatus());
		user.setUserName(request.getUserName());
		sysUserService.createUser(user);
		return ServiceResponse.ok();
	}
	
	
	@RequestMapping(value="/update")
	@RequiresPermissions("sys:user:update")
	public ServiceResponse updateSysUser(@RequestBody ModifySysUserRequest request){
		if(StringUtils.isBlank(request.getUserName())){
			return ServiceResponse.error("用户名不能为空");
		}
		SysUser user = sysUserService.searchUserById(request.getUserId());
		user.setEmail(request.getEmail());
		user.setMobile(request.getMobile());
		user.setPassword(request.getPassword());
		user.setStatus(request.getStatus());
		user.setUserName(request.getUserName());
		user.setRoleIdList(request.getRoleIdList());
		sysUserService.modifyUser(user);
		return ServiceResponse.ok();
	}


	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public ServiceResponse deleteSysUser(@RequestBody Integer[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return ServiceResponse.error("系统管理员不能删除");
		}
		if(ArrayUtils.contains(userIds, getUserId())){
			return ServiceResponse.error("当前用户不能删除");
		}
		sysUserService.batchDelete(userIds);
		return ServiceResponse.ok();
	}
}
