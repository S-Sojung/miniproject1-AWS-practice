<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ include file="../layout/header.jsp" %>

        <div class="container d-flex mt-4">
            <div class="list-group ms-2 mt-4">
                <a href="/person/info" class="list-group-item  hs_list_effect" style="width: 150px;">회원 정보</a>
                <a href="/person/resumes" class="list-group-item ">이력서 관리</a>
                <a href="/person/scrap" class="list-group-item ">스크랩</a>
                <a href="/person/history" class="list-group-item ">지원 이력</a>
            </div>



            <div class="ms-2 p-4">
                <div class="border border-tertiary w-100 p-5 rounded ">
                    <div class="d-flex justify-content-between">
                        <h1 class="hs_line">개인회원 수정</h1>
                        <button type="button" class="btn btn-dark hs_update_button"
                            onclick="location.href=`/person/updateInfoForm`;">저장</button>
                    </div>
                    <hr />
                    <div>
                     <div class="container w-50 mt-5 text ">
                            <form action="/info" method="post">
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">이름</span>
                                    <input type="text" class="form-control" value="성소정" >
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">연락처</span>
                                    <input type="tel" class="form-control" value="0100000000" >
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">이메일</span>
                                    <input type="email" class="form-control" value="email@email.com" >
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">주소</span>
                                    <input type="text" class="form-control" value="부산광역시 진구 서면" >
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">현재 비밀번호</span>
                                    <input type="password" class="form-control">
                                </div>
                                    <%-- <span style="font-size:10px; color: grey;">비밀번호(영문자, 숫자, 특수문자 포함 8-20자 이내)</span> --%>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">비밀번호</span>
                                    <input type="password" class="form-control">
                                </div>
                                <div class="input-group mb-3">
                                    <span
                                        class="input-group-text bg-light justify-content-center hs_span_size">비밀번호 확인</span>
                                    <input type="password" class="form-control">
                                </div>
                            

                                <div class="my-5">
                                    <h4>기술스택</h4>
                                    <div class="row row-cols-auto gap-3 justify-content-center my-3 jh_form_group">
                                        <input type="checkbox" class="btn-check" id="btn1" value="java"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn1">java</label>
                                        <input type="checkbox" class="btn-check" id="btn2" value="spring"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn2">spring</label>
                                        <input type="checkbox" class="btn-check" id="btn3" value="html"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn3">html</label>
                                        <input type="checkbox" class="btn-check" id="btn4" value="javascript"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn4">javascript</label>
                                        <input type="checkbox" class="btn-check" id="btn5" value="sql"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn5">sql</label>
                                        <input type="checkbox" class="btn-check" id="btn6" value="android"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn6">android</label>
                                        <input type="checkbox" class="btn-check" id="btn7" value="React"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn7">React</label>
                                        <input type="checkbox" class="btn-check" id="btn8" value="Node.js"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn8">Node.js</label>
                                        <input type="checkbox" class="btn-check" id="btn9" value="Express"
                                            autocomplete="off">
                                        <label class="btn btn-outline-primary" for="btn9">Express</label>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@ include file="../layout/footer.jsp" %>