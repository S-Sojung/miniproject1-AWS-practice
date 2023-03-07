<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex mt-4">
            <div class="list-group ms-2 mt-4">
                <a href="/person/info" class="list-group-item" style="width: 150px;">회원 정보</a>
                <a href="/person/resumes" class="list-group-item hs_list_effect">이력서 관리</a>
                <a href="/person/scrap" class="list-group-item">스크랩</a>
                <a href="/person/history" class="list-group-item ">지원 이력</a>
            </div>

            <div class="ms-2 p-4">
                     <div class="border border-tertiary w-100 p-5 rounded ">
                <div class="d-flex justify-content-between">
                    <h1 class="hs_line">이력서 관리</h1>
                </div>
                <hr>
                    <div class="jh_resume mt-5">
                        <button class="jh_resume_button mb-5 rounded bg-light"
                            onclick="location.href=`/person/saveResumeForm`">➕ 새 이력서 등록</button>
                        <c:forEach items="${resumes}" var="resume">

                        <div id="resume_del" class="jh_resume_content mt-5 mb-3" style="display: flex; justify-content: space-between" >
                           <a href="/person/resumeDetail/${resume.id}"> ${resume.title} </a>
                           <div>
                             <c:choose>
                           <c:when test="${resume.publish == true}">
                        <button type="button"  class="btn btn-success">공개중</button>
                           </c:when>
                           <c:otherwise>
                             <button type="button"  class="btn btn-warning">비공개중</button>
                           </c:otherwise>
                        </c:choose>

                            <button type="button"  class="btn btn-secondary" onclick="deleteResume(${resume.id})">삭제</button>
                            </div>
                        </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>

        <script>

        function deleteResume(id) {
                 
            $.ajax({
              type: "DELETE",
              url : "/person/resumes/"+id,      
            }).done((res) => {
                $("#resume_del").remove();
                alert("이력서 삭제에 성공하였습니다");
                location.href="/person/resumes";
            }).fail((err)=>{
                alert("이력서 삭제에 실패하였습니다");
            });
        }
        </script>
        <%@ include file="../layout/footer.jsp" %>