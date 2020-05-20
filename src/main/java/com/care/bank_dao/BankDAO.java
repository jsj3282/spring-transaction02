package com.care.bank_dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import com.care.bank_dto.BankDTO;


public class BankDAO {
	private JdbcTemplate template;
	private TransactionTemplate transactionTemplate;
	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	public int[] deposit(BankDTO dto) {
		String sql_user = "insert into myaccount(num, money) values(bank_num.nextval,?)";
		String sql_system = "insert into sysaccount(num, money) values(bank_num.nextval,?)";
		String sql_balance = "update balance set num = bank_num.nextval, totmoney = ?";
		final int arr[] = new int[3];
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					arr[0] = template.update(sql_user, ps->{
						ps.setInt(1,  dto.getMoney());
						System.out.println(dto.getMoney());
					});
					arr[1] = template.update(sql_system, ps->{
						ps.setInt(1,  dto.getMoney());
					});
					arr[2] = template.update(sql_balance, ps->{
						ps.setInt(1,  show()+dto.getMoney());
					});
				};
			});	
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return arr;
		
	}
	public int[] send(BankDTO dto) {
		String sql_user = "insert into myaccount(num, money) values(bank_num.nextval,?)";
		String sql_system = "insert into sysaccount(num, money) values(bank_num.nextval,?)";
		String sql_balance = "update balance set num = bank_num.nextval, totmoney = ?";
		final int arr[] = new int[3];
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
					arr[0] = template.update(sql_user, ps->{
						ps.setInt(1,  -dto.getMoney());
						System.out.println(dto.getMoney());
					});
					arr[1] = template.update(sql_system, ps->{
						ps.setInt(1,  -dto.getMoney());
					});
					arr[2] = template.update(sql_balance, ps->{
						System.out.println(show());
						ps.setInt(1,  show()-dto.getMoney());
					});
				};
			});	
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return arr;
	}
	public int show() {
		String sql_balance = "select totmoney from balance";
		int totMoney = 0;
		try {
			totMoney =  template.queryForObject(sql_balance, Integer.class);
		}catch(EmptyResultDataAccessException e) {
			e.printStackTrace();
		}
		System.out.println("========================================DB : " + totMoney);
		return totMoney;
	}
}
