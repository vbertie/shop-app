package server.electronics.order.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
public class CustomerDto implements Serializable {

    @Null
    private Long id;

    @NotNull
    @Size(min = 2, max = 40)
    private final String firstName;

    @NotNull
    @Size(min = 2, max = 40)
    private final String lastName;

    @Email
    @NotNull
    @Size(min = 1, max = 40)
    private final  String email;

    @NotBlank
    @Pattern(regexp ="(^$|[0-9]{9})")
    private final String phoneNumber;

    @NotNull
    @Pattern(regexp ="(^[0-9]{2}(?:-[0-9]{3})?$)")
    private final  String zipCode;

    @NotNull
    @Size(min = 1, max = 40)
    private final  String town;

    @NotNull
    @Size(min = 1, max = 40)
    private final  String province;

    @NotNull
    @Size(min = 1, max = 30)
    private final  String street;

    public CustomerDto(){
        this.firstName = "";
        this.lastName = "";
        this.email = "";
        this.phoneNumber = "";
        this.zipCode = "";
        this.town = "";
        this.province = "";
        this.street = "";
    }
}
