package com.ssy.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.ssy.DBUtil;
import com.ssy.member.MemberDTO;

public class ProductDAO {

	Connection conn = null;
	Statement stat = null;
	PreparedStatement pstat = null;
	ResultSet rs = null;
	
	public ProductDAO() {
		conn = DBUtil.open();
	}

	
	

	/**
	    * @author 유동환
	    * <p>검색어의 유무에 따라 검색어와 클라이언트에 응답할 카테고리별로 상품들을 리스트를 담아 반환하는 메소드.</p>
	    * @param  map 
	    * @return ArrayList
	    */
	   public ArrayList<ProductDTO> productList(HashMap<String,String> map) {

	      try {
	         
	         if(map.get("word").equals("")) {
	         String sql = "select p.*,(select sStore from tblSeller where sSeq = p.sSeq) as sstore from tblProduct p where cSeq = ? and post='Y'";
	         pstat = conn.prepareStatement(sql);
	         pstat.setString(1, map.get("cSeq"));
	         rs = pstat.executeQuery();
	         } else {
	            String sql = "select p.*,s.sName,s.sStore from tblProduct p inner join tblSeller s on p.sSeq = s.sSeq where post='Y' and p.pName like '%'||?||'%' or s.sName like '%'||?||'%' or s.sStore like '%'||?||'%'";
	            pstat = conn.prepareStatement(sql);
	            pstat.setString(1, map.get("word"));
	            pstat.setString(2, map.get("word"));
	            pstat.setString(3, map.get("word"));
	            rs = pstat.executeQuery();
	            
	         }
	      
	         ArrayList<ProductDTO> list = new ArrayList<>(); 
	         while(rs.next()) {
	            ProductDTO dto = new ProductDTO();
	            dto.setSseq(rs.getString("sseq"));
	            dto.setPname(rs.getString("pname"));
	            dto.setPimage(rs.getString("pimage"));
	            
	            dto.setPprice(rs.getString("pprice"));
	            dto.setPseq(rs.getString("pseq"));
	            dto.setPorigin(rs.getString("porigin"));
	            dto.setPquantity(rs.getString("pquantity"));
	            dto.setPdiscount(rs.getString("pdiscount"));
	            dto.setPrefundox(rs.getString("prefundOX"));
	            dto.setPamount(rs.getString("pamount"));
	            dto.setDseq(rs.getString("dseq"));
	            dto.setSseq(rs.getString("sseq"));
	            dto.setCseq(rs.getString("cseq"));
	            dto.setStorename(rs.getString("sstore"));
	            list.add(dto);
	         }
	         
	         return list;
	         
	      } catch (Exception e) {
	         System.out.println("ProductDAO.productList");
	         e.printStackTrace();
	      }
	      
	      
	      
	      return null;
	   }


	/**
	    * @author 유동환
	    * <p>상품등록에 필요한 정보를 클라이언트로 부터 받아 정보를 데이터베이스에 저장하는 메소드입니다.</p>
	    * @param  
	    * @return int
	    */
	public int addProduct(ProductDTO dto) {

		try {
			String sql = "insert into tblProduct (pseq,pName,pImage,pPrice,pOrigin,pQuantity,pDiscount,pRefundOx,pAmount,dseq,sseq,cseq) values (seqProduct.nextVal, ?, ?, ?, ?, ?, ?, ?, ?, 1, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getPname());
			pstat.setString(2, dto.getPimage());
			pstat.setString(3, dto.getPprice());
			pstat.setString(4, dto.getPorigin());
			pstat.setString(5, dto.getPquantity());
			pstat.setString(6, dto.getPdiscount());
			pstat.setString(7, dto.getPrefundox());
			pstat.setString(8, dto.getPamount());
			pstat.setString(9, dto.getSseq());
			pstat.setString(10, dto.getCseq());
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ProductDAO.addProduct");
			e.printStackTrace();
		}
		
		
		
		
		
		
		return 0;
	}

