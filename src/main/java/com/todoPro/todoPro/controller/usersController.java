package com.todoPro.todoPro.controller;

import com.todoPro.todoPro.comm.JwtUtil;
import com.todoPro.todoPro.service.TodosService;
import com.todoPro.todoPro.service.UsersService;
import com.todoPro.todoPro.vo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class usersController {

    private final UsersService usersService;

    private final JwtUtil jwtUtil;

    public usersController(UsersService usersService,JwtUtil jwtUtil) {
        this.usersService = usersService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/signup")
    public ResponseEntity<Users> singUp(@RequestBody Users users){

      usersService.signup(users);
      return new ResponseEntity<Users>(HttpStatus.OK);

    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Users users){

        // 여기에 실제 사용자 검증 로직이 들어감
        String msg = "아이디 또는 비밀번호가 올바르지 않습니다.";
        Users userInfo = usersService.getUsers(users);

        if(userInfo == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", msg));
        }

        String userId = userInfo.getUserId();
        String pw = userInfo.getPw();
        try {
            if (users.getUserId().equals(userId) && users.getPw().equals(pw)) {
                String accessToken = jwtUtil.generateAccessToken(userId);
                String refreshToken = jwtUtil.generateRefreshToken(userId);

                Map<String, String> tokens = new HashMap<>();
                tokens.put("access_token", accessToken);
                tokens.put("refresh_token", refreshToken);


                return ResponseEntity.ok(tokens);
            }else{
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("message", msg));
            }
        }catch (Exception e){
            e.getMessage();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("message", msg));
        }

    }

    @PostMapping("/refresh")
    public ResponseEntity<Map<String, String>> refresh(@RequestBody Map<String, String> payload) {
        String refreshToken = payload.get("refresh_token");
        if (jwtUtil.isTokenValid(refreshToken)) {
            String username = jwtUtil.extractUserId(refreshToken);
            String newAccessToken = jwtUtil.generateAccessToken(username);
            Map<String, String> response = new HashMap<>();
            response.put("access_token", newAccessToken);
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @RequestMapping(value = "/me", method = {RequestMethod.GET, RequestMethod.PUT,RequestMethod.DELETE})
    public ResponseEntity me(HttpServletRequest request , @RequestBody Users users){

        String method = request.getMethod();

        if ("GET".equalsIgnoreCase(method)) {
            // GET 요청 처리 로직

            Users userInfo = usersService.getUsers(users);
            return ResponseEntity.ok(userInfo);
        } else if ("PUT".equalsIgnoreCase(method)) {

            try {
                // PUT 요청 처리 로직
                int result = usersService.updateUser(users);

                if(result > 0){
                    return ResponseEntity.ok("비밀번호가 변경되었습니다.");
                }else{
                    return ResponseEntity.ok("비밀번호 변경에 실패하였습니다.");
                }
            }catch (Exception e){
                return ResponseEntity.ok("비밀번호 변경에 실패하였습니다.");
            }

        }else if("DELETE".equalsIgnoreCase(method)){

            try {
                int result = usersService.deleteUser(users);

                if(result > 0){
                    return ResponseEntity.ok("회원이 삭제되었습니다.");
                }else{
                    return ResponseEntity.ok("회원 삭제에 실패하였습니다.");
                }

            }catch (Exception e){
                return ResponseEntity.ok("회원 삭제에 실패하였습니다.");
            }

        }else {
            // 지원하지 않는 HTTP 메서드에 대한 처리
            return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("지원하지 않는 메서드입니다.");
        }
    }

}
