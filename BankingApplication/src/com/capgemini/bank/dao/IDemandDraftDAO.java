package com.capgemini.bank.dao;
import java.util.List;
import com.capgemini.bank.bean.DemandDraft;
import com.capgemini.bank.exception.BankException;
public interface IDemandDraftDAO {
	
		public int addDemandDraftDetails(DemandDraft demandDraft) throws BankException;
		public DemandDraft getDemandDraftDetails(int transactionId) throws BankException;
		
	}


