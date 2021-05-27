package com.flipkart.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.flipkart.bean.Credit;
import com.flipkart.bean.Debit;
import com.flipkart.bean.Payment;
import com.flipkart.bean.Scholarship;
import com.flipkart.bean.Upi;
import com.flipkart.constants.StatusConstants;
import com.flipkart.utils.DBUtils;

public class PaymentsDao implements PaymentsDaoInterface{
	
	private static Logger logger = Logger.getLogger(PaymentsDao.class);
	private static volatile PaymentsDao instance = null;
	
	private PaymentsDao() {};
	
	public static PaymentsDao getInstance() {
		if (instance == null) {
			synchronized(PaymentsDao.class){
				instance = new PaymentsDao();
			}
		}
		return instance;
	}

	@Override
	public StatusConstants addTransaction(String studentId, Payment details) {
		Connection conn = null;
		PreparedStatement prep_stmt = null;
		try {
			conn = DBUtils.getConnection();
			String raw_stmt = "insert into payments values(?,?,?,?,?,?)";
			prep_stmt = conn.prepareStatement(raw_stmt);
			prep_stmt.setString(1, studentId);
			prep_stmt.setString(2, details.getTransactionId());
			prep_stmt.setString(3, details.getAmount());
			prep_stmt.setString(4, details.getTimestamp());
			prep_stmt.setString(6, details.getStatus().toString());
			if (details instanceof Debit) {
				prep_stmt.setString(5, ((Debit) details).getNumber());
			}
			else if (details instanceof Credit) {
				prep_stmt.setString(5, ((Credit) details).getNumber());
			}
			else if (details instanceof Scholarship) {
				prep_stmt.setString(5, ((Scholarship) details).getScholarshipId());
			}
			else if (details instanceof Upi) {
				prep_stmt.setString(5, ((Upi) details).getUpiId());
			}
			prep_stmt.executeUpdate();
			return StatusConstants.SUCCESS;
		}catch(SQLException se){
			logger.error(se.getMessage());
		}catch(Exception e){
		    logger.error(e.getMessage());
		}finally{
			try{
		    	if(prep_stmt!=null)
		            prep_stmt.close();
		    }catch(SQLException se2){
		    	logger.error(se2.getMessage());
		    }
		}
		return StatusConstants.FAIL;
	}
	
}
