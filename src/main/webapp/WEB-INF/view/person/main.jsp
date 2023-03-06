<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

<script>
    let deadline;
    let today = new Date();
    let dDay;
    let currDay = 24 * 60 * 60 * 1000; 
</script>
<div class="jm_body">
    <div class="sj_full_container">
        <div class="select_box jm_select_box mt-5">
            <select id="skill" class="jm_select selectpicker" data-style="btn-info" name="">
                <option value="none" selected>분야</option>
                <option value="Java">Java</option>
                <option value="Springboot">Springboot</option>
                <option value="C">C</option>
                <option value="CSS">CSS</option>
                <option value="html">Html</option>
                <option value="Flutter">Flutter</option>
                <option value="JavaScript">JavaScript</option>
            </select>
            <select id="career" class="jm_select" name="">
                <option value="none" selected>고용형태</option>
                <option value="신입">신입</option>
                <option value="경력">경력</option>
            </select>
            <select id="address" class="jm_select" name="">
                <option value="none" selected>근무지</option>
                <option value="경기">전국</option>
                <option value="경기">경기</option>
                <option value="서울">서울</option>
                <option value="부산">부산</option>
                <option value="경기">경남</option>
                <option value="제주">제주</option>
                <option value="울산">울산</option>
            </select>
            <select id="size" class="jm_select" name="">
                <option value="none" selected>규모</option>
                <option value="10_down">10명 이하</option>
                <option value="10_up">10명 이상</option>
                <option value="20">20명 이상</option>
                <option value="50">50명 이상</option>
                <option value="100">100명 이상</option>
            </select>
        </div>


        <div class="container jm_container mt-5">
            <div class="row row-cols-3 g-4 d-flex flex-wrap">
            <c:forEach items="${mainPosts}" var="post" varStatus="status">
           
                <div class="col-xs-4 post">
                    <a href="/person/detail/${post.postId}" style="color: inherit; text-decoration: none;">
                        <div class="card jm_card h-100">
                            <img src="${post.logo}" class="card-img-top jm_card_img_top">
                            <div class="card-body jm_card_body">
                                <div class="jm_company_name">${post.name}</div>
                                <div class="jm_company_title"> ${post.title} </div>
                               
                                <div class="jm_company_address">${post.address}</div>
                                <div class="jm_D-day d-flex justify-content-between">
                                    <div id="dDay-${status.count}"></div>
                            <c:choose>
                               <c:when test="${post.scrap == 0}">
                                    <div><i class="fa-regular fa-thumbs-up fa-lg" onclick="scrapOrCancle()" value="${post.postId}"></i></div>
                               </c:when>
                            
                               <c:otherwise>
                                    <div><i class="fa-solid fa-thumbs-up fa-lg" onclick="scrapOrCancle()" value="${post.postId}"></i></div>
                               </c:otherwise>
                            </c:choose>
                                 
                                </div>
                            </div>
                        </div>
                    </a>
                <input type="hidden" value="${post.deadline}" name="" class="deadline" id="deadline-${status.count}"/>
                </div>
            </c:forEach>
            <input type="hidden" value="${size}" id="postSize"/>
            </div>
        </div>
    </div>
</div>
 
    <script>
        // 1. 마감 1일 후 숨기기, 디데이 설정
        let postSize = document.querySelector("#postSize").getAttribute("value");
        for (let i = 1; i <= postSize; i++) {
            // console.log($("#dDay-"+i));
            deadline = $("#deadline-"+i).get(0).getAttribute("value");
            // console.log(deadline);
            deadline = new Date(deadline);
            dDay = Math.ceil((deadline-today)/currDay);
            // console.log(dDay);
            if(dDay>0){
                $("#dDay-"+i).text("D-"+dDay);
            }else if(dDay<0){
                $("#dDay-"+i).text("마감되었습니다.");
            }else{
                $("#dDay-"+i).text("D-Day");
            }
        }
        
    </script>
    <%@ include file="../layout/footer.jsp" %>