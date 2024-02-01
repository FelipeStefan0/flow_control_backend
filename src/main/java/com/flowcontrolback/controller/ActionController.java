package com.flowcontrolback.controller;

import com.flowcontrolback.entities.Action;
import com.flowcontrolback.models.ApiResponse;
import com.flowcontrolback.services.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/action")
@RestController
@CrossOrigin(
        origins = {"http://192.168.0.100:8100", "http://localhost:8100"},
        allowedHeaders = "*"
)
public class ActionController {

  private final ActionService service;

  @GetMapping
  public ResponseEntity<ApiResponse<List<Action>>> getAll() {
    ApiResponse<List<Action>> response = new ApiResponse<>();
    List<Action> listedAction = service.list();
    response.of(HttpStatus.OK, "Listado com sucesso!", listedAction);
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @PostMapping
  public ResponseEntity<ApiResponse<Action>> create(@RequestBody Action action) {
    ApiResponse<Action> response = new ApiResponse<>();
    Action createdAction = service.create(action);
    response.of(HttpStatus.OK, "Registrado com sucesso!", createdAction);
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @DeleteMapping
  public ResponseEntity<ApiResponse<Action>> delete(@RequestParam Long id) throws Exception {
    ApiResponse<Action> response = new ApiResponse<>();
    service.delete(id);
    response.of(HttpStatus.OK, "Deletado com sucesso!");
    return ResponseEntity.status(response.getStatus()).body(response);
  }

  @PutMapping
  public ResponseEntity<ApiResponse<Action>> edit(@RequestBody Action action) {
    ApiResponse<Action> response = new ApiResponse<>();
    Action actionChanged = service.edit(action);
    response.of(HttpStatus.OK, "Resgistro modificado com sucesso!", actionChanged);
    return ResponseEntity.status(response.getStatus()).body(response);
  }
}
