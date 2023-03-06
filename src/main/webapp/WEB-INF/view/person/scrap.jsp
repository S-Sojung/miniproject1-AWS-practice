<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex mt-4">
            <div class="list-group ms-2 mt-4">
                <a href="/person/info" class="list-group-item" style="width: 150px;">회원 정보</a>
                <a href="/person/resumes" class="list-group-item">이력서 관리</a>
                <a href="/person/scrap" class="list-group-item hs_list_effect">스크랩</a>
                <a href="/person/history" class="list-group-item">지원 이력</a>
            </div>

            <div class="ms-2 p-4">
                <div class="border border-tertiary w-100 p-5 rounded ">
                    <div class="d-flex justify-content-between">
                        <h1 class="hs_line">스크랩 공고</h1>
                    </div>
                    <hr class="mb-4">

                    <div class="container mb-5 mt-3 w-100">
                        <div class="row row-cols-3 g-4 d-flex flex-wrap">
                            <c:forEach items="${pScrapList}" var="pScrap">
                            
                            <div class="col-xs-4" id="card-${pScrap.postId}">
                                <a href="/person/detail/${pScrap.postId}" style="color: inherit; text-decoration: none;">
                                    <div class="card jm_card h-100">
                                        <img src="${pScrap.logo}" class="card-img-top jm_card_img_top">
                                        <div class="card-body jm_card_body">
                                            <div class="jm_company_name">${pScrap.name}</div>
                                            <div class="jm_company_title"> ${pScrap.title} </div>
                                            <div class="jm_company_address">${pScrap.address}</div>
                                            <div class="jm_D-day d-flex justify-content-between">
                                                <c:choose>
                                                   <c:when test="${pScrap.deadline < 0}">
                                                   <b style="color:grey";>마감</b>
                                                   </c:when>
                                                
                                                   <c:otherwise>
                                                   D-${pScrap.deadline}
                                                   </c:otherwise>
                                                </c:choose>
                                                  <button type="button" class="btn btn-sm" onclick="cancle(event, ${pScrap.postId})">
                                                     <i class="fa-solid fa-thumbs-up fa-2xl"></i>
                                                </button>
                                            </div>

                                        </div>
                                    </div>
                                </a>
                            </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
     <script>
     function cancle(event, postId) {
         event.preventDefault();
  
                let data = {
                    "postId": postId
                };
                    $.ajax({
                        type: "delete",
                        url: "/person/scrap/" + postId,
                        dateType: "JSON"
                    }).done((res) => {
                      $("#card-"+ postId).remove();
                    }).fail((err) => {
                        alert(err.responseJSON.msg);
                    });
                }
      
     </script>


        <%@ include file="../layout/footer.jsp" %>