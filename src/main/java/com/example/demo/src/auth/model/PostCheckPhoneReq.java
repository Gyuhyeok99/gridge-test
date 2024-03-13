package com.example.demo.src.auth.model;

import com.example.demo.common.validation.annotation.PhoneForm;
import com.example.demo.common.validation.annotation.PhoneUnique;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostCheckPhoneReq {

    @NotNull
    @PhoneUnique
    @Size(max = 20)
    @PhoneForm
    private String phoneNumber;
}
