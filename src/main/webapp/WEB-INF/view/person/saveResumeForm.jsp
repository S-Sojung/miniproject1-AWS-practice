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
                    <h1 class="hs_line"><input type="text" placeholder="이력서 제목을 입력하세요" style="width: 700px"></h1>
                    <hr>
                    <div class="d-flex justify-content-center">
                        <div style="width: 85%;">
                            <div class="jh_resume_flexbox mt-3">
                                <img src="/images/logo.png" border="1" style="width: 188px; height: 226px;">
                                <div class="jh_resume_personal_info">
                                    <input type="text" class="form-control" placeholder="이름">
                                    <input type="date" class="form-control" placeholder="생년월일">
                                    <input type="tel" class="form-control" placeholder="연락처">
                                    <input type="text" class="form-control" placeholder="주소">
                                    <input type="email" class="form-control" placeholder="이메일">
                                </div>
                            </div>
                            <input type="file" class="form-control" name="resume_profile"
                                onchange="chooseImage(this)" />
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
                                    <input type="checkbox" class="btn-check" id="btn2" value="spring"
                                        autocomplete="off">
                                    <label class="btn btn-outline-primary" for="btn2">spring</label>
                                    <input type="checkbox" class="btn-check" id="btn3" value="html" autocomplete="off">
                                    <label class="btn btn-outline-primary" for="btn3">html</label>
                                    <input type="checkbox" class="btn-check" id="btn4" value="javascript"
                                        autocomplete="off">
                                    <label class="btn btn-outline-primary" for="btn4">javascript</label>
                                    <input type="checkbox" class="btn-check" id="btn5" value="sql" autocomplete="off">
                                    <label class="btn btn-outline-primary" for="btn5">sql</label>
                                    <input type="checkbox" class="btn-check" id="btn6" value="android"
                                        autocomplete="off">
                                    <label class="btn btn-outline-primary" for="btn6">android</label>
                                    <input type="checkbox" class="btn-check" id="btn7" value="React" autocomplete="off">
                                    <label class="btn btn-outline-primary" for="btn7">React</label>
                                    <input type="checkbox" class="btn-check" id="btn8" value="Node.js"
                                        autocomplete="off">
                                    <label class="btn btn-outline-primary" for="btn8">Node.js</label>
                                    <input type="checkbox" class="btn-check" id="btn9" value="Express"
                                        autocomplete="off">
                                    <label class="btn btn-outline-primary" for="btn9">Express</label>
                                </div>
                            </div>
                            <div class="">
                                <h4>자기소개</h4>
                                <textarea name="resume_content" cols="109" rows="10" placeholder="내용을 입력하세요"></textarea>
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
        <script>
            function chooseImage(obj) {
                console.log(obj);
                console.log(obj.files);
            }
        </script>
        <%@ include file="../layout/footer.jsp" %>