package com.gms.web.dao;

import java.sql.Connection;


import java.sql.PreparedStatement;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gms.web.command.Command;
import com.gms.web.constants.DB;
import com.gms.web.constants.SQL;
import com.gms.web.constants.Vendor;
import com.gms.web.domain.MajorBean;
import com.gms.web.domain.MemberBean;
import com.gms.web.domain.StudentBean;
import com.gms.web.factory.DatabaseFactory;


public class MemberDAOImpl implements MemberDAO {
   Connection conn;
   public static MemberDAOImpl getInstance() {
      return new MemberDAOImpl();
   }
   private MemberDAOImpl(){
      conn=null;
   }

   
   @Override
   public String insert(Map<?,?>map) {
      int rs = 0;
      MemberBean member=(MemberBean)map.get("member");
      
      @SuppressWarnings("unchecked")
      List<MajorBean> list =(List<MajorBean>)map.get("major");
      PreparedStatement pstmt = null;
      try {
         conn= DatabaseFactory.createDatabase(Vendor.MARIADB, DB.USERNAME, DB.PASSWORD)
               .getConnection();
         conn.setAutoCommit(false);
         pstmt=conn.prepareStatement(SQL.MEMBER_INSERT);
         pstmt.setString(1, member.getUserId());
         pstmt.setString(2, member.getName());
         pstmt.setString(3, member.getUserPw());
         pstmt.setString(4, member.getSsn());
         pstmt.setString(5, member.getPhone());
         pstmt.setString(6, member.getEmail());
         pstmt.setString(7, member.getUserId() + ".jpg");
         pstmt.executeUpdate();
         for(int i=0;i<list.size();i++){
         pstmt=conn.prepareStatement(SQL.MAJOR_INSERT);
         pstmt.setString(1, list.get(i).getMajorId());
         pstmt.setString(2, list.get(i).getTitle());
         pstmt.setString(3, list.get(i).getUserId());
         pstmt.setString(4, list.get(i).getSubjId());
         rs=pstmt.executeUpdate();
         }
         conn.commit();
      }catch (SQLException e) {
         e.printStackTrace();
         if(conn!=null){
            try {
               conn.rollback();
            } catch (SQLException ex) {
               e.printStackTrace();
            }
         }
      }
      return String.valueOf(rs);
   }

