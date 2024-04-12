package com.example.project.Controllers;

import com.example.project.DTO.*;
import com.example.project.Services.AuthenticationService;
import com.example.project.Utils.JWTUtil;
import com.example.project.Utils.RoleConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import nonapi.io.github.classgraph.json.JSONDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.cloudinary.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/auth")
public class AuthController {

    //http://localhost:8080/swagger-ui/index.html#/
    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private JWTUtil jwtUtility;

    @Autowired
    private RoleConfig roleConfig;

    Date currentDate = new Date();

    @PostMapping("/signup")
    public ResponseEntity registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        AuthController eventLog = new AuthController();

        RegisterResponseDTO basicResponseDTO = new RegisterResponseDTO();


        if(registerRequestDTO.getEmail()==null || registerRequestDTO.getEmail()==""){
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request","Invalid Email", "/api/auth/signup");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        if(registerRequestDTO.getPassword()==null || registerRequestDTO.getPassword()==""){
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request","Invalid Password", "/api/auth/signup");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        Optional<RegisterResponseDTO> response = authenticationService.addUser(registerRequestDTO);
        if(!response.isPresent()) {
            return new ResponseEntity<>(basicResponseDTO, HttpStatus.BAD_REQUEST);
        }

        basicResponseDTO.setEmail(response.get().getEmail());
        return new ResponseEntity<>(basicResponseDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDTO loginRequestDTO){
        AuthController eventLog = new AuthController();

        if(loginRequestDTO.getEmail()==null || loginRequestDTO.getEmail()==""){
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request","Invalid Email", "/api/auth/login");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        if(loginRequestDTO.getPassword()==null || loginRequestDTO.getPassword()==""){
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request","Invalid Password", "/api/auth/login");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

        BasicResponseDTO<LoginResponseDTO> result = authenticationService.login(
                loginRequestDTO.getEmail(),
                loginRequestDTO.getPassword()
                );
        return new ResponseEntity<>(result.getData(), result.isSuccess() ? HttpStatus.OK : HttpStatus.UNAUTHORIZED);
    }

    @GetMapping("/findEmail")
    public ResponseEntity findEmail(@RequestParam String email)
    {
        AuthController eventLog = new AuthController();
        try {


            Boolean response = authenticationService.findEmail(email);

            if(!response){
                BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>("","400","Bad Request","Email Not Exist", "");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            ChangePasswordDTO data =new ChangePasswordDTO(email,"Email Exist");
            return new ResponseEntity<>(data, HttpStatus.OK);

        }catch (Exception ex){
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>("","400","Bad Request","The password is in the hacker's database!", "");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/changepassword")
    public ResponseEntity changePassword(@RequestParam String email, String newPassword){
        AuthController eventLog = new AuthController();
        try {


            if(newPassword==null || newPassword==""){
                BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request","Invalid Password", "/api/auth/changepassword");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            BasicResponseDTO response = authenticationService.changePassword(email, newPassword);

            if(response==null){
                BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>("","400","Bad Request","The password is in the hacker's database!", "");
                return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
            }

            ChangePasswordDTO data =new ChangePasswordDTO(email,"The password has been updated successfully");
            return new ResponseEntity<>(data, HttpStatus.OK);

        }catch (Exception ex){

            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>("","400","Bad Request","The password is in the hacker's database!", "");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }

    }

}


