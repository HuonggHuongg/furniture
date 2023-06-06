package com.graduation.furniture.controller;

import com.graduation.furniture.config.JwtResponse;
import com.graduation.furniture.config.JwtUtil;
import com.graduation.furniture.dto.*;
import com.graduation.furniture.entities.Cart;
import com.graduation.furniture.entities.UserRole;
import com.graduation.furniture.entities.Users;
import com.graduation.furniture.service.CartService;
import com.graduation.furniture.service.EmailService;
import com.graduation.furniture.service.UserRoleService;
import com.graduation.furniture.service.UserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("")
    public ResponseEntity<Page<Users>> findAll(@RequestParam(name = "page", defaultValue = "1") int page, @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<Users> feedbackPage = userService.findAll(page, size);
        if (feedbackPage.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(feedbackPage);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO registerUserDTO, BindingResult bindingResult) {
        new RegisterUserDTO().validate(registerUserDTO, bindingResult);
        userService.findById(registerUserDTO.getUserName()).ifPresent(existUser -> bindingResult.rejectValue("userName", "", "User is exist!!"));

        Users userEmailExist = userService.findByEmail(registerUserDTO.getEmail());
        if (userEmailExist != null) {
            bindingResult.rejectValue("email", "", "Email is exist!!");
        }
        if (bindingResult.hasFieldErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        Users newUser = userService.registerUser(registerUserDTO);

        UserRole userRole = userRoleService.save(newUser);
        return new ResponseEntity<>(newUser, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> createAuthenticationToken(@RequestBody LoginDTO loginDTO) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);
        User user = (User) authentication.getPrincipal();
        Users userDB = userService.findById(loginDTO.getUserName()).orElse(null);
        List<String> roles = user.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).toList();
        UserLoginResponse userLoginResponse = UserLoginResponse.builder()
                .jWT(jwt)
                .user(userDB)
                .roles(roles)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(userLoginResponse);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPassDTO forgotPassDTO, BindingResult bindingResult) throws Exception {
        Users userEmailExist = userService.findByEmail(forgotPassDTO.getEmail());
        if (userEmailExist == null) {
            bindingResult.rejectValue("email", "", "Email is not exist!!");
        }
        if (bindingResult.hasFieldErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
        if (userEmailExist != null) {
            String resetPassword = RandomString.make(20);
            userEmailExist.setResetPassToken(resetPassword);
            userService.update(userEmailExist);
            String status = emailService.sendMailForgotPassword(forgotPassDTO.getEmail(), "http://localhost:3000/reset-password?token="
                    + resetPassword + "&email=" + forgotPassDTO.getEmail());
            if ("200".equals(status)) {
                return new ResponseEntity<>("Mail sent Successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Sent email failed", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("Your email is not registered", HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO, BindingResult bindingResult) {
        Users userEmailExist = userService.findByEmail(resetPasswordDTO.getEmail());
        if (userEmailExist == null) {
            bindingResult.rejectValue("email", "", "Email is not exist!!");
        } else if (!resetPasswordDTO.getToken().equals(userEmailExist.getResetPassToken())) {
            bindingResult.rejectValue("token", "", "Your request to change your password is already in use, please send new request");
        }
        if (bindingResult.hasFieldErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }

        assert userEmailExist != null;
        userEmailExist.setPassword(passwordEncoder.encode(resetPasswordDTO.getResetPassword()));
        userEmailExist.setResetPassToken(null);
        Users updateUser = userService.update(userEmailExist);
        return ResponseEntity.ok(updateUser);
    }

    @PatchMapping(value = "")
    public ResponseEntity<?> update(Authentication currentUser, @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        new UserDTO().validate(userDTO, bindingResult);
        Users existUser = userService.findById(currentUser.getName()).orElse(null);
        if (existUser == null) {
            bindingResult.rejectValue("userName", "", "User is not exist!!");
        }
        if (bindingResult.hasFieldErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return new ResponseEntity<>(fieldErrors, HttpStatus.BAD_REQUEST);
        }
//        Users user = new Users();
//        userDTO.setUserName(currentUser.getName());
//        BeanUtils.copyProperties(userDTO, user);
//        existUser = Users.builder()
//                .avatar(userDTO.getAvatar())
//                .firstName(userDTO.getFirstName())
//                .lastName(userDTO.getLastName())
//                .address(userDTO.getAddress())
//                .phoneNumber(userDTO.getPhoneNumber())
//                .build();
        existUser.setAvatar(userDTO.getAvatar());
        existUser.setFirstName(userDTO.getFirstName());
        existUser.setLastName(userDTO.getLastName());
        existUser.setPhoneNumber(userDTO.getPhoneNumber());
        existUser.setAddress(userDTO.getAddress());

        Users updateUser = userService.update(existUser);
        return ResponseEntity.ok(updateUser);
    }

    @GetMapping("/{username}")
    public ResponseEntity<Users> findById(@PathVariable String username) {

        Users user = userService.findById(username).orElse(null);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

//    @GetMapping("/{currentUser}")
//    public ResponseEntity<User> getCurrentUser(Authentication currentUser) {
//
//        currentUser.getName();
//
//        System.out.println(currentUser.getName());
//        return ResponseEntity.ok().build();
//    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteById(@PathVariable String username) {
        Users user = userService.findById(username).orElse(null);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteById(username);
        return ResponseEntity.ok().build();
    }
}
