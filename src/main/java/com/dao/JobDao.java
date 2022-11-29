package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.dto.Jobs;
import com.dto.Registration;

public class JobDao {
	public static final String QUERY_1 = "INSERT INTO JOBPORTAL_JOBVIEWDETAILS(ID,TITLE,DESCRIPTION,CATEGORY,STATUS,LOCATION,NAME,PDATE) VALUES(JOBPORTAL_APPLYJOB_SQ.NEXTVAL,?,?,?,?,?,?,?)";
	public static final String QUERY_2 = "SELECT ID,TITLE,DESCRIPTION,CATEGORY,STATUS,LOCATION,PDATE FROM JOBPORTAL_JOBVIEWDETAILS WHERE NAME=?";
	public static final String QUERY_3 = "SELECT ID,TITLE,DESCRIPTION,CATEGORY,STATUS,LOCATION,PDATE FROM JOBPORTAL_JOBVIEWDETAILS WHERE ID=?";
	public static final String QUERY_4 = "UPDATE JOBPORTAL_JOBVIEWDETAILS SET TITLE=?,DESCRIPTION=?,CATEGORY=?,STATUS=?,LOCATION=? WHERE ID=?";
	public static final String QUERY_5 = "DELETE FROM JOBPORTAL_JOBVIEWDETAILS WHERE ID=?";
	public static final String QUERY_6 = "INSERT INTO JOBPORTAL_REGISTRATIONDETAILS(ID,NAME,EMAIL,PASSWORD,QUALIFICATION,DESIGNATION,ROLE) VALUES(JOBPORTAL_SQ.NEXTVAL,?,?,?,?,?,?)";
	// public static final String QUERY_7 = "SELECT NAME FROM
	// JOBPORTAL_REGISTRATIONDETAILS";
	public static final String QUERY_7 = "SELECT TITLE,DESCRIPTION,CATEGORY,STATUS,LOCATION,PDATE FROM JOBPORTAL_JOBVIEWDETAILS";

	private Connection conn;

	public JobDao(Connection conn) {

		this.conn = conn;
	}

	public boolean postJobs(Jobs j) {
		boolean f = false;
		try {
			PreparedStatement ps = conn.prepareStatement(QUERY_1);
			ps.setString(1, j.getTitle());
			ps.setString(2, j.getDescription());
			ps.setString(3, j.getCategory());
			ps.setString(4, j.getStatus());
			ps.setString(5, j.getLocation());
			ps.setString(6, j.getName());
			
			ps.setDate(7, j.getPdate());

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

	public List<Jobs> getAllJobs(String name) {
		List<Jobs> list = new ArrayList<Jobs>();
		Jobs j = null;
		try {
			PreparedStatement ps = conn.prepareStatement(QUERY_2);

			ps.setString(1, name);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				j = new Jobs();

				j.setId(rs.getInt(1));
				j.setTitle(rs.getString(2));
				j.setDescription(rs.getString(3));
				j.setCategory(rs.getString(4));
				j.setStatus(rs.getString(5));
				j.setLocation(rs.getString(6));		
				j.setPdate(rs.getDate(7));
				 System.out.println(j.getPdate());
				list.add(j);
			}
			
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public Jobs getJobById(int id) {
		Jobs j = null;
		try {
			PreparedStatement ps = conn.prepareStatement(QUERY_3);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				j = new Jobs();
				j.setId(rs.getInt(1));
				j.setTitle(rs.getString(2));
				j.setDescription(rs.getString(3));
				j.setCategory(rs.getString(4));
				j.setStatus(rs.getString(5));
				j.setLocation(rs.getString(6));
				 j.setPdate(rs.getDate(7));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}

	public boolean updateJob(Jobs j) {
		boolean f = false;
		try {
			PreparedStatement ps = conn.prepareStatement(QUERY_4);
			ps.setString(1, j.getTitle());
			ps.setString(2, j.getDescription());
			ps.setString(3, j.getCategory());
			ps.setString(4, j.getStatus());
			ps.setString(5, j.getLocation());
			ps.setInt(6, j.getId());

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

	public boolean deleteJobs(int id) {
		boolean f = false;
		try {
			PreparedStatement ps = conn.prepareStatement(QUERY_5);
			ps.setInt(1, id);

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

	public boolean insertRegisrationDetails(Registration j) {
		boolean f = false;
		try {
			PreparedStatement ps = conn.prepareStatement(QUERY_6);
			ps.setString(1, j.getName());
			ps.setString(2, j.getEmail());
			ps.setString(3, j.getPassword());
			ps.setString(4, j.getQualification());
			ps.setString(5, j.getDesignation());
			ps.setString(6, j.getRole());

			int i = ps.executeUpdate();

			if (i == 1) {
				f = true;

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}

	public List<Jobs> getAllJobs() {
		List<Jobs> list = new ArrayList<Jobs>();
		Jobs j = null;
		try {

			PreparedStatement ps = conn.prepareStatement(QUERY_7);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				j = new Jobs();

				j.setTitle(rs.getString(1));
				j.setDescription(rs.getString(2));
				j.setCategory(rs.getString(3));
				j.setStatus(rs.getString(4));
				j.setLocation(rs.getString(5));
				 j.setPdate(rs.getDate(6));
				list.add(j);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}


//CREATE TABLE "SYSTEM"."JOBPORTAL_JOBVIEWDETAILS" 
// (	"ID" NUMBER NOT NULL ENABLE, 
//	"TITLE" VARCHAR2(150 BYTE) NOT NULL ENABLE, 
//	"DESCRIPTION" VARCHAR2(500 BYTE) NOT NULL ENABLE, 
//	"CATEGORY" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
//	"STATUS" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
//	"LOCATION" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
//	"NAME" VARCHAR2(40 BYTE) NOT NULL ENABLE, 
//	"PDATE" TIMESTAMP (6), 
//	 CONSTRAINT "JOBPORTAL_JOBVIEWDETAILS_PK" PRIMARY KEY ("ID")
//USING INDEX PCTFREE 10 INITRANS 2 MAXTRANS 255 COMPUTE STATISTICS 
//STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
//PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
//BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
//TABLESPACE "SYSTEM"  ENABLE
// ) PCTFREE 10 PCTUSED 40 INITRANS 1 MAXTRANS 255 
//NOCOMPRESS LOGGING
//STORAGE(INITIAL 65536 NEXT 1048576 MINEXTENTS 1 MAXEXTENTS 2147483645
//PCTINCREASE 0 FREELISTS 1 FREELIST GROUPS 1
//BUFFER_POOL DEFAULT FLASH_CACHE DEFAULT CELL_FLASH_CACHE DEFAULT)
//TABLESPACE "SYSTEM" ;



//CREATE SEQUENCE  "SYSTEM"."JOBPORTAL_APPLYJOB_SQ"  MINVALUE 1 MAXVALUE 9999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;
//  CREATE SEQUENCE  "SYSTEM"."JOBPORTAL_SQ"  MINVALUE 1 MAXVALUE 9999999 INCREMENT BY 1 START WITH 41 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

