package foxtrot.uniform.charlie.kilo.service.implementation.dto.mappers.response;

import foxtrot.uniform.charlie.kilo.model.Order;
import foxtrot.uniform.charlie.kilo.model.Ticket;
import foxtrot.uniform.charlie.kilo.model.dto.response.OrderResponseDto;
import foxtrot.uniform.charlie.kilo.service.implementation.dto.mappers.MapperToDto;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class OrderResponseMapperToDto implements MapperToDto<OrderResponseDto, Order> {
    @Override
    public OrderResponseDto toDto(Order order) {
        OrderResponseDto dto = new OrderResponseDto();
        dto.setId(order.getId());
        dto.setDate(order.getOrderDate().toString());
        dto.setTicketIds(order.getTickets().stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        dto.setUserId(order.getUser().getId());
        return dto;
    }
}
