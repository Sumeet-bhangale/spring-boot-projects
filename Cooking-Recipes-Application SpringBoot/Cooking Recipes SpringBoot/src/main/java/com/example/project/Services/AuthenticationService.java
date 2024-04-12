package com.example.project.Services;

import com.example.project.DTO.BasicResponseDTO;
import com.example.project.DTO.LoginResponseDTO;
import com.example.project.DTO.RegisterRequestDTO;
import com.example.project.DTO.RegisterResponseDTO;
import com.example.project.Models.User;
import com.example.project.Repositories.UserDAO;
import com.example.project.Utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    UserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JWTUtil jwtUtil;


    public Optional<RegisterResponseDTO> addUser(RegisterRequestDTO r) {
        if(userDAO.existsByEmail(r.getEmail())){
            return Optional.empty();
        }
        User user = new User();
        user.setEmail(r.getEmail());
        user.setRole(r.getRole());
        user.setPassword(passwordEncoder.encode(r.getPassword()));
        //user.setPassword(r.getPassword());
        userDAO.save(user);
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        return Optional.of(new RegisterResponseDTO(user.getEmail()));
    }


    public BasicResponseDTO<LoginResponseDTO> login(String email, String password) {
        BasicResponseDTO<LoginResponseDTO> basicResponseDTO = new BasicResponseDTO<>();
        Optional<User> _user = userDAO.findUserByEmail(email);
        if(_user.isEmpty()){
            basicResponseDTO.setMessage("User not found");
            return basicResponseDTO;
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            email,
                            password
                    )
            );

        } catch (BadCredentialsException e) {
            basicResponseDTO.setMessage("Credentials not matched");
            return basicResponseDTO;
        }
        User user = _user.get();
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        LoginResponseDTO loginResponseDTO = new LoginResponseDTO();
        loginResponseDTO.setToken(jwtUtil.generateToken(user));
        loginResponseDTO.setUser(user);
        basicResponseDTO.setData(loginResponseDTO);
        basicResponseDTO.setSuccess(true);

        return basicResponseDTO;
    }

    public BasicResponseDTO changePassword(String email, String password){
        try {
            Optional<User> user = userDAO
                    .findAll()
                    .stream()
                    .filter(x -> x.getEmail().toLowerCase().equalsIgnoreCase(email.toLowerCase()))
                    .findFirst();

            user.get().setPassword(passwordEncoder.encode(password));
            userDAO.save(user.get());

            BasicResponseDTO responseDTO = new BasicResponseDTO();
            responseDTO.setSuccess(true);
            responseDTO.setMessage("The password has been updated successfully");
            return responseDTO;

        }catch (Exception ex){
            return null;
        }

    }

    public Boolean findEmail(String email){
        BasicResponseDTO responseDTO = new BasicResponseDTO();
        try {
            Optional<User> user = userDAO
                    .findAll()
                    .stream()
                    .filter(x -> x.getEmail().toLowerCase().equalsIgnoreCase(email.toLowerCase()))
                    .findFirst();

            if(user.isEmpty()){
                responseDTO.setSuccess(false);
                responseDTO.setMessage("Email Not Exist");
                return false;
            }


            responseDTO.setSuccess(true);
            responseDTO.setMessage("Email Exist");


        }catch (Exception ex){
            responseDTO.setSuccess(false);
            responseDTO.setMessage(ex.getMessage());
            return false;
        }

        return true;
    }

}
