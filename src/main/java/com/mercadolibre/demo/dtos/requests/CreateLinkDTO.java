package com.mercadolibre.demo.dtos.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Validated
@AllArgsConstructor
public class CreateLinkDTO {


    @NotNull(message = "You must enter a 'url' parameter")
    @NotBlank(message = "The url parameter cannot be empty")
    @Pattern(message = "The url entered is not valid", regexp = "(@)?(href=')?(HREF=')?(HREF=\")?(href=\")?(http://)?(https://)?[a-zA-Z_0-9\\-]+(\\.\\w[a-zA-Z_0-9\\-]+)+(/[#&\\n\\-=?\\+\\%/\\.\\w]+)?")
    private String url;

    @NotNull(message = "You must enter the 'password' parameter to use in the redirection")
    @Pattern(message = "The password must have between 8 and 16 characters, at least one digit, " +
            "at least one lower case and at least one upper case. " +
            "May have other symbols",regexp = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$")
    private String password;
}
