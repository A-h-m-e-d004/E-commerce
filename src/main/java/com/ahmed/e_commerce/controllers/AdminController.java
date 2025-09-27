package com.ahmed.e_commerce.controllers;

import com.ahmed.e_commerce.Entity.User.Role;
import com.ahmed.e_commerce.service.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

	private final AdminService adminService;

	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	@PostMapping("/user/{id}/role")
	public ResponseEntity<?> updateUserRole(@PathVariable Long id, @RequestParam Role role){
		adminService.updateUserRole(id, role);
		return ResponseEntity.ok("User role updated");
	}
}
