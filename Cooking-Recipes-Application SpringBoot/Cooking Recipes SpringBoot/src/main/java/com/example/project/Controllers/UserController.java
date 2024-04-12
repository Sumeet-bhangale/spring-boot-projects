package com.example.project.Controllers;

import com.example.project.DTO.*;
import com.example.project.Models.ProfileDetail;
import com.example.project.Models.ReceipesDetail;
import com.example.project.Models.ReviewDetail;
import com.example.project.Services.UserService;
import com.example.project.Utils.JWTUtil;
import com.example.project.Utils.RoleConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    UserService userService;

    Date currentDate = new Date();

    // Profile

    @PostMapping("/AddProfile")
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity AddProfile(@RequestBody ProfileDetail request) {
        try {


            String response = userService.AddProfile(request);
            BasicResponseDTO _response = new BasicResponseDTO();
            _response.setMessage(response);
            return new ResponseEntity<>(_response, HttpStatus.OK);
        }
        catch (Exception ex) {
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request",ex.getMessage(), "/user/addUserTour");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/UpdateProfile")
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity UpdateProfile(@RequestBody ProfileDetail request) {

        try {
            String response = userService.UpdateProfile(request);
            BasicResponseDTO _response = new BasicResponseDTO();
            _response.setMessage(response);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        catch (Exception ex) {
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request",ex.getMessage(), "/user/updateUserTour");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/GetProfile")
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity GetProfile(@RequestParam Long UserId) {

        try {
            Optional<ProfileDetail> response = userService.GetProfile(UserId);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception ex) {
            Date currentDate = new Date();
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request",ex.getMessage(), "/api/admin/user");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }

    //

    @GetMapping("/GetReceipesDetailList")
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity GetReceipesDetailList() {

        try {
            List<ReceipesDetail> response = userService.GetReceipesDetailList();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception ex) {
            Date currentDate = new Date();
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request",ex.getMessage(), "/api/admin/user");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/GetReceipesByCategory")
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity GetReceipesByCategory(@RequestParam String keyword) {

        try {
            List<ReceipesDetail> response = userService.GetReceipesByCategory(keyword);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception ex) {
            Date currentDate = new Date();
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request",ex.getMessage(), "/api/admin/user");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/GetReceipesByKeyword")
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity GetReceipesByKeyword(String keyword) {

        try {
            List<ReceipesDetail> response = userService.GetReceipesByKeyword(keyword);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception ex) {
            Date currentDate = new Date();
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request",ex.getMessage(), "/api/admin/user");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }

    //

    @PostMapping("/AddUserReview")
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity AddUserReview(@RequestBody ReviewDetail request) {
        try {


            String response = userService.AddUserReview(request);
            BasicResponseDTO _response = new BasicResponseDTO();
            _response.setMessage(response);
            return new ResponseEntity<>(_response, HttpStatus.OK);
        }
        catch (Exception ex) {
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request",ex.getMessage(), "/user/addUserTour");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/UpdateUserReview")
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity UpdateUserReview(@RequestBody ReviewDetail request) {

        try {
            String response = userService.UpdateUserReview(request);
            BasicResponseDTO _response = new BasicResponseDTO();
            _response.setMessage(response);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        catch (Exception ex) {
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request",ex.getMessage(), "/user/updateUserTour");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/GetUserReviewList")
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity GetUserReviewList() {

        try {
            List<GetReviewDetailListDAO> response = userService.GetUserReviewList();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception ex) {
            Date currentDate = new Date();
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request",ex.getMessage(), "/api/admin/user");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/DeleteUserReview")
    @Operation( security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity DeleteUserReview(@RequestParam Long Id) {

        try {
            String response = userService.DeleteUserReview(Id);
            BasicResponseDTO _response = new BasicResponseDTO();
            _response.setMessage(response);
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        catch (Exception ex) {
            Date currentDate = new Date();
            BasicResponseErrorDTO data =  new BasicResponseErrorDTO<>(currentDate.toString(),"400","Bad Request",ex.getMessage(), "/api/user/deleteUserTour");
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }
    }

}
