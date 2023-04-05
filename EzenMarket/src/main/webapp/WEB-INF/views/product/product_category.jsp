<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"> <!-- ��Ʈ��Ʈ��-->
    

<style>
      a:link {
        text-decoration: none;
      }
      a {
        color: black;
      }
      
      
      
       .link-dark {
        margin-left: 70px;
        font-size: 15px;
        color: hsla(0, 1%, 15%, 0.651);
      } 
      .dropbarBtn {
  
        font-size: 20px;
        color: hsla(0, 0%, 0%, 0.651);
      }
  
    #Category {
        /* overflow:visible; */
        float: left;
        margin-left: 200px;
        margin-top:  50px;
        
        /* box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15); */
      }
   
</style>
<title>ī�װ�</title>
</head>
<body>

      <div class="flex-shrink-0 p-2 bg-white dropbar" id="Category" style="width: 220px;">
            <!-- <a href="/" class="d-flex align-items-center pb-3 mb-3 link-dark text-decoration-none border-bottom"> -->
              <svg class="bi pe-none me-2" width="30" height="24"><use xlink:href="#bootstrap"/></svg>
              <span class="fs-5 fw-semibold"></span>
            <!-- </a> -->
            <ul class="list-unstyled ps-0">
            
               <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0= collapsed dropbarBtn" data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true">
                �Ƿ�
                </button>
              <li class="mb-1">
                <!-- collapse show ������ ���� ���̴� �� -->
                <div class="collapse" id="home-collapse">
                  <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li><a href="category?category_id=1" class="link-dark d-inline-flex text-decoration-none rounded">�����Ƿ�</a></li>
                    <li><a href="category?category_id=2" class="link-dark d-inline-flex text-decoration-none rounded">�����Ƿ�</a></li>
                  </ul>
                </div>
              </li>
              <li class="mb-1">
                <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed dropbarBtn" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
                 �м���ȭ
                </button>
                <div class="collapse" id="dashboard-collapse">
                  <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li><a href="category?category_id=3 " class="link-dark d-inline-flex text-decoration-none rounded">�Ź�</a></li>
                    <li><a href="category?category_id=4" class="link-dark d-inline-flex text-decoration-none rounded">����</a></li>
                    <li><a href="category?category_id=5" class="link-dark d-inline-flex text-decoration-none rounded">�ð�</a></li>
                  </ul>
                </div>
              </li>
              <li class="mb-1">
                <button  class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed dropbarBtn" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
                  ������Ȱ
                </button>
                <div class="collapse" id="orders-collapse">
                  <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li><a href="category?category_id=6" class="link-dark d-inline-flex text-decoration-none rounded">�ֹ��ǰ</a></li>
                    <li><a href="category?category_id=7" class="link-dark d-inline-flex text-decoration-none rounded">���׸���</a></li>
                    <li><a href="category?category_id=8" class="link-dark d-inline-flex text-decoration-none rounded">����ǰ</a></li>
                  </ul>
                </div>
              </li>
              <li class="mb-1">
                <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed dropbarBtn" data-bs-toggle="collapse" data-bs-target="#a-collapse" aria-expanded="false">
                 ��� �÷���
                </button>
                <div class="collapse" id="a-collapse">
                  <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li><a href="category?category_id=9" class="link-dark d-inline-flex text-decoration-none rounded">�ǰ���ǰ</a></li>
                    <li><a href="category?category_id=10" class="link-dark d-inline-flex text-decoration-none rounded">�ݷ���ǰ</a></li>
                    <li><a href="category?category_id=11" class="link-dark d-inline-flex text-decoration-none rounded">�繫��ǰ</a></li>
                  </ul>
                </div>
              </li>
              <li class="mb-1">
                <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed dropbarBtn" data-bs-toggle="collapse" data-bs-target="#sport-collapse" aria-expanded="false">
                  ����������
                </button>
                <div class="collapse" id="sport-collapse">
                  <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li><a href="category?category_id=12" class="link-dark d-inline-flex text-decoration-none rounded">�ƿ�����</a></li>
                    <li><a href="category?category_id=13" class="link-dark d-inline-flex text-decoration-none rounded">�ȭ</a></li>
                  
                  </ul>
                </div>
              </li>
              <li class="mb-1">
                <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed dropbarBtn" data-bs-toggle="collapse" data-bs-target="#baby-collapse" aria-expanded="false">
                 �����б��
                </button>
                <div class="collapse" id="baby-collapse">
                  <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                 	<li><a href="category?category_id=14" class="link-dark d-inline-flex text-decoration-none rounded">��Ʈ��/PC</a></li>
                    <li><a href="category?category_id=15" class="link-dark d-inline-flex text-decoration-none rounded">�޴���ȭ</a></li>
                    <li><a href="category?category_id=16" class="link-dark d-inline-flex text-decoration-none rounded">�ֺ����</a></li>
                   
                  </ul>
                </div>
              </li>
              <li class="mb-1">
                <button class="btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed dropbarBtn" data-bs-toggle="collapse" data-bs-target="#acc-collapse" aria-expanded="false">
                  ��Ÿ
                </button>
                <div class="collapse" id="acc-collapse">
                  <ul class="btn-toggle-nav list-unstyled fw-normal pb-1 small">
                    <li><a href="category?category_id=17" class="link-dark d-inline-flex text-decoration-none rounded">��Ÿ��ǰ</a></li>
                    
                  </ul>
                </div>
              </li>
              
             
              <!-- border-top Ŭ���� �ȿ� ���� �־�� �� -->
             
            </ul>
          </div>
          
           <!-- ��Ʈ��Ʈ�� �ڹٽ�ũ��Ʈ-->
          <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        
</body>
</html>