package com.example.project.Controllers;

import com.example.project.DTO.*;
import com.example.project.Models.ProfileDetail;
import com.example.project.Models.ReceipesDetail;
import com.example.project.Models.User;
import com.example.project.Services.AdminService;
import com.example.project.Utils.RoleConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("api/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    Date currentDate = new Date();



    @PostMapping("/AddReceipesDetail")
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity AddReceipesDetail(@RequestBody ReceipesDetail request) {
        try{

            String response = adminService.AddReceipesDetail(request);
            BasicResponseDTO _response = new BasicResponseDTO();
            _response.setMessage(response);
            return new ResponseEntity<>(_response, HttpStatus.OK);

        }catch (Exception ex){
            Date currentDate = new Date();
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request",ex.getMessage(), "/api/acct/payments");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/UpdateReceipesDetail")
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity UpdateReceipesDetail(@RequestBody ReceipesDetail request){
        try{
            String response = adminService.UpdateReceipesDetail(request);
            BasicResponseDTO _response = new BasicResponseDTO();
            _response.setMessage(response);
            return new ResponseEntity(_response, HttpStatus.OK);

        }catch (Exception ex){
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request",ex.getMessage(), "/api/admin/updatetourlocation");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/GetReceipesDetailList")
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity GetReceipesDetailList(){
        try{
            List<ReceipesDetail> response = adminService.GetReceipesDetailList();
            return new ResponseEntity(response, HttpStatus.OK);

        }catch (Exception ex){

            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @DeleteMapping("/DeleteReceipesDetailL")
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity DeleteReceipesDetailL(@RequestParam Long Id){

        try{
            String response = adminService.DeleteReceipesDetailL(Id);
            BasicResponseDTO _response = new BasicResponseDTO();
            _response.setMessage(response);
            return new ResponseEntity(_response, HttpStatus.OK);

        }catch (Exception ex){

            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }


}
