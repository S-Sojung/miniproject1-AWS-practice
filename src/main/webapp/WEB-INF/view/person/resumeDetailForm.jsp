<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container mt-4">
            <div class="ms-2 p-4">

                <div class="border border-tertiary w-100 p-5 rounded">
                    <h1 class="hs_line">${resumeDetail.title}</h1>
                    <hr>
                    <div class="d-flex justify-content-center">
                        <div style="width: 85%;">
                            <div class="jh_resume_flexbox mt-3">
                                <img src="${resumeDetail.profile == null ? '/images/profile1.jpg' : resumeDetail.profile}"
                                    style="width: 188px; height: 226px;">
                                <%-- <img src="/images/${resumeDetail.profile}" /> --%>
                                <table class="jh_resume_table">
                                    <tr>
                                        <td>이름</td>
                                        <td>${personDetail.name}</td>
                                    </tr>
                                    <tr>
                                        <td>생년월일</td>
                                        <td>${personDetail.birthday}</td>
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
                            <div class="mt-5">
                                <h4>자기소개</h4>
                                <textarea name="resume_content" class="w-100" rows="10" readOnly>${resumeDetail.selfIntro}
                               </textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <%@ include file="../layout/footer.jsp" %>