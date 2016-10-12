package com.bstek.urule.springboot;

import java.io.File;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

import com.bstek.urule.URulePropertyPlaceholderConfigurer;

/**
 * @author Jacky.gao
 * @since 2016年10月12日
 */

public class PropertiesConfiguration extends URulePropertyPlaceholderConfigurer implements InitializingBean{
	@Value("${repo}")
	private String repo;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		Properties props=new Properties();
		if(StringUtils.isBlank(repo)){
			repo=initReporityDir();
		}
		props.setProperty("urule.repository.dir", repo);			
		setProperties(props);
	}
	private String initReporityDir(){
		String dir="d:/repo";
		File file=new File(dir);
		try{
			if(!file.exists()){
				file.mkdir();
			}			
		}catch(Exception ex){
			dir="c:/repo";
			file=new File(dir);
			try{
				if(!file.exists()){
					file.mkdir();
				}			
			}catch(Exception ex1){
				throw new IllegalArgumentException("Please specify a config file.");
			}
		}
		return dir;
	}
}
