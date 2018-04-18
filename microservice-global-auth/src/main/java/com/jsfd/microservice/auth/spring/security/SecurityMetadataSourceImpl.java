package com.jsfd.microservice.auth.spring.security;

import com.lottery.auth.domain.AccessPerm;
import com.lottery.auth.service.IAccessPermService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.*;
import java.util.Map.Entry;

/**
 * 
 * @ClassName:SecurityMetadataSourceImpl
 * @Description: TODO(自定义安全元数据资源)
 * @author:"czlxming".
 * @CreateDate:2017年8月28日-下午10:23:10.
 */
public class SecurityMetadataSourceImpl  implements FilterInvocationSecurityMetadataSource ,InitializingBean{
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private IAccessPermService accessPermService;
	
	/** 资源与权限的对应关系. */
	private Map<String, Collection<ConfigAttribute>> configAttributeMap;
	
	private PathMatcher pathMatcher = new AntPathMatcher();
	
	/** 初始化方法权限对应集合，绑定方法权限集合. */
	@Override
	public void afterPropertiesSet() throws Exception {
		this.configAttributeMap = bindResourceDefine();
	}
	
	/** 重载Spring Security所有资源与权限的关系. */
	public void reloadResourceDefine() {
		configAttributeMap.clear();
		bindResourceDefine();
	}
	
	//@PostConstruct
	private Map<String, Collection<ConfigAttribute>> bindResourceDefine() {
		log.debug("Spring Security加载所有URL资源与权限的关系_jdbcTemplate");
		return loadMetadataSource();
	}

	private Map<String, Collection<ConfigAttribute>> loadMetadataSource() {
		Map<String, Collection<ConfigAttribute>> configAttributeMap = new LinkedHashMap<String, Collection<ConfigAttribute>>();
		List<AccessPerm> list = accessPermService.findFullAccessPerm();
        for (AccessPerm ap : list) {
        	//Access#value
            String resKeyVal = String.valueOf(ap.getAccess().getValue());
            //Perm#code
            String authKeyVal = String.valueOf(ap.getPerm().getCode());
            ConfigAttribute ca = new SecurityConfig(authKeyVal);
    		if (!configAttributeMap.containsKey(resKeyVal)) {
    			Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>();
    			configAttributeMap.put(resKeyVal, configAttributes);
    		}
    		configAttributeMap.get(resKeyVal).add(ca);
        }
		return configAttributeMap;
	}
	
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		//log.debug("取URL对应的授权权限集合");
		// object 是一个URL，被用户请求的url。
		FilterInvocation filterInvocation = (FilterInvocation) object;  
		String url = filterInvocation.getRequestUrl();
		int firstQuestionMarkIndex = url.indexOf("?");
		if (firstQuestionMarkIndex != -1) {
			url = url.substring(0, firstQuestionMarkIndex);
		}
		Iterator<String> ite = configAttributeMap.keySet().iterator();
		while (ite.hasNext()) {
			String resURL = ite.next();
			if (pathMatcher.match(url, resURL)) {
				return configAttributeMap.get(resURL);
			}
		}
		//此URL是不需要权限控制的资源url
		return null;
	}

	/** 得到所有的权限集合. */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> allAttributes = new HashSet<ConfigAttribute>();
		for (Entry<String, Collection<ConfigAttribute>> entry : this.configAttributeMap.entrySet()) {
			allAttributes.addAll(entry.getValue());
		}
		return allAttributes;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

}
