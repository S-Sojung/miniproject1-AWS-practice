<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

 <div class="p-5"></div>
    <div class="d-flex justify-content-center mb-4">
        <h2>개인 회원</h2>
    </div>
    <div class="d-flex justify-content-center">

        <div class="border rounded p-3 m-3 shadow-lg" style="width: 300px;">

            <form action="/personLogin" method="post">

                <div class="form-group form-floating m-3 mx-auto">
                    <input type="text" name ="email" class="form-control" id="floatingInput" placeholder="사용자 이메일" required>
                    <label for="floatingInput">사용자 이메일
                </div>

                <div class="form-group form-floating m-3 mx-auto">
                    <input type="password" name = "password" class="form-control" id="floatingInput" placeholder="name@example.com"
                        required>
                    <label for="floatingInput">password</label>
                </div>

                <div class="form-group form-floating m-3 mx-auto">
                    <button type="submit" class="btn btn-dark btn-block" style="width: 265px;">로그인</button>
                </div>
            </form>
            <div class="text-center sj_login_join">
                <p><a href="/personJoinForm1">아직 회원이 아니신가요?</a></p>
            </div>
        </div>
    </div>


    <%@ include file="../layout/footer.jsp" %>