package com.dao;

/*CREATE TABLE "SYSTEM"."JOBPORTAL_APPLYJOB" 
(	"ID" NUMBER NOT NULL ENABLE, 
	"FIRSTNAME" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
	"LASTNAME" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
	"DATEOFBIRTH" DATE NOT NULL ENABLE, 
	"YEAROFGRADUATION" DATE NOT NULL ENABLE, 
	"EXPERIENCE" NUMBER NOT NULL ENABLE, 
	"TECHNOLOGIESKNOWN" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
	"PHONENUMBER" NUMBER(14,0) NOT NULL ENABLE, 
	"ALTERNATEPHNUMBER" NUMBER(14,0) NOT NULL ENABLE, 
	"EMAIL" VARCHAR2(40 BYTE) NOT NULL ENABLE, 
	"RESUME" VARCHAR2(200 BYTE) NOT NULL ENABLE, 
	"PHOTO" VARCHAR2(200 BYTE) NOT NULL ENABLE, 
	"ADDRESS" VARCHAR2(200 BYTE) NOT NULL ENABLE, 
	"ROLE" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
	"COLLEGENAME" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
	 CONSTRAINT "JOBPORTAL_APPLYJOB_PK" PRIMARY KEY ("ID"))*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.cassandra.thrift.Cassandra.system_add_column_family_args;

import com.dto.Apply;


public class ApplyDao {
	public static final String QUERY_1 = "INSERT INTO JOBPORTAL_APPLYJOB(ID,FIRSTNAME,LASTNAME,DATEOFBIRTH,YEAROFGRADUATION,EXPERIENCE,TECHNOLOGIESKNOWN,"
										+ "PHONENUMBER,ALTERNATEPHNUMBER,EMAIL,RESUME,PHOTO,ADDRESS,ROLE,COLLEGENAME,OWNER,COUNT) VALUES(JOBPORTAL_APPLYJOB_SQ.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	public static final String QUERY_2="SELECT ID,YEAROFGRADUATION,EXPERIENCE,TECHNOLOGIESKNOWN,FIRSTNAME,ADDRESS FROM JOBPORTAL_APPLYJOB WHERE OWNER=?";
	public static final String QUERY_3="SELECT FIRSTNAME,LASTNAME,DATEOFBIRTH,YEAROFGRADUATION,EXPERIENCE,TECHNOLOGIESKNOWN,"
			+ "PHONENUMBER,ALTERNATEPHNUMBER,EMAIL,COLLEGENAME,RESUME,PHOTO,ROLE,ADDRESS,COUNT FROM JOBPORTAL_APPLYJOB WHERE ID=?";
	public static final String QUERY_4="UPDATE JOBPORTAL_APPLYJOB SET COUNT=? WHERE ID=?";
	private Connection conn;

	public ApplyDao(Connection conn) {
		this.conn = conn;
	}
	public Apply getMoreDetails(int id) {
		Apply a=new Apply();
		System.out.println(id);
		int count1;
		try {
			
		PreparedStatement ps = conn.prepareStatement(QUERY_3);
		
		ps.setInt(1,id);
		
		ResultSet rs = ps.executeQuery();
		rs.next();
		a.setFirstName(rs.getString(1));
		  a.setLastName(rs.getString(2));
		  a.setDateOfBirth(rs.getDate(3));
		  a.setYearOfGraduation(rs.getString(4));
		  a.setExperience(rs.getInt(5));
		  a.setTechnologiesKnown(rs.getString(6));
		  a.setPhoneNumber(rs.getLong(7));
		  a.setAlternatePhoneNumber(rs.getLong(8));
		  a.setEmail(rs.getString(9));
		  a.setCollegeName(rs.getString(10));
		  a.setResume(rs.getString(11));
		  a.setPhoto(rs.getString(12));
		  a.setRole(rs.getString(13));
		  a.setAddress(rs.getString(14));
		  a.setCount(rs.getInt(15));
		  count1=a.getCount()+1;
		  PreparedStatement ps1 = conn.prepareStatement(QUERY_4);
		  ps1.setInt(1,count1);
		  ps1.setInt(2, id);
		  ps1.executeUpdate(); 
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return a;  
	}
	public List<Apply> getAllAppliedJobs(String name) {
		System.out.println("ApplyDao.getAllAppliedJobs()");
		List<Apply> list = new ArrayList<Apply>();
		Apply j = null;
		try {
			PreparedStatement ps = conn.prepareStatement(QUERY_2);
	
			ps.setString(1,name);
			
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				j=new Apply();
				System.out.println(rs.getString(2));
				j.setId(rs.getInt(1));
				j.setYearOfGraduation(rs.getString(2));
				j.setExperience(rs.getInt(3));
				j.setTechnologiesKnown(rs.getString(4));
				j.setFirstName(rs.getString(5));
				j.setAddress(rs.getString(6));
				list.add(j);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("out");
		return list;
	}

	
	public boolean ApplyJob(Apply a) {
		boolean f = false;
		try {
			System.out.println("%%%%%%%%%%%%%%%%%% Entered try block of Query");
			PreparedStatement ps = conn.prepareStatement(QUERY_1);
			ps.setString(1, a.getFirstName());
			ps.setString(2, a.getLastName());
			ps.setDate(3, a.getDateOfBirth());	
			ps.setString(4, a.getYearOfGraduation());	
			ps.setInt(5, a.getExperience());
			ps.setString(6, a.getTechnologiesKnown());
			ps.setLong(7, a.getPhoneNumber());
			ps.setLong(8, a.getAlternatePhoneNumber());
			ps.setString(9, a.getEmail());
			ps.setString(10, a.getResume());
			ps.setString(11, a.getPhoto());
			ps.setString(12, a.getAddress());
			ps.setString(13, a.getRole());
			ps.setString(14, a.getCollegeName());
			ps.setString(15, a.getOwner());
			ps.setInt(16, a.getCount());
			System.out.println("################### in before query execution");
			int i = ps.executeUpdate();
			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% in after query execution");
			if (i == 1) {
				System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@ in query executed");
				f = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return f;

	}
}

//CREATE TABLE "SYSTEM"."JOBPORTAL_APPLYJOB" 
// (	"ID" NUMBER, 
//	"FIRSTNAME" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
//	"LASTNAME" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
//	"DATEOFBIRTH" DATE NOT NULL ENABLE, 
//	"YEAROFGRADUATION" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
//	"EXPERIENCE" NUMBER NOT NULL ENABLE, 
//	"TECHNOLOGIESKNOWN" VARCHAR2(30 BYTE) NOT NULL ENABLE, 
//	"PHONENUMBER" NUMBER(14,0) NOT NULL ENABLE, 
//	"ALTERNATEPHNUMBER" NUMBER(14,0) NOT NULL ENABLE, 
//	"EMAIL" VARCHAR2(40 BYTE) NOT NULL ENABLE, 
//	"RESUME" VARCHAR2(200 BYTE) NOT NULL ENABLE, 
//	"PHOTO" VARCHAR2(200 BYTE) NOT NULL ENABLE, 
//	"ADDRESS" VARCHAR2(200 BYTE) NOT NULL ENABLE, 
//	"ROLE" VARCHAR2(20 BYTE) NOT NULL ENABLE, 
//	"COLLEGENAME" VARCHAR2(100 BYTE) NOT NULL ENABLE, 
//	"OWNER" VARCHAR2(60 BYTE), 
//	"COUNT" NUMBER NOT NULL ENABLE, 
//	 CONSTRAINT "JOBPORTAL_APPLYJOB_PK" PRIMARY KEY ("ID")
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

//SEQUENCE GENERATOR
//CREATE SEQUENCE  "SYSTEM"."JOBPORTAL_APPLYJOB_SQ"  MINVALUE 1 MAXVALUE 9999999 INCREMENT BY 1 START WITH 61 CACHE 20 NOORDER  NOCYCLE  NOKEEP  NOSCALE  GLOBAL ;

