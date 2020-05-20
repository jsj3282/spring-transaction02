package com.care.bank_service;

import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;

import com.care.bank_dao.BankDAO;
import com.care.bank_dto.BankDTO;


public class BankDepositServiceImpl implements BankService{
	private BankDAO dao;
	public BankDepositServiceImpl() {
		String config = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(config);
		dao = ctx.getBean("dao", BankDAO.class);
	}
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		BankDTO dto = (BankDTO)map.get("dto");
		int arr[] = new int[3];
		arr = dao.deposit(dto);
		model.addAttribute("arr", arr);
		
	}

}
