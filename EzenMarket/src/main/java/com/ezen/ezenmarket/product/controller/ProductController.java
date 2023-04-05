package com.ezen.ezenmarket.product.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.ezenmarket.product.dto.PagingVO;
import com.ezen.ezenmarket.product.dto.Post;
import com.ezen.ezenmarket.product.dto.Post2;
import com.ezen.ezenmarket.product.dto.PostImage;
import com.ezen.ezenmarket.product.mapper.ProductMapper;
import com.ezen.ezenmarket.product.service.ProductService;
import com.ezen.ezenmarket.user.dto.User;
import com.ezen.ezenmarket.user.mapper.UserMapper;
import com.ezen.ezenmarket.user.service.UserService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
public class ProductController {
   
   @Autowired
   ProductMapper productMapper;
   
   @Autowired
   UserService userService;
   
   @Autowired
   UserMapper userMapper;
   
   @Autowired
   ProductService productService;   
   
   
   
//   @GetMapping(value="/product")
//   public String productDetail(String id, Model model) {
//      
//      model.addAttribute("post", productMapper.selectProduct(id));
//      
//      return "product/product_detail";
//   }

   
   @GetMapping(value="/register")
   public String registerProduct(HttpServletRequest req) {
      //List<PostImage> postImages = productMapper.getPostImages(73);
      HttpSession session = req.getSession();
      if(session.getAttribute("login") == null || !session.getAttribute("login").equals("yes")) {
         req.setAttribute("requestUri", "/register");
         return "user/signin";
      } else {
         return "product/product_register";
      }      
   }
   
   
   @GetMapping(value="/insertSuccess")
   public String insertProduct(HttpServletRequest req) {
   
     HttpSession session = req.getSession();
     Integer user_number = Integer.parseInt(session.getAttribute("user_number").toString());
      
      // main을 /로 해놔서 main으로 가게 한다는 의미
      return "redirect:/product?id=" + productMapper.getPost_Id(user_number);
   }
   
   public String generateHash(MultipartFile file) {
	    // 파일의 바이트 스트림 생성
	    try {
	    	InputStream is = file.getInputStream();
	    

	    // SHA-256 해시 생성
	    MessageDigest digest = MessageDigest.getInstance("SHA-256");
	    byte[] buffer = new byte[4096];
	    int bytesRead;
	    while ((bytesRead = is.read(buffer)) != -1) {
	        digest.update(buffer, 0, bytesRead);
	    }
	    byte[] hash = digest.digest();

	    // 바이트 배열을 16진수 문자열로 변환
	    StringBuffer sb = new StringBuffer();
	    for (int i = 0; i < hash.length; i++) {
	        sb.append(Integer.toString((hash[i] & 0xff) + 0x100, 16).substring(1));
	    }

	    return sb.toString();
	    } catch (Exception e){
	    	return "";
	    }
	}
   
