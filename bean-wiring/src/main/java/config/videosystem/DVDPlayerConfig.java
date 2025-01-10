package config.videosystem;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import videosystem.Avengers;
import videosystem.DVDPlayer;
import videosystem.DigitalVideoDisc;
import videosystem.IronMan;

@Configuration
public class DVDPlayerConfig {
	@Bean
	public DigitalVideoDisc avengers() { // id: avengers
		return new Avengers();
	}
	
	@Bean
	public DigitalVideoDisc ironMan() {
		return new IronMan();
	}
	
	// 의존성 주입(Dependency Injection)하기 1
	// bean 생성 메소드(avengers())를 직접 호출하는 방법
	// 생성자 주입
	@Bean
	public DVDPlayer dvdPlayer1() {
		return new DVDPlayer(avengers());
	}
	
	@Bean("DVDPlayer2nd")
	public DVDPlayer dvdPlayer2(IronMan dvd) {
		return new DVDPlayer(dvd);
	}
	
	// setter 주입
	@Bean
	public DVDPlayer dvdPlayer3(@Qualifier("ironMan") DigitalVideoDisc dvd) {
		DVDPlayer dvdPlayer = new DVDPlayer(dvd);
		dvdPlayer.setDvd(dvd);
		
		
		return dvdPlayer;
	}
}