	/**
     * @author 유동환
     * <p>상품을 게시글에서 제거하는 메소드입니다.</p>
     * @param  
     * @return ArrayList
     */
 public int deleteProduct(String pseq) {
    
    try {

       String sql = "update  tblProduct set post='N' where pseq=?";
       
       pstat = conn.prepareStatement(sql);
       
       pstat.setString(1, pseq);
       
       return pstat.executeUpdate();
       
       
    } catch (Exception e) {
       System.out.println("ProductDAO.deleteProduct");
       e.printStackTrace();
    }
    
    
    return 0;
 }
	/**
	    * @author 유동환
	    * <p>상품정보를 수정하는 메소드입니다.</p>
	    * @param  
	    * @return int
	    */
	public int editProduct(ProductDTO dto) {
	try {
			
		
		if(dto.getPimage() == null || dto.getPimage().equals("")) {
//		pseq,pName,pImage,pPrice,pOrigin,pQuantity,pDiscount,pRefundOx,pAmount,cseq
			String sql = "update tblProduct set pName= ?, pPrice= ?,pOrigin= ?,pQuantity=?,pdiscount=?,pRefundOx= ?, pAmount=?, cseq= ? where pseq = ?  ";
			
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getPname());
			pstat.setString(2, dto.getPprice());
			pstat.setString(3, dto.getPorigin());
			pstat.setString(4, dto.getPquantity());
			pstat.setString(5, dto.getPdiscount());
			pstat.setString(6, dto.getPrefundox());
			pstat.setString(7, dto.getPamount());
			pstat.setString(8, dto.getCseq());
			
			pstat.setString(9, dto.getPseq());
			
			
			return pstat.executeUpdate();
			
			
		}else {
			
			
			
			
			String sql = "update tblProduct set pName= ?, pPrice= ?,pOrigin= ?,pQuantity=?,pdiscount=?,pRefundOx= ?, pAmount=?, cseq= ?, pImage=? where pseq = ?  ";
			
	pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, dto.getPname());
			pstat.setString(2, dto.getPprice());
			pstat.setString(3, dto.getPorigin());
			pstat.setString(4, dto.getPquantity());
			pstat.setString(5, dto.getPdiscount());
			pstat.setString(6, dto.getPrefundox());
			pstat.setString(7, dto.getPamount());
			pstat.setString(8, dto.getCseq());
			
			pstat.setString(9, dto.getPimage());
			pstat.setString(10, dto.getPseq());
			
			
			return pstat.executeUpdate();
		}
			
			
			
			
			
		} catch (Exception e) {
			System.out.println("ProductDAO.editProduct");
			e.printStackTrace();
		}
		
		
		
		return 0;
	}
	/**
	    * @author 유동환
	    * <p>상품에 대한 정보들을 가져오는 메소드입니다.</p>
	    * @return ProductDTO
	    */
	public ProductDTO productDetail(String pseq) {

		try {
			
			String sql = "select * from tblProduct p inner join tblSeller s on p.sseq = s.sseq where pseq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, pseq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				ProductDTO dto = new ProductDTO();
				
				dto.setPseq(pseq);
				dto.setPname(rs.getString("pname"));
				dto.setSseq(rs.getString("sseq"));
				dto.setPimage(rs.getString("pimage"));
				dto.setPprice(rs.getString("pprice"));
				dto.setPorigin(rs.getString("porigin"));
				dto.setPquantity(rs.getString("pquantity"));
				dto.setPdiscount(rs.getString("pdiscount"));
				dto.setPrefundox(rs.getString("prefundOX"));
				dto.setPamount(rs.getString("pamount"));
				dto.setDseq(rs.getString("dseq"));
				dto.setSseq(rs.getString("sseq"));
				dto.setCseq(rs.getString("cseq"));
				dto.setStorename(rs.getString("sstore"));
			
				return dto; 
			}
			
			
		} catch (Exception e) {
			System.out.println("ProductDAO.productDetail");
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}


	public String getPseq() {

		try {
			
			String sql = "select max(pseq) as seq from tblProduct";
			
			stat = conn.createStatement();
			
			rs  = stat.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getString("seq");
				
			}
			
		} catch (Exception e) {
			System.out.println("BoardDAO.getPseq");
			e.printStackTrace();
		}
		
		
		return null;
	}


	public boolean existHashTag(String tagName) {

try {
			
			String sql = "select count(*) as cnt from tblHashtag where hContent = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, tagName);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("cnt") > 0 ? true : false;
			}
			
		} catch (Exception e) {
			System.out.println("BoardDAO.existHashTag");
			e.printStackTrace();
		}
		
		
		
		return false;
	}

	/**
	    * @author 유동환
	    * <p>기존 존재하는 해시태그가 있으면 해시태그번호를 가져오는 메소드입니다.</p>
	    */
	public String getRHseq(String tagName) {

try {
			
			String sql = "select hseq from tblHashtag where hContent = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, tagName);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				return rs.getString("hseq");
				
			}
			
		} catch (Exception e) {
			System.out.println("BoardDAO.getRHseq");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	    * @author 유동환
	    * <p>새로운 해시태그를 저장하는 메소드입니다.</p>
	    */
	public void addHashTag(String tagName) {
		
try {
			
			String sql = "insert into tblHashtag (hseq, hcontent) values (seqHashtag.nextVal, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, tagName);
			
			pstat.executeUpdate();			
			
		} catch (Exception e) {
			System.out.println("BoardDAO.addHagTag");
			e.printStackTrace();
		}
		
	}

	/**
	    * @author 유동환
	    * <p>가장 최근에 등록된 해시태그 번호를 가져오는 메소드입니다.</p>
	    */
	public String getHseq() {
		
		
		try {
			
			String sql = "select max(hseq) as seq from tblHashTag";
			
			stat = conn.createStatement();
			
			rs  = stat.executeQuery(sql);
			
			if(rs.next()) {
				return rs.getString("seq");
			}
			}catch (Exception e) {
				System.out.println("BoardDAO.getHseq");
				e.printStackTrace();
			}
			
		
		return null;
	}


	/**
	    * @author 유동환
	    * <p>해쉬태그와 상품을 잇는 메소드입니다.</p>
	    */
	public void addTagging(HashMap<String, String> map) {

		try {
			
			String sql = "insert into tblHashConnect (hcseq, hseq, pseq) values (seqHashConnect.nextVal, ?, ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, map.get("hseq"));
			pstat.setString(2, map.get("pseq"));
			
			pstat.executeUpdate();			
			
		} catch (Exception e) {
			System.out.println("BoardDAO.addHagTag");
			e.printStackTrace();
		}
	}

	/**
	    * @author 유동환
	    * <p>해쉬태그를 삭제하는 메소드입니다.</p>
	    */
	public int deletehash(String pseq) {

		try {

			String sql = "delete from tblHashConnect where pSeq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, pseq);
			
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("ProductDAO.deletehash");
			e.printStackTrace();
		}
		return 0;
	}


	/**
	    * @author 유동환
	    * <p>판매되는 상품에 등록된 해시태그를 가져오는 메소드입니다.</p>
	    * @return ArrayList
	    */
	public ArrayList<String> getTag(String pseq) {

		try {
			
			String sql = "select * from tblHashtag ht inner join tblHashConnect hc on ht.hseq = hc.hseq where pseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, pseq);
			
			rs = pstat.executeQuery();
			
			ArrayList<String> list = new ArrayList<>();
			while(rs.next()) {
				list.add(rs.getString("hcontent"));
			}
			
			return list;
			}catch (Exception e) {
				System.out.println("BoardDAO.getHseq");
				e.printStackTrace();
			}
		
		return null;
	}
	
	
	/**
	    * @author 유동환
	    * <p>상품 수정시 기존의 상품정보를 가져오는 메소드입니다.</p>
	    * @param  pseq
	    * @return ProductDTO
	    */
	public ProductDTO ProductDetail(String pseq) {
	      
	      try {
	         
	         //System.out.println(pseq);
	         String sql = "select p.*,(select sStore from tblSeller where sSeq = p.sSeq) as sstore, (select sAddress from tblSeller where sSeq = p.sSeq) as sAddress from tblProduct p where pSeq = ?";

	         pstat = conn.prepareStatement(sql);
	         
	         pstat.setString(1, pseq);
	         
	         
	         rs = pstat.executeQuery();
	         
	         
	          if(rs.next()) {
	            ProductDTO dto = new ProductDTO();
	            dto.setPseq(rs.getString("pseq"));
	            dto.setSseq(rs.getString("sseq"));
	            dto.setPname(rs.getString("pname"));
	            dto.setPimage(rs.getString("pimage"));
	            
	            dto.setPprice(rs.getString("pprice"));
	            
	            dto.setPorigin(rs.getString("porigin"));
	            dto.setPquantity(rs.getString("pquantity"));
	            dto.setPdiscount(rs.getString("pdiscount"));
	            dto.setPrefundox(rs.getString("prefundOX"));
	            dto.setPamount(rs.getString("pamount"));
	            dto.setDseq(rs.getString("dseq"));
	            dto.setSseq(rs.getString("sseq"));
	            dto.setCseq(rs.getString("cseq"));
	            dto.setStorename(rs.getString("sstore"));
	            dto.setSaddress(rs.getString("saddress"));
	            
	                  
	            return dto;
	          }
	      } catch (Exception e) {
	         System.out.println("ProductDAO.productList");
	         e.printStackTrace();
	      }
	      
	      return null;
	   }



	public MemberDTO getMember(String mseq) {

 try {
	         
	         //System.out.println(pseq);
	         String sql = "select * from tblMember where mseq = ?";

	         pstat = conn.prepareStatement(sql);
	         
	         pstat.setString(1, mseq);
	         
	         
	         rs = pstat.executeQuery();
	         
	         
	          if(rs.next()) {
	        	  MemberDTO dto = new MemberDTO();
	           
		            dto.setMname(rs.getString("mname"));
		            dto.setMaddress(rs.getString("maddress"));
	            
	                  
	            return dto;
	          }
	      } catch (Exception e) {
	         System.out.println("ProductDAO.productList");
	         e.printStackTrace();
	      }
	      
		
		
		
		return null;
	}
	
	

	/**
	 * @author 김유진
	 * <p>문의게시판에 들어갈 리스트를 가져오는 메소드</p>
	 * @param pseq
	 * @param map
	 * @return
	 */

	/**
	 * @author 김유진
	 * <p>문의게시판에 들어갈 리스트를 가져오는 메소드</p>
	 * @param pseq
	 * @param map
	 * @return
	 */

	public ArrayList<ProductDTO> getqnaList(String pseq, HashMap<String, String> map) {
		
		try {
			String sql = String.format("select * from (select rownum as rnum, a.* from (select * from tblQna q inner join tblmember m on q.mseq = m.mseq where q.pseq = ?) a) where rnum between %s and %s" , map.get("begin"), map.get("end"));
			
//			String sql = "select * from tblQna q inner join tblmember m on q.mseq=m.mseq where q.pseq = ?";
			
			
			pstat = conn.prepareStatement(sql);

	        pstat.setString(1, pseq);
	        
	        ArrayList<ProductDTO> list = new ArrayList<ProductDTO>();
	        
	        rs = pstat.executeQuery();
	        
	        while (rs.next()) {
	        	
	        	ProductDTO dto = new ProductDTO();
	        	
	        	dto.setQseq(rs.getString("qseq"));
	        	dto.setQtitle(rs.getString("qtitle"));
	        	dto.setQcontent(rs.getString("qcontent"));
	        	dto.setQdate(rs.getString("qdate"));
	        	dto.setMname(rs.getString("mname"));
	        	dto.setPseq(rs.getString("pseq"));
	        	dto.setRnum(rs.getString("rnum"));
	        	
	        	list.add(dto);
	        	
	        }
	        
	        return list;
			
		} catch (Exception e) {
			System.out.println("ProductDAO.getqnaList");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * @author 김유진
	 * <p>판매자문의 게시글의 총 갯수를 확인하기 위한 메소드</p>
	 * @param pseq
	 * @return
	 */

	public int getTotalCount(String pseq) {
		
		try {
			
			String sql = "";

			
			sql = "select count(*) as cnt from tblQna where pseq =  ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, pseq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				return rs.getInt("cnt");
			}
			
			
		} catch (Exception e) {
			System.out.println("ProductDAO.getTotalCount");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * @author 김유진
	 * <p>판매자 문의게시글들에 수정,삭제 권한을 주기위해 작성한 회원의 계정번호를 가져오는 메소드</p>
	 * @param aiseq
	 * @return
	 */

	public ProductDTO getId(String aiseq) {
		
		try {
			
		String sql = "select * from tblMember m inner join tblAccountInfo ai on m.aiseq = ai.aiseq where ai.aiseq = ?";
			
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, aiseq);
			
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				ProductDTO dto = new ProductDTO();
				
				dto.setAiid(rs.getString("aiid"));
				
				return dto;
				
			}
			
			
		} catch (Exception e) {
			System.out.println("ProductDAO.getId");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * @author 김하늘
	 * <p>최근 등록한 10개의 상품을 보여주는 메소드입니다.</p>
	 * @return ArrayList
	 */


	public ArrayList<ProductDTO> newList() {
		
		try {
			
			String sql = "select * from(select p.*, s.sstore, rank() over(order by p.pregdate desc) as rank from tblSeller s inner join tblProduct p on s.sseq = p.sseq) pRank where pRank.rank <= 10";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<ProductDTO> list = new ArrayList<>(); 
			
			while(rs.next()) {
				
				ProductDTO dto = new ProductDTO();
				dto.setSseq(rs.getString("sseq"));
				dto.setPname(rs.getString("pname"));
				dto.setPimage(rs.getString("pimage"));
				dto.setPprice(rs.getString("pprice"));
				dto.setPorigin(rs.getString("porigin"));
				dto.setPquantity(rs.getString("pquantity"));
				dto.setPdiscount(rs.getString("pdiscount"));
				dto.setPrefundox(rs.getString("prefundOX"));
				dto.setPamount(rs.getString("pamount"));
				dto.setDseq(rs.getString("dseq"));
				dto.setSseq(rs.getString("sseq"));
				dto.setCseq(rs.getString("cseq"));
				dto.setStorename(rs.getString("sstore"));
				dto.setPseq(rs.getString("pseq"));
				list.add(dto);
				
				//System.out.println(list);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ProductDAO.newList");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @author 김하늘
	 * <p>판매량을 기준으로 20개의 상품을 보여주는 메소드입니다.</p>
	 * @return ArrayList
	 */

	public ArrayList<ProductDTO> recommendList() {
		
		try {
			
			String sql = "select * from(select p.pname, p.pimage, p.pprice, p.porigin, p.pquantity, p.pdiscount, p.pRefundOX, p.pamount, p.sseq, p.dseq, p.cseq, s.sstore, p.pseq, dense_rank() over(order by d.odquantity desc) as rank from tblOrderDetail d inner join tblProduct p on d.pseq = p.pseq inner join tblSeller s on s.sseq = p.sseq group by d.odquantity, p.pname, p.pseq, p.pimage, p.pprice, p.porigin, p.pquantity, p.pdiscount, p.pRefundOX, p.pamount, p.sseq, p.dseq, p.cseq, s.sstore) pRank where pRank.rank <= 20";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			
			ArrayList<ProductDTO> list = new ArrayList<>(); 
			
			while(rs.next()) {
				
				ProductDTO dto = new ProductDTO();
				
				dto.setSseq(rs.getString("sseq"));
				dto.setPname(rs.getString("pname"));
				dto.setPimage(rs.getString("pimage"));
				dto.setPprice(rs.getString("pprice"));
				dto.setPorigin(rs.getString("porigin"));
				dto.setPquantity(rs.getString("pquantity"));
				dto.setPdiscount(rs.getString("pdiscount"));
				dto.setPrefundox(rs.getString("prefundOX"));
				dto.setPamount(rs.getString("pamount"));
				dto.setDseq(rs.getString("dseq"));
				dto.setSseq(rs.getString("sseq"));
				dto.setCseq(rs.getString("cseq"));
				dto.setStorename(rs.getString("sstore"));
				dto.setPseq(rs.getString("pseq"));
				
				list.add(dto);
				
				//System.out.println(rs.getString("sseq"));
				
				//System.out.println(list);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ProductDAO.recommendList");
			e.printStackTrace();
		}
		return null;
	}
}