   @PostMapping(value="/insert2")
	public String insert2() {
		
		// main을 /로 해놔서 main으로 가게 한다는 의미
		return  "redirect:/";
	}
   
   
   @PostMapping(value="/insert")
	@ResponseBody
	public String insertProduct(HttpServletRequest req, String post_address, String title, 
			String post_content, Integer category_id, Integer price, MultipartFile[] file) {
	   System.out.println("여기까진왔음1111111111111111");
	   System.out.println("여기까진왔음1111111111111111");
	   System.out.println("여기까진왔음1111111111111111");
	   System.out.println("여기까진왔음1111111111111111");
	   System.out.println("여기까진왔음1111111111111111");
	   System.out.println("여기까진왔음1111111111111111");
	   System.out.println("여기까진왔음1111111111111111");
	   System.out.println("여기까진왔음1111111111111111");
		
		// post테이블 insert
		String user_id = userService.getUserId(req);
		System.out.println("1");
		
		User user = userMapper.getUserInfo(user_id);
		System.out.println("2");
		int user_number = user.getUser_number();
		System.out.println("3");
		Post post = new Post (user_number, post_address, title, post_content, category_id, price);
		System.out.println("4");
		System.out.println(user_number);
		System.out.println(post_address);
		System.out.println(title);
		System.out.println(post_content);
		System.out.println(category_id);
		System.out.println(price);
		productMapper.insertNewPost(post);
		System.out.println("5");
//		productMapper.insertProduct(post);
		// postImage테이블 insert
		int post_id = productMapper.getPost_Id(user_number);
		System.out.println("6");
		post.setPost_id(post_id);
		
		System.out.println("여기까진왔음22222222222222222");
		System.out.println("여기까진왔음22222222222222222");
		System.out.println("여기까진왔음22222222222222222");
		System.out.println("여기까진왔음22222222222222222");
		for (int i = 0; i < file.length; ++i) {

			String imgName = generateHash(file[i]) + "." + FilenameUtils.getExtension(file[i].getOriginalFilename());
		System.out.println("여기도옴");
		System.out.println("여기도옴");
		System.out.println("여기도옴");
		System.out.println("여기도옴");
			if (!file[i].isEmpty()) {
			    try {
			        byte[] bytes = file[i].getBytes();

			        // Creating the directory to store file
			        String rootPath = System.getProperty("catalina.home");
			        File dir = new File(rootPath + File.separator + "tmpFiles");
			        if (!dir.exists())
			            dir.mkdirs();
			        // Create the file on server
			        File serverFile = new File(dir.getAbsolutePath()
			                + File.separator + imgName);
			        
			        
			        post.setImage_url("http://localhost:8888/ezenmarket/tmpFiles/" + imgName);
			        productMapper.insertPostImage(post);
			        
			        BufferedOutputStream stream = new BufferedOutputStream(
			                new FileOutputStream(serverFile));
			        stream.write(bytes);
			        stream.close();
			        
			    } catch (Exception e) {
			        e.printStackTrace();
			        System.out.println("캐치문들어옴");
			        System.out.println("캐치문들어옴");
			        System.out.println("캐치문들어옴");
			        System.out.println("캐치문들어옴");
			        return "0";
			    }
			} else {
				System.out.println("파일이없음");
				System.out.println("파일이없음");
				System.out.println("파일이없음");
				System.out.println("파일이없음");

			    return "0"; // 실패
			}
		}
		
		return "1"; // 성공
	}
   
   
   @GetMapping(value="/modifyProduct")
	public String modifyProduct(Integer post_Id, Model model) {
		Post2 p = productMapper.getProductInfo(post_Id);
		
		List<PostImage> f = productMapper.getPostImages(post_Id);
		List<String> images = new ArrayList<>();
		
		for (int i = 0; i < f.size(); ++i) {
			images.add(f.get(i).getImage_url());
		}
		
		model.addAttribute("post_Id", post_Id);
		model.addAttribute("images", images);
		model.addAttribute("p", p);
		return "product/product_modify";
	}
	
	@PostMapping(value="/modify")
	@ResponseBody
	public String modifyProduct(HttpServletRequest req, String post_address, String title, 
			String post_content, Integer category_id, Integer price, Integer post_Id, MultipartFile[] file) {
		
		
		System.out.println(file[0].isEmpty());
		
		// post테이블 insert
		String user_id = userService.getUserId(req);
		User user = userMapper.getUserInfo(user_id);
		int user_number = user.getUser_number();
		Post2 post = new Post2 (post_Id, user_number, post_address, title, post_content, category_id, price);
		productMapper.updateProduct(post);
		
		System.out.println("파일 이름:" + file[0].getOriginalFilename());
		for (int i = 0; i < file.length; ++i) {

			String imgName = generateHash(file[i]) + "." + FilenameUtils.getExtension(file[i].getOriginalFilename());
			
			if (!file[i].isEmpty()) {
			    try {
			        byte[] bytes = file[i].getBytes();

			        // Creating the directory to store file
			        String rootPath = System.getProperty("catalina.home");
			        File dir = new File(rootPath + File.separator + "tmpFiles");
			        if (!dir.exists())
			            dir.mkdirs();
			        // Create the file on server
			        File serverFile = new File(dir.getAbsolutePath()
			                + File.separator + imgName);
			        
			        post.setPost_id(post_Id);
			        post.setImage_url("http://localhost:8888/ezenmarket/tmpFiles/" + imgName);
			        
			        productMapper.insertPostImage2(post);
			        
			        BufferedOutputStream stream = new BufferedOutputStream(
			                new FileOutputStream(serverFile));
			        stream.write(bytes);
			        stream.close();
			        
			    } catch (Exception e) {
			        e.printStackTrace();
			        return "0";
			    }
				
			} else {
				System.out.println("여기로 나옴");
			    return "0"; // 실패
			}
		}
		
		return "1"; // 성공
	}
   
/* 여기부터 내가 만든 것! 위의 것은 건들지 말기!!*/
   
