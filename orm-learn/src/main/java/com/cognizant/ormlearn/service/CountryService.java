package com.cognizant.ormlearn.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cognizant.ormlearn.CountryRepository;
import com.cognizant.ormlearn.model.Country;
import com.cognizant.springlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {
	@Autowired
	CountryRepository countryRepository;

	@Transactional
	public List<Country> getAllCountries() {
		return countryRepository.findAll();
	}

	@Transactional
	public Country findCountryByCode(String countryCode) throws CountryNotFoundException {
		Optional<Country> result = countryRepository.findById(countryCode);
		if (!result.isPresent())
			throw new CountryNotFoundException();
		Country country = result.get();
		return country;

	}

	@Transactional
	public void addCountry(Country country) {
		countryRepository.save(country);
	}

	@Transactional
	public void updateCountry(String code, String name) throws CountryNotFoundException {
		Optional<Country> result = countryRepository.findById(code);
		if (!result.isPresent())
			throw new CountryNotFoundException();
		Country country = result.get();
		country.setName(name);
		countryRepository.save(country);

	}

	@Transactional
	public void deleteCountry(String code) throws CountryNotFoundException {
		try {
			countryRepository.deleteById(code);
		} catch (Exception e) {
			throw new CountryNotFoundException();
		}

	}

	@Transactional
	public List<Country> findCountryByCharacters(String characters) throws CountryNotFoundException {
		List<Country> result = countryRepository.findByNameContainingOrderByNameAsc(characters);
		if (result == null || result.isEmpty())
			throw new CountryNotFoundException();
		return result;
	}

	@Transactional
	public List<Country> findCountryByStartingLetter(String startingLetter) throws CountryNotFoundException {
		List<Country> result = countryRepository.findByNameStartingWithOrderByNameAsc(startingLetter);
		if (result == null || result.isEmpty())
			throw new CountryNotFoundException();
		return result;
	}

}
