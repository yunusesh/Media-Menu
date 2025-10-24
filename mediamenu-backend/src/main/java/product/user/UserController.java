package product.user;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import product.user.model.AppUser;
import product.user.model.AppUserDTO;
import product.user.model.UpdateUserCommand;
import product.user.services.*;

import java.util.List;

//examples of controllers that are used to write and read data with HTTP requests
@RestController
public class UserController {
    private final UpdateUserService updateUserService;
    private final GetUsersService getUsersService;
    private final DeleteUserService deleteUserService;
    private final GetUserService getUserService;


    public UserController(UpdateUserService updateUserService,
                          GetUsersService getUsersService,
                          DeleteUserService deleteUserService,
                          GetUserService getUserService) {
        this.getUsersService = getUsersService;
        this.updateUserService = updateUserService;
        this.deleteUserService = deleteUserService;
        this.getUserService = getUserService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUserDTO>> getUsers() {

        return getUsersService.execute(null);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AppUserDTO> getUserById(@PathVariable Integer id){

        return getUserService.execute(id);
    }

    @GetMapping("/user/me")
    public ResponseEntity<AppUser> authenticatedUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        AppUser currentUser = (AppUser) auth.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<AppUserDTO> updateUser(@PathVariable Integer id, @RequestBody AppUser user) {
        return updateUserService.execute(new UpdateUserCommand(id, user));
    }

    @DeleteMapping("/user/{id}") // id here must match id in ??
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {

        return deleteUserService.execute(id);
    }


}
