package com.NovelBookOnline.NovelBookOnline.DTO.Request.Auth;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IntrospectRequest {
    private String token;
}
