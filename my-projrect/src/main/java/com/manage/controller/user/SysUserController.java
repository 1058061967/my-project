package com.manage.controller.user;
import java.util.List;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.manage.controller.AbstractController;
import com.manage.controller.user.api.SearchUserRequest;
import com.manage.entity.SysUser;
import com.manage.model.PagingData;
import com.manage.model.SearchResult;
import com.manage.filter.UserFilter;
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
	
	 //用户列表
	@RequestMapping("/list")
	@RequiresPermissions("sys:user:list")
	public ServiceResponse searchUserlist(SearchUserRequest request){
		UserFilter filter = new UserFilter();
		filter.setPagingData(new PagingData(request.getPageNumber(), request.getPageSize()));
		filter.setUserName(request.getUserName());
		filter.setPaged(request.isPaged());
		SearchResult<SysUser> result = sysUserService.searchUserByFilter(filter);
		PageResponse  response = new PageResponse(result.getResult(), 
				result.getPagingResult().getRecordNumber(), 
				result.getPagingResult().getPageNumber(),
				result.getPagingResult().getPageSize());
		return ServiceResponse.ok().put("page", response);
}		
	

	//获取登录的用户信息
	@RequestMapping("/info")
	public ServiceResponse searchUserinfo(){
		return ServiceResponse.ok().put("user", getUser());
	}
	
	//修改登录用户密码
	@RequestMapping("/password")
	public ServiceResponse updateUserPassword(String password, String newPassword){
		if(StringUtils.isBlank(newPassword)){
			return ServiceResponse.error("新密码不为能空");
		}
		password = new Sha256Hash(password).toHex();
		newPassword = new Sha256Hash(newPassword).toHex();		
		//更新密码
		int count = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(count == 0){
			return ServiceResponse.error("原密码不正确");
		}
		//退出
		ShiroUtils.logout();
		
		return ServiceResponse.ok();
	}
	
	//用户信息
	@RequestMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public ServiceResponse searchUserinfoById(@PathVariable("userId") Integer userId){
		SysUser user = sysUserService.queryObject(userId);
		
		//获取用户所属的角色列表
		List<Integer> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		
		return ServiceResponse.ok().put("user", user);
	}
	
	
	 //保存用户
	@RequestMapping("/save")
	@RequiresPermissions("sys:user:save")
	public ServiceResponse saveUser(@RequestBody SysUser user){
		if(StringUtils.isBlank(user.getUserName())){
			return ServiceResponse.error("用户名不能为空");
		}
		if(StringUtils.isBlank(user.getPassword())){
			return ServiceResponse.error("密码不能为空");
		}
		
		sysUserService.save(user);
		
		return ServiceResponse.ok();
	}
	
	
	// 修改用户
	@RequestMapping("/update")
	@RequiresPermissions("sys:user:update")
	public ServiceResponse updateUser(@RequestBody SysUser user){
		if(StringUtils.isBlank(user.getUserName())){
			return ServiceResponse.error("用户名不能为空");
		}
		
		sysUserService.update(user);
		
		return ServiceResponse.ok();
	}
	

	//删除用户
	@RequestMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public ServiceResponse deleteUser(@RequestBody Integer[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return ServiceResponse.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return ServiceResponse.error("当前用户不能删除");
		}
		sysUserService.deleteBatch(userIds);
		return ServiceResponse.ok();
	}
}