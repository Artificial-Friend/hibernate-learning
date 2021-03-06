package foxtrot.uniform.charlie.kilo.controller;

import foxtrot.uniform.charlie.kilo.model.Role;
import foxtrot.uniform.charlie.kilo.model.User;
import foxtrot.uniform.charlie.kilo.service.RoleService;
import foxtrot.uniform.charlie.kilo.service.UserService;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InjectData {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public InjectData(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostConstruct
    public void inject() {
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        roleService.add(adminRole);
        Role userRole = new Role();
        userRole.setName("USER");
        roleService.add(userRole);
        Set<Role> roles = new HashSet<>();

        User userUser = new User();
        userUser.setPassword("123456");
        userUser.setEmail("user@gmail.com");
        roles.add(userRole);
        userUser.setRoles(roles);
        userService.add(userUser);

        User adminUser = new User();
        adminUser.setEmail("admin");
        adminUser.setPassword("admin");
        roles.add(adminRole);
        adminUser.setRoles(roles);
        userService.add(adminUser);
    }
}
