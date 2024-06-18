package com.sw1.demo.clientews.model;

import lombok.Data;

@Data
public class ResponseLogin {
	public String token_type;
	public String access_token;
	public int expires_in;
}
