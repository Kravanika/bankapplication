package com.capgemini.bank.dao;
	 import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.ArrayList;
	import java.util.List;
	 import org.apache.log4j.Logger;
	import org.apache.log4j.PropertyConfigurator;
	 import com.capgemini.bank.bean.DemandDraft;
	import com.capgemini.bank.exception.BankException;
	import com.capgemini.bank.util.DBConnection;
	 public class DemandDraftDAO implements IDemandDraftDAO 
	{
		
		Logger logger=Logger.getRootLogger();
		
		PropertyConfigurator.configure("resources//log4j.properties");
	}
		
		
	 	//------------------------ 1. Donor Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	addDonorDetails(DonorBean donor)
		 - Input Parameters	:	DonorBean donor
		 - Return Type		:	String
		 - Throws			:  	DonorException
		 - Author			:	CAPGEMINI
		 - Creation Date	:	18/11/2016
		 - Description		:	Adding Donor
		 ********************************************************************************************************/
	 	public int  addDemandDraftDetails(DemandDraft demanddraft) throws BankException 
		{
			Connection connection = DBConnection.getInstance().getConnection();	
			
			PreparedStatement preparedStatement=null;		
			ResultSet resultSet = null;
			
			int transactionId=0;
			
			int queryResult=0;
			try
			{		
				preparedStatement=connection.prepareStatement(QueryMapper.INSERT_QUERY);
	 			preparedStatement.setString(1,demanddraft.getCustomer_name());			
				preparedStatement.setString(2,demanddraft.getIn_favor_of());
				preparedStatement.setString(3,demanddraft.getPhone_number());
				preparedStatement.setDate(4,(Date) demanddraft.getDate_transaction());
				preparedStatement.setInt(5,demanddraft.getDd_amount());
				preparedStatement.setInt(6,demanddraft.getDd_commission());
				preparedStatement.setString(7,demanddraft.getDd_description());
				
				queryResult=preparedStatement.executeUpdate();
			
				preparedStatement = connection.prepareStatement(QueryMapper.TRANSACTIONID_QUERY_SEQUENCE);
				resultSet=preparedStatement.executeQuery();
	 			if(resultSet.next())
				{
					transactionId=resultSet.getInt(1);
							
				}
		
				if(queryResult==0)
				{
					logger.error("Insertion failed ");
					throw new BankException("Inserting bank details failed ");
	 			}
				else
				{
					logger.info("Bank details added successfully:");
					return transactionId;
				}
	 		}
			catch(SQLException sqlException)
			{
				sqlException.printStackTrace();
				logger.error(sqlException.getMessage());
				throw new BankException("Tehnical problem occured refer log");
			}
	 		finally
			{
				try 
				{
					//resultSet.close();
					preparedStatement.close();
					connection.close();
				}
				catch (SQLException sqlException) 
				{
					sqlException.printStackTrace();
					logger.error(sqlException.getMessage());
					throw new BankException("Error in closing db connection");
	 			}
			}
			
			
		}
	 	//------------------------ 1. Donor Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	viewDonorDetails(String donorId)
		 - Input Parameters	:	donorId
		 - Return Type		:	DonorBean
		 - Throws			:  	DonorException
		 - Author			:	CAPGEMINI
		 - Creation Date	:	18/11/2016
		 - Description		:	ViewDonorDetails
		 ********************************************************************************************************/
	public DemandDraft getDemandDraftDetails(int transactionId) throws BankException {
			
			Connection connection=DBConnection.getInstance().getConnection();
			
			
			PreparedStatement preparedStatement=null;
			ResultSet resultset = null;
			DemandDraft demanddraft=null;
			
			try
			{
				preparedStatement=connection.prepareStatement(QueryMapper.GET_ALL_DETAILS_QUERY);
				preparedStatement.setInt(1,transactionId);
				resultset=preparedStatement.executeQuery();
				
				if(resultset.next())
				{
					demanddraft = new DemandDraft();
					demanddraft.setCustomer_name(resultset.getString(1));
					demanddraft.setIn_favor_of(resultset.getString(2));
					demanddraft.setPhone_number(resultset.getString(3));
					demanddraft.setDate_transaction(resultset.getDate(4));
					demanddraft.setDd_amount(resultset.getInt(5));
					demanddraft.setDd_commission(resultset.getInt(5));
					demanddraft.setDd_description(resultset.getString(5));
					
				}
				
				if( demanddraft != null)
				{
					logger.info("Record Found Successfully");
					return demanddraft;
				}
				else
				{
					logger.info("Record Not Found Successfully");
					return null;
				}
				
			}
			catch(Exception e)
			{
				logger.error(e.getMessage());
				throw new BankException(e.getMessage());
			}
			finally
			{
				try 
				{
					resultset.close();
					preparedStatement.close();
					connection.close();
				} 
				catch (SQLException e) 
				{
					logger.error(e.getMessage());
					throw new BankException("Error in closing db connection");
	 			}
			}
	}
	}
			
	 	//------------------------ 1. Donor Application --------------------------
		/*******************************************************************************************************
		 - Function Name	:	retriveAllDetails()
		 - Input Parameters	:	
		 - Return Type		:	List
		 - Throws		    :  	DonorException
		 - Author	     	:	CAPGEMINI
		 - Creation Date	:	18/11/2016
		 - Description		:	return list
		 ********************************************************************************************************/
	 /*	public DemandDraft getDemandDraftDetails(int transactionId) throws BankException {
			
			Connection con=DBConnection.getInstance().getConnection();
			int demandCount = 0;
			
			PreparedStatement ps=null;
			ResultSet resultset = null;
			
			DemandDraft demanddraftList=new DemandDraft();
			try
			{
				ps=con.prepareStatement(QueryMapper.GET_ALL_DETAILS_QUERY);
				resultset=ps.executeQuery();
				
				while(resultset.next())
				{	
					DemandDraft demand = new DemandDraft();
					demand.setCustomer_name(resultset.getString(1));
					demand.setIn_favor_of(resultset.getString(2));
					demand.setPhone_number(resultset.getString(3));
					demand.setDate_transaction(resultset.getDate(4));
					demand.setDd_amount(resultset.getInt(5));
					demand.setDd_commission(resultset.getInt(5));
					demand.setDd_description(resultset.getString(5));
					
					((Object) demanddraftList).add(demand);
					
					demandCount++;
				}			
				
			} catch (SQLException sqlException) {
				logger.error(sqlException.getMessage());
				throw new BankException("Tehnical problem occured. Refer log");
			}
			
			finally
			{
				try 
				{
					resultset.close();
					ps.close();
					con.close();
				} 
				catch (SQLException e) 
				{
					logger.error(e.getMessage());
					throw new BankException("Error in closing db connection");
	 			}
			}
			
			if( demandCount == 0)
				return null;
			else
				return demanddraftList;
	
}

} */
