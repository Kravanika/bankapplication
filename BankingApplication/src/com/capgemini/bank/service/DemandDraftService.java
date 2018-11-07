package com.capgemini.bank.service;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.capgemini.bank.exception.BankException;
import com.capgemini.bank.bean.DemandDraft;
import com.capgemini.bank.dao.DemandDraftDAO;
import com.capgemini.bank.dao.IDemandDraftDAO;

public class DemandDraftService implements IDemandDraftService {
	
	IDemandDraftDAO demanddraft;
	
	//------------------------ 1. Donor Application --------------------------
	/*******************************************************************************************************
	 - Function Name	:	addDonorDetails
	 - Input Parameters	:	donor object
	 - Return Type		:	String id
	 - Throws			:  	DonorException
	 - Author			:	CAPGEMINI
	 - Creation Date	:	11/11/2016
	 - Description		:	adding donor to database calls dao method addDonorDetails(donor)
	 ********************************************************************************************************/
	public int addDemandDraftDetails(DemandDraft demandDraft) throws BankException {
		demanddraft =new DemandDraftDAO();	
		int transaction_Seq;
		transaction_Seq= demanddraft.addDemandDraftDetails(demandDraft);
		return transaction_Seq; 
	}
	//------------------------ 1. Donor Application --------------------------
	/*******************************************************************************************************
	 - Function Name	:	viewDonorDetails
	 - Input Parameters	:	String donorId
	 - Return Type		:	donor object
	 - Throws		    :  	DonorException
	 - Author		    :	CAPGEMINI
	 - Creation Date	:	18/11/2016
	 - Description		:	calls dao method viewDonorDetails(donorId)
	 ********************************************************************************************************/
/*	public DonorBean viewDonorDetails(String donorId) throws DonorException {
		donorDao=new DonorDaoImpl();
		DonorBean bean=null;
		bean=donorDao.viewDonorDetails(donorId);
		return bean;
	}*/
	//------------------------ 1. Donor Application --------------------------
	/*******************************************************************************************************
	 - Function Name	: retriveAll()
	 - Input Parameters	:	
	 - Return Type		: list
	 - Throws		    : DonorException
	 - Author	      	: CAPGEMINI 
	 - Creation Date	: 18/11/2016
	 - Description		: calls dao method retriveAllDetails()
	 ********************************************************************************************************/
	public DemandDraft getDemandDraftDetails(int transactionId) throws BankException {
		demanddraft=new DemandDraftDAO();
		DemandDraft demanddraftlist=null;
		demanddraftlist=demanddraft.getDemandDraftDetails(1);
		return demanddraftlist;
	}
	
	
	/*******************************************************************************************************
	 - Function Name	: validateDonor(DonorBean bean)
	 - Input Parameters	: DonorBean bean
	 - Return Type		: void
	 - Throws		    : DonorException
	 - Author	      	: CAPGEMINI
	 - Creation Date	: 18/11/2016
	 - Description		: validates the DonorBean object
	 ********************************************************************************************************/
	public void validateDemand(DemandDraft demanddraft) throws BankException
	{
		List<String> validationErrors = new ArrayList<String>();
		//Validating donor name
		if(!(isValidName(demanddraft.getCustomer_name()))) {
			validationErrors.add("\n Donar Name Should Be In Alphabets and minimum 3 characters long ! \n");
		}
		//Validating address
		//if(!(isValidAddress(demanddraft.getIn_favor_of()))){
			validationErrors.add("\n Address Should Be Greater Than 5 Characters \n");
		//}
		//Validating Phone Number
		if(!(isValidPhoneNumber(demanddraft.getPhone_number()))){
			validationErrors.add("\n Phone Number Should be in 10 digit \n");
		}
		//Validating Donation Amount
		if(!(isValidAmount(demanddraft.getDd_amount()))){
			validationErrors.add("\n Amount Should be a positive Number \n" );
		}
		if(!(isValidInt(demanddraft.getDd_commission()))){
			validationErrors.add("\n Amount Should be a positive Number \n" );
		}
		if(!(isValidDd_description(demanddraft.getDd_description()))){
			validationErrors.add("\n Amount Should be a positive Number \n" );
		}
		
		if(!validationErrors.isEmpty())
			throw new BankException(validationErrors +"");
	}
	private boolean isValidInt(int dd_commission) {
		// TODO Auto-generated method stub
		
		return false;
	}
	private boolean isValidDd_description(String dd_description) {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean isValidName(String donorName){
		Pattern namePattern=Pattern.compile("^[A-Za-z]{3,}$");
		Matcher nameMatcher=namePattern.matcher(donorName);
		return nameMatcher.matches();
	}
//	public boolean isValidAddress(String address){
	//	return (address.length() > 6);
//	}
	
	public boolean isValidPhoneNumber(String phoneNumber){
		Pattern phonePattern=Pattern.compile("^[1-9]{1}[0-9]{9}$");
		Matcher phoneMatcher=phonePattern.matcher(phoneNumber);
		return phoneMatcher.matches();
		
	}
	public boolean isValidAmount(double amount){
		return (amount>0);
	}
	public boolean validatetransactionId(int transactionId) {
		
		Pattern idPattern = Pattern.compile("[0-9]{1,4}");
	
			return false;		
	}
}
