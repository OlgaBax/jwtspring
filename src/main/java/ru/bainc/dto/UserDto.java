package ru.bainc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.bainc.model.Status;
import ru.bainc.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDateTime created;
    private LocalDateTime updated;
    private Status status;
    private List<RoleDto> roles;

    public UserDto (User user){
        this.id = user.getId();
        this.userName = user.getUserName();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.created = user.getCreated();
        this.updated = user.getUpdated();
        this.status = user.getStatus();
        this.roles = user.getRoles().stream().map(role -> new RoleDto(role)).collect(Collectors.toList());
    }







}