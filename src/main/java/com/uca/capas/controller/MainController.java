package com.uca.capas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uca.capas.domain.Contribuyente;
import com.uca.capas.service.ContribuyenteService;
import com.uca.capas.service.ImportanciaService;

@Controller
public class MainController {

	@Autowired
	ContribuyenteService contribuyenteService;
	
	@Autowired
	ImportanciaService importanciaService;
	
	@RequestMapping("/index")
	public ModelAndView index() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("contribuyente", new Contribuyente());
		mav.addObject("importancias", importanciaService.findAll());
		mav.setViewName("inicio");
		
		return mav;
	}
	
	@RequestMapping("/listado")
	public ModelAndView listado() {
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("contribuyentes", contribuyenteService.findAll());
		mav.setViewName("listado");
		
		return mav;
	}
	
	@RequestMapping("/ingresar")
	public ModelAndView ingresar(@ModelAttribute Contribuyente contribuyente) {
		ModelAndView mav = new ModelAndView();
			
		try {
			contribuyenteService.save(contribuyente);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mav.setViewName("exito");
		
		return mav;
	}
}
