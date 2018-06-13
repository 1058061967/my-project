import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.manage.entity.SysUser;
import com.manage.filter.UserFilter;
import com.manage.mapper.SysUserMapper;

public class UserTest extends AbstractPersistenceTest{
	
//	@Autowired
	private SysUserMapper userMapper;
	
//	@Test
	public void testUSer() {
		List<SysUser> users = new ArrayList<>();
		UserFilter filter = new UserFilter();
//		users = userMapper.selectUserByFilter(filter);
//		for(SysUser user : users) {
//			System.out.println(JSON.toJSON("******************************:"+JSON.toJSONString(user)));
//		}
	}
}
