package com.capgemini.bank.service;
	 import java.util.List;
	 import com.capgemini.bank.bean.DemandDraft;
	import com.capgemini.bank.exception.BankException;
	 public interface IDemandDraftService
	{

			public int addDemandDraftDetails(DemandDraft demandDraft) throws BankException;
			public DemandDraft getDemandDraftDetails(int transactionId) throws BankException;

}
