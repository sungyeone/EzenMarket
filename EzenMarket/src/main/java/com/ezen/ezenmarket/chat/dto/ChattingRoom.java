package com.ezen.ezenmarket.chat.dto;

import com.ezen.ezenmarket.product.dto.Post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ChattingRoom extends Post{
	
	private Integer chattingRoom_id;
	private Integer seller_user_number;
	private Integer buyer_user_number;
	private Integer post_id;
	
}
