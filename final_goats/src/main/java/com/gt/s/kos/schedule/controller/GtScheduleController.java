package com.gt.s.kos.schedule.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.FormSubmitEvent.MethodType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gt.s.common.DateFormatUtil;
import com.gt.s.common.KosConnectivity;
import com.gt.s.kos.board.vo.GtBoardVO;
import com.gt.s.kos.project.service.GtProjectService;
import com.gt.s.kos.project.vo.GtProjectVO;
import com.gt.s.kos.schedule.service.GtScheduleService;
import com.gt.s.kos.schedule.vo.GtScheduleVO;


@Controller
public class GtScheduleController {
   Logger log = LogManager.getLogger(GtScheduleController.class);
   
   
   @Autowired(required = false)
   private GtScheduleService gtScheduleService;
   @Autowired(required = false)
	GtProjectService gtProjectService;

   @RequestMapping(value = "mainpage", method = {RequestMethod.GET, RequestMethod.POST})
   public String gtScheduleSelect(HttpServletRequest req, Model m, GtScheduleVO svo) {
//	  if(svo.getGpstart() == null) {
//		  svo.setGpstart(DateFormatUtil.ymdFormat());
//	  }
//	   Connection conn = KosConnectivity.getConnectionMysql();
//	   PreparedStatement pstmt = null;
//	   ResultSet rsRs = null;
//	   List<GtBoardVO> list = null;
//	   list = new ArrayList<GtBoardVO>();
//	   try {
//		   pstmt = conn.prepareStatement( "SELECT * "
//		   								+ "FROM GOAT_BOARD "
//		   								+ "WHERE DELETEYN = 'Y' "
//		   								+ "AND insertrdate <= CURDATE()"
//		   								+ "AND deleterdate >= CURDATE()"
//		   								+ "ORDER BY 1 DESC			   ");
//		   rsRs = pstmt.executeQuery();
//		   if(rsRs != null) {
//			   
//			   while(rsRs.next()) {
//				   GtBoardVO bvo = new GtBoardVO();
//				   bvo.setGbnum(rsRs.getInt(1));
//				   bvo.setGbsubject(rsRs.getString(2));
//				   bvo.setGename(rsRs.getString(3));
//				   bvo.setGbmemo(rsRs.getString(4));
//				   bvo.setGbcnt(rsRs.getInt(5));
//				   bvo.setGbfile(rsRs.getString(6));
//				   bvo.setDeleteyn(rsRs.getString(7));
//				   bvo.setInsertdate(rsRs.getString(8));
//				   bvo.setUpdatedate(rsRs.getString(9));
//				   bvo.setInsertrdate(rsRs.getString(10));
//				   bvo.setDeleterdate(rsRs.getString(11));
//				   list.add(bvo);
//			   }
//		   }
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//
//      log.info(svo.getGpstart());
//      log.info(list);
//      List<GtScheduleVO> listVa = gtScheduleService.gtScheduleSelectVa(svo);
//      List<GtScheduleVO> listBu = gtScheduleService.gtScheduleSelectBu(svo);
//      List<GtProjectVO> listPr = gtProjectService.gtProjectSelectAll();
//      
//      String selectday = req.getParameter("selectday");
//      
//      log.info("컨트롤러에서 받은 dd >> selectday >>> : " +selectday);
//      m.addAttribute("list", list);
//      m.addAttribute("selectday", selectday);
//      m.addAttribute("listVa", listVa);
//      m.addAttribute("listBu", listBu);
//      m.addAttribute("listPr" , listPr);
      
      

         return "/main/mainpage";
         

   } // end of gtScheduleSelect 
   
} // end of class