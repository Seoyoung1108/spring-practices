package config.videosystem.mixing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import config.videosystem.DVDPlayerConfig;
import videosystem.DVDPlayer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes={DVDPlayerConfig.class})
public class DVDPlayerMixingConfigTest01 {
	@Autowired
	private DVDPlayer dvdPlayer;
	
	@Test
	public void test() {
		assertEquals("Playing Movie Marvel's Avengers", dvdPlayer.play());
	}
}
