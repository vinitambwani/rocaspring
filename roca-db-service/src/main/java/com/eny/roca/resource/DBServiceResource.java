package com.eny.roca.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rs/db")
public class DBServiceResource {
		
		@GetMapping("/test")
		public String getDummyString() {
			return "Hello:world";
		}
		
}
