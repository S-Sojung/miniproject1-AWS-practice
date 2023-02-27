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
            <div class="border border-tertiary w-100 p-5 rounded">
                <h1 class="hs_line">성실한 소정이의 이력서입니다.</h1>
                <hr>
                <div class="d-flex justify-content-center">
                    <div style="width: 85%;">
                        <div class="jh_resume_flexbox mt-3">
                            <img src="/images/profile.png" alt="이력서 사진" style="width: 188px; height: 226px;">
                            <table class="jh_resume_table">
                                <tr>
                                    <td>이름</td>
                                    <td>성소정</td>
                                </tr>
                                <tr>
                                    <td>생년월일</td>
                                    <td>99.00.00</td>
                                </tr>
                                <tr>
                                    <td>연락처</td>
                                    <td>000-0000-0000</td>
                                </tr>
                                <tr>
                                    <td>주소</td>
                                    <td>부산광역시 진구 서면1동</td>
                                </tr>
                                <tr>
                                    <td>이메일</td>
                                    <td>email@email.com</td>
                                </tr>
                            </table>
                        </div>
                        <div class="mt-5">
                            <h4>포트폴리오 주소</h4>
                            <input type="url" class="form-control mt-2" id="floatingInput"
                                placeholder="git 또는 blog 주소를 입력해주세요" required style="display: block;">
                        </div>
                        <div class="my-5">
                            <h4>기술스택 (최대 5개 선택)</h4>
                            <div class="row row-cols-auto gap-3 justify-content-center my-3">
                                <input type="checkbox" class="btn-check" id="btn1" value="java" autocomplete="off">
                                <label class="btn btn-outline-primary" for="btn1">java</label>
                                <input type="checkbox" class="btn-check" id="btn2" value="spring" autocomplete="off">
                                <label class="btn btn-outline-primary" for="btn2">spring</label>
                                <input type="checkbox" class="btn-check" id="btn3" value="html" autocomplete="off">
                                <label class="btn btn-outline-primary" for="btn3">html</label>
                                <input type="checkbox" class="btn-check" id="btn4" value="javascript"
                                    autocomplete="off">
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
                        <div class="">
                            <h4>자기소개</h4>
                            <textarea name="resume_content" cols="109" rows="10">
                                안녕하십니까 저는 chatGPT를 뛰어넘는 인공지능 성소정이라고 합니다.</textarea>
                        </div>
						    <div class="d-flex mt-4 justify-content-center">
                            <div class="px-2">
                                <button type="button" class="btn btn-light">저장</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
            <%@ include file="../layout/footer.jsp" %>