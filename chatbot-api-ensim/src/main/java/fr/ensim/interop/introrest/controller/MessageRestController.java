package fr.ensim.interop.introrest.controller;

import fr.ensim.interop.introrest.model.telegram.ApiResponseUpdateTelegram;
import fr.ensim.interop.introrest.model.telegram.Message;
import fr.ensim.interop.introrest.model.telegram.Update;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class MessageRestController {
	
	@Value("${telegram.api.url}")
	private String telegramApiUrl;
	private String telegramApiToken = "6861595645:AAGZl6Ztk5kq9GcnZgrqAizESF_w-0Kj3pg";
	@Value("${telegram.bot.id}")
	private String telegramBotId;
	private long chatId = 6984281278L;


	//Opérations sur la ressource Message
	@PostMapping("/sendMessage")
	public ResponseEntity<Message> postMessage(@RequestBody Message message) {
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	public ApiResponseUpdateTelegram getUpdates(Integer offset) {
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<ApiResponseUpdateTelegram> responseEntity = restTemplate.getForEntity(telegramApiUrl + telegramApiToken + "/getUpdates&offset={offset}", ApiResponseUpdateTelegram.class, offset);



		return responseEntity.getBody();
	}
}