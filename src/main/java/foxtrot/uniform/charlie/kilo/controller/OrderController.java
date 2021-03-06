package foxtrot.uniform.charlie.kilo.controller;

import foxtrot.uniform.charlie.kilo.model.Order;
import foxtrot.uniform.charlie.kilo.model.User;
import foxtrot.uniform.charlie.kilo.model.dto.response.OrderResponseDto;
import foxtrot.uniform.charlie.kilo.service.OrderService;
import foxtrot.uniform.charlie.kilo.service.ShoppingCartService;
import foxtrot.uniform.charlie.kilo.service.UserService;
import foxtrot.uniform.charlie.kilo.service.implementation.dto.mappers.MapperToDto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final MapperToDto<OrderResponseDto, Order> responseMapper;

    @Autowired
    public OrderController(OrderService orderService, UserService userService,
                           ShoppingCartService shoppingCartService,
                           MapperToDto<OrderResponseDto, Order> responseMapper) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.responseMapper = responseMapper;
    }

    @GetMapping
    public List<OrderResponseDto> getHistory(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        Optional<User> user = userService.findByEmail(username);
        return orderService.getOrdersHistory(user.get()).stream()
                .map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public void completeOrder(Authentication authentication) {
        UserDetails principal = (UserDetails) authentication.getPrincipal();
        String username = principal.getUsername();
        Optional<User> user = userService.findByEmail(username);
        orderService.completeOrder(shoppingCartService.getByUser(user.get()));
    }
}
