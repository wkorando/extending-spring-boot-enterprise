package com.ibm.cloud.startup;

import java.nio.file.FileSystems;

import org.cloudfoundry.operations.DefaultCloudFoundryOperations;
import org.cloudfoundry.operations.applications.ApplicationSummary;
import org.cloudfoundry.operations.applications.PushApplicationRequest;
import org.cloudfoundry.operations.buildpacks.Buildpack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

@Service
public class IBMCloudStartupService {
	private static final Logger LOGGER = LoggerFactory.getLogger(IBMCloudStartupService.class);
	private final DefaultCloudFoundryOperations cfOperations;

	public IBMCloudStartupService(DefaultCloudFoundryOperations cfOperations) {
		this.cfOperations = cfOperations;
	}

	public void call() {
		cfOperations.applications().list().map(ApplicationSummary::toString).subscribe(System.out::println);
		try {
			Thread.sleep(3000l);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addNewApp() {

		PushApplicationRequest request = PushApplicationRequest.builder()
//				.path(FileSystems.getDefault().getPath("/"))
				.buildpack("java_buildpack")
				.memory(512)
				.name("Start Up New App 2").build();
		try {
			Void mono = cfOperations.applications().push(request).block();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
		
	}

}
