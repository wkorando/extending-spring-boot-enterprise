package com.ibm.cloud.startup;

import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.doppler.DopplerClient;
import org.cloudfoundry.operations.DefaultCloudFoundryOperations;
import org.cloudfoundry.reactor.ConnectionContext;
import org.cloudfoundry.reactor.DefaultConnectionContext;
import org.cloudfoundry.reactor.TokenProvider;
import org.cloudfoundry.reactor.client.ReactorCloudFoundryClient;
import org.cloudfoundry.reactor.doppler.ReactorDopplerClient;
import org.cloudfoundry.reactor.tokenprovider.PasswordGrantTokenProvider;
import org.cloudfoundry.reactor.tokenprovider.RefreshTokenGrantTokenProvider;
import org.cloudfoundry.reactor.uaa.ReactorUaaClient;
import org.cloudfoundry.uaa.UaaClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IBMCloudStartupApplication {

	public static void main(String[] args) {
		SpringApplication.run(IBMCloudStartupApplication.class, args);
	}

	@Bean
	ReactorCloudFoundryClient cloudFoundryClient(ConnectionContext connectionContext, TokenProvider tokenProvider) {
		return ReactorCloudFoundryClient.builder().connectionContext(connectionContext).tokenProvider(tokenProvider)
				.build();
	}

	@Bean
	ReactorDopplerClient dopplerClient(ConnectionContext connectionContext, TokenProvider tokenProvider) {
		return ReactorDopplerClient.builder().connectionContext(connectionContext).tokenProvider(tokenProvider).build();
	}

	@Bean
	ReactorUaaClient uaaClient(ConnectionContext connectionContext, TokenProvider tokenProvider) {
		return ReactorUaaClient.builder().connectionContext(connectionContext).tokenProvider(tokenProvider).build();
	}

	@Bean
	DefaultConnectionContext connectionContext(@Value("${cf.host}") String apiHost) {
		return DefaultConnectionContext.builder().apiHost(apiHost).build();
	}

	@Bean
	PasswordGrantTokenProvider tokenProvider() {
//			@Value("${cf.username}") String username,
//			@Value("${cf.password}") String password) {
//		
		return PasswordGrantTokenProvider.builder().
				password("I-sXMORlcrT7_KKrl5j75NY4iYkILSdeXtNyazK8wBrl").username("apikey").build();
	}

	@Bean
	DefaultCloudFoundryOperations cloudFoundryOperations(CloudFoundryClient cloudFoundryClient,
			DopplerClient dopplerClient, UaaClient uaaClient, @Value("${cf.organization}") String organization,
			@Value("${cf.space}") String space) {
		return DefaultCloudFoundryOperations.builder().cloudFoundryClient(cloudFoundryClient)
				.dopplerClient(dopplerClient).uaaClient(uaaClient).organization(organization).space(space).build();
	}
}
