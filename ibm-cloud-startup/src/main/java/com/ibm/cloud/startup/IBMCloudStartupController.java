package com.ibm.cloud.startup;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IBMCloudStartupController {
	private final IBMCloudStartupService service;

	public IBMCloudStartupController(IBMCloudStartupService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<String> test() {
		service.call();
		return ResponseEntity.ok("Success");
	}
}
