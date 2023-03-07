<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div>
            <!--배경 이미지 넣을 시-->
        </div>
        <div class="container mt-5 w-75">
            <h4>등록한 공고와 매칭되는 구직자를 확인해보세요🌟</h4>
        </div>

        <div class="container mb-5 mt-3 w-75">
            <div class="accordion" id="accordionExample">

                <c:forEach items="${postInfoAndResumes}" var="postInfoAndResumes">

                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading-${postInfoAndResumes.postId}">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse"
                                data-bs-target="#collapse-${postInfoAndResumes.postId}" aria-expanded="true"
                                aria-controls="collapse-${postInfoAndResumes.postId}">

                                ${postInfoAndResumes.title}

                            </button>
                        </h2>

                        <div id="collapse-${postInfoAndResumes.postId}" class="accordion-collapse collapse"
                            aria-labelledby="heading-${postInfoAndResumes.postId}" data-bs-parent="#accordionExample">
                            <div class="accordion-body">

                                <div class="container mb-5 mt-3 w-100">
                                    <div class="d-flex justify-content-around w-100 align-center align-items-center bg-dark"
                                        style="height: 40px;">
                                        <div class="text-light"><b>이름</b></div>
                                        <div class="text-light"><b>이력서</b></div>
                                        <div class="text-light"><b>기술스택 </b></div>
                                        <div></div>
                                        <div></div>
                                    </div>
                                    <c:forEach items="${postInfoAndResumes.resumes}" var="resume">
                                    <a href = "/company/resumeDetail/${resume.id}" style="text-decoration: none;">
                                        <div class="card rounded-0" id="card-${resume.id}">
                                            <div
                                                class="card-body d-flex justify-content-around align-center align-items-center w-100">

                                                <div>${resume.name}</div>
                                                <div>${resume.title}</div>
                                                <div>
                                                    <c:forEach items="${resume.skills}" var="skill">
                                                        <span class="badge text-bg-info">${skill}</span>
                                                    </c:forEach>
                                                </div>
                                                <div>
                                                    <button type="button" class="btn btn-sm"
                                                        onclick="scrapOrCancle(event, ${postInfoAndResumes.postId},  ${resume.id})">
                                                        <c:choose>
                                                           <c:when test="${resume.scrap == 0}">
                                                        <i class="fa-regular fa-thumbs-up fa-2x"  id="scrap-${postInfoAndResumes.postId}-${resume.id}"
                                                        value="${resume.scrap}"></i>
                                                           </c:when>
                                                        
                                                           <c:otherwise>
                                                        <i class="fa-solid fa-thumbs-up fa-2x"  id="scrap-${postInfoAndResumes.postId}-${resume.id}"
                                                        value="${resume.scrap}"></i>
                                                           </c:otherwise>
                                                        </c:choose>

                                                    </button>
                                                </div>

                                            </div>
                                        </div>
                                        </a>
                                    </c:forEach>

                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    <script>
    
    function scrapOrCancle(event, postId, resumeId) {
         event.preventDefault();
  
    //console.log(postId);
                let scrapValue = $("#scrap-"+postId+"-"+resumeId).attr("value");
    console.log(scrapValue);
                let data = {
                    "resumeId": resumeId
                };

                if (scrapValue == 0) {
                    // insert
                    $.ajax({
                        type: "put",
                        url: "/company/scrap/" + resumeId,
                        data: JSON.stringify(data),
                        dateType: "JSON",
                        headers: {
                            "Content-Type": "application/json; charset=UTF-8"
                        }
                    }).done((res) => {
                        $("#scrap-"+postId+"-"+resumeId).attr("value", 1);
                        $("#scrap-"+postId+"-"+resumeId).addClass("fa-solid");
                        $("#scrap-"+postId+"-"+resumeId).removeClass("fa-regular");
                    }).fail((err) => {
                        alert(err.responseJSON.msg);
                    });
                } else {
                    $.ajax({
                        type: "delete",
                        url: "/company/scrap/" + resumeId
                       
                    }).done((res) => {
                        $("#scrap-"+postId+"-"+resumeId).attr("value", 0);
                        $("#scrap-"+postId+"-"+resumeId).addClass("fa-regular");
                        $("#scrap-"+postId+"-"+resumeId).removeClass("fa-solid");
                    }).fail((err) => {
                        alert(err.responseJSON.msg);
                    });
                }
            }

    
    </script>




        <%@ include file="../layout/footer.jsp" %>