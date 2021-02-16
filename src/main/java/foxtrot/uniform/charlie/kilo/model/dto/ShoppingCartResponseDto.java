package foxtrot.uniform.charlie.kilo.model.dto;

import java.util.List;

public class ShoppingCartResponseDto {
    private Long id;
    private List<Long> ticketIds;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getTicketIds() {
        return ticketIds;
    }

    public void setTicketIds(List<Long> ticketIds) {
        this.ticketIds = ticketIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "ShoppingCartResponseDto{" + "id=" + id
                + ", ticketIds=" + ticketIds
                + ", userId=" + userId + '}';
    }
}
