package com.care.bank_service;

import java.util.Map;

import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.ui.Model;
import com.care.bank_dao.BankDAO;


public class BankShowServiceImpl implements BankService{
	private BankDAO dao;
	public BankShowServiceImpl() {
		String config = "classpath:applicationJDBC.xml";
		GenericXmlApplicationContext ctx = new GenericXmlApplicationContext(config);
		dao = ctx.getBean("dao", BankDAO.class);
	}
	@Override
	public void execute(Model model) {
		Map<String, Object> map = model.asMap();
		int totMoney = 0;
		totMoney = dao.show();
		System.out.println("==========================service : " +  totMoney);
		model.addAttribute("totMoney", totMoney);
		
	}

}
