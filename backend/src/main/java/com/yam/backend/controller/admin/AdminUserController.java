package com.yam.backend.controller.admin;

import com.yam.backend.exception.RequestException;
import com.yam.backend.model.dto.response.AdminUserDTO;
import com.yam.backend.model.dto.response.PageResponseDTO;
import com.yam.backend.service.admin.AdminUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/users")
public class AdminUserController {

    private final AdminUserService adminUserService;

    @GetMapping
    public ResponseEntity<?> getUsers(@RequestParam(value="page_number") int pageNumber,
                                      @RequestParam(value="page_size") int pageSize){
        PageResponseDTO<AdminUserDTO> responseDTO = new PageResponseDTO<>(adminUserService.getUsers(pageNumber, pageSize));
        return ResponseEntity.ok()
                .body(responseDTO);
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<?> changeUserActiveStatus(@PathVariable UUID id,
                                                    @RequestBody Map<String,Object> body){
        if(!body.containsKey("active")){
            throw new RequestException("Request must contains new status");
        }

        boolean active = Boolean.parseBoolean(body.get("active").toString());
        adminUserService.changeUserActiveStatus(id, active);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/role")
    public ResponseEntity<?> changeUserRole(@PathVariable UUID id,
                                            @RequestBody Map<String,Object> body){
        if(!body.containsKey("role")){
            throw new RequestException("Request must contains new status");
        }

        adminUserService.changeUserRole(id, body.get("role").toString());
        return ResponseEntity.ok().build();
    }
}
