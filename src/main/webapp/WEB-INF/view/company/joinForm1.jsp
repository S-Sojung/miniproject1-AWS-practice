<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>


        <div class="p-5"></div>
        <div class="d-flex justify-content-center">
            <h2>기업 회원</h2>
        </div>
        <div class="d-flex justify-content-center">

            <div class="border rounded p-3 m-3" style="width: 300px;">
            
                <!-- companyJoin 실행 후 이동해도 됨! -->
            <form action="/companyJoin1" method="post"> 
                    <div class="form-group form-floating m-3 mx-auto">
                        <input type="text" name ="name" class="form-control" id="floatingInput" placeholder="사용자 이름" required>
                        <label for="floatingInput">회사명
                    </div>

                    <div class="form-group form-floating m-3 mx-auto">
                        <input type="text" name = "address" class="form-control" id="floatingInput" placeholder="name@example.com"
                            required>
                        <label for="floatingInput">회사 주소</label>
                    </div>
                    <div class="form-group form-floating m-3 mx-auto">
                        <input type="password" name = "number" class="form-control" id="floatingInput" placeholder="name@example.com"
                            required>
                        <label for="floatingInput">사업자 번호</label>
                    </div>

                    <div class="form-group form-floating m-3 mx-auto">
                        <button type="submit" class="btn btn-dark btn-block" style="width: 265px;">다음</button>
                    </div>
                </form>
                <div class="text-center sj_login_join">
                    <p><a href="/companyLoginForm">이미 회원이신가요?</a></p>
                </div>
            </div>
        </div>

        <%@ include file="../layout/footer.jsp" %>