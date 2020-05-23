package com.uca.capas.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.uca.capas.dao.ContribuyenteDAO;
import com.uca.capas.dao.ImportanciaDAO;
import com.uca.capas.domain.Contribuyente;

@Service
public class ContribuyenteServiceImpl implements ContribuyenteService{
	
	@Autowired
	ContribuyenteDAO contribuyenteDao;
	
	@Autowired
	ImportanciaDAO importanciaService;
	
	public List<Contribuyente> findAll() throws DataAccessException {
		return contribuyenteDao.findAll();
	}
	
	@Transactional
	public void save(Contribuyente c) throws DataAccessException {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		
		String fecha = dtf.format(now);
		
		try {
			c.setFechaIngreso(new SimpleDateFormat("dd/MM/yyyy").parse(fecha));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		try {
			c.setImportancia(importanciaService.findOne(c.getcImportancia()));
			contribuyenteDao.save(c);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