   /* 전체 상품 보기(페이징) */
   // http://localhost:8888/ezenmarket/viewAll?page=2
   @GetMapping("/viewAll")
   public String getAll(@RequestParam(required = false, defaultValue = "1") Integer page,
            HttpServletRequest req, Model model) {
      
      productService.pagingAllProd(req);
      model.addAttribute("prodList", req.getAttribute("boards"));      
      model.addAttribute("page",  req.getParameter("page"));
      model.addAttribute("pagination_start", req.getAttribute("pagination_start"));
      model.addAttribute("pagination_end", req.getAttribute("pagination_end"));
            
      return "product/product_viewAll";
   }
   
   
   /* 카테고리별로 상품 조회하기 (+페이징) */
   // http://localhost:8888/ezenmarket/category?category_id=1&page=2
   @GetMapping("/category")
   public String cateList(@RequestParam(required = false, defaultValue = "1") Integer category_id,
                          @RequestParam(required = false, defaultValue = "1") Integer page,
                          HttpServletRequest req, Model model) {
      productService.paging(req, category_id);
      model.addAttribute("cateList", req.getAttribute("boards"));      
      model.addAttribute("page",  req.getParameter("page"));
      model.addAttribute("pagination_start", req.getAttribute("pagination_start"));
      model.addAttribute("pagination_end", req.getAttribute("pagination_end"));
      log.info("--------------------category_id" + category_id);
      
      return "product/product_menu";
   }

   
    /* 상품 상세페이지(상품정보 + 상품이미지정보 + 판매자정보) */
   @GetMapping(value="/product")
   public String productDetail(@RequestParam("id") Integer post_id, 
                                    HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    Model model) {
                   
      Post p =  productService.getDetails(post_id);   
      int cntProd = productService.cntProdBySeller(p.getUser_number());   
      model.addAttribute("cntProd", cntProd);   
      model.addAttribute("post", p);    
         
      // 판매자가 등록한 연관상품 목록 가져오기
      List<Post> lists = productService.getRelatedProd(p.getUser_number(), p.getPost_id());
      if(lists != null && lists.size() > 4) {
         model.addAttribute("lists", lists.subList(0, 4));         
      } else if(lists != null){
         model.addAttribute("lists", lists);
      }
         
      // 찜목록 개수 가져오기
      int cntWishlist = productService.cntWishlist(p.getPost_id());      
      model.addAttribute("cntWishlist", cntWishlist);
            
      
      /* 조회수 가져오기 (새로고침 시  중복 방지) */         
      
      // 이전에 생성된 쿠키가 있는지 확인
       boolean isVisited = false;
       Cookie[] cookies = request.getCookies();
       
       // 1) 쿠키O & 쿠키에 현재 상품의 id 값이 포함된 경우 -> 이전에 방문한 것으로 간주
       if (cookies != null) {
           for (Cookie cookie : cookies) {
               if (cookie.getName().equals("visit_cookie") && cookie.getValue().contains(p.getPost_id().toString())) {
                   isVisited = true;                
                   break;
               }
           }
       }
       
       // 쿠키가 없거나, 쿠키는 있지만 현재 상품의 id값이 포함되지 않은 경우 -> 새로운 쿠키를 생성 or 이전 쿠키의 값을 업데이트
       if (!isVisited) {
           String visitCookieValue = "";
           
           if (cookies != null) {
              // 2) 쿠키O&방문X  -> 기존 쿠키의 값에 현재 상품의 id값을 추가
               for (Cookie cookie : cookies) {
                   if (cookie.getName().equals("visit_cookie")) {
                       visitCookieValue = cookie.getValue();        
                       break;
                   }
               }
           } 
           
           // 3) 쿠키X&방문X  -> new cookie 생성 & id 추가
           visitCookieValue += "/" + p.getPost_id();
           Cookie newCookie = new Cookie("visit_cookie", visitCookieValue);
           newCookie.setMaxAge(60);   // 쿠키는 24시간(하루) 동안 유효
           response.addCookie(newCookie);        
           productService.plusView(p.getPost_id());
       }
       
       
       //이 포스트의 포스트 이미지들 모두 가져오기
       model.addAttribute("postImages", productMapper.getPostImages(post_id));
       
       //세션 객체 생성
       HttpSession session = request.getSession();
       
       // 찜 여부 체크
       if(session.getAttribute("user_number") != null) {
          if(productMapper.countNumOfZzim(Integer.parseInt(session.getAttribute("user_number").toString()), post_id) > 0) {
             model.addAttribute("zzim", "yes");
          }          
       }
      
       // 상세페이지에 판매자 프로필 사진 가져오기     
       model.addAttribute("profileImg", productMapper.getProfileImg(p.getUser_number(), p.getPost_id()));
       
       
       boolean isEndDeal = false;
       if(productMapper.isEndDeal(post_id) != null) {
    	   isEndDeal = true;
       }
       
       model.addAttribute("isEndDeal",isEndDeal);
       
       
      return "product/product_detail";
      
   }
   
