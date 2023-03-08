<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex">
            <div class="list-group mx-2">
                <a href="/person/info" class="list-group-item shadow" style="width: 150px;">회원 정보</a>
                <a href="/person/resumes" class="list-group-item hs_list_effect shadow">이력서 관리</a>
                <a href="/person/scrap" class="list-group-item shadow">스크랩</a>
                <a href="/person/history" class="list-group-item shadow">지원 이력</a>
            </div>

            <div class=" mx-2 pb-4 w-100">
                <div class="border border-tertiary p-5 rounded shadow">
                    <h1>${resumeDetail.title}</h1>
                    <span>
                        공개 여부 설정 | &nbsp
                    </span>
                    <c:choose>
                        <c:when test="${resumeDetail.publish == true}">
                            <span class="badge rounded-pill text-bg-success" style="width:50px;">공개</span>

                        </c:when>
                        <c:otherwise>
                            <span class="badge rounded-pill text-bg-warning" style="width:50px;">비공개</span>
                        </c:otherwise>
                    </c:choose>
                    <hr>
                    <div class="d-flex justify-content-center">
                        <div style="width: 85%;">
                            <div class="jh_resume_flexbox mt-3">
                                <img src="${resumeDetail.profile == null ? '/images/profile1.jpg' : resumeDetail.profile}"
                                    style="width: 188px; height: 226px;">
                                <table class="jh_resume_table">
                                    <tr>
                                        <td>이름</td>
                                        <td>${personDetail.name}</td>
                                    </tr>
                                    <tr>
                                        <td>생년월일</td>
                                        <td>${birthday}</td>
                                    </tr>
                                    <tr>
                                        <td>연락처</td>
                                        <td>${personDetail.phone}</td>
                                    </tr>
                                    <tr>
                                        <td>주소</td>
                                        <td>${personDetail.address}</td>
                                    </tr>
                                    <tr>
                                        <td>이메일</td>
                                        <td>${principal.email}</td>
                                    </tr>
                                </table>
                            </div>
                            <div class="mt-5">
                                <h4>포트폴리오 주소</h4>
                                <input type="url" class="form-control mt-2" id="floatingInput"
                                    placeholder="git 또는 blog 주소를 입력해주세요" style="display: block;"
                                    value="${resumeDetail.portfolio}" readOnly>
                            </div>
                            <div class="mt-5 jh_resume_skill">
                                <h4>기술스택</h4>
                                <div>
                                    <ul>
                                        <c:forEach items="${skillDetail}" var="skill">
                                            <li>${skill}</li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>
                            <div class="mt-5 w-100">
                                <h4>자기소개</h4>

                                <textarea name="resume_content" class="w-100 opacity-50" rows="10" readOnly>${resumeDetail.selfIntro}
                               </textarea>
                            </div>

                            <div class="d-flex mt-4 justify-content-center">
                                <div class="px-2">
                                    <button type="button" class="btn btn-dark"
                                        onclick="updateResumeForm(${resumeDetail.id})">수정하기</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script>
            function updateResumeForm(id) {
                console.log(id);
                $.ajax({
                    type: "GET",
                    url: "/person/updateResume/" + id
                }).done((res) => {
                    location.href = "/person/updateResume/" + id;
                }).fail((err) => {

                });
            }
        </script>
        <%@ include file="../layout/footer.jsp" %>