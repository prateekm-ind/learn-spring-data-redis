package learn.redis.springdata.controller;

import learn.redis.springdata.model.UserInfo;
import learn.redis.springdata.repository.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/rest/user")
public class UserController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @GetMapping(value = "/all")
    public ResponseEntity<Map<String, UserInfo>> getAllUsers() {
        return ResponseEntity.ok(this.userInfoRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserInfo> getSingleUserInfo(@RequestParam String id) {
        return ResponseEntity.ok(this.userInfoRepository.findById(id));
    }

    @PostMapping(value = "/insert")
    public ResponseEntity insertUserInfo(@RequestBody UserInfo userInfo) {
        this.userInfoRepository.save(userInfo);
        return (ResponseEntity) ResponseEntity.status(HttpStatus.CREATED);
    }

    @PostMapping(value = "/update")
    public ResponseEntity<String> updateUserInfo(@RequestBody UserInfo userInfo) {
        this.userInfoRepository.update(userInfo);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("UPDATED");
    }
}
