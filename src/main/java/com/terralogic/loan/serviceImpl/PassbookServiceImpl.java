package com.terralogic.loan.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.terralogic.loan.model.Passbook;
import com.terralogic.loan.service.PassbookService;

@Service

public class PassbookServiceImpl implements PassbookService {
	HashMap<Long, Long> map1 = new HashMap<>();

	HashMap<Long, List<String>> map = new HashMap<>();

	@Override
	public String addLoan(Passbook pass) {
		// TODO Auto-generated method stub
		Passbook p = new Passbook();
		p.setAccountNo(pass.getAccountNo());
		p.setDate(pass.getDate());
		p.setTime(pass.getTime());
		p.setEmi(pass.getEmi());
		Set<Long> keySet = map1.keySet();
		ArrayList<Long> arrayList = new ArrayList<>(keySet);
		if (arrayList.contains(pass.getAccountNo())) {
			p.setBalance(map1.get(p.getAccountNo()));
		}

		List<Passbook> l1 = new ArrayList<Passbook>(Arrays.asList(p));
		for (Passbook p1 : l1) {
			if (map.containsKey(p1.getAccountNo())) {
				map.get(p1.getAccountNo()).add(p1.getAccountNo() + " " + p1.getDate() + " " + p1.getTime() + " "
						+ p1.getEmi() + " " + p1.getBalance());

			} else {
				map.put(p1.getAccountNo(), new ArrayList<String>());
				map.get(p1.getAccountNo()).add(p1.getAccountNo() + " " + p1.getDate() + " " + p1.getTime() + " "
						+ p1.getEmi() + " " + p1.getBalance());
			}
		}

		return "transaction added for accountNo " + pass.getAccountNo();
	}

	@Override
	public List<String> getAllTransactionByAccountNo(long accountNo) {
		// TODO Auto-gnerated method stub
		List<String> passbook = null;
		for (Entry<Long, List<String>> eachEntry : map.entrySet()) {
			boolean flag = eachEntry.getKey() == accountNo;
			if (!flag) {
				continue;
			} else {
				passbook = eachEntry.getValue();
				return passbook;
			}
		}
		return null;
	}

	@Override
	public HashMap<Long, Long> saveLoan(Passbook pass) {
		Passbook p1 = new Passbook();
		p1.setAccountNo(pass.getAccountNo());
		p1.setLoanAmount(pass.getLoanAmount());
		map1.put(p1.getAccountNo(), p1.getLoanAmount());
		return map1;
		// TODO Auto-generated method stub

	}

	@Override
	public String payEmi(long accountNo, Passbook pass) {
		// TODO Auto-generated method stub
		Set<Long> keySet = map1.keySet();
		ArrayList<Long> arrayList = new ArrayList<>(keySet);
		if (arrayList.contains(accountNo)) {
			Passbook p1 = new Passbook();
			long amt = map1.get(accountNo) - pass.getEmi();
			if (amt > 0)
				p1.setLoanAmount(map1.get(accountNo) - pass.getEmi());
			map1.put(accountNo, p1.getLoanAmount());
			return "Emi is paid successfully and your balance is " + p1.getLoanAmount();
		} else {
			return "there is no loan on accountNo ";
		}
	}

}
