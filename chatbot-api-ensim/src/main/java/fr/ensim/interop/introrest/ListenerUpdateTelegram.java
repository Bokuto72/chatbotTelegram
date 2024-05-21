package fr.ensim.interop.introrest;

import fr.ensim.interop.introrest.model.telegram.ApiResponseUpdateTelegram;
import fr.ensim.interop.introrest.model.telegram.Message;
import fr.ensim.interop.introrest.model.telegram.Update;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

// TODO


@Component
public class ListenerUpdateTelegram implements CommandLineRunner {

	private Integer updateId = 389257981;

	@Override
	public void run(String... args) throws Exception {
		Logger.getLogger("ListenerUpdateTelegram").log(Level.INFO, "Démarage du listener d'updates Telegram...");
		
		// Operation de pooling pour capter les evenements Telegram
		Timer timer = new Timer();
		long delay = 5000L;
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				System.out.println("Pooling...");
				ApiResponseUpdateTelegram apiResponseUpdateTelegram = new ApiResponseUpdateTelegram();
				apiResponseUpdateTelegram.getResult().add(getUpdates(updateId));
			}
		};
		timer.schedule(timerTask, delay);
	}

	@GetMapping("/getUpdates")
	public Update getUpdates(@RequestParam("offset") Integer offset) {
		Update update = new Update();
		if(updateId != offset) {

		}
		System.out.println(update.getMessage().getText());
		return update;
	}
}
