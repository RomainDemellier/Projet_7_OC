package com.oc.projets.projet_7.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
//	@Bean
//	JobLauncher jobLauncher() {
//		return new JobLauncher() {
//			
//			@Override
//			public JobExecution run(Job job, JobParameters jobParameters) throws JobExecutionAlreadyRunningException,
//					JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
//				// TODO Auto-generated method stub
//				return null;
//			}
//		};
//	}
}
