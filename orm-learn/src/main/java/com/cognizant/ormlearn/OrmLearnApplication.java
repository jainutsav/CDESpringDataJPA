package com.cognizant.ormlearn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Stock;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
@SpringBootApplication
public class OrmLearnApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);
	private static CountryService countryService;
	@Autowired
	static StockRepository stockRepository;
	public static void main(String[] args) throws CountryNotFoundException {
		LOGGER.info("Inside main");

		ApplicationContext context = SpringApplication.run(OrmLearnApplication.class, args);

		countryService = context.getBean(CountryService.class);
		stockRepository=context.getBean(StockRepository.class);
		testGetAllCountries();
		getAllCountriesTest();
		testAddCountry();
		updateCountryTest();
		deleteCountryTest();
		findCountryByCharactersTest();
		findCountryByStartingLetterTest();
		getFbSep2019Test();
		getGoogl1250Test();
		getTop3VolumeTest();
		getLeast3NflxTest();
	}

	private static void testGetAllCountries() {

		LOGGER.info("Start");

		List<Country> countries = countryService.getAllCountries();

		LOGGER.debug("countries={}", countries);

		LOGGER.info("End");

	}

	private static void getAllCountriesTest() throws CountryNotFoundException {

		LOGGER.info("Start");

		Country country = countryService.findCountryByCode("IN");

		LOGGER.debug("Country:{}", country);

		LOGGER.info("End");

	}

	private static void testAddCountry() throws CountryNotFoundException {
		LOGGER.info("Start");

		Country testCountry = new Country();
		testCountry.setCode("TC");
		testCountry.setName("Test Country");
		countryService.addCountry(testCountry);
		Country country = countryService.findCountryByCode("TC");

		LOGGER.debug("Country:{}", country);

		LOGGER.info("End");
	}

	private static void updateCountryTest() throws CountryNotFoundException {

		LOGGER.info("Start");

		countryService.updateCountry("TC", "Trial Country");

		LOGGER.info("End");

	}

	private static void deleteCountryTest() throws CountryNotFoundException {
		LOGGER.info("Start");

		countryService.deleteCountry("TC");

		LOGGER.info("End");

	}

	private static void findCountryByCharactersTest() throws CountryNotFoundException {
		LOGGER.info("Start");

		List<Country> countries = countryService.findCountryByCharacters("ou");
		LOGGER.debug("countries={}", countries);

		LOGGER.info("End");
	}

	private static void findCountryByStartingLetterTest() throws CountryNotFoundException {
		LOGGER.info("Start");

		List<Country> countries = countryService.findCountryByStartingLetter("Z");
		LOGGER.debug("countries={}", countries);

		LOGGER.info("End");
	}
	
	private static void getFbSep2019Test() {

		LOGGER.info("Start");

		List<Stock> stockList = stockRepository.findByCodeAndDateLike("FB","2019-9%");

		LOGGER.debug("stockList={}", stockList);

		LOGGER.info("End");

	}
	
	private static void getGoogl1250Test() {

		LOGGER.info("Start");

		List<Stock> stockList = stockRepository.findByCodeAndOpenGreaterThanAndCloseGreaterThan("GOOGL", BigDecimal.valueOf(1250), BigDecimal.valueOf(1250));

		LOGGER.debug("stockList={}", stockList);

		LOGGER.info("End");

	}
	
	private static void getTop3VolumeTest() {

		LOGGER.info("Start");

		List<Stock> stockList = stockRepository.findTop3ByOrderByVolumeDesc();

		LOGGER.debug("stockList={}", stockList);

		LOGGER.info("End");

	}
	
	private static void getLeast3NflxTest() {

		LOGGER.info("Start");

		List<Stock> stockList = stockRepository.findTop3ByCodeOrderByClose("NFLX");

		LOGGER.debug("stockList={}", stockList);

		LOGGER.info("End");

	}
}