   @Override
   public List<StudentBean> selectAll(Command cmd) {
      List<StudentBean> list = new ArrayList<>();
      try {
         conn= DatabaseFactory.createDatabase(Vendor.MARIADB, DB.USERNAME, DB.PASSWORD).getConnection();
         PreparedStatement pstmt=conn.prepareStatement(SQL.STUDENT_LIST);
         pstmt.setString(1, cmd.getStartRow());
         pstmt.setString(2, cmd.getEndRow());
         ResultSet rs=pstmt.executeQuery();
         StudentBean student = null;
         while (rs.next()) {
            student = new StudentBean();
            System.out.println("멤버디에이오넘버"+rs.getString(DB.NUM));
            student.setNum(String.valueOf(rs.getInt(DB.NUM)));
            student.setUserId(rs.getString(DB.ID));
            student.setName(rs.getString(DB.MEM_NAME));
            student.setEmail(rs.getString(DB.MEM_EMAIL));
            student.setSsn(rs.getString(DB.MEM_SSN));
            student.setRegdate(rs.getString(DB.MEM_REGDATE));
            student.setPhone(rs.getString(DB.MEM_PHONE));
            student.setTitle(rs.getString(DB.SUBJECTS));
            list.add(student);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return list;
   }

   @Override
   public String count(Command cmd) {
      System.out.println("count****"+cmd.getSearch());
      System.out.println("count****"+cmd.getColumn());
      int count=0;
      try {
      conn = DatabaseFactory.createDatabase(Vendor.MARIADB, DB.USERNAME, DB.PASSWORD).getConnection()
               .prepareStatement(SQL.STUDENT_COUNT).getConnection();
      PreparedStatement pstmt=null;
      
      if(cmd.getSearch()==null){
            System.out.println("cmd.getSearch() is null");
            
            pstmt=conn.prepareStatement(SQL.STUDENT_COUNT);
            pstmt.setString(1, "%");
      }
         else{
            System.out.println("cmd.getSearch() is not null");
            pstmt=conn.prepareStatement(SQL.STUDENT_COUNT);
            pstmt.setString(1, "%"+cmd.getSearch()+"%");
         }
      
         ResultSet rs= pstmt.executeQuery();
         
         if(rs.next()){count = Integer.parseInt(rs.getString("count"));}
         
   
      } catch (SQLException e) {
         
         e.printStackTrace();
      }
      System.out.println("count"+count);
      return String.valueOf(count);
   }

   @Override
   public StudentBean selectById(Command cmd) {
      StudentBean member = null;
      try {
         PreparedStatement pstmt = DatabaseFactory.createDatabase(Vendor.MARIADB, DB.USERNAME, DB.PASSWORD)
               .getConnection().prepareStatement(SQL.STUDENT_FINDBYID);
         pstmt.setString(1, cmd.getSearch());
         ResultSet rs = pstmt.executeQuery();
         if (rs.next()) {
            member = new StudentBean();
            member.setUserId(rs.getString(DB.ID));
            member.setName(rs.getString(DB.MEM_NAME));
            member.setEmail(rs.getString(DB.MEM_EMAIL));
            member.setSsn(rs.getString(DB.MEM_SSN));
            member.setRegdate(rs.getString(DB.MEM_REGDATE));
            member.setPhone(rs.getString(DB.MEM_PHONE));
            member.setTitle(rs.getString(DB.SUBJECTS));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return member;
   }

   @Override
   public List<StudentBean> selectByName(Command cmd) {
      System.out.println("selectByName(멤버DAO임플)"+cmd.getSearch());
      System.out.println("selectByName(멤버DAO임플)"+cmd.getColumn());
      List<StudentBean> list = new ArrayList<>();
   
      try {
         PreparedStatement pstmt = DatabaseFactory.createDatabase(Vendor.MARIADB, DB.USERNAME, DB.PASSWORD)
               .getConnection().prepareStatement(SQL.STUDENT_SEARCH);
         pstmt.setString(1, "%"+cmd.getSearch()+"%");
         
         ResultSet rs = pstmt.executeQuery();
         StudentBean member = null;
         while (rs.next()) {
            member = new StudentBean();
            member.setUserId(rs.getString(DB.ID));
            member.setName(rs.getString(DB.MEM_NAME));
            member.setSsn(rs.getString(DB.MEM_SSN));
            member.setRegdate(rs.getString(DB.MEM_REGDATE));
            list.add(member);
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return list;
   }

   @Override
   public String update(MemberBean bean) {
      String rs = "";
      try {
         PreparedStatement pstmt = DatabaseFactory.createDatabase(Vendor.MARIADB, DB.USERNAME, DB.PASSWORD)
               .getConnection().prepareStatement(SQL.MEMBER_UPDATE);
         pstmt.setString(1,bean.getUserPw() );
         pstmt.setString(2,bean.getUserId());
               rs = String.valueOf(pstmt.executeUpdate());
      } catch (SQLException e) {
         e.printStackTrace();
      }

      return rs;
   }

   @Override
   public String delete(Command cmd) {
      int rs = 0;
      try {
         PreparedStatement pstmt = DatabaseFactory.createDatabase(Vendor.MARIADB, DB.USERNAME, DB.PASSWORD)
               .getConnection().prepareStatement(SQL.MEMBER_DELETE);
         pstmt.setString(1, cmd.getSearch());
         rs = pstmt.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return String.valueOf(rs);
   }
   @Override
   public MemberBean login(Command cmd) {
      MemberBean member = null;
      try {
         PreparedStatement pstmt = DatabaseFactory.createDatabase(Vendor.MARIADB, DB.USERNAME, DB.PASSWORD)
               .getConnection().prepareStatement(SQL.MEMBER_FINDBYID);
         pstmt.setString(1, cmd.getSearch());
         ResultSet rs = pstmt.executeQuery();
         if (rs.next()) {
            member=new MemberBean();
            member.setUserId(rs.getString(DB.MEM_ID));
            member.setName(rs.getString(DB.MEM_NAME));
            member.setUserPw(rs.getString(DB.MEM_PW));
            member.setSsn(rs.getString(DB.MEM_SSN));
            member.setRegdate(rs.getString(DB.MEM_REGDATE));
         }
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return member;

   }

   
}