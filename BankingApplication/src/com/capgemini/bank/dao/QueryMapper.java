package com.capgemini.bank.dao;

public class QueryMapper {
    public static final String GET_ALL_DETAILS_QUERY="SELECT customer_name,in_favor_of,phone_numner,date_transaction,dd_amount,dd_commission,dd_description FROM demand_draft";
	//public static final String VIEW_DONAR_DETAILS_QUERY="SELECT donor_name,address,phone_number,donor_date,donor_amount FROM donor_details WHERE  donor_id=?";
	public static final String INSERT_QUERY="INSERT INTO demand_draft VALUES(transactionId_sequence.NEXTVAL,?,?,?,SYSDATE,?,?,?)";
	public static final String TRANSACTIONID_QUERY_SEQUENCE="SELECT transactionId_sequence.CURRVAL FROM DUAL";
	
}
