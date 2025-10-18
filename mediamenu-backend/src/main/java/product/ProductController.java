package product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import product.model.AppUser;
import product.model.AppUserDTO;
import product.model.UpdateUserCommand;
import product.services.*;

import java.util.List;

//examples of controllers that are used to write and read data with HTTP requests
@RestController
public class ProductController {
    private final CreateUserService createUserService;

    private final UpdateUserService updateUserService;

    private final GetUsersService getUsersService;

    private final DeleteUserService deleteUserService;

    private final GetUserService getUserService;


    public ProductController(CreateUserService createUserService,
                             UpdateUserService updateUserService,
                             GetUsersService getUsersService,
                             DeleteUserService deleteUserService,
                             GetUserService getUserService) {
        this.createUserService = createUserService;
        this.getUsersService = getUsersService;
        this.updateUserService = updateUserService;
        this.deleteUserService = deleteUserService;
        this.getUserService = getUserService;
    }

    @PostMapping("/user")
    public ResponseEntity<AppUserDTO> createUser(@RequestBody AppUser user) {
        return createUserService.execute(user);
    }

    @GetMapping("/users")
    public ResponseEntity<List<AppUserDTO>> getUsers() {

        return getUsersService.execute(null);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<AppUserDTO> getUserById(@PathVariable Integer id){

        return getUserService.execute(id);
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
