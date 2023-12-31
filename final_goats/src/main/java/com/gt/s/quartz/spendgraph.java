package com.gt.s.quartz;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gt.s.common.CodeUtil;
import com.gt.s.common.CommonUtils;
import com.gt.s.common.DateFormatUtil;
import com.gt.s.common.KosConnectivity;
import com.gt.s.kos.flask.controller.FlaskController;
import com.gt.s.kos.spend.service.GtSpendService;
import com.gt.s.kos.spend.vo.GtSpendVO;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;

@Component
public class spendgraph {

//	Logger log = LogManager.getLogger(spendgraph.class);
//	
//	
//	@Scheduled(cron = "0 0 6 * * *")
//	public void insertMongoDB() {
//		log.info("10초 지났음");
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rsRs = null;
//		GtSpendVO kbvo = null;
//		ArrayList<GtSpendVO> list = null;
//		
//		conn = KosConnectivity.getConnection();
//		
//		String sql = "select * from goat_spend";
//		
//		log.info("셀렉트 올 쿼리 >> : \n" + sql);
//		
//		try {
//			pstmt = conn.prepareStatement(sql);
//			rsRs = pstmt.executeQuery();
//			if (rsRs != null) {
//				list = new ArrayList<GtSpendVO>();
//				while (rsRs.next()) {
//					kbvo = new GtSpendVO();
//					
//					kbvo.setGsgubun(rsRs.getString("GSGUBUN"));
//					kbvo.setGscount(rsRs.getString("GSCOUNT"));
//					kbvo.setGscost(rsRs.getString("GSCOST"));
//					kbvo.setCount(rsRs.getString("COUNT"));
//					kbvo.setApprover(rsRs.getString("APPROVER"));
//					list.add(kbvo);
//				}
//				log.info("aList >> : " + list.size());
//			}
//			
//			KosConnectivity.conClose(conn, pstmt, rsRs);
//		} catch (Exception e) {
//			
//			log.info("셀렉트 올 쿼리 연결시 에러 >> : " + e.getMessage());
//		} finally {
//			KosConnectivity.conClose(conn, pstmt, rsRs);
//		}
//    	try {     
//	        String MongoDB_IP = "localhost";
//	        int MongoDB_PORT = 27017;
//	        String DB_NAME = "goats";
//	     
//	        //Connect to MongoDB
//	        MongoClient  mongoClient = new MongoClient(new ServerAddress(MongoDB_IP, MongoDB_PORT));
//	        DB db = mongoClient.getDB(DB_NAME);
//	        DBCollection collection = db.getCollection("spend");
//	        //Insert Test Data
//	        List<DBObject> datalist = new ArrayList<DBObject>();
//	        List<DBObject> datalist_ = new ArrayList<DBObject>();
//	        Calendar cal = Calendar.getInstance();
//			int cost_[] = new int[13];
//			for(int i=0; i<cost_.length; i++) {
//				cost_[i] = 0;
//			}
//			log.info("여긴 나오는가 6");
//			if(list != null) {
//				log.info("여긴 나오는가 6");
//				for(int i=0; i<list.size(); i++) {
//					GtSpendVO gvo = list.get(i);
//					log.info("여긴 나오는가 6");
//					String app = gvo.getApprover();
//					String gubun = gvo.getGsgubun();
//					String count = gvo.getGscount();
//					String cost = gvo.getGscost();
//					String appcount = gvo.getCount();
//					if(Integer.parseInt(appcount) > 0) {
//						log.info("appcount >>> : " + appcount);
//						log.info("여긴 나오는가 6");
//						String s =CodeUtil.edsmjeoncheck(app, Integer.parseInt(appcount));
//						String ss = CodeUtil.edsmcheck(app, Integer.parseInt(appcount));
//						log.info("s > : " + s);
//						log.info("ss > : " + ss);
//						log.info("여긴 나오는가 6");
//						if(!s.equals("") || ss.equals("OK")) {
//							String[] gubuns = gubun.split(",");
//							String[] counts = count.split(",");
//							String[] costs = cost.split(",");
//							log.info("여긴 나오는가 6");
//							for(int ii=0; ii<gubuns.length; ii++) {
//								
//								if(!gubuns[ii].equals("-선택-")) {
//									
//									for(int x=0; x<CommonUtils.s.length; x++) {
//										
//										if(gubuns[ii].equals(CommonUtils.s[x])) {
//											log.info("여긴 나오는가 6");
//											cost_[x] += Integer.parseInt(counts[ii]) * Integer.parseInt(costs[ii]);
//										}
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//			log.info("여긴 나오는가 6");
//	        for (int i=0; i<CommonUtils.s.length; i++) {
//	            BasicDBObject data = new BasicDBObject();
//	            data.append(CommonUtils.s[i], Integer.toString(cost_[i]));
//	            System.out.println(data);
//	            datalist.add(data);
//	        }
//	        log.info(datalist);
//            BasicDBObject data = new BasicDBObject();
//            data.append(DateFormatUtil.documentFormat(), datalist);
//            datalist_.add(data);
//            log.info(datalist_);
//	        collection.insert(datalist_);
//	    	}catch(Exception e) {
//	    		log.info("MongoDB 등록시 에러 >>> : " + e + e.getMessage());
//	    	}
//	}
//	
//	@Scheduled(cron="0 0/10 * * * *")
//	public void news() throws Exception{
//		log.info("File Check Test run...");
//		log.info("======================");
//		
//		FlaskController fc = new FlaskController();
//		
//		fc.flask_test();
//		
//	}
}
