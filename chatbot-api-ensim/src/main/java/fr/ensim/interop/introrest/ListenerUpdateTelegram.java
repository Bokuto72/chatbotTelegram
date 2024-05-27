package fr.ensim.interop.introrest;

import fr.ensim.interop.introrest.controller.MessageRestController;
import fr.ensim.interop.introrest.model.telegram.ApiResponseUpdateTelegram;
import fr.ensim.interop.introrest.model.telegram.Message;
import fr.ensim.interop.introrest.model.telegram.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class ListenerUpdateTelegram implements CommandLineRunner {
	private Integer updateId = 389257989;

	@Autowired
	private MessageRestController messageRestController;

	@Override
	public void run(String... args) throws Exception {
		Logger.getLogger("ListenerUpdateTelegram").log(Level.INFO, "Démarage du listener d'updates Telegram...");
		
		// Operation de pooling pour capter les evenements Telegram
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				System.out.println("Pooling...");

				try {
					ApiResponseUpdateTelegram apiResponseUpdateTelegram = messageRestController.getUpdates(updateId).getBody();
                    if(!apiResponseUpdateTelegram.getResult().isEmpty()) {
						List<Update> updates = apiResponseUpdateTelegram.getResult();
						updateId = updates.get(0).getUpdateId()+1;
						String message = updates.get(0).getMessage().getText();
						System.out.println("message : " + message);
						traitementMessage(message);
					}
				} catch (Exception e) {
					Logger.getLogger("ListenerUpdateTelegram").log(Level.WARNING, e.getMessage());
				}
			}
		}, 0, 5000);
	}

	private void traitementMessage(String message) {
		if(message.toLowerCase().contains("meteo")) {

		}
		if(message.toLowerCase().contains("blague")) {
			
		}
	}
}
