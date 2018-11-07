package com.capgemini.bank.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import com.capgemini.bank.bean.DemandDraft;
import com.capgemini.bank.exception.BankException;
import com.capgemini.bank.service.DemandDraftService;
import com.capgemini.bank.service.IDemandDraftService;
public class Client {
	static Scanner sc = new Scanner(System.in);
	static IDemandDraftService IdemandService = null;
	static DemandDraftService demanddraftService = null;
	static Logger logger = Logger.getRootLogger();
	public static void main(String[] args) {
		PropertyConfigurator.configure("resources//log4j.properties");
		DemandDraft demanddraft = null;
		int transactionId = 0;
		int option = 0;
		while (true) {
			// show menu
			System.out.println();
			System.out.println();
			System.out.println("   ICARE CAPGEMINI TRUST ");
			System.out.println("_______________________________\n");
			System.out.println("1.Add Demand draft ");
			System.out.println("2.Print Details");
			System.out.println("3.Exit");
			System.out.println("________________________________");
			System.out.println("Select an option:");
			// accept option
			try {
				option = sc.nextInt();
				switch (option) {
				case 1:
					while (demanddraft == null) {
						demanddraft = populateDemanddraft();
						// System.out.println(donorBean);
					}
					try {
						IdemandService = new DemandDraftService();
						transactionId = IdemandService.addDemandDraftDetails(demanddraft);
						System.out
								.println("Donator details  has been successfully registered ");
						System.out.println("Donator  ID Is: " + transactionId);
					} catch (BankException bankException) {
						logger.error("exception occured", bankException);
						System.out.println("ERROR : "
								+ bankException.getMessage());
					} finally {
						transactionId = 0;
						IdemandService = null;
						demanddraft = null;
					}
					break;
	
	

		
			case 2:
				demanddraftService = new DemandDraftService();
				System.out.println("Enter numeric transcation id:");
					transactionId = sc.nextInt();
					while (true) {
						if (demanddraftService.validatetransactionId(transactionId)) {
							break;
						} else {
							System.err
									.println("Please enter numeric donor id only, try again");
							transactionId = sc.nextInt();
						}
					}
					demanddraft = getDemandDraftDetails(transactionId);
					if (demanddraft != null) {
						System.out.println("Name             :"
								+ demanddraft.getCustomer_name());
						System.out.println("Address          :"
								+ demanddraft.getIn_favor_of());
						System.out.println("Phone Number     :"
								+ demanddraft.getPhone_number());
						System.out.println("Transaction Amount       :"
								+ demanddraft.getDd_amount());
						System.out.println("Dd commission :"
								+ demanddraft.getDd_commission());
						System.out.println("Dd description :"
								+ demanddraft.getDd_description());
					} else {
						System.err
								.println("There are no donation details associated with donor id "
										+ transactionId);
					}
					break;  
			
				case 3:
					System.out.print("Exit Trust Application");
					System.exit(0);
					break;
				default:
					System.out.println("Enter a valid option[1-4]");
				}// end of switch
			}
			catch (InputMismatchException e) {
				sc.nextLine();
				System.err.println("Please enter a numeric value, try again");
			}
	}
			}
	

	// end of while
// end of try
	/*
	 * This function will call the service layer method and return the bean
	 * object which is populated by the information of the given donorId in
	 * parameter
	 */
	private static DemandDraft getDemandDraftDetails(int transactionId) {
		DemandDraft demanddraft = null;
		demanddraftService = new DemandDraftService();
		try {
			demanddraft = demanddraftService.getDemandDraftDetails(transactionId);
		} catch (BankException bankException) {
			logger.error("exception occured ", bankException);
			System.out.println("ERROR : " + bankException.getMessage());
		}
		demanddraftService = null;
		return demanddraft;
	}
			
	/*
	 * This function will be called by main and will return a validated bean
	 * object OR null if details are invalid
	 */
	private static DemandDraft populateDemanddraft() {
		// Reading and setting the values for the donorBean
		
		DemandDraft demanddraft = new DemandDraft();;
		System.out.println("\n Enter Details");
		System.out.println("Enter customer name: ");
		demanddraft.setCustomer_name(sc.next());
		System.out.println("In favor ");
		demanddraft.setIn_favor_of(sc.next());
		System.out.println("Enter phone number: ");
		demanddraft.setPhone_number(sc.next());
		System.out.println("Enter dd amount: ");
		try {
			demanddraft.setDd_amount(sc.nextInt());
		} catch (InputMismatchException inputMismatchException) {
			sc.nextLine();
			System.err
					.println("Please enter a numeric value for donation amount, try again");
			}
		System.out.println("Enter dd commission: ");
		demanddraft.setDd_commission(sc.nextInt());
		System.out.println("Enter dd description: ");
		demanddraft.setDd_description(sc.next());
		
		demanddraftService = new DemandDraftService();
		try {
			demanddraftService.validateDemand(demanddraft);
			return demanddraft;
		} catch (BankException bankException) {
			logger.error("exception occured", bankException);
			System.err.println("Invalid data:");
			System.err.println(bankException.getMessage() + " \n Try again..");
			System.exit(0);
		}
		return null;
	}
}