package com.deep.fintechbackend.controller;
import com.deep.fintechbackend.dto.RegisterRequest;
import com.deep.fintechbackend.repository.UserRepository;
import com.deep.fintechbackend.repository.WalletRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import com.deep.fintechbackend.dto.LoginRequest;
import com.deep.fintechbackend.entity.User;
import com.deep.fintechbackend.entity.Wallet;
import com.deep.fintechbackend.repository.WalletRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
    @RequestMapping("/api/auth")
    public class AuthController {
        private final UserRepository userRepository;


    private final PasswordEncoder passwordEncoder;
    private final WalletRepository walletRepository;
    public AuthController(UserRepository userRepository,
                          PasswordEncoder passwordEncoder,WalletRepository walletRepository) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.walletRepository=walletRepository;
    }

        @GetMapping("/test")
        public String test() {
            return "FinTech Backend Running";
        }

        @PostMapping("/register")
        public String register(@RequestBody RegisterRequest request){
            if (userRepository.existsByEmail(request.email())){
                return "email already exists";
            }
            User user=new User();
            user.setName(request.name());
            user.setEmail(request.email());
            user.setPassword(
                    passwordEncoder.encode(request.password())
            );
            userRepository.save(user);
            Wallet wallet = new Wallet();
            wallet.setUser(user);
            wallet.setBalance(0.0);

            walletRepository.save(wallet);
            return "user  registered   successfully";
        }
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request){

        Optional<User> user =
                userRepository.findByEmail(request.email());

        if(user.isEmpty()){
            return "User not found";
        }

        if(passwordEncoder.matches(
                request.password(),
                user.get().getPassword()
        )){
            return "Login successful";
        }

        return "Invalid password";
    }
    }

