<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

 <div class="p-5"></div>
    <div class="d-flex justify-content-center">
        <h2>개인 회원</h2>
    </div>
    <div class="d-flex justify-content-center">

        <div class="border rounded p-3 m-3" style="width: 300px;">
            <h5 class="text-center">기술 분야</h5>
            <hr class="w-100 color-dark">
            <form action="/personJoin2" method="post">
                <input type="hidden" value="${pInfoId}" name="pInfoId"/>
                <div class="text-center align-self-center">
                    <div class="row row-cols-auto gap-3 justify-content-center my-3">
                        <c:forEach items="${skills}" var="skill">
                            <input type="checkbox" name="skills" class="btn-check" id="${skill}"
                                value="${skill}" autocomplete="off">
                            <label class="btn btn-outline-primary"
                                for="${skill}">${skill}</label>
                        </c:forEach>
                    </div>
                </div>

                <div class="form-group form-floating m-3 mx-auto">
                    <button type="submit" class="btn btn-dark btn-block" style="width: 265px;">회원가입</button>
                </div>
            </form>
            <div class="text-center sj_login_join">
                <p><a href="/personLoginForm">이미 회원이신가요?</a></p>
            </div>
        </div>
    </div>


    <%@ include file="../layout/footer.jsp" %>