   // 검색기능 구현
   @GetMapping(value="/search")
   public String searchProduct(HttpServletRequest req, HttpServletResponse resp) {
      
       String title = req.getParameter("search");
       req.setAttribute("products", productMapper.searchProduct(title));
      
       return "product/product_search"; 
   }
   
   // 검색기능 + 페이지네이션 구현
   @GetMapping("/searchPagenation")
   public String searchProductList(@Param("vo")PagingVO vo, @Param("title") String title, Post post, Model model,
         @RequestParam(value = "nowPage", required = false) String nowPage,
         @RequestParam(value = "cntPerPage", required = false) String cntPerPage, String type) {
      
      int total = productMapper.countProduct(title);
            
      // 이게 이해가 잘 안됨
      if (nowPage == null && cntPerPage == null) {
         nowPage = "1";
         cntPerPage = "30";
      } else if (nowPage == null) {
         nowPage = "1";
      } else if (cntPerPage == null) {
         cntPerPage = "30";
      }
      
      vo = new PagingVO(total, Integer.parseInt(nowPage), Integer.parseInt(cntPerPage));
      model.addAttribute("paging", vo); // 페이지네이션
      model.addAttribute("keyword", title); // 검색한 키워드 
      
      // 검색어가 없을 때 없다고 화면에 나오게 하려고 이렇게 만듬
            List<Post> list = productMapper.getProductWithPaging(title, vo);
            
            if(!list.isEmpty()) {
               model.addAttribute("title", list);
               System.out.println("검색어가 있는 경우 출력");
            } else {
               model.addAttribute("searchKeyword", "empty");
               System.out.println("검색어가 없는 경우 출력 제발!!!");
            }
            
            
      if (type == null) {
         model.addAttribute("title", list);
                  
      } else if (type.equals("low")) {
         
         model.addAttribute("title", productMapper.getProductLowPrice(title, vo)); 
   
      } else if (type.equals("high")) {
         model.addAttribute("title", productMapper.getProductHighPrice(title, vo)); 
         
      } else if (type.equals("latest")){
         model.addAttribute("title", list);
      }
      
      int searchCnt = productService.getSearchCnt(title);
      model.addAttribute("searchCnt", searchCnt);
      
      
      
      
//      model.addAttribute("title", productMapper.getProductWithPaging(title, vo)); 
            
      return "product/product_search";
   }
   
   
   
   
   
   
   
   
   
}