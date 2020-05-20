package com.care.bank;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.care.bank_dto.BankDTO;
import com.care.bank_service.BankDepositServiceImpl;
import com.care.bank_service.BankSendServiceImpl;
import com.care.bank_service.BankService;
import com.care.bank_service.BankShowServiceImpl;



/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	private BankService bs;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping("form")
	public String form() {
		return "form";
	}
	
	
	@RequestMapping("deposit")
	public String deposit() {
		return "deposit";
	}
	
	@RequestMapping("send")
	public String send() {
		return "send";
	}
	
	@RequestMapping("deposit_balance")
	public String deposit_balance(BankDTO dto, Model model) {
		bs = new BankDepositServiceImpl();
		model.addAttribute("dto", dto);
		bs.execute(model);
		return "redirect:balance";
	}
	
	@RequestMapping("send_balance")
	public String send_balance(BankDTO dto, Model model) {
		bs = new BankSendServiceImpl();
		model.addAttribute("dto", dto);
		System.out.println("==========================================controller 실행 중?");
		bs.execute(model);
		return "redirect:balance";
	}
	
	@RequestMapping("balance")
	public String balance(Model model) {
		bs = new BankShowServiceImpl();
		model.addAttribute(model);
		bs.execute(model);
		return "balance";
	}
	
	
	
}
