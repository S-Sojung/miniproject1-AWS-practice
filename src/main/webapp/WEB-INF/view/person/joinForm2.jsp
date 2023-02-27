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

            <form action="/person/join" method="post">
                <div class="text-center align-self-center">
                    <div class="row row-cols-auto gap-3 justify-content-center my-4">
                        <input type="checkbox" class="btn-check" id="btn1" value="java" autocomplete="off">
                        <label class="btn btn-outline-primary" for="btn1">java</label>
                        <input type="checkbox" class="btn-check" id="btn2" value="spring" autocomplete="off">
                        <label class="btn btn-outline-primary" for="btn2">spring</label>
                        <input type="checkbox" class="btn-check" id="btn3" value="html" autocomplete="off">
                        <label class="btn btn-outline-primary" for="btn3">html</label>
                        <input type="checkbox" class="btn-check" id="btn4" value="javascript" autocomplete="off">
                        <label class="btn btn-outline-primary" for="btn4">javascript</label>
                        <input type="checkbox" class="btn-check" id="btn5" value="sql" autocomplete="off">
                        <label class="btn btn-outline-primary" for="btn5">sql</label>
                        <input type="checkbox" class="btn-check" id="btn6" value="android" autocomplete="off">
                        <label class="btn btn-outline-primary" for="btn6">android</label>
                        <input type="checkbox" class="btn-check" id="btn7" value="React" autocomplete="off">
                        <label class="btn btn-outline-primary" for="btn7">React</label>
                        <input type="checkbox" class="btn-check" id="btn8" value="Node.js" autocomplete="off">
                        <label class="btn btn-outline-primary" for="btn8">Node.js</label>
                        <input type="checkbox" class="btn-check" id="btn9" value="Express" autocomplete="off">
                        <label class="btn btn-outline-primary" for="btn9">Express</label>
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